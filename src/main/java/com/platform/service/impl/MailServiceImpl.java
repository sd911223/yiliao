package com.platform.service.impl;

import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.MailReq;
import com.platform.service.MailService;
import com.platform.util.RedisUtil;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

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

    /**
     * 功能描述：发送带图片的邮件
     *
     * @param to      发送目标邮箱
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param imgPath 图片路径
     * @param imgId   图片id，在img标签里使用
     * @throws MessagingException the messaging exception
     * @author: ***
     */
    @Override
    public void sendImageMail(String to, String subject, String content, String imgPath, String imgId) {
        //创建message
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发件人
            helper.setFrom(from);
            //收件人
            helper.setTo(to);
            //标题
            helper.setSubject(subject);
            //true指的是html邮件，false指的是普通文本
            helper.setText(content, true);
            //添加图片
            FileSystemResource file = new FileSystemResource(new File(imgPath));
            helper.addInline(imgId, file);
            //发送邮件

        } catch (javax.mail.MessagingException e) {
            log.info("发送邮件带图片发送异常{}", e);
            e.printStackTrace();
        }
        log.info("发送邮件带图片成功!");
        mailSender.send(message);
    }
}
