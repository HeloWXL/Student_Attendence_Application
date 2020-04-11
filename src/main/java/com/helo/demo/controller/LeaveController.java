package com.helo.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.helo.demo.config.DataResult;
import com.helo.demo.model.Leave;
import com.helo.demo.service.LeaveService;
import com.helo.demo.utils.CommonUtil;
import com.helo.demo.utils.ConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
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

    @ApiOperation(value = "请假详情")
    @GetMapping("/leaveDetail/{id}")
    public String toLeaveList(Model model,@PathVariable("id") Integer id) {
        model.addAttribute("leave",leaveService.selectByPrimaryKey(id));
        return "/counselor/leaveDetail";
    }

    @ApiOperation(value = "跳转到辅导员管理请假列表页面")
    @GetMapping("/toCounselorLeaveTable")
    public String toCounselorLeaveList() {
        return "/counselor/leave";
    }

    @ApiOperation(value = "添加一条请假记录")
    @PostMapping("/insertSelective")
    @ResponseBody
    public DataResult<Integer> insertSelective(@RequestParam("file") MultipartFile file,@RequestParam("coundelorId") Integer coundelorId,
                                               @RequestParam("studentSno") String studentSno,
                                               @RequestParam("leaveReason") String leaveReason,
                                               @RequestParam("startTime") String startTime,
                                               @RequestParam("endTime") String endTime,
                                               @RequestParam("leaveTitle") String leaveTitle,@RequestParam("courseId") Integer courseId) {
        DataResult<Integer> result = new DataResult<>();
        Leave leave = new Leave();
        leave.setCoundelorId(coundelorId);
        leave.setStudentSno(studentSno);
        leave.setLeaveReason(leaveReason);
        leave.setStartTime(CommonUtil.parse(startTime,"yyyy-MM-dd HH:mm"));
        leave.setEndTime(CommonUtil.parse(endTime,"yyyy-MM-dd HH:mm"));
        leave.setLeaveTitle(leaveTitle);
        leave.setCourseId(courseId);

        String fileName = file.getOriginalFilename();
        Date date = new Date();
        String picDir = ConfigUtil.getValue("imageDir");
        String relativeDir = getRelativeDir(date);
        File fileDir = new File(picDir + relativeDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //新的文件名
        String newName = CommonUtil.format(date, "HHmmssSSS") +
                Math.round(Math.random() * 8999 + 1000) +
                fileName.substring(fileName.lastIndexOf("."));
        //头像地址
        String imgPath = relativeDir + newName;
        leave.setFileUrl(imgPath);
        try {
            file.transferTo(new File(picDir + imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setBody(leaveService.insertSelective(leave));
        return result;
    }

    @ApiOperation(value = "获取图片路径")
    @GetMapping(value = "/getLocalImg")
    public void getLocalImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("path") String path){
        try {
            File file = new File(ConfigUtil.getValue("imageDir") + path);
            FileInputStream fin = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fin.read(data);
            fin.close();
            response.setContentType("image/*");
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据日期得到年/月/日/的相对路径
     * @param date
     * @return
     */
    private String getRelativeDir(Date date) {
        String year = CommonUtil.format(date, "yyyy");
        String month = CommonUtil.format(date, "MM");
        String day = CommonUtil.format(date, "dd");
        String dir = year + "/" + month + "/" + day + "/";
        return dir;
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
