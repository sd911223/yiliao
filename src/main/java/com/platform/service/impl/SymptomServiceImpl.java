package com.platform.service.impl;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import com.platform.dao.SymptomMapper;
import com.platform.dao.SymptomTypeMapper;
import com.platform.model.Symptom;
import com.platform.model.SymptomExample;
import com.platform.model.SymptomType;
import com.platform.model.SymptomTypeExample;
import com.platform.service.SymptomService;
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
        List<Symptom> symptomList = symptomMapper.selectByExample(symptomExample);
        return ResultUtil.success(symptomList);
    }
}
