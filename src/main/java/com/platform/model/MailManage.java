package com.platform.model;

import java.util.Date;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/22
 */
public class MailManage {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 邮件发送人
     */
    private String mailSender;

    /**
     * 收件人
     */
    private String mailReceive;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 邮件类型
     */
    private Integer mailType;

    /**
     * 邮件内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    public String getMailReceive() {
        return mailReceive;
    }

    public void setMailReceive(String mailReceive) {
        this.mailReceive = mailReceive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMailType() {
        return mailType;
    }

    public void setMailType(Integer mailType) {
        this.mailType = mailType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}