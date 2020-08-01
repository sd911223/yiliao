package com.platform.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("收藏夹请求实体")
public class CollectReq {
    /**
     * 备注名称
     */
    @ApiModelProperty("备注名称")
    private String name;
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;
    /**
     * 预留字段一
     */
    @ApiModelProperty("预留字段一")
    private String reservedOne;
    /**
     * 预留字段二
     */
    @ApiModelProperty("预留字段二")
    private String reservedTow;
}
