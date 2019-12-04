package com.helo.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author wangxl
 * @ClassName UploadUtils
 * @Description TODO
 * @date 2019/11/8 9:30
 */
public class UploadUtils {

  public static String upload(MultipartFile file, String path, String fileName) throws Exception {
    // 生成新的文件名
    String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
    File dest = new File(realPath);
    // 判断文件父目录是否存在
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdir();
    }
    // 保存文件
    file.transferTo(dest);
    return dest.getName();
  }
}
