package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Counselor;
import com.helo.demo.model.Profession;
import com.helo.demo.service.CounselorService;
import com.helo.demo.service.ProfessionService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangxl
 * @ClassName CounselorController
 * @Description
 * @date 2019/8/21 0:03
 */
@Api(tags = "辅导员接口")
@Controller
@RequestMapping("counselorApi")
@Slf4j
public class CounselorController {

  @Resource
  private CounselorService counselorService;
  @Resource
  private ProfessionService professionService;

  @ApiOperation(value = "跳转到辅导员首页界面")
  @GetMapping("/toCounselorIndex")
  public String toCounselorIndex(){
    return "counselor/index";
  }

  @ApiOperation(value = "跳转到辅导员登录界面")
  @GetMapping("/toCounselorLogin")
  public String toCounselorLogin(){
    return "counselor/login";
  }

  @ApiOperation(value = "跳转到学生列表界面")
  @GetMapping("/toCounselorStudentTable")
  public String toStudentTable(){
    return "counselor/student";
  }

  @ApiOperation(value = "跳转到系统统计界面")
  @GetMapping("/toStatistcs")
  public String toStatistcs(){
    return "counselor/statistcs";
  }

  @ApiOperation(value = "添加辅导员信息")
  @PostMapping("/insertCounselor")
  @ResponseBody
  public DataResult<Integer> insertAdmin(@RequestBody Counselor counselor){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(counselorService.insertSelective(counselor));
    return result;
  }

  @ApiOperation(value = "根据ID查询辅导员信息")
  @PostMapping("/selectByCounselorId/{id}")
  @ResponseBody
  public DataResult<Counselor> selectByPrimaryKey(@PathVariable("id") Integer id){
    DataResult<Counselor> result = new DataResult<>();
    result.setBody(counselorService.selectByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "根据ID删除辅导员信息")
  @GetMapping("/deleteByCounselorId/{id}")
  @ResponseBody
  public DataResult<Integer> deleteByCounselorId(@RequestParam("id") Integer id){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(counselorService.deleteByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "修改辅导员信息")
  @PostMapping("/updateCounselor")
  @ResponseBody
  public DataResult<Integer> updateCounselor(@RequestBody Counselor counselor){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(counselorService.updateByPrimaryKeySelective(counselor));
    return result;
  }


  @ApiOperation(value = "辅导员登录")
  @PostMapping("/checkLogin")
  @ResponseBody
  public DataResult<Boolean> checkLogin(@RequestParam("cno") String cno, @RequestParam("password") String password, HttpServletRequest request){
    Counselor counselor = counselorService.selectByCno(cno);
    DataResult<Boolean> result = new DataResult<>();
    if (Md5Utils.getSaltverifyMD5(password, counselor.getCounselorPassword())) {
      log.info(counselor.getCounselorName() + "已登录" );
      result.setBody(true);
      request.getSession().setAttribute("counselor", counselor);
    } else {
      request.getSession().setAttribute("counselor", null);
      result.setBody(false);
    }
    return result;
  }

  @ApiOperation(value = "获取辅导员的session对象")
  @PostMapping("/getCounselorSession")
  @ResponseBody
  public DataResult<Counselor> getTeacherSession(HttpServletRequest request, @RequestParam("counselorrBean") String counselorrBean){
    DataResult<Counselor> result = new DataResult<>();
    Counselor counselor = (Counselor) request.getSession().getAttribute(counselorrBean);
    if (counselor == null) {
      result.setBody(null);
      return result;
    } else {
      result.setBody(counselor);
      return result;
    }
  }

  @ApiOperation(value = "清除辅导员的session对象")
  @GetMapping("/removeCounselorSession")
  @ResponseBody
  public void removeCounselorSession(HttpServletRequest request, HttpServletResponse response){
    Counselor counselor = (Counselor) request.getSession().getAttribute("counselor");
    request.getSession().removeAttribute("counselor");
    if (request.getSession().getAttribute("counselor") == null) {
      try {
        log.info(counselor.getCounselorName()+"已退出登录");
        response.sendRedirect("/helo/counselorApi/toCounselorLogin");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


}
