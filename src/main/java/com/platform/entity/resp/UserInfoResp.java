package com.platform.entity.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息返回实体")
public class UserInfoResp {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String eMail;

    /**
     * 职位
     */
    @ApiModelProperty("职位")
    private String title;

    /**
     * 医院
     */
    @ApiModelProperty("医院")
    private String hospital;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String synopsis;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 头像url
     */
    @ApiModelProperty("头像url")
    private String headImgUrl;
}
