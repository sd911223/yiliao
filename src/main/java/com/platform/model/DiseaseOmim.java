package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/28
 */
public class DiseaseOmim {
    /**
     * 
     */
    private Integer id;

    /**
     * 基因ID
     */
    private Integer omimId;

    /**
     * 疾病名称
     */
    private String diseaseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOmimId() {
        return omimId;
    }

    public void setOmimId(Integer omimId) {
        this.omimId = omimId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}