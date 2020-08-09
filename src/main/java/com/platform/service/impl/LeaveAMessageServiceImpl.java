package com.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.LeaveMessageMapper;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.entity.req.ReplyMessageReq;
import com.platform.model.LeaveMessage;
import com.platform.model.LeaveMessageExample;
import com.platform.model.UserInfo;
import com.platform.service.LeaveAMessageService;
import com.platform.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    @Autowired
    MailService mailService;

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
        leaveMessage.setIsEffective(Byte.valueOf("1"));
        leaveMessage.setTitle(leaveAMessageReq.getTitle());
        leaveMessage.setMail(userInfo.geteMail());
        leaveMessage.setContent(leaveAMessageReq.getContent());
        //初始化未回复
        leaveMessage.setReserved("1");
        leaveMessageMapper.insertSelective(leaveMessage);
        return ResultUtil.success("创建留言成功!");
    }

    /**
     * 留言列表
     *
     * @param pageNum
     * @return
     */
    @Override
    public RestResponse listLeaveMessage(String pageNum) {
        PageHelper.startPage(Integer.valueOf(pageNum), 10);
        LeaveMessageExample leaveMessageExample = new LeaveMessageExample();
        leaveMessageExample.createCriteria().andIsEffectiveEqualTo(Byte.valueOf("1"));
        List<LeaveMessage> leaveMessages = leaveMessageMapper.selectByExample(leaveMessageExample);
        PageInfo<LeaveMessage> pageInfo = new PageInfo<>(leaveMessages);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 回复邮件
     *
     * @param replyMessageReq
     * @return
     */
    @Override
    public RestResponse replyLeaveMessage(ReplyMessageReq replyMessageReq) {
        LeaveMessage leaveMessage = leaveMessageMapper.selectByPrimaryKey(Integer.valueOf(replyMessageReq.getLeaveId()));
        mailService.sendMail(replyMessageReq, leaveMessage.getMail());
        leaveMessage.setReserved("2");
        leaveMessageMapper.updateByPrimaryKey(leaveMessage);
        return ResultUtil.success();
    }

    /**
     * 留言板详细
     *
     * @param leaveId
     * @return
     */
    @Override
    public RestResponse detailedLeaveMessage(String leaveId) {
        return ResultUtil.success(leaveMessageMapper.selectByPrimaryKey(Integer.valueOf(leaveId)));
    }
}
