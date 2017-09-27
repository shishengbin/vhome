package com.sy.web.alipay.config;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
* 
* @ClassName: IndentUtil 
* @Description:(订单工具类) 
* @author LiuCheng
* @date 2013年12月12日 下午5:22:05 
*
*/
public class IndentUtil {
	public static final String format = "xml";//返回格式
	public static final String v = "2.0";//返回格式
	public static final String notify_url = "http://115.29.224.98/vhome_r/ws/indent/async_inform";//服务器异步通知页面路径
	public static final String call_back_url = "http://115.29.224.98/vhome_r/ws/indent/syn_inform";//页面跳转同步通知页面路径
	public static final String merchant_url = "http://115.29.224.98/vhome_r/ws/indent/alipay_break";//操作中断返回地址
	public static final String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";//支付宝网关地址
	/**
	 * 
	* @Title: yieldIndentNumber 
	* @Description:(生成订单编号) 
	* @return StringBuffer    返回类型 
	* @author LiuCheng
	* @throws
	 */
	public StringBuffer yieldIndentNumber(Long userid){
		StringBuffer number = new StringBuffer("fd58");
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
		String a=sdf.format(new Date());
		number.append(a);
		number.append(userid);//用户id
		return number;
	}
	/*
	 * 
	* @Title: yieldPostage 
	* @Description:(是否包邮) 
	* @return Integer    返回类型 
	* @author LiuCheng
	* @throws
	 */
	/*public Integer yieldPostage(WsIndent indent){
		//是否包邮规则
		//.......
		return 1;
	}*/
	
	/**
	 * 
	* @Title: cartNumber 
	* @Description: (生成购物车编号) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	public String cartNumber(Long userid){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		return userid+date;
	}
}
