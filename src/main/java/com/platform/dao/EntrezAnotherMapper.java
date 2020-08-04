package com.platform.dao;

import com.platform.model.EntrezAnother;
import com.platform.model.EntrezAnotherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntrezAnotherMapper {
    long countByExample(EntrezAnotherExample example);

    int deleteByExample(EntrezAnotherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EntrezAnother record);

    int insertSelective(EntrezAnother record);

    List<EntrezAnother> selectByExample(EntrezAnotherExample example);

    EntrezAnother selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EntrezAnother record, @Param("example") EntrezAnotherExample example);

    int updateByExample(@Param("record") EntrezAnother record, @Param("example") EntrezAnotherExample example);

    int updateByPrimaryKeySelective(EntrezAnother record);

    int updateByPrimaryKey(EntrezAnother record);
}