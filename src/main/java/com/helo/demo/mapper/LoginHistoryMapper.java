package com.helo.demo.mapper;

import com.helo.demo.model.LoginHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginHistoryMapper {
    int deleteByPrimaryKey(Integer historyId);

    int insert(LoginHistory record);

    int insertSelective(LoginHistory record);

    LoginHistory selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(LoginHistory record);

}