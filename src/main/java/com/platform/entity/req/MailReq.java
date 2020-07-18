package com.platform.entity.req;

import com.platform.annotation.EnumValid;
import com.platform.entity.enums.MailType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("发送邮件请求实体")
public class MailReq {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", required = true)
    private String eMail;

    @EnumValid(target = MailType.class, message = "邮件类型不对/REGISTERED/FORGET_PASSWORD")
    private String mailType;
}
