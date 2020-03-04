package com.helo.demo.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 常用工具�?
 * @author fan.fan
 * @date 2014-3-31 上午11:18:45
 */
public class CommonUtil {
	/**
	 * 验证�?��字符串是否有�?既不是null,也不是空字符�?
	 * @param value
	 * @return
	 */
	public static final boolean hasValue(String value) {
		return value != null && value.trim().length() > 0;
	}

	/**
	 * 格式化日�?
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static final String format(Date date, String formatPattern) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(formatPattern).format(date);
	}

	/**
	 * 将字符串按格式，格式化成日期
	 * @param stringValue
	 * @param formatPattern
	 * @return
	 */
	public static final Date parse(String stringValue, String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		try {
			return format.parse(stringValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* @Description: 将MultipartFile转成File
	* @params: [multiFile]
	* @return: java.io.File
	* @Author: wangxianlin
	* @Date: 2020/3/4 8:30 PM
	*/ 
	public static File MultipartFileToFile(MultipartFile multiFile) {
		// 获取文件名
		String fileName = multiFile.getOriginalFilename();
		// 获取文件后缀
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		// 若需要防止生成的临时文件重复,可以在文件名后添加随机码

		try {
			File file = File.createTempFile(fileName, prefix);
			multiFile.transferTo(file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
