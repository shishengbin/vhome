package com.sy.manage.controller;


import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.sy.manage.commons.Constants;
import com.sy.manage.commons.JsonUtil;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.AgtMenu;
import com.sy.modules.service.agt.AgtMenuService;


@Controller
public class AgtMenuController extends PageSet {
	private static final Logger log=LoggerFactory.getLogger(AgtMenuController.class);
	
	@Autowired
	private AgtMenuService amservice;
	
	//根据角色id查询菜单
	@RequestMapping(value="/findMenuByRoleId")
	@ResponseBody
	public String findMenuByRoleId(HttpServletRequest request,HttpServletResponse response)
	{
		log.info("entering...AgtMenuController...findMenuByRoleId()");
		if(SessionUtil.getAgtLoginUser(request, response)==null){
			return JsonUtil.transferJsonResponse(0, Constants.MSG_LOGIN_NO,null);
		}
		//获取登录用户角色id
		Long roleId= SessionUtil.getAgtLoginUser(request, response).getRole().getId();
	    List<AgtMenu> mlist=amservice.findMenusByRoleId(roleId);
	    //	    排序
	   // ComparatorMenu cm = new ComparatorMenu();
	   // Collections.sort(mlist,cm);
	    
	    Type type=new TypeToken<List<AgtMenu>>(){}.getType();
	    return JsonUtil.transformJson(Constants.SUCCESS,Constants.MSG_GET_SUCCESS , mlist, type);
		
	}
	
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(HttpServletRequest request)
	{
		System.out.println("<<<<");
		List<AgtMenu> mlist=amservice.getAll();
		Type type=new TypeToken<List<AgtMenu>>(){}.getType();
		return JsonUtil.transformJson("200", "success", mlist, type);
	}

}
