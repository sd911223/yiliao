package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.LeaveMessageMapper;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.model.LeaveMessage;
import com.platform.model.UserInfo;
import com.platform.service.LeaveAMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 留言板
 */
@Service
@Slf4j
public class LeaveAMessageServiceImpl implements LeaveAMessageService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    LeaveMessageMapper leaveMessageMapper;

    /**
     * 创建留言
     *
     * @param userInfo
     * @param leaveAMessageReq
     * @return
     */
    @Override
    public RestResponse addLeaveMessage(UserInfo userInfo, LeaveAMessageReq leaveAMessageReq) {
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setUserId(userInfo.getUserId());
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setImgList(leaveAMessageReq.getImgList());
        leaveMessage.setIsEffective(1);
        leaveMessage.setTitle(leaveAMessageReq.getTitle());
        leaveMessage.setContent(leaveAMessageReq.getContent());
        leaveMessageMapper.insertSelective(leaveMessage);
        return ResultUtil.success("创建留言成功!");
    }
}
