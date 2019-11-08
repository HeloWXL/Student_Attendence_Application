package com.helo.demo.controller;

import com.helo.demo.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName UploadController
 * @Description TODO
 * @date 2019/11/8 9:17
 */
@Api(tags = "上传接口")
@Controller
@RequestMapping("uploadApi")
public class UploadController {

  @ApiOperation(value = "跳转到图片上传页面")
  @GetMapping("/toUploadImage")
  public String toUploadImage() {
    return "uploadDemo/uploadImage";
  }


  @ApiOperation(value = "上传图片")
  @PostMapping("/uploadImage")
  @ResponseBody
  public Map<String,Object> uploadImage(@RequestParam("file") MultipartFile file) {
    Map<String,Object> map  = new HashMap<>();
    String uploadDir = "F:/kdgc_project/Student_Attendence_Application/src/main/webapp/resources/upload/";
    try {
      // 图片路径
      String imgUrl = null;
      //上传
      String filename = UploadUtils.upload(file, uploadDir, file.getOriginalFilename());
      if (filename != null) {
        imgUrl = new File(uploadDir).getName() + "/" + filename;
      }
      map.put("code",0);
      map.put("msg","");
      map.put("data",imgUrl);
      return map;
    } catch (Exception e) {
      e.printStackTrace();
      map.put("code",500);
      map.put("msg","上传失败");
      map.put("data",null);
      return map;
    }
  }


}
