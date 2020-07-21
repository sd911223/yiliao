package com.platform.entity.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel("VCF统计返回")
public class VcfCountResp {
    /**
     * 总任务
     */
    @ApiModelProperty("总任务")
    private Long totalTask;
    /**
     * 已将完成
     */
    @ApiModelProperty("已将完成")
    private Long completeTask;
    /**
     * 未完成任务
     */
    @ApiModelProperty("未完成任务")
    private Long NotTask;
}
