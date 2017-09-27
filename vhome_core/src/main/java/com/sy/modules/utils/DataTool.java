package com.sy.modules.utils;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author LiuCheng 2013-9-25
 *
 */
public class DataTool {
	/**
	 * 将制定字符串用制定字符连接起来
	 * @param sign  连接字符
	 * @param data  将要连接字符串
	 * @return 最后结果
	 */
	public static StringBuffer stringConnect(String sign,String... data){
		StringBuffer data2 = new StringBuffer();
		for(int i = 0;i<data.length;i++){
			if(i!=0){
				data2.append(sign);
			}
			data2.append(data[i]);
		}
		return data2;
	}
	/**
	 * 获取表单提交数据
	 */
	public static Map<String,Object> getParam(HttpServletRequest request,String... param){
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i=0;i<param.length;i++){
			String a = request.getParameter(param[i]);
			if(a!=null&&a!=""){
//				传递给后台处理
				map.put(param[i],a);
//				同时将数据返回给页面
				request.setAttribute(param[i],a);
			}
		}
		return map;
	}
	
	/**
	 * 
	* @Title: getParament 
	* @Description: TODO(获取客户端请求中参数) 
	* @return Map<String,Object>    返回类型 
	* @author LiuCheng
	* @throws
	 */
	public static Map<String, Object> getParament(HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			String param = request.getParameter(key);
			try {
				if (param != null&&param!=""&&param!="-1"){
					if (request.getMethod().equalsIgnoreCase("Get")){
						param = new String(param.getBytes("iso-8859-1"), "UTF-8");
					}
					map.put(key,param);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		return map;
	}
	/**
	 * 增加日期
	 *如果是1则代表的是对年份操作，
	 *2是对月份操作，
	 *3是对星期操作，
	 *5是对日期操作，
	 *11是对小时操作，
	 *12是对分钟操作，
	 *13是对秒操作，
	 *14是对毫秒操作。
	 */
	public static Date addDateNumber(Object date,int va,int shu){
		Date d =null;
		if(date instanceof Date){
			d = (Date)date;
		}else if(date instanceof String){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				d = sdf.parse(date.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar c = Calendar.getInstance();
		if (date != null && date!="") {
			c.setTime(d);
			c.add(va,shu);
		}
		return c.getTime();
	}
	
	//生成注册编码
	public static String cretaeCoding(Long id){
		StringBuffer c=new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		if(id!=null){
			c.append("SY"+date+(id));
		}
		return c.toString();
	}
	
	public static String getUUID()
	{
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}
}
