package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Sign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper extends BaseMapper<Sign> {

    int insertSelective(Sign record);

    int updateByPrimaryKeySelective(Sign record);
}