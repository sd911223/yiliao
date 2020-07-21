package com.platform.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("留言板请求实体")
public class LeaveAMessageReq {
    /**
     * 留言标题
     */
    @ApiModelProperty(value = "留言标题", required = true)
    @NotBlank(message = "留言标题不能为空")
    private String title;
    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容", required = true)
    @NotBlank(message = "留言内容不能为空")
    private String content;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    @NotBlank(message = "图片地址不能为空")
    private String imgList;

    /**
     * 图片ID
     */
    @ApiModelProperty(value = "图片ID")
    @NotBlank(message = "图片ID不能为空")
    private String imgId;
}
