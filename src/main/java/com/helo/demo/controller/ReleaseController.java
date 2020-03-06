package com.helo.demo.controller;

import com.helo.demo.model.Release;
import com.helo.demo.service.ReleaseService;
import com.helo.demo.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (Release)表控制层
 *
 * @author makejava
 * @since 2020-03-05 23:35:42
 */
@Api(tags ="考勤接口")
@RestController
@RequestMapping("releaseApi")
public class ReleaseController {
    /**
     * 服务对象
     */
    @Resource
    private ReleaseService releaseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Release selectOne(Integer id) {
        return this.releaseService.queryById(id);
    }


    /**
    * @Description: 发布考勤
    * @params: [release]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/3/6 1:20 AM
    */
    @ApiOperation("发布考勤")
    @PostMapping("insertRelease")
    public int insertRelease(@RequestParam("teacherId") int teacherId,@RequestParam("professionId") int professionId,
                             @RequestParam("startTime") String startTime,
                             @RequestParam("endTime") String endTime,@RequestParam("courseId") int courseId) {
        Release release = new Release();
        release.setStartTime(CommonUtil.parse(startTime,"yyyy-MM-dd HH:mm:ss"));
        release.setEndTime(CommonUtil.parse(endTime,"yyyy-MM-dd HH:mm:ss"));
        release.setCourseId(courseId);
        release.setProfessionId(professionId);
        release.setTeacherId(teacherId);
        return this.releaseService.insert(release);
    }

    /**
    * @Description: 跳转到发布考勤页面
    * @params: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: wangxianlin
    * @Date: 2020/3/6 9:08 AM
    */
    @ApiOperation("跳转到发布考勤页面")
    @GetMapping("toRelease")
    public ModelAndView toRelease() {
        return new ModelAndView("/teacher/release");
    }

    /**
     * @Description: 考勤列表
     * @params: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: wangxianlin
     * @Date: 2020/3/6 9:08 AM
     */
    @ApiOperation("考勤列表")
    @GetMapping("toReleaseList/{tid}")
    public ModelAndView toReleaseList(@PathVariable("tid") int tid) {
        return new ModelAndView("/teacher/releaseList").addObject("release",this.releaseService.getReleaseByTid(tid));
    }
    
    /**
    * @Description: 根据专业的ID查询最新的一条考勤
    * @params: [pid]
    * @return: com.helo.demo.model.Release
    * @Author: wangxianlin
    * @Date: 2020/3/6 10:53 AM
    */
    @ApiOperation("根据专业的ID查询最新的一条考勤")
    @GetMapping("getReleaseBypid")
    public Release getReleaseBypid(@RequestParam("pid") int pid){
        return this.releaseService.getReleaseBypid(pid);
    }

}