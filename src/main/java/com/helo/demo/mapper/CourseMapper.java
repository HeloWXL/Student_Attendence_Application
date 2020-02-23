package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Course;
import com.helo.demo.model.Student;
import com.helo.demo.vo.CourseVo;
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
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 删除课程
     * @param courseId
     * @return
     */
    int deleteByPrimaryKey(Integer courseId);

    /**
     * 添加课程
     * @param record
     * @return
     */
    int insertSelective(Course record);

    /**
     * 根据ID查询课程信息
     * @param courseId
     * @return
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     * 修改课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 根据教师的Tno查询教师的课程
     * @param map
     * @return
     */
    List<Course> selectCourseByTno(Map<String, Object> map);

    /**
     *  根据ID查询课程的详细信息
     * @param cid
     * @return
     */
    Course selectCourseDetailByCid(Integer cid);


    /**
     * 分页查询课程信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Select("select * from course c , teacher t , profession p where c.teacher_id = t.teacher_id and t.profession_id = p.profession_id limit #{pageNo},#{pageSize}")
    List<CourseVo> getCourseList(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * @Description: 查询上本门课程的所有学生
     * @Author: wangxianlin
     * @Date: 2020/2/14 9:10 PM
     */
    @Select("select * from ")
    List<Student> selectStudentByCid(Integer cid);

}