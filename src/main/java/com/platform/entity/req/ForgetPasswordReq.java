package com.platform.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ForgetPasswordReq {
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", required = true)
    private String eMail;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", required = true)
    @NotBlank(message = "用户密码不能为空")
    private String password;

    /**
     * 再次密码
     */
    @ApiModelProperty(value = "再次密码", required = true)
    @NotBlank(message = "再次密码不能为空")
    private String againPassword;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码" ,required = true)
    @NotBlank(message = "验证码不能为空")
    @Length(min = 4, max = 4,message = "验证码格式错误")
    private String verificationCode;
}
