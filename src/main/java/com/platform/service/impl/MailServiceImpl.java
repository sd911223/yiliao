package com.platform.service.impl;

import com.platform.dao.MailManageMapper;
import com.platform.dao.UserInfoMapper;
import com.platform.entity.req.MailReq;
import com.platform.entity.req.ReplyMessageReq;
import com.platform.model.MailManage;
import com.platform.service.MailService;
import com.platform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Value("${img.location}")
    private String imgLocation;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MailManageMapper mailManageMapper;

    @Autowired
    RedisUtil redisUtil;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${mail.fromMail.title}")
    private String title;


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
            addMailManage(from, mailReq.getEMail(), title, sb.toString(), mailReq.getMailType());
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
     * @param imgList 图片路径
     * @throws MessagingException the messaging exception
     * @author: ***
     */
    @Override
    public void sendImageMail(String to, String subject, String content, List<String> imgList) {
        //创建message
        MimeMessage message = mailSender.createMimeMessage();
        try {
            log.info("======================== 进入发送邮箱图片==========================");
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            //发件人
            helper.setFrom(from);
            //收件人
            helper.setTo(to);
            //标题
            helper.setSubject(subject);

            StringBuffer sb = new StringBuffer();
            sb.append("<p>");
            sb.append(content);
            sb.append("</p><br/>");
            Map<String, String> imgMap = new HashMap<>();
            if (!imgList.isEmpty()) {
                if (imgList.size() == 1) {
                    imgMap.put("img01", imgLocation + imgList.get(0));
                    sb.append("<img src=\\\"cid:img01\\\" />");
                }
                if (imgList.size() == 2) {
                    imgMap.put("img01", imgLocation + imgList.get(0));
                    imgMap.put("img02", imgLocation + imgList.get(1));
                    sb.append("<img src=\\\"cid:img01\\\" />");
                    sb.append("<img src=\\\"cid:img02\\\" />");
                }
                if (imgList.size() == 3) {
                    imgMap.put("img01", imgLocation + imgList.get(0));
                    imgMap.put("img02", imgLocation + imgList.get(1));
                    imgMap.put("img03", imgLocation + imgList.get(2));
                    sb.append("<img src=\\\"cid:img01\\\" />");
                    sb.append("<img src=\\\"cid:img02\\\" />");
                    sb.append("<img src=\\\"cid:img03\\\" />");
                }
                if (imgList.size() == 4) {
                    imgMap.put("img01", imgLocation + imgList.get(0));
                    imgMap.put("img02", imgLocation + imgList.get(1));
                    imgMap.put("img03", imgLocation + imgList.get(2));
                    imgMap.put("img04", imgLocation + imgList.get(3));
                    sb.append("<img src=\\\"cid:img01\\\" />");
                    sb.append("<img src=\\\"cid:img02\\\" />");
                    sb.append("<img src=\\\"cid:img03\\\" />");
                    sb.append("<img src=\\\"cid:img04\\\" />");
                }
            }
            //内容
            log.info("发送内容为:{}", sb.toString());
            helper.setText(sb.toString(), true);
            // 添加图片
            if (imgMap.size() > 0) {
                for (Map.Entry<String, String> entry : imgMap.entrySet()) {
                    FileSystemResource fileResource = new FileSystemResource(new File(entry.getValue()));
                    if (fileResource.exists()) {
                        String filename = fileResource.getFilename();
                        helper.addInline(entry.getKey(), fileResource);
                    }
                }
            }
        } catch (MessagingException e) {
            log.info("发送邮件带图片发送异常{}", e.getMessage());
            e.printStackTrace();
        }
        log.info("发送邮件带图片成功!");
        //发送邮件
        mailSender.send(message);
        addMailManage(to, from, title, content, "");
    }

    @Override
    public void sendMail(ReplyMessageReq replyMessageReq, String toMail) {
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        //发送目标
        message.setTo(toMail);
        //发送标题
        message.setSubject(replyMessageReq.getTitle());

        //发送内容
        message.setText(replyMessageReq.getContent());
        try {
            mailSender.send(message);
            addMailManage(from, toMail, replyMessageReq.getTitle(), replyMessageReq.getContent(), "4");
            log.info("验证码邮件已经发送。");
        } catch (Exception e) {
            log.error("发送验证码邮件时发生异常！", e);
        }
    }

    @Async
    public void addMailManage(String send, String to, String title, String content, String type) {
        MailManage mailManage = new MailManage();
        //内容
        mailManage.setContent(content);
        //标题
        mailManage.setTitle(title);
        //创建时间
        mailManage.setCreateTime(new Date());
        //发送邮箱
        mailManage.setMailSender(send);
        //收件邮箱
        mailManage.setMailReceive(to);
        if ("REGISTERED".equals(type)) {
            mailManage.setMailType(1);
        }
        if ("FORGET_PASSWORD".equals(type)) {
            mailManage.setMailType(2);
        }
        if (StringUtils.isBlank(type)) {
            mailManage.setMailType(3);
        }
        mailManageMapper.insert(mailManage);
    }
}
