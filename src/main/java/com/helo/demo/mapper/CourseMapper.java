package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Course;
import org.apache.ibatis.annotations.Mapper;

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
     *
     * @param courseId
     * @return
     */
    int deleteByPrimaryKey(Integer courseId);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(Course record);

    /**
     *
     * @param courseId
     * @return
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     *
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
}