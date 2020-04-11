package com.helo.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @GetMapping("/noticeList/{stuId}")
    public ModelAndView toNoticeList(@PathVariable("stuId") Integer stuId){
        return new ModelAndView("/student/noticeList").addObject("noticeList",leaveNoticeService.getLeaveNoticeByStuId(stuId));
    }

    @GetMapping("/updateNoticeById")
    public int updateNoticeById(int id){
        return this.leaveNoticeService.updateNoticeByid(id);
    }

    @GetMapping("/updateNoticeByTeaId")
    public int updateNoticeByTeaId(int id){
        return this.leaveNoticeService.updateNoticeByTeaId(id);
    }

    @GetMapping("/noticeTeaList/{teaId}")
    public ModelAndView noticeTeaList(@PathVariable("teaId") Integer teaId){
        return new ModelAndView("/teacher/noticeTeaList").addObject("noticeList",leaveNoticeService.getLeaveNoticeByTeaId(teaId));
    }

    @GetMapping("/getLeaveNoticeByTeaId")
    public JSONObject getLeaveNoticeByTeaId(@RequestParam("id") Integer id){
        JSONObject object = new JSONObject();
        object.put("data",leaveNoticeService.getLeaveNoticeByTeaId(id));
        return object;
    }

}