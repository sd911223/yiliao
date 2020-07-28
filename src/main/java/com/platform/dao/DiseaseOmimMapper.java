package com.platform.dao;

import com.platform.model.DiseaseOmim;
import com.platform.model.DiseaseOmimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiseaseOmimMapper {
    long countByExample(DiseaseOmimExample example);

    int deleteByExample(DiseaseOmimExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DiseaseOmim record);

    int insertSelective(DiseaseOmim record);

    List<DiseaseOmim> selectByExample(DiseaseOmimExample example);

    DiseaseOmim selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DiseaseOmim record, @Param("example") DiseaseOmimExample example);

    int updateByExample(@Param("record") DiseaseOmim record, @Param("example") DiseaseOmimExample example);

    int updateByPrimaryKeySelective(DiseaseOmim record);

    int updateByPrimaryKey(DiseaseOmim record);
}