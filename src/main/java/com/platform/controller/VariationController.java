package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.service.VariationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 变异管理
 */
@RestController
@Api(tags = "变异管理")
@RequestMapping("mtApi/")
public class VariationController {
    @Autowired
    VariationService variationService;

    /**
     * 查询变异
     *
     * @param rsId
     * @return
     */
    @ApiOperation("变异查询")
    @GetMapping("/variation/byRsId")
    public RestResponse variationQuery(@ApiParam("变异ID") @RequestParam("rsId") String rsId) {

        return variationService.variationService(rsId);
    }

    /**
     * 通过变异ID查询疾病名称
     *
     * @param rsId
     * @return
     */
    @ApiOperation("通过变异ID查询疾病名称")
    @GetMapping("/variation/diseaseName")
    public RestResponse diseaseName(@ApiParam("变异ID") @RequestParam("rsId") Integer rsId) {

        return variationService.diseaseName(rsId);
    }

    /**
     * 导入数据库
     *
     * @return
     */
    @ApiOperation("导入数据库")
    @GetMapping("/variation/DeImport")
    public void DeImport() {
        variationService.DeImport();
    }


}
