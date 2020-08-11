package com.platform.service;

import com.platform.common.RestResponse;

/**
 * @author shitou
 */
public interface SymptomService {
    /**
     * 查询症状类型
     *
     * @return
     */
    RestResponse querySymptomType();

    /**
     * 查询症状
     *
     * @param id
     * @return
     */
    RestResponse querySymptom(Integer id);

    /**
     * 模糊查询症状
     *
     * @param symptom
     * @return
     */
    RestResponse querySymptomLike(String symptom);
}
