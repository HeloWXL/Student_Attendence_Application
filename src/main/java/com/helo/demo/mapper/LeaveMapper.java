package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 * @since
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(Leave record);

    /**
     * 根据ID查询请假信息
     * @param leaveId
     * @return
     */
    Leave selectByPrimaryKey(Integer leaveId);



    /**
     * 根据学生的学号查询 -分页
     * @param map
     * @return
     */
    List<Leave> selectLeaveAndStudentByPage(Map<String, Object> map);


    /**
     * 请假列表-分页
     * @param map
     * @return
     */
    List<Leave> selectByPage(Map<String, Object> map);


    /**
    * @Description: 根据教师的ID查询学生的请假信息
    * @Author: wangxianlin
    * @Date: 2020/2/6 2:27 PM
    */
    @Select("select l.leave_id,l.student_sno,s.student_name, l.leave_title,l.is_read,l.start_time,l.end_time \n" +
            "from leaves l , teacher t ,course c ,student s\n" +
            "where l.course_id = c.course_id and t.teacher_id = c.teacher_id and l.student_sno = s.student_sno and t.teacher_id = #{tId} order by l.is_read desc")
    List<Leave> getLeaveByTeacher(@Param("tId") Integer tId);

}