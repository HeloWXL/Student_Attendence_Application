package com.helo.demo.controller;

import com.helo.demo.model.LeaveNotice;
import com.helo.demo.service.LeaveNoticeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * (LeaveNotice)表控制层
 *
 * @author makejava
 * @since 2020-03-28 17:14:43
 */
@RestController
@RequestMapping("leaveNotice")
public class LeaveNoticeController {
    /**
     * 服务对象
     */
    @Resource
    private LeaveNoticeService leaveNoticeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public LeaveNotice selectOne(Integer id) {
        return this.leaveNoticeService.queryById(id);
    }


    @GetMapping("/noticeList/{stuId}")
    public ModelAndView toNoticeList(@PathVariable("stuId") Integer stuId){
        return new ModelAndView("/student/noticeList").addObject("noticeList",leaveNoticeService.getLeaveNoticeByStuId(stuId));
    }

}