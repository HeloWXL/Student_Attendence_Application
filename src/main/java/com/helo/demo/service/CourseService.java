package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.CourseMapper;
import com.helo.demo.model.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiayj
 * @ClassName CourseService
 * @Description
 * @date 2019/8/21 0:02
 */
@Service
public class CourseService {
  @Resource
  private CourseMapper courseMapper;

  /**
   * 根据ID删除课程信息
   * @param courseId
   * @return int
   */
  public int deleteByPrimaryKey(Integer courseId){
    return courseMapper.deleteByPrimaryKey(courseId);
  }

  /**
   * 添加课程信息
   * @param record
   * @return int
   */
  public int insertSelective(Course record){

    return courseMapper.insertSelective(record);
  }

  /**
   * 根据ID查询课程信息
   * @param courseId
   * @return Course
   */
  public Course selectByPrimaryKey(Integer courseId){
    return courseMapper.selectByPrimaryKey(courseId);
  }

  /**
   * 更新课程信息
   * @param record
   * @return int
   */
  public  int updateByPrimaryKeySelective(Course record){
    return courseMapper.updateByPrimaryKeySelective(record);
  }



  /**
   * 获取课程列表分页
   * @param pageNo
   * @param pageSize
   * @return
   */
  public Map<String, Object> getCourseByPage(Integer pageNo, Integer pageSize) {
    Map<String,Object> data = new HashMap<>();
    EntityWrapper entityWrapper = new EntityWrapper();
    data.put("pageNo",(pageNo - 1) * pageSize);
    data.put("pageSize",pageSize);
//    List<Course> courses = courseMapper.selectCourseByPage(data);
    int count = courseMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
//    map.put("list",courses);
    map.put("count",count);
    return map;
  }


  /**
   * 根据教师的ID查询 课程列表
   * @param pageNo
   * @param pageSize
   * @param
   * @return
   */
  public Map<String, Object> selectCourseByTno(Integer pageNo, Integer pageSize,Integer tid){
    Map<String,Object> data = new HashMap<>();
    EntityWrapper entityWrapper = new EntityWrapper();
    entityWrapper.eq("teacher_id",tid);
    data.put("pageNo",(pageNo - 1) * pageSize);
    data.put("pageSize",pageSize);
    data.put("teacherId",tid);
    List<Course> courses = courseMapper.selectCourseByTno(data);
    int count = courseMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
    map.put("list",courses);
    map.put("count",count);
    return map;
  }

  /**
   * 根据ID查询课程的详细信息
   * @param cid
   * @return
   */
  public Course selectCourseDetailByCid(Integer cid){
    return courseMapper.selectCourseDetailByCid(cid);
  }

}
