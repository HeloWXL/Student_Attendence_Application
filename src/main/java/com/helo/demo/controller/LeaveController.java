package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Leave;
import com.helo.demo.service.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangxianlin
 * @ClassName LeaveController
 * @Description TODO
 * @date 2019/8/23 17:51
 * @Version 1.0
 */
@Api(tags = "请假接口")
@RequestMapping("/leaveApi")
@Controller
public class LeaveController {

    @Resource
    private LeaveService leaveService;

    @ApiOperation(value = "跳转到学生请假页面")
    @GetMapping("/toAskForLeave")
    public String toAskLeave() {
        return "/student/askForLeave";
    }

    @ApiOperation(value = "跳转到请假请假列表页面")
    @GetMapping("/toLeaveList")
    public String toLeaveList() {
        return "/student/leaveList";
    }

    @ApiOperation(value = "跳转到辅导员管理请假列表页面")
    @GetMapping("/toCounselorLeaveTable")
    public String toCounselorLeaveList() {
        return "/counselor/leave";
    }

    @ApiOperation(value = "添加一条请假记录")
    @PostMapping("/insertSelective")
    @ResponseBody
    public DataResult<Integer> insertSelective(@RequestBody Leave leave) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(leaveService.insertSelective(leave));
        return result;
    }

    @ApiOperation(value = "根据id查询请假记录")
    @GetMapping("/selectByPrimaryKey/{leaveId}")
    public String selectByPrimaryKey(@PathVariable("leaveId") Integer leaveId, Model model) {
        Leave leave = leaveService.selectByPrimaryKey(leaveId);
        model.addAttribute("leave", leave);
        return "/student/leaveDetail";
    }

    @ApiOperation(value = "根据学号查询请假信息 -分页")
    @GetMapping("/selectLeaveByPage")
    @ResponseBody
    public DataResult<Map<String, Object>> selectLeaveByPage(@RequestParam("pageNo") Integer pageNo,
                                                             @RequestParam("pageSize") Integer pageSieze, @RequestParam("studentSno") Integer studentSno) {
        DataResult<Map<String, Object>> result = new DataResult<>();
        result.setBody(leaveService.getLeaveByPage(pageNo, pageSieze, studentSno));
        return result;
    }


    @ApiOperation(value = "获取请假列表-分页")
    @GetMapping("/selectByPage")
    @ResponseBody
    public Map<String, Object> selectByPage(@RequestParam("page") Integer pageNo,
                                            @RequestParam("limit") Integer pageSieze) {
        return leaveService.selectByPage(pageNo, pageSieze);
    }


    @ApiOperation(value = "同意请假")
    @GetMapping("/agreeLeaves")
    @ResponseBody
    public DataResult<Integer> agreeLeaves(@RequestParam("id") Integer id) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(leaveService.agreeLeaves(id));
        return result;
    }


    @ApiOperation(value = "不同意请假")
    @GetMapping("/notAgreeLeaves")
    @ResponseBody
    public DataResult<Integer> notAgreeLeaves(@RequestParam("id") Integer id) {
        DataResult<Integer> result = new DataResult<>();
        result.setBody(leaveService.agreeLeaves(id));
        return result;
    }


}
