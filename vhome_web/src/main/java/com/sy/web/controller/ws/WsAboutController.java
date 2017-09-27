package com.sy.web.controller.ws;


import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.sy.web.commons.Constants;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsAbout;
import com.sy.modules.entity.ws.WsAboutUser;
import com.sy.modules.service.ws.WsAboutService;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *公司简介
 *#@author LiuCheng 2013-8-27 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsAboutController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsAboutController.class);
	
	@Autowired 
	private WsAboutService aboutservice;

	/**
	 * 查看公司简介
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/showAbout")
	public String showAbout(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...");
		//获取用户ID
		SysUser u=(SysUser) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION_KEY);
		WsAbout wa = aboutservice.selectAbout(u.getId());
		//如果公司简介还不存在就创建一个
		if(wa==null){
			wa = new WsAbout();
			wa.setCreateName(u.getUsername());
			aboutservice.addAbout(wa,new WsAboutUser(u.getId(),u.getUsername()));
		}
		request.setAttribute("about",wa);
		return "ws/about/about";
	}
	
	/**
	 * 返回公司简介的json格式
	 * 首页显示
	 * @author LiuCheng
	 * @param request
	 * @return
	 */
	@RequestMapping("/showAboutIndex")
	@ResponseBody
	public String showNewsIndex(HttpServletRequest request){
		log.info("entering...WsProductController...showNewsIndex()");
		//获取用户id
		Long userid = SessionUtil.getUserId(request);
		WsAbout about = aboutservice.selectAbout(userid);
		Type type=new TypeToken<WsAbout>(){}.getType();
		return JsonUtil.transformJson(Constants.SUCCESS, Constants.MSG_GET_SUCCESS, about, type);
	}
	
	/**
	 * 修改公司简介
	 * @author LiuCheng
	 * @param response
	 * @param request
	 * @param about
	 * @return
	 */
	@RequestMapping("/updateAbout")
	@ResponseBody
	public String updateAbout(HttpServletResponse response,HttpServletRequest request,@ModelAttribute WsAbout about){
		log.info("entering...WsProductController...updateAbout()");
		aboutservice.update(about);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS,"","","","");
	}
	
	
	//----------------------------------------------wap-------------------------------------------------
	
	/**
	 * 返回公司简介的json格式
	 * 首页显示
	 * @author LiuCheng
	 * @param request
	 * @return
	 */
	@RequestMapping("wap/showAboutForWap")
	@ResponseBody
	public String showAboutForWap(HttpServletRequest request){
		log.info("entering...WsProductController...showAboutForWap()");
		long userid=0;
		//获取用户id
		String useridstr=request.getParameter("id");
		if(StringUtils.isNotBlank(useridstr)){
			userid=Long.valueOf(useridstr);
			WsAbout about = aboutservice.selectAbout(userid);
			Type type=new TypeToken<WsAbout>(){}.getType();
			return JsonUtil.transformJson(Constants.SUCCESS, Constants.MSG_GET_SUCCESS, about, type);
		}
		return null;
	}
	
	
	
	
	
	
	
	

}
