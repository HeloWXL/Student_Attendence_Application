package com.helo.demo.controller;

import com.helo.demo.facecompare.FaceCompare;
import com.helo.demo.model.API;
import com.helo.demo.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.List;

/**
 * @Classname ApiController
 * @Description TODO 人脸对比API接口
 * @Date 2019/12/28 11:09 上午
 * @Created by wangxianlin
 */
@Api(tags = "人脸对比API接口")
@Controller
@RequestMapping("FaceApi")
public class ApiController {

    @Resource
    private ApiService apiService;

    @ApiOperation(value = "跳转到Api管理页面")
    @GetMapping("/toApiView")
    public String toApiView() {
        return "counselor/api";
    }

    @ApiOperation(value = "添加Api配置")
    @PostMapping("/insertFaceApi")
    @ResponseBody
    public int insertFaceApi(@RequestBody API api) {
        return apiService.insertFaceApi(api);
    }

    @ApiOperation(value = "修改Api配置")
    @PostMapping("/updateFaceApi")
    @ResponseBody
    public int updateFaceApi(@RequestBody API api) {
        return apiService.updateFaceApi(api);
    }

    @ApiOperation(value = "删除Api配置")
    @PostMapping("/deleteFaceApi")
    @ResponseBody
    public int deleteFaceApi(@RequestBody List<Integer> ids) {
        return apiService.deleteFaceApi(ids);
    }

    @ApiOperation(value = "分页获取Api配置")
    @GetMapping("/getApiListByPage")
    @ResponseBody
    public Map<String, Object> getApiListByPage(int counselorId, int page, int limit) {
        return apiService.getApiListByPage(counselorId, page, limit);
    }

    @ApiOperation(value = "人脸识别API测试")
    @PostMapping("/compareFaceApi")
    @ResponseBody
    public Object compareFaceApi(String base64Img,String base64Img2){
        API api = null;
        try {
            api = apiService.selectGetUseFAceApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FaceCompare faceCompare = new FaceCompare(api.getApiKey(),api.getApiSecret(),api.getApiUrl(),base64Img,base64Img2);
        return faceCompare.compareRes();
    }


}
