package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.TeacherMapper;
import com.helo.demo.model.Teacher;
import com.helo.demo.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName TeacherService
 * @Description
 * @date 2019/8/21 0:03
 */
@Service
public class TeacherService {

  @Resource
  private TeacherMapper teacherMapper;


  /**
   *根据教师ID删除教师信息
   * @param teacherId
   * @return
   */
  public int deleteByPrimaryKey(Integer teacherId){
    return teacherMapper.deleteByPrimaryKey(teacherId);
  }

  /**
   * 添加教师信息
   * @param record
   * @return
   */
  public int insertSelective(Teacher record){
    record.setTeacherPassword(Md5Utils.getSaltMD5(record.getTeacherPassword()));
    return teacherMapper.insertSelective(record);
  }


  /**
   * 根据教师ID查询教师信息
   * @param tno
   * @return
   */
  public Teacher selectByPrimaryKey(String tno){
    return teacherMapper.selectByPrimaryKey(tno);
  }

  /**
   * 修改教师信息
   * @param record
   * @return
   */
  public int updateByPrimaryKeySelective(Teacher record){
    return teacherMapper.updateByPrimaryKeySelective(record);
  }





  /**
   * 获取教师列表分页
   * @param pageNo
   * @param pageSize
   * @return
   */
    public Map<String, Object> getTeacherByPage(Integer pageNo, Integer pageSize){
      Map<String, Object> data = new HashMap();
      Map<String, Object> map = new HashMap();

      EntityWrapper entityWrapper = new EntityWrapper();
      data.put("pageNo", (pageNo - 1) * pageSize);
      data.put("pageSize", pageSize);
      List<Teacher> teacherList = teacherMapper.selectByPage(data);
      map.put("list",teacherList);
      int count = teacherMapper.selectCount(entityWrapper);
      map.put("count",count);
      return map;
    }

  /**
   * 根据学号查询辅导员信息 用于登录判断密码是否相一致
   * @param tno
   * @return
   */
  public Teacher selectByTno(String tno){
    return teacherMapper.selectByTno(tno);
  }


}
