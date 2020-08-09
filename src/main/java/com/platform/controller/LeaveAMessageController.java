package com.platform.controller;

import com.platform.annotation.LoginUser;
import com.platform.common.RestResponse;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.entity.req.ReplyMessageReq;
import com.platform.model.UserInfo;
import com.platform.service.LeaveAMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 留言列表
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("留言列表")
    @PostMapping("/list/leaveMessage")
    public RestResponse listLeaveMessage(@ApiParam("当前页") @RequestParam(value = "pageNum") String pageNum) {

        return leaveAMessageService.listLeaveMessage(pageNum);
    }


    /**
     * 回复留言
     *
     * @param replyMessageReq
     * @return
     */
    @ApiOperation("回复留言")
    @PostMapping("/reply/leaveMessage")
    public RestResponse replyLeaveMessage(@RequestBody @Valid ReplyMessageReq replyMessageReq) {

        return leaveAMessageService.replyLeaveMessage(replyMessageReq);
    }

    /**
     * 留言详情
     *
     * @param leaveId
     * @return
     */
    @ApiOperation("留言详情")
    @PostMapping("/detailed/leaveMessage")
    public RestResponse detailedLeaveMessage(@ApiParam("留言板ID") @RequestParam(value = "leaveId") String leaveId) {

        return leaveAMessageService.detailedLeaveMessage(leaveId);
    }

}
