package com.platform.entity.req;

import com.platform.annotation.EnumValid;
import com.platform.entity.enums.SexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新建患者请求实体")
public class PatientAddReq {
    /**
     * 患者名字
     */
    @ApiModelProperty(value = "患者名字", required = true)
    @NotBlank(message = "患者名字不能为空")
    private String patientName;

    /**
     * 性别
     */
    @EnumValid(target = SexType.class, message = "性别:GIRL(女);MAN(男)")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", required = true)
    @NotNull(message = "年龄不能为空")
    @Min(value = 1, message = "年龄必须大于0")
    @Max(value = 200, message = "别闹!")
    private Integer age;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = true)
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String idCard;
    /**
     * 症状
     */
    @ApiModelProperty(value = "症状", required = true)
    @NotBlank(message = "症状不能为空")
    private String symptom;
    /**
     * 家族病史
     */
    @ApiModelProperty(value = "家族病史", required = true)
    private String familyMedicalHistory;
}
