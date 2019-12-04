package com.helo.demo.controller;

import com.helo.demo.model.FileMeta;
import com.helo.demo.utils.CommonUtil;
import com.helo.demo.utils.ConfigUtil;
import com.helo.demo.utils.SftpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UploadController
 * @Description TODO
 * @Date 2019/12/4 8:57 下午
 * @Created by wangxianlin
 */
@Controller
@RequestMapping("uploadApi")
public class UploadController {

    //跳转到图片上传界面
    @GetMapping("/toUploadLocalView")
    public String toUploadImage() {
        return "upload/uploadToLocal";
    }

    //图片上传接口
    @PostMapping("/uploadToLocal")
    @ResponseBody
    public Map<String,Object> uploadToLocal(@RequestParam("file") MultipartFile file,
                                           HttpServletResponse response) {
        Map<String,Object> result  = new HashMap<>();
        response.setContentType("text/html;charset=UTF-8");
            String fileName = file.getOriginalFilename();
            try {
                Date date = new Date();
                String picDir = ConfigUtil.getValue("image");
                String relativeDir = getRelativeDir(date);
                File fileDir = new File(picDir + relativeDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                //新的文件名
                String newName = CommonUtil.format(date, "HHmmssSSS") +
                        Math.round(Math.random() * 8999 + 1000) +
                        fileName.substring(fileName.lastIndexOf("."));
                //群头像地址
                String imgPath = relativeDir + newName;
                file.transferTo(new File(picDir + imgPath));
                result.put("code", 200);
                result.put("msg", fileName);
                result.put("data", imgPath);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("code", 500);
                result.put("msg", fileName);
                result.put("data", "图片上传发生未知异常，请联系管理员！");
            }
            return result;
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

    /***
     * @Author wangxl
     * @Description //TODO 获取文件上传路径
     * @Date 2:01 下午 2019/12/2
     * @Param [date]
     * @return java.lang.String
     **/
    public String getFilePath(Date date,String upload) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH) + 1;
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        // 文件路径 upload/groupID/年月日
        String monthStr = month < 10 ? "0" + month : "" + month;
        String dayStr = day < 10 ? "0" + day : "" + day;
        String filePath = upload + year + monthStr + dayStr;
        return filePath;
    }

    //跳转到图片上传界面
    @GetMapping("/uploadToServerView")
    public String uploadToServerView() {
        return "upload/uploadToServer";
    }

    @PostMapping("/uploadToServer")
    @ResponseBody
    public Map<String,Object> uploadToServer(@RequestParam("file") MultipartFile file) {
        Map<String,Object> result  = new HashMap<>();
        //上传时间
        Date createTime = new Date();
        String upload = ConfigUtil.getValue("server_file");
        //构造文件路径
        //绝对路径
        String fileAbsDir = getFilePath(createTime,upload);
        //2.11附加非空判断 前台可能上传为空的情况
        if(file.isEmpty()) {
            //通过构造一个文件大小为-1的文件元数据对象返回
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileSize("-1");
            result.put("code", 500);
            result.put("msg", "文件不能为空");
        }
        //获取文件后缀名前面的点的位置
        int i=file.getOriginalFilename().lastIndexOf(".");
        //取文件后缀检验文件类型
        String fileSuffix = file.getOriginalFilename().substring(i);

        //上传文件大小限制为10M
        if(file.getSize()<=10485760){
            //2.3 创建文件元数据
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileSize(Math.floor(file.getSize()/(double)1024)+" Kb");
            fileMeta.setFileType(file.getContentType());
            String loadedFileName ="";
            loadedFileName = new SimpleDateFormat("ddHHmmssSSS").format(createTime)+Math.round(Math.random()*8999+1000)+fileSuffix;
            //将上传后的路径加文件名返回。用来刷新头像
            fileMeta.setFileName(fileAbsDir+"/"+loadedFileName);
            //因为防火墙问题，改用sftp
            try {
                SftpUtil.upload(file.getInputStream(), fileAbsDir, loadedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.put("code", 200);
            result.put("msg", "上传成功");
            result.put("data", fileMeta.getFileName());

        }else{
            //通过构造一个文件大小为-1的文件元数据对象返回
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileSize("-1");
            result.put("code", 500);
            result.put("msg", "文件不能超过10 MB");
        }
        return result;
    }
}
