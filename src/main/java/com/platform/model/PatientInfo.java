package com.platform.model;

import java.util.Date;

/**
 * 
 *
 * @author shiTou
 * @date   2020/07/23
 */
public class PatientInfo {
    /**
     * 患者id
     */
    private Integer patientId;

    /**
     * 医生ID
     */
    private Integer doctorId;

    /**
     * 患者名字
     */
    private String patientName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄1:男'2女
     */
    private Integer age;

    /**
     * 症状
     */
    private String symptom;

    /**
     * 家族病史
     */
    private String familyMedicalHistory;

    /**
     * vcf:id
     */
    private Integer jobId;
    /**
     * jobName
     */
    private String jobName;
    /**
     * jobName
     */
    private String jsonResult;

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 解析时间
     */
    private Date resolveTime;

    /**
     * 是否解读1:解读;2未读
     */
    private Integer isResolve;

    /**
     * 是否有效 1:有效;2无效
     */
    private Integer isEffective;

    /**
     * 身份证号
     */
    private String idCard;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getFamilyMedicalHistory() {
        return familyMedicalHistory;
    }

    public void setFamilyMedicalHistory(String familyMedicalHistory) {
        this.familyMedicalHistory = familyMedicalHistory;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(Date resolveTime) {
        this.resolveTime = resolveTime;
    }

    public Integer getIsResolve() {
        return isResolve;
    }

    public void setIsResolve(Integer isResolve) {
        this.isResolve = isResolve;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}