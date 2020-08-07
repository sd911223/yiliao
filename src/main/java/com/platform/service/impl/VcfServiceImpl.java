package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.config.PDFExportConfig;
import com.platform.dao.DiseaseOmimMapper;
import com.platform.dao.PatientInfoMapper;
import com.platform.dao.VcfFileMapper;
import com.platform.entity.resp.VcfCountResp;
import com.platform.exception.BusinessException;
import com.platform.model.*;
import com.platform.service.DiseaseService;
import com.platform.service.VcfService;
import com.platform.util.PDFUtil;
import com.platform.util.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

/**
 * VCF管理
 */
@Service
@Slf4j
public class VcfServiceImpl implements VcfService {
    @Autowired
    PDFExportConfig pdfExportConfig;
    @Autowired
    PatientInfoMapper patientInfoMapper;
    @Autowired
    VcfFileMapper vcfFileMapper;
    @Autowired
    ShellUtil shellUtil;
    @Value("${vcf.file.path}")
    private String path;
    @Autowired
    DiseaseOmimMapper diseaseOmimMapper;
    @Autowired
    DiseaseService diseaseService;

    /**
     * VCF统计
     *
     * @param userInfo
     * @return
     */
    @Override
    public RestResponse<VcfCountResp> handleStatus(UserInfo userInfo) {
        //总条数
        PatientInfoExample patientInfoExample = new PatientInfoExample();
        patientInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long total = patientInfoMapper.countByExample(patientInfoExample);
        //已完成
        PatientInfoExample completeInfoExample = new PatientInfoExample();
        completeInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andIsResolveEqualTo(1)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long complete = patientInfoMapper.countByExample(patientInfoExample);
        //未完成
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(null);
        PatientInfoExample noInfoExample = new PatientInfoExample();
        noInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andIsResolveIn(integers)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long not = patientInfoMapper.countByExample(patientInfoExample);
        VcfCountResp vcfCountResp = VcfCountResp.builder().totalTask(total).completeTask(complete).NotTask(not).build();
        return ResultUtil.success(vcfCountResp);
    }

    @Override
    @Transactional
    public VcfFile addVcf(MultipartFile vcfFile, String jobName, String geneType, String omimId, String patientId, String symptomType, String symptom) {
        if (vcfFile == null) {
            log.error("上传的VCF为空!");
            throw new BusinessException(ResultEnum.VCF_IS_EXISTS.getStatus(), ResultEnum.VCF_IS_EXISTS.getMsg());
        }
        //文件名
        String fileName = vcfFile.getOriginalFilename();
        //文件类型判断
        if (!fileName.toLowerCase().endsWith(".vcf")) {
            log.error("VCF格式错误!");
            throw new BusinessException(ResultEnum.VCF_FORMAT_ERROR.getStatus(), ResultEnum.VCF_FORMAT_ERROR.getMsg());
        }
        //文件类型转换
        Blob blob;
        try {
            blob = new SerialBlob(vcfFile.getBytes());
        } catch (SQLException | IOException e) {
            log.error("VCF格式错误!");
            throw new BusinessException(ResultEnum.FILE_TYPE_CHANGE_ERROR.getStatus(), ResultEnum.FILE_TYPE_CHANGE_ERROR.getMsg());
        }
        //生成参数对象
        VcfFile vf = new VcfFile();
        //创建日期
        vf.setCreateTime(new Date());
        //文件名
        vf.setFileName(fileName);
        //jobName
        vf.setJobName(jobName);
        //基因版组
        vf.setGeneType(geneType);
        //关注疾病/OMIM ID
        vf.setAttentionDisease(omimId.toString());
        //症状类型
        vf.setSymptomType(symptomType);
        //症状
        vf.setSymptom(symptom);
        //有效
        vf.setIsEffective(1);
        //解析之前，存入原始文件
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
        if (null == patientInfo) {
            log.error("患者ID不存在!");
            throw new BusinessException(ResultEnum.ID_NOT_EXISTS.getStatus(), ResultEnum.ID_NOT_EXISTS.getMsg());
        }
        vcfFileMapper.insertSelective(vf);
        //添加VCF id
        patientInfo.setJobId(vf.getId());

        patientInfoMapper.updateByPrimaryKey(patientInfo);

        //把vcf存入文件夹
        File file = new File(path + vf.getId() + "/" + fileName);
        //获取父目录
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        //储存文件
        try {
            file.createNewFile();
            log.info("文件是否存在：" + file.exists());
            FileUtils.copyInputStreamToFile(vcfFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vf;
    }

    /**
     * VCF解析
     *
     * @param vcfFile
     * @param geneType
     * @param omimId
     * @param patientId
     * @param vcf
     */
    @Override
    @Async("taskExecutor")
    public void vcfDecode(MultipartFile vcfFile, String geneType, String omimId, String patientId, VcfFile vcf) {
        if (StringUtils.isNotBlank(omimId)) {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            sb.append(omimId);
            sb.append("]");
            omimId = sb.toString();
        }
        //文件名
        String fileName = vcfFile.getOriginalFilename();
        //开始解析
        log.info("=================  开始解析VCF star ====================");
        StringBuffer sb = new StringBuffer();
        try {
            int msg = shellUtil.analysisVcf(path + vcf.getId() + "/" + fileName, geneType,
                    path + vcf.getId() + "/operate", omimId);
            log.info("解析结果{}", msg);
            //成功执行shell脚本
            if (msg == 0) {
                log.info("============== 进入读取结果  =====================");
                //读取解析之后的结果json文件
                //新分析结果
                File jsonFile = new File(path + vcf.getId() + "/operate" + "/tables.json");
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(jsonFile));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        vcf.setJsonResult(sb.toString());
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
        patientInfo.setIsResolve(1);
        patientInfo.setResolveTime(new Date());
        patientInfoMapper.updateByPrimaryKey(patientInfo);
        vcfFileMapper.updateByPrimaryKey(vcf);
        log.info("vfc解析并保存完成");
    }

    /**
     * 查询vcf
     *
     * @param vcfId
     * @return
     */
    @Override
    public RestResponse vcfDetail(Integer vcfId) {
        VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(vcfId);
        return ResultUtil.success(vcfFile.getJsonResult());
    }

    /**
     * 删除vcf
     *
     * @param vcfId
     * @param patientId
     * @return
     */
    @Override
    public RestResponse vcfDelete(Integer vcfId, String patientId) {
        VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(vcfId);
        vcfFile.setIsEffective(2);
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
        patientInfo.setJobId(null);
        vcfFileMapper.updateByPrimaryKey(vcfFile);
        patientInfoMapper.updateByPrimaryKey(patientInfo);
        return ResultUtil.success("删除VCF成功!");
    }

    @Override
    public ResponseEntity<?> exportPdf(String patientId) {
        HttpHeaders headers = new HttpHeaders();
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
        VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(patientInfo.getJobId());
        JSONObject json = JSONObject.parseObject(vcfFile.getJsonResult());
        HashMap<String, String> map = new HashMap<>();
        StringBuffer literature = new StringBuffer();
        //计算有几个高度关注
        HashMap heighList = new HashMap<String, String>();
        String heighResult = "";
        String heighDisease = "";
        if (null != json.get("高度关注") && StringUtils.isNotBlank(json.get("高度关注").toString())) {
            heighList = JSON.parseObject(json.get("高度关注").toString(), HashMap.class);
            heighResult = getMutation(json, "高度关注");
            HashMap<String, Object> hashMap = getDiseaseName(json, "高度关注");
            heighDisease = hashMap.get("disease").toString();
            List<HashMap> hashMaps = (List<HashMap>) hashMap.get("emphasis");
            map = getHashMap(hashMaps);

        }
        //计算有几个中度关注
        HashMap moderateList = new HashMap<String, String>();
        String moderateResult = "";
        String moderateDisease = "";
        if (null != json.get("中度关注") && StringUtils.isNotBlank(json.get("中度关注").toString())) {
            moderateList = JSON.parseObject(json.get("中度关注").toString(), HashMap.class);
            moderateResult = getMutation(json, "中度关注");
            HashMap<String, Object> hashMap = getDiseaseName(json, "中度关注");
            moderateDisease = hashMap.get("disease").toString();
        }
        HashMap lowList = new HashMap<String, String>();
        String lowResult = "";
        String lowDisease = "";
        if (null != json.get("低度关注") && StringUtils.isNotBlank(json.get("低度关注").toString())) {
            lowList = JSON.parseObject(json.get("低度关注").toString(), HashMap.class);
            lowResult = getMutation(json, "低度关注");
            HashMap<String, Object> hashMap = getDiseaseName(json, "低度关注");
            lowDisease = hashMap.get("disease").toString();
        }
        /**
         * 数据导出(PDF 格式)
         */
        Map<String, Object> dataMap = new HashMap<>(16);
        dataMap.put("statisticalTime", new Date().toString());
        dataMap.put("doctor", "userInfo.getUserName()");
        dataMap.put("patientName", patientInfo.getPatientName());
        dataMap.put("sex", patientInfo.getSex() == 1 ? "男" : "女");
        dataMap.put("age", patientInfo.getAge());
        dataMap.put("symptom", patientInfo.getSymptom());
        dataMap.put("homeDisease", patientInfo.getSymptom());
        dataMap.put("heighList", heighList.size());
        dataMap.put("moderateList", moderateList.size());
        dataMap.put("lowList", lowList.size());
        dataMap.put("heighResult", heighResult);
        dataMap.put("moderateResult", moderateResult);
        dataMap.put("lowResult", lowResult);
        dataMap.put("heighDisease", heighDisease);
        dataMap.put("moderateDisease", moderateDisease);
        dataMap.put("lowDisease", lowDisease);
        dataMap.put("maps", map);
        dataMap.put("heighData", heighList);
        dataMap.put("moderateData", moderateList);
        dataMap.put("lowData", lowList);

        String htmlStr = PDFUtil.freemarkerRender(dataMap, pdfExportConfig.getEmployeeKpiFtl());
        byte[] pdfBytes = PDFUtil.createPDF(htmlStr, pdfExportConfig.getFontSimsun());
        if (pdfBytes != null && pdfBytes.length > 0) {
            String fileName = System.currentTimeMillis() + (int) (Math.random() * 90000 + 10000) + ".pdf";
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
        }

        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>("{ \"code\" : \"404\", \"message\" : \"not found\" }",
                headers, HttpStatus.NOT_FOUND);
    }

    /**
     * 通过OMID 查询疾病名称
     *
     * @return
     */
    private HashMap<String, Object> getDiseaseName(JSONObject json, String attention) {
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject o = JSONObject.parseObject(json.get(attention).toString());
        JSONObject jsonObject = JSONObject.parseObject(o.get("0").toString());
        String disease = jsonObject.get("相关疾病").toString();
        String[] split = disease.substring(1, disease.length() - 1).split(",");
        StringBuffer sb = new StringBuffer();
        List<String> list = Arrays.asList(split);
        List<Map> mapArrayList = new ArrayList<>();
        for (String e : list) {
            log.info("疾病ID{}", e);
            String substring = e.substring(1, e.length() - 1);
            if ("高度关注".equals(attention)) {
                Map<String, Object> disease1 = diseaseService.disease(substring, "1");
                mapArrayList.add(disease1);
            }
            DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
            diseaseOmimExample.createCriteria().andOmimIdEqualTo(Integer.valueOf(substring));
            List<DiseaseOmim> diseaseOmims = diseaseOmimMapper.selectByExample(diseaseOmimExample);
            if (!diseaseOmims.isEmpty()){
                sb.append(diseaseOmims.get(0).getDiseaseName());
                if (list.size() > 1) {
                    sb.append(",");
                }
            }
        }
        hashMap.put("disease", sb.toString());
        hashMap.put("emphasis", mapArrayList);
        return hashMap;
    }

    /**
     * 拼装突变
     *
     * @param json
     * @param disease
     * @return
     */
    private String getMutation(JSONObject json, String disease) {
        StringBuffer sb = new StringBuffer();
        JSONObject o = JSONObject.parseObject(json.get(disease).toString());
        JSONObject jsonObject = JSONObject.parseObject(o.get("0").toString());
        sb.append(jsonObject.get("染色体位置"));
        sb.append(jsonObject.get("Ref"));
        sb.append(jsonObject.get(">"));
        sb.append(jsonObject.get("Alt"));
        return sb.toString();
    }

    /**
     * 重度疾病
     *
     * @param list
     * @return
     */
    private HashMap<String, String> getHashMap(List<HashMap> list) {
        HashMap<String, String> hashMap = new HashMap<>();
        StringBuffer disease_name = new StringBuffer();
        StringBuffer age_of_onset_orp = new StringBuffer();
        StringBuffer inheritance_orp = new StringBuffer();
        StringBuffer prevalence_orp = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = list.get(i);
            disease_name.append(map.get("disease_name"));
            age_of_onset_orp.append(map.get("Age_of_onset_orp"));
            inheritance_orp.append(map.get("Inheritance_orp"));
            prevalence_orp.append(map.get("Prevalence_orp"));
            if (list.size() > 1 && i < list.size()) {
                disease_name.append(",");
                age_of_onset_orp.append(",");
                inheritance_orp.append(",");
                prevalence_orp.append(",");
            }
        }
        hashMap.put("disease_name", disease_name.toString());
        hashMap.put("age_of_onset_orp", age_of_onset_orp.toString());
        hashMap.put("inheritance_orp", inheritance_orp.toString());
        hashMap.put("prevalence_orp", prevalence_orp.toString());
        return hashMap;
    }

    /**
     * 获取文献
     *
     * @param hashMap
     * @return
     */
    private String getLiterature(HashMap<String, String> hashMap) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        if (hashMap.size() > 0) {
            for (int i = 0; i < hashMap.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(hashMap.get(String.valueOf(count)));
                String literature = jsonObject.get("文献").toString();
                sb.append(literature);

                count++;
            }

        }
        return sb.toString();
    }
}
