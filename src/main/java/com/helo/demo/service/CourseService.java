package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.CourseMapper;
import com.helo.demo.model.Course;
import com.helo.demo.model.Student;
import com.helo.demo.vo.CourseVo;
import org.apache.ibatis.annotations.Param;
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
   * 分页查询课程列表信息
   * @param pageNo
   * @param pageSize
   * @return
   */
  public Map<String, Object> getCourseList(int pageNo, int pageSize){
    Map<String,Object> map = new HashMap<>();
    List<CourseVo> list = courseMapper.getCourseList((pageNo-1)*pageSize,pageSize);
    map.put("data",list);
    map.put("count",courseMapper.selectCount(new EntityWrapper<>()));
    map.put("msg","");
    map.put("code",0);
    return map;
  }



  /**
   * 根据教师的ID查询 课程列表
   * @param
   * @return
   */
  public Map<String, Object> selectCourseByTno(Integer tid){
    Map<String,Object> data = new HashMap<>();
    EntityWrapper entityWrapper = new EntityWrapper();
    entityWrapper.eq("teacher_id",tid);
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

  /**
  * @Description: 查询上本门课程的所有学生
  * @Author: wangxianlin
  * @Date: 2020/2/14 9:10 PM
  */ 
  public List<Student> selectStudentByCid(Integer cid){
      return courseMapper.selectStudentByCid(cid);
  }


  /**
  * @Description: 根据专业的ID查询课程列表
  * @Author: wangxianlin
  * @Date: 2020/2/24 1:10 PM
  */ 
  public List<Course> getCourseNameByProsessionId(int prosessioonId){
    return courseMapper.getCourseNameByProsessionId(prosessioonId);
  }

  /**
  * @Description: 根据教师的ID查询课程
  * @params: [tid]
  * @return: java.util.List<com.helo.demo.model.Course>
  * @Author: wangxianlin
  * @Date: 2020/3/6 1:24 AM
  */ 
  public List<Course> getCourseByTid(int tid){
    return courseMapper.selectList(new EntityWrapper<Course>().eq("teacher_id",tid));
  }
}
