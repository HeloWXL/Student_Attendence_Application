package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.model.Student;
import com.helo.demo.service.ProfessionService;
import com.helo.demo.service.StudentService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiayj
 * @ClassName StudentController
 * @Description
 * @date 2019/8/21 0:08
 */
@Api(tags = "学生接口")
@Controller
@RequestMapping("studentApi")
public class StudentController {

  @Resource
  private StudentService studentService;
  @Resource
  private ProfessionService professionService;


  @ApiOperation(value = "跳转到学生登录界面")
  @GetMapping("/toLogin")
  public String toLogin(){
    return "student/login";
  }

  @ApiOperation(value = "跳转到学生首页界面")
  @GetMapping("/index")
  public String toIndex(){
    return "student/index";
  }

  @ApiOperation(value = "跳转到学生个人信息界面")
  @GetMapping("/myInfo")
  public String myInfo(){
    return "student/myinfo";
  }

  @ApiOperation(value = "跳转到学生个人信息界面")
  @GetMapping("/askForLeave")
  public String askForLeave(){
    return "student/askForLeave";
  }

  @ApiOperation(value = "根据Sno查询学生信息")
  @GetMapping("/selectByStudentId/{sno}")
  @ResponseBody
  public DataResult<Student> selectByStudentId(@PathVariable("sno") String sno){
    DataResult<Student> result = new DataResult<>();
    result.setBody(studentService.selectByPrimaryKey(sno));
    return result;
  }

  @ApiOperation(value = "根据ID删除学生信息")
  @GetMapping("/deleteByStudentId}")
  @ResponseBody
  public DataResult<Integer> deleteByStudentId(@RequestParam("id") Integer id){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(studentService.deleteByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "添加学生信息")
  @PostMapping("/insertStudent")
  @ResponseBody
  public DataResult<Integer> insertStudent(@RequestBody Student student){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(studentService.insertSelective(student));
    return result;
  }

  @ApiOperation(value = "修改学生信息")
  @PostMapping("/updateStudent")
  @ResponseBody
  public DataResult<Integer> updateStudent(@RequestBody Student student){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(studentService.updateByPrimaryKeySelective(student));
    return result;
  }

  @ApiOperation(value = "学生登录")
  @PostMapping("/checkLogin")
  @ResponseBody
  public DataResult<Boolean> checkLogin(@RequestParam("sno") String sno, @RequestParam("password") String password, HttpServletRequest request){
      Student student = studentService.selectBySno(sno);

      DataResult<Boolean> result = new DataResult<>();
    if (Md5Utils.getSaltverifyMD5(password,student.getStudentPassword())) {
      //根据学号的ID获取学生的专业相关信息
      Profession profession = professionService.selectByPrimaryKey(student.getProfessionId());
      student.setProfession(profession);
      result.setBody(true);
      request.getSession().setAttribute("studentsession",student);
    } else {
      request.getSession().setAttribute("studentsession",null);
      result.setBody(false);
    }
    return result;
  }


  @ApiOperation(value = "获取学生的session对象")
  @PostMapping("/getStudentSession")
  @ResponseBody
  public DataResult<Student> getStudentSession(HttpServletRequest request, @RequestParam("studentBean") String studentBean){
    DataResult<Student> result = new DataResult<>();
    Student student = (Student) request.getSession().getAttribute(studentBean);
    if (student == null) {
      result.setBody(null);
      return result;
    } else {
      result.setBody(student);
      return result;
    }
  }

  @ApiOperation(value = "查询学生信息-分页显示")
  @GetMapping("/selectStudentByPage")
  @ResponseBody
  public Map<String,Object> selectStudentByPage(@RequestParam("page")Integer page , @RequestParam("limit") int limit){
    return studentService.getStudentList(page,limit);
  }

  @ApiOperation(value = "清除学生的session对象")
  @GetMapping("/removeStudentSession")
  @ResponseBody
  public void removeStudentSession(HttpServletRequest request, HttpServletResponse response){
    request.getSession().removeAttribute("studentsession");
    if ( request.getSession().getAttribute("studentsession") == null) {
      try {
        response.sendRedirect("/toLogin");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @ApiOperation(value = "根据学生的学号查询学生课程")
  @GetMapping("/selectCourseBySno")
  @ResponseBody
  public  DataResult<List<Student>> selectCourseBySno(@RequestParam("sno") String sno){
    DataResult<List<Student>> result = new DataResult<>();
    result.setBody(studentService.selectCourseBySno(sno));
    return result;
  }


}
