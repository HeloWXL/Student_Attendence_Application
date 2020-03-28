package com.helo.demo.mapper;

import com.helo.demo.model.LeaveNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (LeaveNotice)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-28 17:15:38
 */
public interface LeaveNoticeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LeaveNotice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LeaveNotice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param leaveNotice 实例对象
     * @return 对象列表
     */
    List<LeaveNotice> queryAll(LeaveNotice leaveNotice);

    /**
     * 新增数据
     *
     * @param leaveNotice 实例对象
     * @return 影响行数
     */
    int insert(LeaveNotice leaveNotice);

    /**
     * 修改数据
     *
     * @param leaveNotice 实例对象
     * @return 影响行数
     */
    int update(LeaveNotice leaveNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}