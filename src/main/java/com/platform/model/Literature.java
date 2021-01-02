package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2021/01/02
 */
public class Literature {
    /**
     * 文献ID
     */
    private Integer literatureId;

    /**
     * 作者
     */
    private String author;

    /**
     * 标题
     */
    private String title;

    /**
     * 期刊名
     */
    private String periodicalName;

    /**
     * 期刊号
     */
    private String periodicalNumber;

    /**
     * 出版时间
     */
    private String publishingTime;

    /**
     * 摘要
     */
    private String summary;

    public Integer getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(Integer literatureId) {
        this.literatureId = literatureId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getPeriodicalNumber() {
        return periodicalNumber;
    }

    public void setPeriodicalNumber(String periodicalNumber) {
        this.periodicalNumber = periodicalNumber;
    }

    public String getPublishingTime() {
        return publishingTime;
    }

    public void setPublishingTime(String publishingTime) {
        this.publishingTime = publishingTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}