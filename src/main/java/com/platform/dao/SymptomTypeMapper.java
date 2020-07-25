package com.platform.dao;

import com.platform.model.SymptomType;
import com.platform.model.SymptomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SymptomTypeMapper {
    long countByExample(SymptomTypeExample example);

    int deleteByExample(SymptomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SymptomType record);

    int insertSelective(SymptomType record);

    List<SymptomType> selectByExample(SymptomTypeExample example);

    SymptomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SymptomType record, @Param("example") SymptomTypeExample example);

    int updateByExample(@Param("record") SymptomType record, @Param("example") SymptomTypeExample example);

    int updateByPrimaryKeySelective(SymptomType record);

    int updateByPrimaryKey(SymptomType record);
}