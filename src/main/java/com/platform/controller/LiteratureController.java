package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.service.LiteratureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "文献管理")
@RequestMapping("mtApi/")
public class LiteratureController {
    @Autowired
    LiteratureService literatureService;

    /**
     * 查询文献
     *
     * @param literatureId
     * @return
     */
    @ApiOperation("文献查询")
    @GetMapping("/literature/byId")
    public RestResponse literatureQuery(@ApiParam("文献ID") @RequestParam("literatureId") String literatureId) {

        return literatureService.literatureQuery(literatureId);
    }

}
