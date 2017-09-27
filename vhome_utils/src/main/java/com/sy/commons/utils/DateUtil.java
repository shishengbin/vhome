package com.sy.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * @author sss 2013-6-18 时间格式
 */
public class DateUtil {

	// 时间格式
	public static final String DATEFORMAT = "yyy-MM-dd";
	public static final String MMDDYYYY = "MM/dd/yyyy";
	public static final String YYYYMMDD = "yyyy/MM/dd";
	public static final String MM_DD_YYYY = "MM-dd-yyyy";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmssSSS";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 用输入的模板参数，格式化输入的日期参数，并返回格式化后的字符串 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将字符串转换成日期
	 */
	public static Date getFormatDate(String str) throws Exception {
		Date date = null;
		if (str != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
			date = sdf.parse(str);
		}
		return date;
	}
	/**
	 * 将string对象转换成date对象
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date convertStringToDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//String s = "2012-12-20";
		//Date d = DateUtil.getFormatDate(s);
		//System.out.println(d);

		 Date d=new Date();
		 String s=DateUtil.formatDate(d, YYYY_MM_DD_HH_MM_SS);
		 System.out.println(s);
	}
}
