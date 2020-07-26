package com.platform.dao;

import com.platform.model.VariationMessage;
import com.platform.model.VariationMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VariationMessageMapper {
    long countByExample(VariationMessageExample example);

    int deleteByExample(VariationMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VariationMessage record);

    int insertSelective(VariationMessage record);

    List<VariationMessage> selectByExample(VariationMessageExample example);

    VariationMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VariationMessage record, @Param("example") VariationMessageExample example);

    int updateByExample(@Param("record") VariationMessage record, @Param("example") VariationMessageExample example);

    int updateByPrimaryKeySelective(VariationMessage record);

    int updateByPrimaryKey(VariationMessage record);
}