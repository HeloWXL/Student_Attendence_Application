package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Leave;
import com.helo.demo.service.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
    @GetMapping("/toLeaveList/{studentSno}")
    public String toLeaveList(Model model,@PathVariable("studentSno") String studentSno) {
        model.addAttribute("leave",leaveService.getLeaveByPage(1, 100, studentSno));
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
        model.addAttribute("leave", leaveService.selectByPrimaryKey(leaveId));
        return "/student/leaveDetail";
    }

    @ApiOperation(value = "根据id查询请假记录1")
    @GetMapping("/selectByPrimaryKey1")
    @ResponseBody
    public Object selectByPrimaryKey1(@RequestParam("leaveId") Integer leaveId) {
        return leaveService.selectByPrimaryKey(leaveId);
    }

    @ApiOperation(value = "根据学号查询请假信息 -分页")
    @GetMapping("/selectLeaveByPage")
    @ResponseBody
    public Map<String, Object> selectLeaveByPage(@RequestParam("pageNo") Integer pageNo,
                                                             @RequestParam("pageSize") Integer pageSieze, @RequestParam("studentSno") String studentSno) {
        return leaveService.getLeaveByPage(pageNo, pageSieze, studentSno);

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
        result.setBody(leaveService.notAgreeLeaves(id));
        return result;
    }

    @ApiOperation(value = "教师查看请假记录")
    @GetMapping("/getLeaveByTeacher")
    @ResponseBody
    public DataResult<List<Leave>> getLeaveByTeacher(@RequestParam("tId") Integer tId) {
        DataResult<List<Leave>> result = new DataResult<>();
        result.setBody(leaveService.getLeaveByTeacher(tId));
        return result;
    }

}
