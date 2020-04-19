package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Admin;
import com.helo.demo.model.Counselor;
import com.helo.demo.service.AdminService;
import com.helo.demo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Api(tags = "AdminApi接口")
@Controller
@RequestMapping("adminApi")
@Slf4j
public class AdminController {


    @Resource
    private AdminService adminService;

    @ApiOperation(value = "管理员登录")
    @PostMapping("/checkLogin")
    @ResponseBody
    public DataResult<Boolean> checkLogin(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest request){
        Admin admin = adminService.checkLogin(name);
        DataResult<Boolean> result = new DataResult<>();
        if (password.equals(admin.getPassword())) {
            log.info(admin.getNickName() + "已登录" );
            result.setBody(true);
            request.getSession().setAttribute("admin", admin);
        } else {
            request.getSession().setAttribute("admin", null);
            result.setBody(false);
        }
        return result;
    }


    @ApiOperation(value = "管理员登录")
    @GetMapping("/login")
    public String toLogin(){
        return "/admin/login";
    }

    @ApiOperation(value = "管理员首页")
    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "/admin/admin";
    }

}
