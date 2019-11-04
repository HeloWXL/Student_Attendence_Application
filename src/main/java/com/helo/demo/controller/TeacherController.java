package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Teacher;
import com.helo.demo.service.ProfessionService;
import com.helo.demo.service.TeacherService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName TeacherController
 * @Description
 * @date 2019/8/21 0:08
 */
@Api(tags = "教师接口")
@Controller
@RequestMapping("/teacherApi")
public class TeacherController {

  @Resource
  private ProfessionService professionService;
  @Resource
  private TeacherService teacherService;


  @ApiOperation(value = "跳转到教师信息列表")
  @GetMapping("/toCounselorTeacherTable")
  public String toTeacherList(){
    return "counselor/teacher";
  }


  @ApiOperation(value = "教师登录")
  @PostMapping("/checkLogin")
  @ResponseBody
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
  @ResponseBody
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
  @GetMapping("/deleteByTeacherId")
  @ResponseBody
  public DataResult<Integer> deleteByTeacherId(@RequestParam("id") Integer id) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.deleteByPrimaryKey(id));
    return result;
  }


  @ApiOperation(value = "添加教师信息")
  @PostMapping("/insertTeacher")
  @ResponseBody
  public DataResult<Integer> insertTeacher(@RequestBody Teacher teacher) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.insertSelective(teacher));
    return result;
  }

  @ApiOperation(value = "根据tno查询教师信息")
  @GetMapping("/selectByTeacherId/{tno}")
  @ResponseBody
  public DataResult<Teacher> selectByTeacherId(@PathVariable("tno") String tno) {
    DataResult<Teacher> result = new DataResult<>();
    result.setBody(teacherService.selectByPrimaryKey(tno));
    return result;
  }

  @ApiOperation(value = "修改教师信息")
  @PostMapping("/updateStudent")
  @ResponseBody
  public DataResult<Integer> updateStudent(@RequestBody Teacher teacher) {
    DataResult<Integer> result = new DataResult<>();
    result.setBody(teacherService.updateByPrimaryKeySelective(teacher));
    return result;
  }

  @ApiOperation(value = "查询教师信息-分页显示")
  @GetMapping("/selectTeacherByPage")
  @ResponseBody
  public Map<String,Object> selectTeacherByPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return teacherService.getTeacherByPage(page,limit);
  }

  @ApiOperation(value = "清除教师的session对象")
  @GetMapping("/removeTeacherSession")
  @ResponseBody
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
