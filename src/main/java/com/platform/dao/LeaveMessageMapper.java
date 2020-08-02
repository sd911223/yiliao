package com.platform.dao;

import com.platform.model.LeaveMessage;
import com.platform.model.LeaveMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveMessageMapper {
    long countByExample(LeaveMessageExample example);

    int deleteByExample(LeaveMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessage record);

    int insertSelective(LeaveMessage record);

    List<LeaveMessage> selectByExample(LeaveMessageExample example);

    LeaveMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveMessage record, @Param("example") LeaveMessageExample example);

    int updateByExample(@Param("record") LeaveMessage record, @Param("example") LeaveMessageExample example);

    int updateByPrimaryKeySelective(LeaveMessage record);

    int updateByPrimaryKey(LeaveMessage record);
}