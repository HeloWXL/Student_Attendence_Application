package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Teacher;
import com.helo.demo.service.ProfessionService;
import com.helo.demo.service.TeacherService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author xiayj
 * @ClassName TeacherController
 * @Description
 * @date 2019/8/21 0:08
 */
@Api(tags = "教师接口")
@RestController
@RequestMapping("/teacherApi")
public class TeacherController {

  @Resource
  private ProfessionService professionService;
  @Resource
  private TeacherService teacherService;

  @ApiOperation(value = "教师登录")
  @PostMapping("/checkLogin")
  public DataResult<Boolean> checkLogin(@RequestParam("tno") String tno, @RequestParam("password") String password, HttpServletRequest request) {
    Teacher teacher = teacherService.selectByTno(tno);
    DataResult<Boolean> result = new DataResult<>();
    if (Md5Utils.getSaltverifyMD5(password, teacher.getTeacherPassword())) {
      //根据专业的ID获取教师的专业相关信息
      Profession profession = professionService.selectByPrimaryKey(teacher.getProfessionId());
      teacher.setProfession(profession);
      result.setBody(true);
      request.getSession().setAttribute("teachersession", teacher);
    } else {
      request.getSession().setAttribute("teachersession", null);
      result.setBody(false);
    }
    return result;
  }

  @ApiOperation(value = "获取教师的session对象")
  @PostMapping("/getTeacherSession")
  public DataResult<Teacher> getTeacherSession(HttpServletRequest request, @RequestParam("teacherBean") String teacherBean){
    DataResult<Teacher> result = new DataResult<>();
    Teacher teacher = (Teacher) request.getSession().getAttribute(teacherBean);
    if (teacher == null) {
      result.setBody(null);
      return result;
    } else {
      result.setBody(teacher);
      return result;
    }
  }

  @ApiOperation(value = "根据ID删除教师信息")
  @GetMapping("/deleteByTeacherId/{id}")
  public DataResult<Integer> deleteByTeacherId(@RequestParam("id") Integer id) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.deleteByPrimaryKey(id));
    return result;
  }


  @ApiOperation(value = "添加教师信息")
  @PostMapping("/insertTeacher")
  public DataResult<Integer> insertTeacher(@RequestBody Teacher teacher) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.insertSelective(teacher));
    return result;
  }

  @ApiOperation(value = "根据tno查询教师信息")
  @GetMapping("/selectByTeacherId/{tno}")
  public DataResult<Teacher> selectByTeacherId(@PathVariable("tno") String tno) {
    DataResult<Teacher> result = new DataResult<>();
    result.setBody(teacherService.selectByPrimaryKey(tno));
    return result;
  }

  @ApiOperation(value = "修改教师信息")
  @PostMapping("/updateStudent")
  public DataResult<Integer> updateStudent(@RequestBody Teacher teacher) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.updateByPrimaryKeySelective(teacher));
    return result;
  }

  @ApiOperation(value = "查询教师信息-分页显示")
  @GetMapping("/selectTeacherByPage")
  public DataResult<Map<String,Object>> selectTeacherByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
    DataResult<Map<String,Object>> result = new DataResult<>();
    result.setBody(teacherService.getTeacherByPage(pageNo,pageSize));
    return result;
  }

  @ApiOperation(value = "清除教师的session对象")
  @GetMapping("/removeTeacherSession")
  public void removeTeacherSession(HttpServletRequest request, HttpServletResponse response){
    request.getSession().removeAttribute("teachersession");
    if ( request.getSession().getAttribute("teachersession") == null) {
      try {
        response.sendRedirect("/toTeaLogin");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }



}
