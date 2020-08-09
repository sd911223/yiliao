package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.DiseaseOmimMapper;
import com.platform.dao.PatientInfoMapper;
import com.platform.dao.VcfFileMapper;
import com.platform.entity.resp.HeightAttentionResp;
import com.platform.entity.resp.VcfCountResp;
import com.platform.exception.BusinessException;
import com.platform.model.*;
import com.platform.service.DiseaseService;
import com.platform.service.VcfService;
import com.platform.util.PdfUtilTest;
import com.platform.util.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * VCF管理
 */
@Service
@Slf4j
public class VcfServiceImpl implements VcfService {

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

    @Value("${pdfExport.employeeKpiFtl}")
    private String employeeKpiFtl;
    @Value("${pdfExport.fontSimsun}")
    private String fontSimsun;

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
        log.info("統計vcf解讀=====總條數{}", total);
        //已完成
        PatientInfoExample completeInfoExample = new PatientInfoExample();
        completeInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andIsResolveEqualTo(1)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long complete = patientInfoMapper.countByExample(completeInfoExample);
        log.info("統計vcf解讀=====已經完成{}", complete);
        //未完成
        PatientInfoExample noInfoExample = new PatientInfoExample();
        noInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andIsResolveEqualTo(2)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long not = patientInfoMapper.countByExample(noInfoExample);
        log.info("統計vcf解讀=====未完成{}", not);
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
    public void download(String patientId, HttpServletResponse response,UserInfo userInfo) throws Exception {
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
            VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(patientInfo.getJobId());
            if (vcfFile==null||vcfFile.getJsonResult()==null){
                log.error("导出PDF没有VCF结果,患者ID{}", patientId);
                throw new BusinessException(ResultEnum.VCF_IS_EXIST.getStatus(), ResultEnum.VCF_IS_EXIST.getMsg());
            }
            JSONObject json = JSONObject.parseObject(vcfFile.getJsonResult());
            HashMap<String, String> map = new HashMap<>();
            //文献
            StringBuffer literature = new StringBuffer();
            //高度关注列表
            List<HeightAttentionResp> ListHeightData = new ArrayList<HeightAttentionResp>();
            //高度关注列表
            List<HeightAttentionResp> ListModerateData = new ArrayList<HeightAttentionResp>();
            //高度关注列表
            List<HeightAttentionResp> ListLowData = new ArrayList<HeightAttentionResp>();
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
                ListHeightData = getListMap(heighList);
                String wenxian = getLiterature(heighList);
                literature.append(wenxian);
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
                ListModerateData = getListMap(moderateList);
                String wenxian = getLiterature(moderateList);
                literature.append(wenxian);
            }
            //计算其他个数
            HashMap lowList = new HashMap<String, String>();
            String lowResult = "";
            String lowDisease = "";
            if (null != json.get("其他") && StringUtils.isNotBlank(json.get("其他").toString())) {
                lowList = JSON.parseObject(json.get("其他").toString(), HashMap.class);
                lowResult = getMutation(json, "其他");
                HashMap<String, Object> hashMap = getDiseaseName(json, "其他");
                if (hashMap.size() > 0) {
                    lowDisease = hashMap.get("disease").toString();
                }
                ListLowData = getListMap(lowList);
            }
            // 模板中的数据，实际运用从数据库中查询
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("statisticalTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dataMap.put("doctor", userInfo.getUserName());
            dataMap.put("patientName", patientInfo.getPatientName());
            dataMap.put("sex", patientInfo.getSex() == 1 ? "男" : "女");
            dataMap.put("age", patientInfo.getAge());
            dataMap.put("symptom", patientInfo.getSymptom());
            dataMap.put("homeDisease", patientInfo.getFamilyMedicalHistory());
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
            dataMap.put("heighData", ListHeightData);
            dataMap.put("moderateData", ListModerateData);
            dataMap.put("lowData", ListLowData);
            dataMap.put("literature", literature);
            baos = PdfUtilTest.createPDF(dataMap, "pdfPage.ftl");
            ;
            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
            response.setContentType("application/x-msdownload");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
            String fileName = URLEncoder.encode(patientInfo.getPatientName() + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            baos.writeTo(out);
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出失败：" + e.getMessage());
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 关注列表
     *
     * @param map
     * @return
     */
    private List<HeightAttentionResp> getListMap(HashMap<String, Object> map) {
        List<HeightAttentionResp> list = new ArrayList<>();
        int cont = 0;
        if (map.size() > 0) {
            for (int i = 0; i < map.size(); i++) {
                Object o = map.get(String.valueOf(cont));
                HeightAttentionResp heightAttentionResp = new HeightAttentionResp();
                JSONObject parse = JSONObject.parseObject(o.toString());
                heightAttentionResp.setVariation(parse.get("变异").toString());
                heightAttentionResp.setChromosomePosition(parse.get("染色体位置").toString());
                heightAttentionResp.setRef(parse.get("Ref").toString());
                heightAttentionResp.setAlt(parse.get("Alt").toString());
                heightAttentionResp.setGeneShape(parse.get("基因型").toString());
                heightAttentionResp.setMaf(parse.get("MAF(亚洲)").toString());
                heightAttentionResp.setGene(parse.get("基因").toString());
                heightAttentionResp.setPathogenicPoints(parse.get("致病分值").toString());
                heightAttentionResp.setMutationType(parse.get("突变类型").toString());
                heightAttentionResp.setProteinChange(parse.get("蛋白变化").toString());
                if (null != parse.get("相关疾病")) {
                    heightAttentionResp.setRelatedDisease(parse.get("相关疾病").toString());
                }
                if (null != parse.get("来源")) {
                    heightAttentionResp.setSource(parse.get("来源").toString());
                }
                if (null != parse.get("文献")) {
                    heightAttentionResp.setLiterature(parse.get("文献").toString());
                }
                list.add(heightAttentionResp);
                cont++;
            }

        }

        return list;
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
        if (null != jsonObject.get("相关疾病")) {
            String disease = jsonObject.get("相关疾病").toString();
            String[] split = null;
            if (disease.contains("[") && disease.contains(",") && disease.contains("]")) {
                split = disease.substring(1, disease.length() - 1).split(",");
            } else if (disease.contains(",") && !disease.contains("[") && !disease.contains("]")) {
                split = disease.split(",");
            }
            StringBuffer sb = new StringBuffer();

            List<Map> mapArrayList = new ArrayList<>();
            if (split == null) {
                log.info("疾病ID{}", disease);
                String substring = "";
                if (disease.contains("[]")) {
                    substring = disease.substring(1, disease.length() - 1);
                } else {
                    substring = disease;
                }
                if ("高度关注".equals(attention)) {
                    Map<String, Object> disease1 = diseaseService.disease(substring, "1");
                    mapArrayList.add(disease1);
                }
                DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
                diseaseOmimExample.createCriteria().andOmimIdEqualTo(Integer.valueOf(substring));
                List<DiseaseOmim> diseaseOmims = diseaseOmimMapper.selectByExample(diseaseOmimExample);
                sb.append(diseaseOmims.get(0).getDiseaseName());
            } else {
                List<String> list = Arrays.asList(split);
                for (String e : list) {
                    if (StringUtils.isBlank(e)) {
                        continue;
                    }
                    log.info("疾病ID{}", e);
                    String substring = "";
                    if (e.contains("[]")) {
                        substring = e.substring(1, e.length() - 1);
                    } else {
                        substring = e.replaceAll("\"", "");
                    }
                    if ("高度关注".equals(attention)) {
                        Map<String, Object> disease1 = diseaseService.disease(substring, "1");
                        mapArrayList.add(disease1);
                    }
                    DiseaseOmimExample diseaseOmimExample = new DiseaseOmimExample();
                    diseaseOmimExample.createCriteria().andOmimIdEqualTo(Integer.valueOf(substring));
                    List<DiseaseOmim> diseaseOmims = diseaseOmimMapper.selectByExample(diseaseOmimExample);
                    if (!diseaseOmims.isEmpty()) {
                        int cont = 0;
                        sb.append(diseaseOmims.get(0).getDiseaseName());
                        if (list.size() > 1 && cont < list.size()) {
                            sb.append(",");
                            cont++;
                        }
                    }
                }
            }

            hashMap.put("disease", sb.toString());
            hashMap.put("emphasis", mapArrayList);
        }

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
     * @param map
     * @return
     */
    private String getLiterature(HashMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        if (map.size() > 0) {
            for (int i = 0; i < map.size(); i++) {
                Object o = map.get(String.valueOf(count));
                JSONObject jsonObject = JSONObject.parseObject(o.toString());
                String literature = jsonObject.get("文献").toString();
                sb.append(literature);
                sb.append(",");
                count++;
            }

        }
        return sb.toString();
    }
}
