package com.platform.dao;

import com.platform.model.Literature;
import com.platform.model.LiteratureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiteratureMapper {
    long countByExample(LiteratureExample example);

    int deleteByExample(LiteratureExample example);

    int deleteByPrimaryKey(Integer literatureId);

    int insert(Literature record);

    int insertSelective(Literature record);

    List<Literature> selectByExample(LiteratureExample example);

    Literature selectByPrimaryKey(Integer literatureId);

    int updateByExampleSelective(@Param("record") Literature record, @Param("example") LiteratureExample example);

    int updateByExample(@Param("record") Literature record, @Param("example") LiteratureExample example);

    int updateByPrimaryKeySelective(Literature record);

    int updateByPrimaryKey(Literature record);
}