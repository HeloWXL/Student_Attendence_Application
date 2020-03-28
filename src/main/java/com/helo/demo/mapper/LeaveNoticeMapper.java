package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.LeaveNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (LeaveNotice)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-28 17:15:38
 */
@Mapper
public interface LeaveNoticeMapper extends BaseMapper<LeaveNotice> {

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

    
    /**
    * @Description: 根据学生的ID查询请假未读的通知
    * @params: [stuId]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/3/28 6:31 PM
    */ 
    @Select("select count(1) from leave_notice where state = 0 and student_id = #{stuId}")
    int getNoRead(int stuId);

    @Select("select * from leave_notice where student_id = #{stuId} order By state ")
    List<LeaveNotice> getLeaveNoticeByStuId(int stuId);
}