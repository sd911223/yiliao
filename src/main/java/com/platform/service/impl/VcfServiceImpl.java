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
import com.platform.service.LiteratureService;
import com.platform.service.VcfService;
import com.platform.util.PdfUtilTest;
import com.platform.util.RedisUtil;
import com.platform.util.ShellUtil;
import com.platform.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    LiteratureService literatureService;
    @Autowired
    RedisUtil redisUtil;

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
                .andIsResolveEqualTo(3)
                .andDoctorIdEqualTo(userInfo.getUserId());
        long not = patientInfoMapper.countByExample(noInfoExample);
        log.info("統計vcf解讀=====未完成{}", not);
        //正在处理
        PatientInfoExample processExample = new PatientInfoExample();
        processExample.createCriteria().andIsEffectiveEqualTo(1).andIsResolveEqualTo(2);
        long patientTotal = patientInfoMapper.countByExample(processExample);
        log.info("統計vcf解讀=====正在处理{}", patientTotal);
        VcfCountResp vcfCountResp = VcfCountResp.builder().totalTask(total).completeTask(complete).NotTask(not).processTask(patientTotal).build();
        return ResultUtil.success(vcfCountResp);
    }

    @Override
    @Transactional
    public VcfFile addVcf(MultipartFile vcfFile, String jobName, String geneType, String omimId, String patientId, String symptomType, String symptom) {
        log.info("===================上传VCF=========================");
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
        patientInfo.setIsResolve(3);
        patientInfoMapper.updateByPrimaryKey(patientInfo);

        //把vcf存入文件夹
        File file = new File(path + vf.getId() + "/" + fileName);
        //获取父目录
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        log.info("===================上传VCF   END=========================");
        //储存文件
        try {
            file.createNewFile();
            log.info("文件是否存在：" + file.exists());
            FileUtils.copyInputStreamToFile(vcfFile.getInputStream(), file);
            patientInfo.setIsResolve(2);
            patientInfoMapper.updateByPrimaryKey(patientInfo);
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
        log.info("=================  开始解析VCF star ====================");
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
        long time = new Date().getTime();
        if (redisUtil.get("vcfDetail:" + vcfId) == null) {
            VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(vcfId);
            String result = vcfFile.getJsonResult();
            JSONObject jsonResult = new JSONObject();
            int count = 1;
            if (result.contains("高度关注")) {
                Object gaoDuGuanZhu = new cn.hutool.json.JSONObject(result).get("高度关注");
                if (gaoDuGuanZhu != null) {
                    Map gaoDMap = JSON.parseObject(gaoDuGuanZhu.toString(), Map.class);
                    HashMap<String, Object> gaoduMap = new HashMap<>();
                    if (gaoDMap.size() > 0) {
                        for (int i = 0; i < gaoDMap.size(); i++) {
                            Map map = JSON.parseObject(new cn.hutool.json.JSONObject(gaoDMap.get(String.valueOf(i))).toString(), Map.class);
                            if (count <= 10) {
                                String[] stingArray = new StringUtil().getStingArray(map.get("文献").toString());
                                if (stingArray != null) {
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    List<String> list = Arrays.asList(stingArray);
                                    for (int j = 0; j < list.size(); j++) {
                                        hashMap.put(String.valueOf(count), list.get(j));

                                        count++;
                                    }
                                    map.put("wenXian", hashMap);
                                }
                            }
                            gaoduMap.put(String.valueOf(i), map);
                        }
                    }
                    jsonResult.put("高度关注", gaoduMap);
                }
            }
            if (result.contains("中度关注")) {
                Object zhongDuGuanZhu = new cn.hutool.json.JSONObject(result).get("中度关注");
                if (zhongDuGuanZhu != null) {
                    Map zDMap = JSON.parseObject(zhongDuGuanZhu.toString(), Map.class);
                    HashMap<String, Object> zduMap = new HashMap<>();
                    if (zDMap.size() > 0) {
                        for (int i = 0; i < zDMap.size(); i++) {
                            Map map = JSON.parseObject(new cn.hutool.json.JSONObject(zDMap.get(String.valueOf(i))).toString(), Map.class);
                            if (count <= 10) {
                                String[] stingArray = new StringUtil().getStingArray(map.get("文献").toString());
                                if (stingArray != null) {
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    List<String> list = Arrays.asList(stingArray);
                                    for (int j = 0; j < list.size(); j++) {
                                        hashMap.put(String.valueOf(count), list.get(j));

                                        count++;
                                    }
                                    map.put("wenXian", hashMap);
                                }
                            }

                            zduMap.put(String.valueOf(i), map);
                        }
                    }
                    jsonResult.put("中度关注", zduMap);
                }
            }
            if (result.contains("其他")) {
                String qiTa = new cn.hutool.json.JSONObject(result).get("其他").toString();
                if (qiTa != null) {
                    Map qTMap = JSON.parseObject(new cn.hutool.json.JSONObject(result).get("其他").toString(), Map.class);
                    HashMap<String, Object> qTaMap = new HashMap<>();
                    if (qTMap.size() > 0) {
                        for (int i = 0; i < qTMap.size(); i++) {
                            Map map = JSON.parseObject(new cn.hutool.json.JSONObject(qTMap.get(String.valueOf(i))).toString(), Map.class);
                            qTaMap.put(String.valueOf(i), map);
                        }
                    }
                    jsonResult.put("其他", qTaMap);
                }
            }
            if (result.contains("高度关注_num")) {
                Object gaoNum = new cn.hutool.json.JSONObject(result).get("高度关注_num");
                if (gaoNum != null) {
                    jsonResult.put("高度关注_num", gaoNum.toString());
                }
            }
            if (result.contains("中度关注_num")) {
                Object zhongNum = new cn.hutool.json.JSONObject(result).get("中度关注_num");
                if (zhongNum != null) {
                    jsonResult.put("中度关注_num", zhongNum.toString());
                }
            }
            if (result.contains("中度关注_num")) {
                Object qiNum = new cn.hutool.json.JSONObject(result).get("其他_num");
                if (qiNum != null) {
                    jsonResult.put("其他_num", qiNum.toString());
                }
            }
            long time1 = new Date().getTime();
            long sss = (time1 - time) / 1000;
            jsonResult.put("cost", sss);
            redisUtil.set("vcfDetail:" + vcfId, jsonResult.toJSONString());
            return ResultUtil.success(jsonResult.toJSONString());
        } else {
            Object o = redisUtil.get("vcfDetail:" + vcfId);
            return ResultUtil.success(o);
        }

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
        patientInfo.setIsResolve(2);
        vcfFileMapper.updateByPrimaryKey(vcfFile);
        patientInfoMapper.updateByPrimaryKey(patientInfo);
        return ResultUtil.success("删除VCF成功!");
    }


    @Override
    public void download(String patientId, HttpServletResponse response, UserInfo userInfo) throws Exception {
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(Integer.valueOf(patientId));
            VcfFile vcfFile = vcfFileMapper.selectByPrimaryKey(patientInfo.getJobId());
            if (vcfFile == null || vcfFile.getJsonResult() == null) {
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
                if (!hashMap.isEmpty()) {
                    heighDisease = hashMap.get("disease").toString();
                    List<HashMap> hashMaps = (List<HashMap>) hashMap.get("emphasis");
                    map = getHashMap(hashMaps);
                    ListHeightData = getListMap(heighList);
                    String wenxian = getLiterature(heighList);
                    literature.append(wenxian);
                }
            }
            //计算有几个中度关注
            HashMap moderateList = new HashMap<String, String>();
            String moderateResult = "";
            String moderateDisease = "";
            if (null != json.get("中度关注") && StringUtils.isNotBlank(json.get("中度关注").toString())) {
                moderateList = JSON.parseObject(json.get("中度关注").toString(), HashMap.class);
                moderateResult = getMutation(json, "中度关注");
                HashMap<String, Object> hashMap = getDiseaseName(json, "中度关注");
                if (!hashMap.isEmpty()) {
                    moderateDisease = hashMap.get("disease").toString();
                    ListModerateData = getListMap(moderateList);
                    String wenxian = getLiterature(moderateList);
                    literature.append(wenxian);
                }

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataMap.put("statisticalTime", sdf.format(patientInfo.getCreateTime()));
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

            String literatureStr = literature.toString();
            //把文献中的,替换为制表符
            String replace = "";
            if (literatureStr.contains(",")) {
                replace = literatureStr.replace(",", "\t");
            }
            //把文献中的[],替换为空
            if (replace.contains("]")) {
                replace = replace.replace("]", "");
            }
            if (replace.contains("[")) {
                replace = replace.replace("[", "");
            }
            if (replace.contains("-")) {
                replace = replace.replace("-", "");
            }
            //把文献中的",替换为空
            if (replace.contains("\"")) {
                replace = replace.replace("\"", "");
            }
            if (replace.contains("None")) {
                replace = replace.replace("None", "");
            }
            log.info("============================={}", replace);
            ArrayList<String> strings1 = new ArrayList<>();
            if (!StringUtils.isBlank(replace)) {
                String[] split = replace.split("\t");
                List<String> strings = Arrays.asList(split);
                for (int j = 0; j < strings.size(); j++) {
                    if (j <= 10) {
                        String s = strings.get(j);
                        if (!"".equals(s)) {
                            log.info("===============================================参数:{}", s);
                            RestResponse restResponse = literatureService.literatureQuery(s);
                            if (restResponse.getData() != null) {
                                Literature literature1 = JSON.parseObject(JSON.toJSONString(restResponse.getData()), Literature.class);
                                StringBuffer sb = new StringBuffer();
                                sb.append(literature1.getAuthor());
                                sb.append(literature1.getTitle());
                                sb.append(literature1.getPeriodicalName());
                                sb.append(literature1.getPeriodicalNumber());
                                sb.append(literature1.getPublishingTime());
                                strings1.add(sb.toString());
                            }
                        }

                    }
                }

            }
            log.info("*************************{}", JSON.toJSONString(strings1));
            dataMap.put("literature", strings1);
            baos = PdfUtilTest.createPDF(dataMap, "pdfPage.ftl");
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
                    String disease = parse.get("相关疾病").toString();
                    if (disease.contains("[")) {
                        disease = disease.replace("[", "");
                    }
                    if (disease.contains("]")) {
                        disease = disease.replace("]", "");
                    }
                    if (disease.contains("\"")) {
                        disease = disease.replace("\"", "");
                    }
                    if (disease.contains("None")) {
                        disease = disease.replace("None", "");
                    }
                    if (disease.contains(",")) {
                        disease = disease.replace(",", "\t");
                    }
                    if (disease.contains("-")) {
                        disease = disease.replace("-", "");
                    }
                    heightAttentionResp.setRelatedDisease(disease);
                }
                if (null != parse.get("来源")) {
                    String source = parse.get("来源").toString();
                    if (source.contains("[")) {
                        source = source.replace("[", "");
                    }
                    if (source.contains("]")) {
                        source = source.replace("]", "");
                    }
                    if (source.contains("\"")) {
                        source = source.replace("\"", "");
                    }
                    if (source.contains("None")) {
                        source = source.replace("None", "");
                    }
                    if (source.contains(",")) {
                        source = source.replace(",", "\t");
                    }
                    if (source.contains("-")) {
                        source = source.replace("-", "");
                    }
                    heightAttentionResp.setSource(source);
                }
                if (null != parse.get("文献")) {
                    String literature = parse.get("文献").toString();
                    if (literature.contains("[")) {
                        literature = literature.replace("[", "");
                    }
                    if (literature.contains("]")) {
                        literature = literature.replace("]", "");
                    }
                    if (literature.contains("\"")) {
                        literature = literature.replace("\"", "");
                    }
                    if (literature.contains("None")) {
                        literature = literature.replace("None", "");
                    }
                    if (literature.contains("-")) {
                        literature = literature.replace("-", "");
                    }
                    if (literature.contains(",")) {
                        literature = literature.replace(",", "\t");
                    }
                    heightAttentionResp.setLiterature(literature);
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
        if (!o.isEmpty()) {
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
        if (!json.isEmpty()) {
            JSONObject o = JSONObject.parseObject(json.get(disease).toString());
            if (!o.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(o.get("0").toString());
                sb.append(jsonObject.get("染色体位置"));
                sb.append(" ");
                sb.append(jsonObject.get("Ref"));
                sb.append(" > ");
                sb.append(jsonObject.get("Alt"));
            }
        }
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
