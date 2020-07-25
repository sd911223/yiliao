package com.platform.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改密码请求实体")
public class UpdatePasswordReq {
    @ApiModelProperty(value = "原始密码", required = true)
    @NotBlank(message = "原始密码不能为空!")
    @Length(min = 6, max = 16, message = "原始密码最少6位,最大16位")
    private String primevalPassword;


    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空!")
    @Length(min = 6, max = 16, message = "新密码密码最少6位,最大16位")
    private String newPassword;

    @ApiModelProperty(value = "再次新密码", required = true)
    @NotBlank(message = "再次新密码不能为空!")
    @Length(min = 6, max = 16, message = "再次新密码最少6位,最大16位")
    private String againPassword;
}
