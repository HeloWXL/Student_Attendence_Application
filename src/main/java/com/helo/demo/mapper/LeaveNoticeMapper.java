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

    /**
    * @Description: 统计教师未读的通知数量
    * @params: [teaId]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:11 PM
    */ 
    @Select("select count(1) from leave_notice where t_state = 0 and teacher_id = #{teaId}")
    int getNoReadByTeaId(int teaId);

    /**
    * @Description: 查询学生未读的通知
    * @params: [stuId]
    * @return: java.util.List<com.helo.demo.model.LeaveNotice>
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:11 PM
    */ 
    @Select("select * from leave_notice where student_id = #{stuId} order By state ")
    List<LeaveNotice> getLeaveNoticeByStuId(int stuId);
    
    /**
    * @Description: 根据教师的ID查询未读的通知
    * @params: [teaId]
    * @return: java.util.List<com.helo.demo.model.LeaveNotice>
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:11 PM
    */ 
    @Select("select l.*,(select student_pic from student s where s.student_id = l.student_id ) student_pic, " +
            "(select student_name from student c where c.student_id = l.student_id ) student_name " +
            "from leave_notice l where course_id in (select c.course_id \n" +
            "from teacher t \n" +
            "left join course c on c.teacher_id = t.teacher_id)\n" +
            "and  l.teacher_id = #{teacherId} order By l.t_state ")
    List<LeaveNotice> getLeaveNoticeByTeaId(int teacherId);

    /**
    * @Description: 学生已读
    * @params: [id]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:12 PM
    */ 
    @Update("update leave_notice set state = 1 where id = #{id}")
    int updateNoticeByid(int id);

    /**
    * @Description: 教师已读
    * @params: [id]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:12 PM
    */ 
    @Update("update leave_notice set t_state = 1 where id = #{id}")
    int updateNoticeByTeaId(int id);

}