package com.helo.demo.utils;


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
}
