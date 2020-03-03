package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Course;
import com.helo.demo.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName CourseController
 * @Description
 * @date 2019/8/21 0:05
 */
@Api(tags = "课程接口")
@Controller
@RequestMapping("/courseApi")
public class CourseController {
  @Resource
  private CourseService courseService;

  @ApiOperation(value = "跳转到课程信息列表")
  @GetMapping("/toCounselorCourseTable")
  public String toCounselorCourseTable(){
    return "counselor/course";
  }

  @ApiOperation(value = "根据ID删除课程信息")
  @GetMapping("/deleteByCourseId/{id}")
  @ResponseBody
  public DataResult<Integer> deleteByCourseId(@PathVariable("id") Integer id){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(courseService.deleteByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "添加课程信息")
  @PostMapping("/insertCourse")
  @ResponseBody
  public DataResult<Integer> insertStudent(@RequestBody Course course){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(courseService.insertSelective(course));
    return result;
  }

  @ApiOperation(value = "修改课程信息")
  @PostMapping("/updateCourse")
  @ResponseBody
  public DataResult<Integer> updateCourse(@RequestBody Course course){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(courseService.updateByPrimaryKeySelective(course));
    return result;
  }

  @ApiOperation(value = "查询课程信息")
  @GetMapping("/selectCourseByPage")
  @ResponseBody
  public Map<String,Object> selectCourseByPage(@RequestParam("page") Integer pageNo,
                                                           @RequestParam("limit") Integer pageSiez){
    return courseService.getCourseList(pageNo,pageSiez);
  }

  @ApiOperation(value = "根据教师工号查询课程列表")
  @GetMapping("/selectCourseListByTno")
  @ResponseBody
  public DataResult<Map<String,Object>> selectCourseListByTno(@RequestParam("pageNo") Integer pageNo,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("tid") Integer tid){
    DataResult<Map<String,Object>> result = new DataResult<>();
    result.setBody(courseService.selectCourseByTno(pageNo,pageSize,tid));
    return result;
  }

  @ApiOperation(value = "根据ID查询课程的详细信息")
  @GetMapping("/selectCourseDetailByCid/")
  @ResponseBody
  public Course selectCourseDetailByCid(@RequestParam("cid") Integer cid){
    return courseService.selectCourseDetailByCid(cid);
  }


  @ApiOperation(value = "根据专业的ID查询课程列表")
  @GetMapping("/getCourseNameByProsessionId")
  @ResponseBody
  public List<Course> getCourseNameByProsessionId(@RequestParam("pid") Integer pid){
    return courseService.getCourseNameByProsessionId(pid);
  }

}
