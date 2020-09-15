package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.SymptomMapper;
import com.platform.dao.SymptomTypeMapper;
import com.platform.model.Symptom;
import com.platform.model.SymptomExample;
import com.platform.model.SymptomType;
import com.platform.model.SymptomTypeExample;
import com.platform.service.SymptomService;
import com.platform.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shitou
 */
@Service
@Slf4j
public class SymptomServiceImpl implements SymptomService {
    @Autowired
    SymptomTypeMapper symptomTypeMapper;
    @Autowired
    SymptomMapper symptomMapper;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 查询症状类型
     *
     * @return
     */
    @Override
    public RestResponse querySymptomType() {
        SymptomTypeExample typeExample = new SymptomTypeExample();
        typeExample.createCriteria();
        List<SymptomType> typeList = symptomTypeMapper.selectByExample(typeExample);
        return ResultUtil.success(typeList);
    }

    /**
     * 查询症状
     *
     * @param id
     * @return
     */
    @Override
    public RestResponse querySymptom(Integer id) {
        SymptomExample symptomExample = new SymptomExample();
        symptomExample.createCriteria().andSymptomTypeIdEqualTo(id);
        symptomExample.setOrderByClause("symptom ASC");
        List<Symptom> symptomList = symptomMapper.selectByExample(symptomExample);
        return ResultUtil.success(symptomList);
    }

    /**
     * 模糊查询症状
     *
     * @param symptom
     * @return
     */
    @Override
    public RestResponse querySymptomLike(String symptom) {
        if (redisUtil.get(symptom) == null) {
            SymptomExample symptomExample = new SymptomExample();
            symptomExample.createCriteria().andSymptomLike(symptom + "%");
            symptomExample.setOrderByClause("symptom ASC");
            List<Symptom> symptomList = symptomMapper.selectByExample(symptomExample);
            if (!symptomList.isEmpty()) {
                redisUtil.set(symptom, JSON.toJSONString(symptomList), 60 * 10L);
            }
            return ResultUtil.success(symptomList);
        } else {
            Object o = redisUtil.get(symptom);
            List<Symptom> symptomList = JSON.parseArray(o.toString(), Symptom.class);
            return ResultUtil.success(symptomList);
        }
    }
}
