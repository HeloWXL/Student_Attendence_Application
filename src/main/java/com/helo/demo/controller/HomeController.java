package com.helo.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiayj
 * @ClassName HomeController
 * @Description
 * @date 2019/8/21 16:41
 */
@Api(tags = "界面跳转接口")
@Controller
public class HomeController {

  /**
   * 学生
   *
   */
  @ApiOperation(value = "跳转到学生登录界面")
  @GetMapping("/toLogin")
  public String toLogin(){
    return "/student/login.html";
  }

  @ApiOperation(value = "跳转到学生首页界面")
  @GetMapping("/index")
  public String toIndex(){
    return "/student/index.html";
  }

  @ApiOperation(value = "跳转到学生信息界面")
  @GetMapping("/person")
  public String toPerson(){
    return "/student/person.html";
  }

  @ApiOperation(value = "跳转到学生请假界面")
  @GetMapping("/leave")
  public String toLeave(){
    return "/student/leave.html";
  }

  @ApiOperation(value = "跳转到请假详情界面")
  @GetMapping("/leaveDetail")
  public String toLeaveDetail(){
    return "/student/leavedetail.html";
  }

  @ApiOperation(value = "跳转到我的请假记录界面")
  @GetMapping("/leaveList")
  public String toLeaveList(){
    return "/student/leavelist.html";
  }


/**
 *  辅导员
 */

  @ApiOperation(value = "跳转到辅导员登录界面")
  @GetMapping("/toCounseLogin")
  public String toCounseLogin(){
    return "/counselor/login.html";
  }

  @ApiOperation(value = "跳转到辅导员首页界面")
  @GetMapping("/toCounselorIndex")
  public String toCounseIndex(){
    return "/counselor/index.html";
  }

  @ApiOperation(value = "跳转到学生列表界面")
  @GetMapping("/toStudentTable")
  public String toStudentTble(){
    return "/counselor/studentTable.html";
  }

  @ApiOperation(value = "跳转到教师列表界面")
  @GetMapping("/toTeacherTable")
  public String toTeacherTable(){
    return "/counselor/teacherTable.html";
  }

  @ApiOperation(value = "跳转到请假列表界面")
  @GetMapping("/toLeaveTable")
  public String toLeaveTable(){
    return "/counselor/leaveTable.html";
  }

  @ApiOperation(value = "跳转到添加学生界面")
  @GetMapping("/insertStudent")
  public String insertStudent(){
    return "/counselor/insertStu.html";
  }

  @ApiOperation(value = "跳转到添加教师界面")
  @GetMapping("/insertStudentTea")
  public String insertStudentTea(){
    return "/counselor/insertTea.html";
  }

  /**
   * 教师
   *
   */

  @ApiOperation(value = "跳转到添加教师登录")
  @GetMapping("/toTeaLogin")
  public String toTeaLogin(){
    return "/teacher/login.html";
  }

  @ApiOperation(value = "跳转到教师首页界面")
  @GetMapping("/toTeaindex")
  public String toTeaindex(){
    return "/teacher/index.html";
  }

  @ApiOperation(value = "跳转到教师信息界面")
  @GetMapping("/toTeaPerson")
  public String toTeaPerson(){
    return "/teacher/person.html";
  }

  @ApiOperation(value = "跳转到添加课程页面")
  @GetMapping("/addCourse")
  public String addCourse(){
    return "/teacher/addcourse.html";
  }

  @ApiOperation(value = "跳转到课程列表界面--教师端")
  @GetMapping("/courseList")
  public String courseList(){
    return "/teacher/courselist.html";
  }

  /**
   * 测试
   *
   */
  @ApiOperation(value = "测试界面")
  @GetMapping("/uploads")
  public String uploads(){
    return "/test/upload.html";
  }
}
