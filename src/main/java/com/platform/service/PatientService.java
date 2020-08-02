package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.PatientAddReq;
import com.platform.entity.req.PatientListReq;
import com.platform.model.UserInfo;

/**
 * 患者管理
 */
public interface PatientService {
    /**
     * 添加患者
     *
     * @param userInfo
     * @param patientAddReq
     * @return
     */
    RestResponse addPatient(UserInfo userInfo, PatientAddReq patientAddReq);

    /**
     * 患者列表
     *
     * @param patientListReq
     * @return
     */
    RestResponse listPatient(PatientListReq patientListReq,UserInfo userInfo);

    /**
     * 删除患者
     *
     * @param patientId
     * @return
     */
    RestResponse deletePatient(Integer patientId);

    /**
     * 患者详情
     *
     * @param patientId
     * @return
     */
    RestResponse detailedPatient(Integer patientId);

    /**
     * 修改患者信息
     *
     * @param patientAddReq
     * @return
     */
    RestResponse updatePatient(PatientAddReq patientAddReq);
}
