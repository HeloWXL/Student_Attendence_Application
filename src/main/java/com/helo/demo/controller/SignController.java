package com.helo.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiayj
 * @ClassName SignController
 * @Description TODO
 * @date 2019/8/23 17:51
 * @Version 1.0
 */
@Api(tags = "打卡接口")
@RequestMapping("/signApi")
@Controller
public class SignController {

    @ApiOperation("跳转到签到界面")
    @RequestMapping("toAttence")
    public String toAttenceView(){
        return "/student/attence";
    }


}
