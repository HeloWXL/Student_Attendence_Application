package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.helo.demo.mapper.StudentMapper;
import com.helo.demo.model.Student;
import com.helo.demo.utils.Md5Utils;
import com.helo.demo.vo.StudentListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName StudentService
 * @Description
 * @date 2019/8/21 0:03
 */
@Service
public class StudentService {

  @Resource
  private StudentMapper studentMapper;
  /**
   *根据学生的ID删除学生信息
   * @param studentId
   * @return int
   */
  public int deleteByPrimaryKey(Integer studentId){
    return studentMapper.deleteByPrimaryKey(studentId);
  }

  /**
   * 添加学生
   * @param student
   * @return int
   */
  public int insertSelective(Student student){
    student.setStudentPassword(Md5Utils.getSaltMD5(student.getStudentPassword()));
    return studentMapper.insertSelective(student);
  }

  /**
   * 根据学生的ID查询学生信息
   * @param sno
   * @return  Student
   */
  public Student selectByPrimaryKey(String sno){
    return studentMapper.selectByPrimaryKey(sno);
  }

  /**
   * 修改学生信息
   * @param record
   * @return int
   */
  public int updateByPrimaryKeySelective(Student record){
    return studentMapper.updateByPrimaryKeySelective(record);
  }


  /**
   * 学生列表获取分页显示
   * @param page
   * @param limit
   * @return
   */
  public Map<String, Object> getStudentList(int page, int limit){
    EntityWrapper entityWrapper = new EntityWrapper();
    int count = studentMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
    map.put("data",studentMapper.getStudentList((page-1)*limit,limit));
    map.put("count",count);
    map.put("msg","");
    map.put("code",0);
    return map;
  }

  /**
   * 根据学号查询学生信息 用于登录判断密码是否相一致
   * @param sno
   * @return
   */
  public Student selectBySno(String sno){
    return studentMapper.selectBySno(sno);
  }

  /**
   * 根据学生的序号查询学生课程
   * @param sno
   * @return
   */
  public  List<Student> selectCourseBySno(String sno){
    return studentMapper.selectCourseBySno(sno);
  }
}
