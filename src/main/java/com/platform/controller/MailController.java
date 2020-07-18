package com.platform.controller;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.entity.req.MailReq;
import com.platform.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "邮件")
@RequestMapping("mtApi/")
public class MailController {
    @Autowired
    MailService mailService;

    @ApiOperation("发送邮件-验证码")
    @PostMapping("/send/captcha")
    public RestResponse sendCaptcha(@RequestBody @Valid MailReq mailReq) {
        mailService.sendTextMail(mailReq);
        return ResultUtil.success();
    }
}
