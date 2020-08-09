package com.platform.service;

import com.platform.entity.req.MailReq;
import com.platform.entity.req.ReplyMessageReq;

import java.util.List;

public interface MailService {
    /**
     * 发送纯文本邮件
     */
    void sendTextMail(MailReq mailReq);

    /**
     * 发送带图片邮件
     *
     * @param to      发送目标邮箱
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param imgList 图片路径
     * @throws javax.mail.MessagingException
     */
    void sendImageMail(String to, String subject, String content, List<String> imgList);


    void sendMail(ReplyMessageReq replyMessageReq,String toMail);
}
