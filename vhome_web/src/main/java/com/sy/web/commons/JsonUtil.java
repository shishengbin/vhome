package com.sy.web.commons;

import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sy.commons.entity.DwzResult;
import com.sy.commons.entity.JsonResult;


/**
 *操作json
 *@author sss 2013-8-10 
 */
public class JsonUtil {
	
	public static String transferJsonResponse(String statusCode, String message, String navTabId,String rel,String callbackType,String forwardUrl) {
		DwzResult d = new DwzResult(statusCode, message, navTabId,rel,callbackType,forwardUrl);
		return d.toJsonString();
	}
	public static String transferJsonResponse(Integer statusCode,String message,String forwardUrl){
		DwzResult d = new DwzResult(statusCode.toString(), message,forwardUrl);
		return d.toJson();
	}
	
	//------------------------------------------------------------------------------------------------------
	//object=>json
	public static String transformJson(String code, String msg, Object data,Type dataType) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String dataStr = "";
		if (data != null && dataType != null)dataStr = gson.toJson(data, dataType);
		if (data != null && dataType == null)dataStr = "{\"val\":\""+ String.valueOf(data)+"\"}";
		JsonResult d = new JsonResult(code, msg, dataStr);
		return d.toJsonString();
	}
	
	
	//解析json=>map
	public static Map<String,String> getMapFromJson(HttpServletRequest request){
		String jsonStr = request.getParameter("jsonStr");
		if(StringUtils.isBlank(jsonStr))return null;
		Gson gson = new GsonBuilder().create();
		Map<String,String> map=gson.fromJson(jsonStr,new TypeToken<Map<String, String>>(){}.getType());
		return map;
	}
}
