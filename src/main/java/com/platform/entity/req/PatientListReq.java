package com.platform.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel("患者列表请求实体")
public class PatientListReq {
    /**
     * 姓名排序
     */
    private String isNameAsc;
    /**
     * 年龄排序
     */
    private String isAgeAsc;
    /**
     * 时间排序
     */
    private String isDateAsc;
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数", required = true)
    @Min(value = 1,message = "当前页必须大于0")
    private Integer pageNum;
    /**
     * 患者名字
     */
    private String patientName;
}
