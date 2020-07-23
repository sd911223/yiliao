package com.platform.dao;

import com.platform.model.VcfFile;
import com.platform.model.VcfFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VcfFileMapper {
    long countByExample(VcfFileExample example);

    int deleteByExample(VcfFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VcfFile record);

    int insertSelective(VcfFile record);

    List<VcfFile> selectByExampleWithBLOBs(VcfFileExample example);

    List<VcfFile> selectByExample(VcfFileExample example);

    VcfFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VcfFile record, @Param("example") VcfFileExample example);

    int updateByExampleWithBLOBs(@Param("record") VcfFile record, @Param("example") VcfFileExample example);

    int updateByExample(@Param("record") VcfFile record, @Param("example") VcfFileExample example);

    int updateByPrimaryKeySelective(VcfFile record);

    int updateByPrimaryKeyWithBLOBs(VcfFile record);

    int updateByPrimaryKey(VcfFile record);
}