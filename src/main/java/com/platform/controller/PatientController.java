package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.req.PatientAddReq;
import com.platform.entity.req.PatientListReq;
import com.platform.model.UserInfo;
import com.platform.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 患者管理
 */
@RequestMapping("mtApi/")
@RestController
@Api(tags = "患者管理")
public class PatientController {
    @Autowired
    PatientService patientService;

    /**
     * 新建患者
     *
     * @param userInfo
     * @param patientAddReq
     * @return
     */
    @ApiOperation("新建患者")
    @PostMapping("/patient/add")
    public RestResponse addPatient(@LoginUser UserInfo userInfo, @RequestBody @Valid PatientAddReq patientAddReq) {

        return patientService.addPatient(userInfo,patientAddReq);
    }

    /**
     * 患者列表
     *
     * @param patientListReq
     * @return
     */
    @ApiOperation("患者列表")
    @PostMapping("/patient/list")
    public RestResponse listPatient(@RequestBody @Valid PatientListReq patientListReq) {

        return patientService.listPatient(patientListReq);
    }

    /**
     * 删除患者
     *
     * @param patientId
     * @return
     */
    @ApiOperation("删除患者")
    @GetMapping("/patient/delete")
    public RestResponse deletePatient(@RequestParam(name = "patientId") Integer patientId) {

        return patientService.deletePatient(patientId);
    }

    /**
     * 患者详情
     *
     * @param patientId
     * @return
     */
    @ApiOperation("患者详情")
    @GetMapping("/patient/detailed")
    public RestResponse detailedPatient(@RequestParam(name = "patientId") Integer patientId) {

        return patientService.detailedPatient(patientId);
    }

}
