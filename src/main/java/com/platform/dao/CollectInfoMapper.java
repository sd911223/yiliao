package com.platform.dao;

import com.platform.model.CollectInfo;
import com.platform.model.CollectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectInfoMapper {
    long countByExample(CollectInfoExample example);

    int deleteByExample(CollectInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollectInfo record);

    int insertSelective(CollectInfo record);

    List<CollectInfo> selectByExample(CollectInfoExample example);

    CollectInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollectInfo record, @Param("example") CollectInfoExample example);

    int updateByExample(@Param("record") CollectInfo record, @Param("example") CollectInfoExample example);

    int updateByPrimaryKeySelective(CollectInfo record);

    int updateByPrimaryKey(CollectInfo record);
}