package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.service.VariationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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


    @ApiOperation("变异查询")
    @PostMapping("/variation/byRsId")
    public RestResponse variationQuery(@ApiParam("变异ID") @RequestParam("rsId") String rsId) {

        return variationService.variationService(rsId);
    }
}
