package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Course;
import com.helo.demo.model.Profession;
import com.helo.demo.service.CourseService;
import com.helo.demo.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Resource
    private CourseService courseService;

    @ApiOperation(value = "跳转到课程信息列表")
    @GetMapping("/toCounselorCourseTable")
    public String toCounselorCourseTable() {
        return "counselor/course";
    }

    @ApiOperation(value = "根据ID删除课程信息")
    @GetMapping("/deleteByCourseId/{id}")
    @ResponseBody
    public DataResult<Integer> deleteByCourseId(@PathVariable("id") Integer id) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(courseService.deleteByPrimaryKey(id));
        return result;
    }

    @ApiOperation(value = "添加课程信息")
    @PostMapping("/insertCourse")
    @ResponseBody
    public DataResult<Integer> insertStudent(@RequestBody Course course) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(courseService.insertSelective(course));
        return result;
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("/updateCourse")
    @ResponseBody
    public DataResult<Integer> updateCourse(@RequestBody Course course) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(courseService.updateByPrimaryKeySelective(course));
        return result;
    }

    @ApiOperation(value = "查询课程信息")
    @GetMapping("/selectCourseByPage")
    @ResponseBody
    public Map<String, Object> selectCourseByPage(@RequestParam("page") Integer pageNo,
                                                  @RequestParam("limit") Integer pageSiez) {
        return courseService.getCourseList(pageNo, pageSiez);
    }

    @ApiOperation(value = "根据教师工号查询课程列表")
    @GetMapping("/selectCourseListByTno")
    @ResponseBody
    public Map<String, Object> selectCourseListByTno(@RequestParam("tid") Integer tid) {
        return courseService.selectCourseByTno(tid);
    }

    @ApiOperation(value = "根据ID查询课程的详细信息")
    @GetMapping("/selectCourseDetailByCid/")
    @ResponseBody
    public Course selectCourseDetailByCid(@RequestParam("cid") Integer cid) {
        return courseService.selectCourseDetailByCid(cid);
    }


    @ApiOperation(value = "根据专业的ID查询课程列表")
    @GetMapping("/getCourseNameByProsessionId")
    @ResponseBody
    public List<Course> getCourseNameByProsessionId(@RequestParam("pid") Integer pid) {
        return courseService.getCourseNameByProsessionId(pid);
    }


    @ApiOperation(value = "加载课程")
    @GetMapping("/loadCourseByTid")
    @ResponseBody
    public List<Course> loadCourse(@RequestParam("tid") int tid) {
        return this.courseService.getCourseByTid(tid);
    }


    @PostMapping("/importCourse")
    @ResponseBody
    public Map<String,Object>  importCourse(@RequestParam("file") MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String pattern = fileName.substring(fileName.lastIndexOf(".") + 1);
        List<List<String>> listContent = new ArrayList<>();
        String message = "导入成功";
        try {
            if (file != null) {
                //文件类型判断
                if (!ExcelUtil.isEXCEL(file)) {
                    message="文件为空";
                    map.put("msg",message);
                    map.put("data",fileName);
                    return map;
                }
                listContent = ExcelUtil.readExcelContents(file, pattern);
                //文件内容判断
                if (listContent.isEmpty()) {
                    message="表格内容为空";
                    map.put("msg",message);
                    map.put("data",fileName);
                    return map;
                }
                courseService.importCourse(listContent);
            } else {
                message="未选择文件";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("code",0);
        map.put("msg",message);
        map.put("data",fileName);
        return map;
    }


}
