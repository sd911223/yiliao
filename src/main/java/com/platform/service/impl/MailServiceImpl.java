package com.platform.service.impl;

import com.platform.common.ResultEnum;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.MailReq;
import com.platform.exception.BusinessException;
import com.platform.model.UserInfo;
import com.platform.model.UserInfoExample;
import com.platform.service.MailService;
import com.platform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${mail.fromMail.title}")
    private String title;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 发送验证码
     */
    @Override
    public void sendTextMail(MailReq mailReq) {
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        //发送目标
        message.setTo(mailReq.getEMail());
        //发送标题
        message.setSubject(title);
        //生成4位随机数
        int captcha = (int) ((Math.random() * 9 + 1) * 1000);
        //存放redis 10分钟
        redisUtil.set(mailReq.getEMail() + ":" + mailReq.getMailType(), captcha, Long.valueOf(60 * 10));
        //发送内容
        StringBuffer sb = new StringBuffer();
        sb.append("【DHExo】验证码：");
        sb.append(captcha);
        sb.append("。欢迎使用DHExo全外显子解读系统，请勿将此验证码告知他人。非本人操作请忽略。");
        message.setText(sb.toString());
        try {
            mailSender.send(message);
            log.info("验证码邮件已经发送。");
        } catch (Exception e) {
            log.error("发送验证码邮件时发生异常！", e);
        }
    }

}
