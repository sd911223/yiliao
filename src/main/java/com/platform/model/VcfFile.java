package com.platform.model;

import java.util.Date;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/24
 */
public class VcfFile {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * vcf名称
     */
    private String jobName;

    /**
     * 基因版组
     */
    private String geneType;

    /**
     * 关注疾病/OMIM ID
     */
    private String attentionDisease;

    /**
     * 症状类型
     */
    private String symptomType;

    /**
     * 症状
     */
    private String symptom;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文件名字
     */
    private String fileName;

    /**
     * 是否有效;1:有效;2:无效
     */
    private Integer isEffective;

    /**
     * 解析结果
     */
    private String jsonResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGeneType() {
        return geneType;
    }

    public void setGeneType(String geneType) {
        this.geneType = geneType;
    }

    public String getAttentionDisease() {
        return attentionDisease;
    }

    public void setAttentionDisease(String attentionDisease) {
        this.attentionDisease = attentionDisease;
    }

    public String getSymptomType() {
        return symptomType;
    }

    public void setSymptomType(String symptomType) {
        this.symptomType = symptomType;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
}