package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.service.DiseaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 疾病控制器
 */
@RestController
@RequestMapping(value = "mtApi/")
@Api(tags = "疾病控制器")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;

    /**
     * 根据疾病id查询疾病的详细信息
     *
     * @param OMIMId
     * @return
     */
    @ApiOperation("根据疾病id查询疾病的详细信息")
        @GetMapping("/diseaseInformation")
    public RestResponse diseaseInformation(@ApiParam("疾病ID") @RequestParam(value = "OMIM_id", required = true) String OMIMId) {

        return diseaseService.disease(OMIMId,"1");

    }

    /**
     * 根据疾病name(英文)查询疾病的详细信息
     *
     * @param diseaseName
     * @return
     */
    @ApiOperation("根据疾病name(英文)查询疾病的详细信息")
    @GetMapping("/diseaseInfoByName")
    public RestResponse diseaseInfoByName(@ApiParam("疾病名称") @RequestParam(value = "disease_name", required = true) String diseaseName) {

        return diseaseService.disease(diseaseName,"2");

    }

    /**
     * 根据多个症状查询对应的疾病列表
     *
     * @param symptoms
     * @return
     */
    @ApiOperation("根据多个症状查询对应的疾病列表")
    @GetMapping("/symptom/byDisease")
    public RestResponse byDisease(@ApiParam("症状") @RequestParam(value = "symptoms", required = true) String symptoms) {
        //此时前台传来的是一个数组的字符串,去掉中括号，生成数组
        String[] symptomArray = symptoms.substring(1, symptoms.length() - 1).split(",");
        List<Map<String, String>> result = diseaseService.listDisease(symptomArray);
        return ResultUtil.success(result);
    }

}
