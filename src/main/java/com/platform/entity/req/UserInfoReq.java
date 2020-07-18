package com.platform.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel("修改用户信息请求实体")
public class UserInfoReq {

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",required = true)
    @Email(message = "邮箱格式不正确")
    private String eMail;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位",required = true)
    @NotBlank(message = "职位不能为空")
    private String title;

    /**
     * 医院
     */
    @ApiModelProperty(value = "医院",required = true)
    @NotBlank(message = "医院不能为空")
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
