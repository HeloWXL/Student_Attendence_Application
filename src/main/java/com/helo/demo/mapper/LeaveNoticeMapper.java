package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.LeaveNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 以下为自定义SQL
     */

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

    @Update("update leave_notice set state = 1 where id = #{id}")
    int updateNoticeByid(int id);

}