package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.model.UserInfo;
import com.platform.service.LeaveAMessageService;
import com.platform.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 留言板
 */
@Service
@Slf4j
public class LeaveAMessageServiceImpl implements LeaveAMessageService {
    @Autowired
    UserInfoMapper userInfoMapper;
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
        UserInfo byIdUserInfo = userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        String[] imgList = leaveAMessageReq.getImgList().toArray(new String[leaveAMessageReq.getImgList().size()]);
        String[] imgIdList = leaveAMessageReq.getImgId().toArray(new String[leaveAMessageReq.getImgId().size()]);
        mailService.sendImageMail(byIdUserInfo.geteMail(), leaveAMessageReq.getTitle(), leaveAMessageReq.getContent(), imgList, imgIdList);
        return ResultUtil.success("发送邮件带图片成功!");
    }
}
