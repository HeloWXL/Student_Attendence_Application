package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-19 10:16:36
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}