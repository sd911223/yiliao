package com.platform.entity.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeightAttentionResp {
    @ApiModelProperty("变异")
    private String variation;

    @ApiModelProperty("染色体位置")
    private String chromosomePosition;

    @ApiModelProperty("Ref")
    private String ref;

    @ApiModelProperty("Alt")
    private String alt;

    @ApiModelProperty("基因型")
    private String geneShape;

    @ApiModelProperty("MAF(亚洲)")
    private String maf;

    @ApiModelProperty("gene")
    private String gene;

    @ApiModelProperty("致病分值")
    private String pathogenicPoints;

    @ApiModelProperty("突变类型")
    private String mutationType;

    @ApiModelProperty("蛋白变化")
    private String proteinChange;

    @ApiModelProperty("相关疾病")
    private String relatedDisease;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("文献")
    private String literature;
}
