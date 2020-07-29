package com.platform.service;

import com.platform.common.RestResponse;

import java.util.List;
import java.util.Map;

/**
 * @author shitou
 */
public interface DiseaseService {
    /**
     * 根据疾病id查询疾病的详细信息
     *
     * @param omimId
     * @return
     */
    RestResponse disease(String omimId,String type);

    /**
     * 根据多个症状查询对应的疾病列表
     *
     * @param symptomArray
     * @return
     */
    List<Map<String, String>> listDisease(String[] symptomArray);
}
