package com.sy.web.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.commons.utils.MD5Util;
import com.sy.modules.entity.ws.WsHuman;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.service.ws.WsHumanService;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.SysuseraccountService;
import com.sy.modules.utils.WeixinUtil;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class AccountController {
	
	private static final Logger log=LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private SysuseraccountService sysuseraccservice;
	@Autowired
	private WsHumanService wshumanService;
	// 获取用户信息
	@RequestMapping("/getAccountInfo")
	@ResponseBody
	public String getAccountInfo(HttpServletRequest request) {
		long userid=SessionUtil.getuserid(request);
		Account ac = accountService.findUserAppinfo(userid);
		String Token=ac.getAc_interface();
		int index=Token.indexOf("=");
		String token=Token.substring(index,Token.length());
		ac.setAc_interface(Token.substring(0,index+1)+MD5Util.convertMD5(token));
		Gson gson = new Gson();
		return gson.toJson(ac);
	}
	// 获取用户信息
	@RequestMapping("/getOpenId")
	@ResponseBody
	public long getOpenId(HttpServletRequest request) {
		long userid=Long.parseLong(request.getParameter("accountid"));
		String code=request.getParameter("code");
		Account ac = accountService.findUserAppinfo(userid);
		String openid=WeixinUtil.getOpenID(code,ac.getAppId(),ac.getAppSecret());
		List<WsHuman> wshuman=wshumanService.findByAccount(openid);
		return wshuman.get(0).getId();
	}
	
	//绑定公众号信息
	@RequestMapping("/saveAccountInfo")
	@ResponseBody
	public String saveAccountInfo(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Account account) {
		//获取登陆id
		Long userid=SessionUtil.getUserId(request);
	    //根据登陆id找到微信公众号id
		Long accoutId= sysuseraccservice.getAccountId(userid);
		account.setId(accoutId);
		try{
		accountService.saveUserInfo(account);
		return "保存成功！";
		}catch (Exception e) {
			e.printStackTrace();
			return "保存失败！";
		}	
	}
		
	@RequestMapping("/wxlogin")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Account account){
		try{
			long userid=accountService.findUserID(account);
			if(userid>0){
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				return "success";
			}else
				return "fail";
		}catch (Exception e) {
			return "fail";
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	/**
	 *修改公众号信息
	 */
	@RequestMapping(value="/updateAccount")
	@ResponseBody
	public String updateAccount(HttpServletRequest request){
		log.info("entering...AccountController...updateAccount()");
		Integer actype=0;
		Long id=Long.valueOf(request.getParameter("id"));
		String ac_name=request.getParameter("ac_name");
		String province = request.getParameter("province");
		String city1 = request.getParameter("city1");
		String city2 = request.getParameter("city2");
		StringBuffer sb = DataTool.stringConnect("-",province,city1,city2);
		//icon地址
		String filename=request.getParameter("filename");
		//拆分图文封面路径，存DB
    	int dex=filename.indexOf(Constants.APPIMAGES);
    	String fileurl=filename.substring(dex+Constants.APPIMAGES.length());
    	
		String ac_original_id=request.getParameter("ac_original_id");
		String ac_wx_number=request.getParameter("ac_wx_number");
		String ac_type=request.getParameter("actype");
		if(StringUtils.isNotEmpty(ac_type)){
			actype=Integer.parseInt(ac_type);
		}
		String ac_email=request.getParameter("ac_email");
		Account account=null;
		if(StringUtils.isNotEmpty(ac_name)){
			account=new Account();
			account.setId(id);
			account.setAc_name(ac_name);
			account.setAc_city(sb.toString());
			account.setAc_pic(fileurl);
			account.setAc_original_id(ac_original_id);
			account.setAc_wx_number(ac_wx_number);
			account.setAc_email(ac_email);
			account.setAc_type(actype);
			accountService.update(account);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, null, null, null,null);	
		}
		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_UPDATE_FAIL, null, null, null,null);
	}
}
			

