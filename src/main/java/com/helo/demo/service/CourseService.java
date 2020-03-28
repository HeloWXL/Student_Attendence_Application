package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.CourseMapper;
import com.helo.demo.mapper.ProfessionMapper;
import com.helo.demo.mapper.TeacherMapper;
import com.helo.demo.model.Course;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Student;
import com.helo.demo.model.Teacher;
import com.helo.demo.utils.CommonUtil;
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
  @Resource
  private  ProfessionMapper professionMapper;
  @Resource
  private TeacherMapper teacherMapper;


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
  * @Date: 2020/2/14 9:10 PM
  */ 
  public List<Student> selectStudentByCid(Integer cid){
      return courseMapper.selectStudentByCid(cid);
  }


  /**
  * @Description: 根据专业的ID查询课程列表
  * @Date: 2020/2/24 1:10 PM
  */ 
  public List<Course> getCourseNameByProsessionId(int prosessioonId){
    return courseMapper.getCourseNameByProsessionId(prosessioonId);
  }

  /**
  * @Description: 根据教师的ID查询课程
  * @params: [tid]
  * @return: java.util.List<com.helo.demo.model.Course>
  * @Date: 2020/3/6 1:24 AM
  */ 
  public List<Course> getCourseByTid(int tid){
    return courseMapper.selectList(new EntityWrapper<Course>().eq("teacher_id",tid));
  }

  /**
  * @Description: 批量导入课程
  * @params: [listContent]
  * @return: void
  * @Date: 2020/3/28 10:32 PM
  */
  public void importCourse(List<List<String>> listContent) {
    for (int i = 0; i < listContent.size(); i++) {
      Course course = new Course();
      //课程名称
      String courseName = listContent.get(i).get(0);
      course.setCourseName(courseName);
      //课程安排
      String classArrangeMent = listContent.get(i).get(1);
      switch (classArrangeMent){
        case "星期一":
          course.setClassarrangement(1);
          break;
        case "星期二":
            course.setClassarrangement(2);
        case "星期三":
          course.setClassarrangement(3);
          break;
        case "星期四":
          course.setClassarrangement(4);
          break;
        case "星期五":
          course.setClassarrangement(5);
          break;
        default: break;
      }

      course.setStarttime(CommonUtil.parse(listContent.get(i).get(2),"yyyy-mm-dd"));
      course.setEndtime(CommonUtil.parse(listContent.get(i).get(3),"yyyy-mm-dd"));

      //专业
      String profession = listContent.get(i).get(4);
      int proId = this.professionMapper.getProfessionByName(profession);
      if(proId==0){
        this.professionMapper.insertSelective(new Profession(profession,"",""));
      }else{
        course.setProfession(proId);
      }

      //教师
      String teacherName = listContent.get(i).get(5);
      int tId = this.teacherMapper.getTeacherIdByName(teacherName);
      if(tId==0){
        this.teacherMapper.insertSelective(new Teacher(teacherName,proId));
      }else{
        course.setTeacherId(tId);
      }
      courseMapper.insertSelective(course);
    }

  }

}
