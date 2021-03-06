package com.helo.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.helo.demo.config.DataResult;
import com.helo.demo.facecompare.CommonMethod;
import com.helo.demo.model.Sign;
import com.helo.demo.service.ReleaseService;
import com.helo.demo.service.SignService;
import com.helo.demo.service.StudentService;
import com.helo.demo.utils.CommonUtil;
import com.helo.demo.vo.SignStudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import static java.lang.String.valueOf;

/**
 * @author wangxl
 * @ClassName SignController
 * @Description TODO 学生签到接口
 * @date 2019/8/23 17:51
 * @Version 1.0
 */
@Api(tags = "签到接口")
@RequestMapping("/signApi")
@RestController
public class SignController {

    @Autowired
    private SignService signService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ReleaseService releaseService;

    /**
    * @Description: 跳转到签到界面
    * @params: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("跳转到签到界面")
    @GetMapping("toAttence/{pid}")
    public ModelAndView toAttenceView(@PathVariable("pid") int pid) {

        return new ModelAndView("/student/attence").addObject("release",this.releaseService.getReleaseBypid(pid));
    }
    
    /**
    * @Description: 跳转到我的签到列表页面
    * @params: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: wangxianlin
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("跳转到我的签到列表页面")
    @GetMapping("toAttenceList")
    public ModelAndView toAttenceList() {
        return new ModelAndView("/student/attenceList");
    }

    /**
    * @Description: 跳转到管理员签到管理页面
    * @params: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: wangxianlin
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("跳转到管理员签到管理页面")
    @GetMapping("toAttenceForCounselor")
    public ModelAndView toAttenceForCounselor() {
        return new ModelAndView("/counselor/sign");
    }
    
    /**
    * @Description: 跳转到签到详情界面
    * @params: [signId]
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: wangxianlin
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("跳转到签到详情界面")
    @GetMapping("toAttenceDetail/{signId}")
    public ModelAndView toAttenceDetail( @PathVariable("signId") Integer signId) {
        ModelAndView view = new ModelAndView("/student/attenceDetail");
        view.addObject("sign", signService.selectSignById(signId));
        return view;
    }

    /**
    * @Description: 学生签到-添加签到记录
    * @params: [signLocation, studentId, courseId, file]
    * @return: com.helo.demo.config.DataResult<java.lang.Integer>
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("学生签到-添加签到记录")
    @PostMapping("insertSign")
    public DataResult<Integer> insertSign(@RequestParam("signLocation") String signLocation,@RequestParam("studentId") int studentId,
                                          @RequestParam("releaseId") int releaseId,@RequestParam("courseId") int courseId, @RequestParam("status") int status,@RequestParam("file") MultipartFile file) {
        DataResult<Integer> results = new DataResult<>();
        //获取该学生的头像地址
        String picPath = studentService.getPicBySid(studentId);
        //将学生的头像抓成file文件
        File goalFile = CommonUtil.getFileByPath(picPath);
        String str = CommonMethod.compareTo(goalFile,CommonUtil.MultipartFileToFile(file));
        JSONObject result = JSONObject.parseObject(str);
        String confidence =String.valueOf(result.get("confidence"));
        String s = confidence.substring(0,confidence.length()-1);
        Double id=Double.valueOf(s);
        if(id>80.00){
            Sign sign = new Sign();
            sign.setCourseId(courseId);
            sign.setSignLocation(signLocation);
            sign.setStudentId(studentId);
            sign.setStatus(status);
            sign.setReleaseId(releaseId);
            results.setBody(signService.insertSign(sign));
       }else{
            results.setBody(0);
        }
        return results;
    }

    /**
    * @Description: 学生下课签退
    * @params: [signOutLocation, studentId, file]
    * @return: int
    * @Date: 2020/3/5 10:12 AM
    */ 
    @ApiOperation("学生下课签退")
    @PostMapping("updateSignById")
    public int updateSignById(@RequestParam("signOutLocation") String signOutLocation,
                              @RequestParam("studentId") int studentId,@RequestParam("file") MultipartFile file) {
        //获取该学生的头像地址
        String picPath = studentService.getPicBySid(studentId);
        //将学生的头像抓成file文件
        File goalFile = CommonUtil.getFileByPath(picPath);
        String str = CommonMethod.compareTo(goalFile,CommonUtil.MultipartFileToFile(file));
        JSONObject result = JSONObject.parseObject(str);
        String confidence =String.valueOf(result.get("confidence"));
        String s = confidence.substring(0,confidence.length()-1);
        Double id=Double.valueOf(s);
        if(id>80.00){
        return signService.updateSignById(signOutLocation,studentId);
        }else{
            return 0;
        }
    }

    /**
    * @Description: 获取所有学生签到记录
    * @params: [pageNo, pageSize]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Date: 2020/3/5 10:11 AM
    */ 
    @ApiOperation("获取所有学生签到记录")
    @GetMapping("/getSignByPage")
    public Map<String, Object> getSignByPage(@RequestParam("page") Integer pageNo,
                                             @RequestParam("limit") Integer pageSize) {
        return signService.getSignByPage(pageNo, pageSize);
    }

    /**
    * @Description: 获取学生签到记录
    * @params: [pageNo, pageSize, stuId]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Date: 2020/3/5 10:11 AM
    */ 
    @ApiOperation("获取学生签到记录")
    @GetMapping("getSignStuByPage")
    public Map<String, Object> getSignStuByPage(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam("stuId") Integer stuId) {
        return signService.getSignStuByPage(pageNo, pageSize, stuId);
    }

    /**
    * @Description: 导出当前学生的考勤记录
    * @params: [response, pageNo, pageSize]
    * @return: void
    * @Date: 2020/3/5 10:11 AM
    */ 
    @ApiOperation("导出当前学生的考勤记录")
    @PostMapping("/exportSign")
    public void exportSign(HttpServletResponse response,@RequestParam("page") Integer pageNo,
                                   @RequestParam("limit") Integer pageSize ) {
        WritableWorkbook workbook = null;
        try {
            String fileName = "考勤记录-" + System.currentTimeMillis() + ".xls";
            Map<String, Object> signMap = signService.getSignByPage(pageNo, pageSize);
            List<SignStudentVo> signList = (List<SignStudentVo>) signMap.get("data");
            response.reset();
            response.setContentType("application/x-xls"); // vnd.ms-excel
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sign = workbook.createSheet("考勤记录", 0);
            sign.setColumnView(0, 20);
            sign.setColumnView(1, 20);
            sign.setColumnView(2, 20);
            sign.setColumnView(3, 20);
            sign.setColumnView(4, 25);
            sign.setColumnView(5, 20);
            sign.setColumnView(6, 20);
            sign.setRowView(0, 450, false);
            WritableCellFormat titleCellFormat = setTitleCellFormat(true);
            sign.addCell(new Label(0, 0, "姓名", titleCellFormat));
            sign.addCell(new Label(1, 0, "签到地点", titleCellFormat));
            sign.addCell(new Label(2, 0, "签退地点", titleCellFormat));
            sign.addCell(new Label(3, 0, "签到时间", titleCellFormat));
            sign.addCell(new Label(4, 0, "签退时间", titleCellFormat));
            sign.addCell(new Label(5, 0, "是否签到", titleCellFormat));
            sign.addCell(new Label(6, 0, "是否签退", titleCellFormat));
            sign.addCell(new Label(7, 0, "状态", titleCellFormat));
            WritableCellFormat contentCellFormat = setContentCellFormat();
            for (int i = 0; i < signList.size(); i++) {
                sign.addCell(new Label(0, i + 1, signList.get(i).getStudentName(), contentCellFormat));
                sign.addCell(new Label(1, i + 1, signList.get(i).getSignLocation(), contentCellFormat));
                sign.addCell(new Label(2, i + 1, signList.get(i).getSignOutLocation(), contentCellFormat));
                sign.addCell(new Label(3, i + 1, valueOf(CommonUtil.format(signList.get(i).getStartTime(),"yyyy-MM-dd HH:mm:ss")), contentCellFormat));
                sign.addCell(new Label(4, i + 1, valueOf(CommonUtil.format(signList.get(i).getEndTime(),"yyyy-MM-dd HH:mm:ss")), contentCellFormat));
                if(signList.get(i).getIsStartStatus()==1){
                    sign.addCell(new Label(5, i + 1, "已签到", contentCellFormat));
                }else{
                    sign.addCell(new Label(5, i + 1, "未签到", contentCellFormat));
                }
                if(signList.get(i).getIsEndStatus()==1){
                    sign.addCell(new Label(6, i + 1, "已签退", contentCellFormat));
                }else{
                    sign.addCell(new Label(6, i + 1, "未签退", contentCellFormat));
                }
                if(signList.get(i).getStatus()==1){
                    sign.addCell(new Label(7, i + 1, "迟到", contentCellFormat));
                }else{
                    sign.addCell(new Label(7, i + 1, "正常", contentCellFormat));
                }

            }
            response.flushBuffer();
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //如果判断workbook是否为空，不为空->关闭链接
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
    * @Description: 获取学生最新的签到信息
    * @params: [stuId]
    * @return: com.helo.demo.model.Sign
    * @Date: 2020/3/5 10:11 AM
    */ 
    @ApiOperation("获取学生最新的签到信息")
    @GetMapping("getSignStuId")
    public Sign getStudentSign(@RequestParam("stuId") Integer stuId) {
        return signService.getStudentSign(stuId);
    }
    
    /**
     * 设置导出表格的标题栏单元格样式
     * @param hasBorder
     * @return
     * @throws WriteException
     */
    private WritableCellFormat setTitleCellFormat(boolean hasBorder) throws WriteException {
        WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat titleCellFormat = new WritableCellFormat();
        titleCellFormat.setFont(titleFont);
        titleCellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        titleCellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        if (hasBorder) {
            titleCellFormat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE, Colour.BLACK);
        }
        return titleCellFormat;
    }

    /**
     * 设置导出表格的内容单元格样式
     * @return
     * @throws WriteException
     */
    private WritableCellFormat setContentCellFormat() throws WriteException {
        WritableCellFormat contentCellFormat = new WritableCellFormat();
        contentCellFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false));
        contentCellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        contentCellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        contentCellFormat.setWrap(true);
        return contentCellFormat;
    }
}
