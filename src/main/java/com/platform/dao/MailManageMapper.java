package com.platform.dao;

import com.platform.model.MailManage;
import com.platform.model.MailManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailManageMapper {
    long countByExample(MailManageExample example);

    int deleteByExample(MailManageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MailManage record);

    int insertSelective(MailManage record);

    List<MailManage> selectByExampleWithBLOBs(MailManageExample example);

    List<MailManage> selectByExample(MailManageExample example);

    MailManage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MailManage record, @Param("example") MailManageExample example);

    int updateByExampleWithBLOBs(@Param("record") MailManage record, @Param("example") MailManageExample example);

    int updateByExample(@Param("record") MailManage record, @Param("example") MailManageExample example);

    int updateByPrimaryKeySelective(MailManage record);

    int updateByPrimaryKeyWithBLOBs(MailManage record);

    int updateByPrimaryKey(MailManage record);
}