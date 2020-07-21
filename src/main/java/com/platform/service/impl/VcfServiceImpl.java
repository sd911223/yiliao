package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.PatientInfoMapper;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.PatientInfoExample;
import com.platform.model.UserInfo;
import com.platform.service.VcfService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * VCF管理
 */
public class VcfServiceImpl implements VcfService {
    @Autowired
    PatientInfoMapper patientInfoMapper;

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
}
