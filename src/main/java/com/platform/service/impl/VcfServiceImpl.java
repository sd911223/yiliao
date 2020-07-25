package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.PatientInfoMapper;
import com.platform.dao.VcfFileMapper;
import com.platform.entity.resp.VcfCountResp;
import com.platform.exception.BusinessException;
import com.platform.model.PatientInfo;
import com.platform.model.PatientInfoExample;
import com.platform.model.UserInfo;
import com.platform.model.VcfFile;
import com.platform.service.VcfService;
import com.platform.util.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

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
    private ShellUtil shellUtil;

    @Value("${vcf.file.path}")
    private String path;

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
        PatientInfoExample noInfoExample = new PatientInfoExample();
        noInfoExample.createCriteria()
                .andIsEffectiveEqualTo(1)
                .andIsResolveEqualTo(2)
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

}
