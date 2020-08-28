package com.platform.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GeneVariantResp {
    private String Variant;
    private String Disease;
    private String SNP;
    private String ClinVar;
    private String OmId;
}
