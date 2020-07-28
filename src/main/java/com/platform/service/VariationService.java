package com.platform.service;

import com.platform.common.RestResponse;

public interface VariationService {
    /**
     * 查询变异
     *
     * @param rsId
     * @return
     */
    RestResponse variationService(String rsId);

    /**
     * 通过变异ID查询疾病名称
     *
     * @param rsId
     * @return
     */
    RestResponse diseaseName(Integer rsId);
}
