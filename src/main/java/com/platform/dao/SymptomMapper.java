package com.platform.dao;

import com.platform.model.Symptom;
import com.platform.model.SymptomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SymptomMapper {
    long countByExample(SymptomExample example);

    int deleteByExample(SymptomExample example);

    int insert(Symptom record);

    int insertSelective(Symptom record);

    List<Symptom> selectByExample(SymptomExample example);

    int updateByExampleSelective(@Param("record") Symptom record, @Param("example") SymptomExample example);

    int updateByExample(@Param("record") Symptom record, @Param("example") SymptomExample example);
}