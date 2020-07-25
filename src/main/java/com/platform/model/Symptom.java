package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/25
 */
public class Symptom {
    /**
     * 症状类型ID
     */
    private Integer symptomTypeId;

    /**
     * 症状
     */
    private String symptom;

    public Integer getSymptomTypeId() {
        return symptomTypeId;
    }

    public void setSymptomTypeId(Integer symptomTypeId) {
        this.symptomTypeId = symptomTypeId;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}