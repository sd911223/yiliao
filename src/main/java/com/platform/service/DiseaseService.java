package com.platform.service;

import com.platform.common.RestResponse;
import com.platform.model.DiseaseOmim;

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
    Map<String, Object> disease(String omimId,String type);

    /**
     * 根据多个症状查询对应的疾病列表
     *
     * @param symptomArray
     * @return
     */
    List<Map<String, String>> listDisease(String[] symptomArray);

    /**
     * 模糊查询疾病名称
     * @param diseaseName
     * @return
     */
    List<DiseaseOmim> byDiseaseName(String diseaseName);
}
