package com.platform.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SymptomResp {
    private String name;
    private String ontologyId;
    private String definition;
    private String catLabel;
}
