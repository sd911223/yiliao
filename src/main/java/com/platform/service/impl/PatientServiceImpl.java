package com.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.common.RestResponse;
import com.platform.common.ResultEnum;
import com.platform.common.ResultUtil;
import com.platform.dao.PatientInfoMapper;
import com.platform.entity.req.PatientAddReq;
import com.platform.entity.req.PatientListReq;
import com.platform.exception.BusinessException;
import com.platform.model.PatientInfo;
import com.platform.model.PatientInfoExample;
import com.platform.model.UserInfo;
import com.platform.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientInfoMapper patientInfoMapper;

    /**
     * 添加患者
     *
     * @param patientAddReq
     * @return
     */
    @Override
    public RestResponse addPatient(UserInfo userInfo, PatientAddReq patientAddReq) {
        PatientInfo patientInfo = new PatientInfo();
        BeanUtils.copyProperties(patientAddReq, patientInfo);
        if (patientAddReq.getSex().equals("MAN")) {
            patientInfo.setSex(1);
        }
        if (patientAddReq.getSex().equals("GIRL")) {
            patientInfo.setSex(2);
        }
        patientInfo.setCreateTime(new Date());
        patientInfo.setDoctorId(userInfo.getUserId());
        patientInfoMapper.insert(patientInfo);
        return ResultUtil.success("添加患者成功!");
    }

    /**
     * 患者列表
     *
     * @param patientListReq
     * @return
     */
    @Override
    public RestResponse listPatient(PatientListReq patientListReq) {
        PageHelper.startPage(patientListReq.getPageNum(), 10);
        PatientInfoExample patientInfoExample = new PatientInfoExample();
        PatientInfoExample.Criteria criteria = patientInfoExample.createCriteria();
        criteria.andIsEffectiveEqualTo(1);
        if (StringUtils.isBlank(patientListReq.getPatientName())) {
            criteria.andPatientNameLike("%" + patientListReq.getPatientName() + "%");
        }
        if ("isAge".equals(patientListReq.getIsAgeAsc())) {
            patientInfoExample.setOrderByClause("age ASC");
        }
        if ("isDate".equals(patientListReq.getIsDateAsc())) {
            patientInfoExample.setOrderByClause("create_time ASC");
        }
        if ("isName".equals(patientListReq.getIsNameAsc())) {
            patientInfoExample.setOrderByClause("patient_name ASC");
        }
        List<PatientInfo> infoList = patientInfoMapper.selectByExample(patientInfoExample);

        return ResultUtil.success(new PageInfo<>(infoList));
    }

    /**
     * 删除患者
     *
     * @param patientId
     * @return
     */
    @Override
    public RestResponse deletePatient(Integer patientId) {
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(patientId);
        if (null == patientInfo) {
            log.error("删除患者,无效ID{}", patientId);
            throw new BusinessException(ResultEnum.ID_NOT_EXISTS.getStatus(), ResultEnum.ID_NOT_EXISTS.getMsg());
        }
        patientInfo.setIsEffective(2);
        patientInfoMapper.updateByPrimaryKey(patientInfo);
        return ResultUtil.success("删除患者成功!");
    }

    /**
     * 患者详情
     *
     * @param patientId
     * @return
     */
    @Override
    public RestResponse detailedPatient(Integer patientId) {
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(patientId);
        if (null == patientInfo) {
            log.error("患者详情,无效ID{}", patientId);
            throw new BusinessException(ResultEnum.ID_NOT_EXISTS.getStatus(), ResultEnum.ID_NOT_EXISTS.getMsg());
        }
        return ResultUtil.success(patientInfo);
    }


}
