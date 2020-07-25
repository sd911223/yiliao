package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.service.SymptomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 症状类型/症状
 */
@RestController
@RequestMapping(value = "mtApi/")
@Api(tags = "症状管理")
public class SymptomController {
    @Autowired
    SymptomService symptomService;

    /**
     * 查询症状类型
     *
     * @return
     */
    @ApiOperation("查询症状类型")
    @GetMapping("/symptom/queryType")
    public RestResponse querySymptomType() {

        return symptomService.querySymptomType();

    }

    /**
     * 查询症状
     *
     * @return
     */
    @ApiOperation("查询症状")
    @GetMapping("/symptom/query")
    public RestResponse querySymptom(@ApiParam("症状类型ID") @RequestParam(value = "id", required = true) Integer id) {

        return symptomService.querySymptom(id);

    }
}
