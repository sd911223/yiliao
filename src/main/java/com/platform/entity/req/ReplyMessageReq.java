package com.platform.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyMessageReq {
    /**
     * 留言ID
     */
    @ApiModelProperty("留言ID")
    @NotBlank(message = "留言ID不能为空")
    private String leaveId;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    @NotBlank(message = "回复内容不能为空")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    @NotBlank(message = "回复内容不能为空")
    private String content;

}
