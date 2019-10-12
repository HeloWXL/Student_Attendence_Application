package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Sign;
import org.apache.ibatis.annotations.Mapper;

/**
 * 签到Mapper
 * @author xiayj
 * @since
 */
@Mapper
public interface SignMapper extends BaseMapper<Sign> {

    /**
     * 添加一条签到信息
     * @param record
     * @return
     */
    int insertSelective(Sign record);

    /**
     * 根据签到ID查询签到信息
     * @param signId
     * @return
     */
    Sign selectByPrimaryKey(Integer signId);

}