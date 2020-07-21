package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.model.UserInfo;

/**
 * 留言板
 */
public interface LeaveAMessageService {
    /**
     * 创建留言
     *
     * @param userInfo
     * @param leaveAMessageReq
     * @return
     */
    RestResponse addLeaveMessage(UserInfo userInfo,LeaveAMessageReq leaveAMessageReq);
}
