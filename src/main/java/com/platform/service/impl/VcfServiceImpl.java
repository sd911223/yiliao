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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
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
        vf.setAttentionDisease(omimId);
        //症状类型
        vf.setSymptomType(symptomType);
        //症状
        vf.setSymptom(symptom);
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
        return vf;
    }
}
