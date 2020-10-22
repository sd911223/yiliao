package com.platform.dao;

import com.platform.model.LiteratureMaterial;
import com.platform.model.LiteratureMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiteratureMaterialMapper {
    long countByExample(LiteratureMaterialExample example);

    int deleteByExample(LiteratureMaterialExample example);

    int deleteByPrimaryKey(Integer literatureId);

    int insert(LiteratureMaterial record);

    int insertSelective(LiteratureMaterial record);

    List<LiteratureMaterial> selectByExampleWithBLOBs(LiteratureMaterialExample example);

    List<LiteratureMaterial> selectByExample(LiteratureMaterialExample example);

    LiteratureMaterial selectByPrimaryKey(Integer literatureId);

    int updateByExampleSelective(@Param("record") LiteratureMaterial record, @Param("example") LiteratureMaterialExample example);

    int updateByExampleWithBLOBs(@Param("record") LiteratureMaterial record, @Param("example") LiteratureMaterialExample example);

    int updateByExample(@Param("record") LiteratureMaterial record, @Param("example") LiteratureMaterialExample example);

    int updateByPrimaryKeySelective(LiteratureMaterial record);

    int updateByPrimaryKeyWithBLOBs(LiteratureMaterial record);

    int updateByPrimaryKey(LiteratureMaterial record);
}