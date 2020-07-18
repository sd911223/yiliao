package com.platform.service;

import com.platform.entity.req.MailReq;

public interface MailService {
    /**
     * 发送纯文本邮件
     */
    public void sendTextMail(MailReq mailReq);

}
