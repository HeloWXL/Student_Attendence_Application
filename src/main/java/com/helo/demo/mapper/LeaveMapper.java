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
    @Select("select leave_id,student_sno, leave_title,is_read,start_time,end_time \n" +
            "from leaves l , teacher t ,course c\n" +
            "where l.course_id = c.course_id and t.teacher_id = c.teacher_id and t.teacher_id = #{tId}")
    List<Leave> getLeaveByTeacher(@Param("tId") Integer tId);

}