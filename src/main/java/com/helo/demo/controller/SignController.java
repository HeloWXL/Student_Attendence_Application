package com.helo.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.helo.demo.config.DataResult;
import com.helo.demo.model.Sign;
import com.helo.demo.model.Student;
import com.helo.demo.service.SignService;
import com.helo.demo.utils.CommonUtil;
import com.helo.demo.vo.SignStudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName SignController
 * @Description TODO 学生签到接口
 * @date 2019/8/23 17:51
 * @Version 1.0
 */
@Api(tags = "打卡接口")
@RequestMapping("/signApi")
@Controller
public class SignController {

    @Autowired
    private SignService signService;

    @ApiOperation("跳转到签到界面")
    @GetMapping("toAttence")
    public String toAttenceView() {
        return "/student/attence";
    }

    @ApiOperation("跳转到我的签到列表页面")
    @GetMapping("toAttenceList")
    public String toAttenceList() {
        return "/student/attenceList";
    }

    @ApiOperation("跳转到管理员签到管理页面")
    @GetMapping("toAttenceForCounselor")
    public String toAttenceForCounselor() {
        return "/counselor/sign";
    }

    @ApiOperation("跳转到签到详情界面")
    @GetMapping("toAttenceDetail/{signId}")
    public String toAttenceDetail(Model model, @PathVariable("signId") Integer signId) {
        model.addAttribute("sign", signService.selectSignById(signId));
        return "/student/attenceDetail";
    }

    @ApiOperation("学生签到-添加签到记录")
    @PostMapping("insertSign")
    @ResponseBody
    public DataResult<Integer> insertSign(@RequestBody Sign sign) {
        DataResult<Integer> result = new DataResult<>();
        result.setMsg("添加成功");
        result.setCode("200");
        result.setBody(signService.insertSign(sign));
        return result;
    }

    @ApiOperation("获取所有学生签到记录")
    @GetMapping("/getSignByPage")
    @ResponseBody
    public Map<String, Object> getSignByPage(@RequestParam("page") Integer pageNo,
                                             @RequestParam("limit") Integer pageSize) {
        return signService.getSignByPage(pageNo, pageSize);
    }



    @ApiOperation("获取学生签到记录")
    @GetMapping("getSignStuByPage")
    @ResponseBody
    public Map<String, Object> getSignStuByPage(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam("stuId") Integer stuId) {
        return signService.getSignStuByPage(pageNo, pageSize, stuId);
    }

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
            WritableCellFormat contentCellFormat = setContentCellFormat();
            for (int i = 0; i < signList.size(); i++) {
                sign.addCell(new Label(0, i + 1, signList.get(i).getStudentName(), contentCellFormat));
                sign.addCell(new Label(1, i + 1, signList.get(i).getSignLocation(), contentCellFormat));
                sign.addCell(new Label(2, i + 1, signList.get(i).getSignOutLocation(), contentCellFormat));
                sign.addCell(new Label(3, i + 1, String.valueOf(CommonUtil.format(signList.get(i).getStartTime(),"yyyy-MM-dd HH:mm:ss")), contentCellFormat));
                sign.addCell(new Label(4, i + 1, String.valueOf(CommonUtil.format(signList.get(i).getEndTime(),"yyyy-MM-dd HH:mm:ss")), contentCellFormat));
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


    @ApiOperation("获取学生最新的签到信息")
    @GetMapping("getSignStuId")
    @ResponseBody
    public Sign getStudentSign(@RequestParam("stuId") Integer stuId) {
        return signService.getStudentSign(stuId);
    }

    @ApiOperation("学生下课签退")
    @PostMapping("updateSignById")
    @ResponseBody
    public int updateSignById(@RequestBody Sign sign,HttpServletRequest request) {
        int studentId=0;
        Student student = (Student) request.getSession().getAttribute("studentsession");
        try {
            if(student==null){
             throw new Exception("学生未登录");
            }else{
                studentId = student.getStudentId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signService.updateSignById(sign.getSignOutLocation(),studentId);
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
        contentCellFormat.setAlignment(jxl.format.Alignment.LEFT);
        contentCellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        contentCellFormat.setWrap(true);
        return contentCellFormat;
    }
}
