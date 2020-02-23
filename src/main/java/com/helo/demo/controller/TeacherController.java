package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Teacher;
import com.helo.demo.service.CourseService;
import com.helo.demo.service.ProfessionService;
import com.helo.demo.service.TeacherService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Slf4j
public class TeacherController {

  @Resource
  private ProfessionService professionService;
  @Resource
  private TeacherService teacherService;
  @Resource
  private CourseService courseService;

  @ApiOperation(value = "跳转到教师信息列表")
  @GetMapping("/toCounselorTeacherTable")
  public String toTeacherList(){
    return "counselor/teacher";
  }

  @ApiOperation(value = "跳转到教师登录页面")
  @GetMapping("/toLogin")
  public String toLogin(){
    return "/teacher/login";
  }

  @ApiOperation(value = "跳转到教师首页页面")
  @GetMapping("/toIndex")
  public String toIndex(){
    return "/teacher/index";
  }

  @ApiOperation(value = "跳转到教师个人信息页面")
  @GetMapping("/toPerson")
  public String toPerson(){
    return "/teacher/person";
  }

  @ApiOperation(value = "跳转到教师添加课程页面")
  @GetMapping("/toCourse")
  public String toCourse(){
    return "/teacher/course";
  }

  @ApiOperation(value = "跳转到课程列表页面")
  @GetMapping("/toCourseList")
  public String toCourseList(){
    return "/teacher/courseList";
  }

  @ApiOperation(value = "跳转到请假记录页面")
  @GetMapping("/toLeaveList")
  public String toLeaveList(){
    return "/teacher/leaveList";
  }

  @ApiOperation(value = "根据ID查询课程的详细信息")
  @GetMapping("/selectCourseDetailByCid/{cid}")
  public String selectCourseDetailByCid(@PathVariable("cid") Integer cid, Model model){
    model.addAttribute("course",courseService.selectCourseDetailByCid(cid));
    return "teacher/courseDetail";
  }

  @ApiOperation(value = "教师登录")
  @PostMapping("/checkLogin")
  @ResponseBody
  public DataResult<Boolean> checkLogin(@RequestParam("tno") String tno, @RequestParam("password") String password, HttpServletRequest request) {
    DataResult<Boolean> result = new DataResult<>();
    Teacher teacher = teacherService.selectByTno(tno);
    if(teacher==null){
      result.setBody(false);
    }else{
      if (Md5Utils.getSaltverifyMD5(password, teacher.getTeacherPassword())) {
        log.info(teacher.getTeacherName()+"已登陆");
        //根据专业的ID获取教师的专业相关信息
        Profession profession = professionService.selectByPrimaryKey(teacher.getProfessionId());
        teacher.setProfession(profession);
        result.setBody(true);
        request.getSession().setAttribute("teacher", teacher);
      } else {
        request.getSession().setAttribute("teacher", null);
        result.setBody(false);
      }
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
    Teacher teacher = (Teacher)request.getSession().getAttribute("teacher");
    request.getSession().removeAttribute("teacher");
    if ( request.getSession().getAttribute("teacher") == null) {
      log.info(teacher.getTeacherName()+"已下线，即将跳转到登录页面。");
      try {
        response.sendRedirect("/helo/teacherApi/toLogin");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }



}
