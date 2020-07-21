package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.resp.VcfCountResp;
import com.platform.model.UserInfo;
import com.platform.service.VcfService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * VCF管理
 */
@Api(tags = "VCF管理")
@RestController
@RequestMapping("mtApi/")
public class VcfController {
    @Autowired
    VcfService vcfService;

    /**
     * 统计未处理,已处理，总数完成的vcf数量
     *
     * @param userInfo
     * @return 数量
     */
    @GetMapping("/vcf/processCount")
    public RestResponse<VcfCountResp> handleStatus(@LoginUser UserInfo userInfo) {
        return vcfService.handleStatus(userInfo);
    }
}
