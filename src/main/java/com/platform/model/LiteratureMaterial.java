package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/10/22
 */
public class LiteratureMaterial {
    /**
     * 文献ID
     */
    private Integer literatureId;

    /**
     * reference
     */
    private String reference;

    /**
     * 文献内容
     */
    private String literatureData;

    public Integer getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(Integer literatureId) {
        this.literatureId = literatureId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLiteratureData() {
        return literatureData;
    }

    public void setLiteratureData(String literatureData) {
        this.literatureData = literatureData;
    }
}