package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/08/04
 */
public class EntrezAnother {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 基因ID
     */
    private Integer entrezId;

    /**
     * 基因名
     */
    private String entrezName;

    /**
     * 别名
     */
    private String anotherName;

    /**
     * HG38位置
     */
    private String hg38Location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrezId() {
        return entrezId;
    }

    public void setEntrezId(Integer entrezId) {
        this.entrezId = entrezId;
    }

    public String getEntrezName() {
        return entrezName;
    }

    public void setEntrezName(String entrezName) {
        this.entrezName = entrezName;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getHg38Location() {
        return hg38Location;
    }

    public void setHg38Location(String hg38Location) {
        this.hg38Location = hg38Location;
    }
}