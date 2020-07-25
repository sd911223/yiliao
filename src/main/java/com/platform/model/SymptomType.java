package com.platform.model;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/25
 */
public class SymptomType {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 类型
     */
    private String symptomType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymptomType() {
        return symptomType;
    }

    public void setSymptomType(String symptomType) {
        this.symptomType = symptomType;
    }
}