package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.service.PdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "PDF")
@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;


    /**
     * 导出解析报告pdf
     *
     * @param id
     * @return
     */
    @ApiOperation("导出解析报告pdf")
    @GetMapping("/pdf/export/{id}")
    public RestResponse exportVcf(@PathVariable String id, HttpServletResponse response) {

//        pdfService.exportPdf(id);
        return ResultUtil.success();
    }
}
