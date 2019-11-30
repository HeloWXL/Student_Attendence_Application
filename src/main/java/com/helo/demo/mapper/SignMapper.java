package com.helo.demo.mapper;

import com.helo.demo.model.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(Integer signId);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer signId);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}