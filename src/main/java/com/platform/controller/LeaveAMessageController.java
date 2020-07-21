package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.model.UserInfo;
import com.platform.service.LeaveAMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("mtApi/")
@Api(tags = "留言板")
public class LeaveAMessageController {
    @Autowired
    LeaveAMessageService leaveAMessageService;

    /**
     * 新建留言
     *
     * @param userInfo
     * @param leaveAMessageReq
     * @return
     */
    @ApiOperation("新建留言")
    @PostMapping("/add/leaveMessage")
    public RestResponse addLeaveMessage(@LoginUser UserInfo userInfo, @RequestBody @Valid LeaveAMessageReq leaveAMessageReq) {

        return leaveAMessageService.addLeaveMessage(userInfo, leaveAMessageReq);
    }

}
