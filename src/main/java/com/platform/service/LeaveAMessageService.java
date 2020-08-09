package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.entity.req.LeaveAMessageReq;
import com.platform.entity.req.ReplyMessageReq;
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
    RestResponse addLeaveMessage(UserInfo userInfo, LeaveAMessageReq leaveAMessageReq);

    /**
     * 留言列表
     *
     * @return
     */
    RestResponse listLeaveMessage(String pageNum);

    /**
     * 回复留言
     *
     * @param replyMessageReq
     * @return
     */
    RestResponse replyLeaveMessage(ReplyMessageReq replyMessageReq);

    /**
     * 留言板详细
     *
     * @param leaveId
     * @return
     */
    RestResponse detailedLeaveMessage(String leaveId);
}
