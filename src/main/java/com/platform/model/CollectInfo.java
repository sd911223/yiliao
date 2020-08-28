package com.platform.model;

import java.util.Date;

/**
 * @author shiTou
 * @date 2020/08/01
 */
public class CollectInfo {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 名称
     */
    private String name;


    private String diseaseName;

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    /**
     * 类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 预留字段
     */
    private String reservedOne;

    /**
     * 预留字段
     */
    private String reservedTow;

    /**
     * 是否有效 1:有效;2:无效
     */
    private Integer isEffective;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReservedOne() {
        return reservedOne;
    }

    public void setReservedOne(String reservedOne) {
        this.reservedOne = reservedOne;
    }

    public String getReservedTow() {
        return reservedTow;
    }

    public void setReservedTow(String reservedTow) {
        this.reservedTow = reservedTow;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }
}