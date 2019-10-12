package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Profession;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专业Mapper
 * @author xiayj
 * @since
 */
@Mapper
public interface ProfessionMapper extends BaseMapper<Profession> {
    /**
     * 根据专业的ID删除专业信息
     * @param professionId
     * @return
     */
    int deleteByPrimaryKey(Integer professionId);

    /**
     * 添加专业信息
     * @param record
     * @return
     */
    int insertSelective(Profession record);

    /**
     * 根据专业的ID查询专业信息
     * @param professionId
     * @return
     */
    Profession selectByPrimaryKey(Integer professionId);

    /**
     * 修改专业信息
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Profession record);

}