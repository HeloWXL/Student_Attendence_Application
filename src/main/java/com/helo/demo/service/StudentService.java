package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.helo.demo.mapper.ProfessionMapper;
import com.helo.demo.mapper.StudentMapper;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Student;
import com.helo.demo.utils.Md5Utils;
import com.helo.demo.vo.StudentListVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Select;
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

    @Resource
    private ProfessionMapper professionMapper;

    /**
    * @Description: 根据ID查询学生信息
    * @params: [id]
    * @return: com.helo.demo.model.Student
    * @Author: wangxianlin
    * @Date: 2020/3/5 9:49 AM
    */ 
    public Student getStudentById(int id){
        Student student = studentMapper.selectById(id);
        student.setProfessions(professionMapper.selectByPrimaryKey(student.getProfessionId()));
        return student;
    }

    /**
     * 根据学生的ID删除学生信息
     *
     * @param studentId
     * @return int
     */
    public int deleteByPrimaryKey(List<Integer> studentId) {
        return studentMapper.deleteBatchIds(studentId);
    }

    /**
     * 添加学生
     *
     * @param student
     * @return int
     */
    public int insertSelective(Student student) {
        student.setStudentPassword(Md5Utils.getSaltMD5(student.getStudentPassword()));
        student.setStudentPic("default");
        return studentMapper.insertSelective(student);
    }

    /**
     * 根据学生的ID查询学生信息
     *
     * @param sno
     * @return Student
     */
    public Student selectByPrimaryKey(String sno) {
        return studentMapper.selectByPrimaryKey(sno);
    }

    /**
     * 修改学生信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Student record) {
        record.setStudentPassword(Md5Utils.getSaltMD5(record.getStudentPassword()));
        return studentMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 学生列表获取分页显示
     *
     * @param page
     * @param limit
     * @return
     */
    public Map<String, Object> getStudentList(String sno, String name, int page, int limit) {
        EntityWrapper entityWrapper = new EntityWrapper();
        //判断学号是否为空
        if(StringUtils.isNotBlank(sno)){
            entityWrapper.like("student_sno", sno);
        }
        //判断姓名是否为空
        if(StringUtils.isNotBlank(name)){
            entityWrapper.like("student_name", name);
        }
        int count = studentMapper.selectCount(entityWrapper);
        Map<String, Object> map = new HashMap<>();
        Page<Student> studentPage = new Page<>(page, limit);
        map.put("data", studentMapper.selectPage(studentPage, entityWrapper));
        map.put("count", count);
        map.put("msg", "");
        map.put("code", 0);
        return map;
    }

    /**
     * 根据学号查询学生信息 用于登录判断密码是否相一致
     *
     * @param sno
     * @return
     */
    public Student selectBySno(String sno) {
        return studentMapper.selectBySno(sno);
    }

    /**
     * 根据学生的序号查询学生课程
     *
     * @param sno
     * @return
     */
    public List<Student> selectCourseBySno(String sno) {
        return studentMapper.selectCourseBySno(sno);
    }

    /**
    * @Description: 根据课程的ID查询所教授的学生
    * @Author: wangxianlin
    * @Date: 2020/2/27 3:39 AM
    */ 
    public List<Student> selectStudentByCid(Integer cid){
        return studentMapper.selectStudentByCid(cid);
    }
    /**
     * @Description: 根据学号查询学生的图片
     * @params: [cid]
     * @return: java.util.List<com.helo.demo.model.Student>
     * @Author: wangxianlin
     * @Date: 2020/3/5 2:36 AM
     */
    public String getPicBySid(Integer sid){
        return studentMapper.getPicBySid(sid);
    }

    /**
    * @Description: 根据ID修改学生头像
    * @params: [sid, url]
    * @return: java.lang.String
    * @Author: wangxianlin
    * @Date: 2020/3/5 2:59 AM
    */ 
    public int upadtePicBySid(Integer sid,String url){
        return studentMapper.upadtePicBySid(sid,url);
    }
}
