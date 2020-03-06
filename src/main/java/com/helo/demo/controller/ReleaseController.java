package com.helo.demo.controller;

import com.helo.demo.model.Release;
import com.helo.demo.service.ReleaseService;
import com.helo.demo.utils.CommonUtil;
import io.swagger.annotations.Api;
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
@Api("发布考勤接口")
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
    @PostMapping("insertRelease")
    public int insertRelease(@RequestBody Release release) {
        return this.releaseService.insert(release);
    }

    /**
    * @Description: 跳转到发布考勤页面
    * @params: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: wangxianlin
    * @Date: 2020/3/6 9:08 AM
    */
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
    @GetMapping("toReleaseList/{tid}")
    public ModelAndView toReleaseList(@PathVariable("tid") int tid) {
        return new ModelAndView("/teacher/releaseList").addObject("release",this.releaseService.getReleaseByTid(tid));
    }

}