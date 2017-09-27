package com.sy.manage.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.manage.commons.PageSet;
import com.sy.modules.entity.agt.AgtImpower;
import com.sy.modules.service.agt.AgtImpowerService;

@Controller
@RequestMapping(value="/cminic")
public class AgtaImpowerController extends PageSet {
	private static final Logger log=LoggerFactory.getLogger(AgtaImpowerController.class);
	
	@Autowired
	private AgtImpowerService agtimpowerservice;
	
	/**
	 *根据授权单位名称查询单位信息 
	 */
	@RequestMapping(value="/findAgtImpowerByName")
	@ResponseBody
	public String findAgtImpowerByName(HttpServletRequest request)
	{
		log.info("entering...AgtaImpowerController...findAgtImpowerByName()");
		//获取名称
		String imname=request.getParameter("imname");
		AgtImpower impower=agtimpowerservice.findAgtImpowerByName(imname);
		Gson gson=new Gson();
		return gson.toJson(impower);
	}
	
	
	/**
	 * @author XZX
	 * 
	 * 
	 * 根据授权单位名称查询单位信息 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/kyFindAgtImpowerByName")
	@ResponseBody
	public String kyFindAgtImpowerByName(HttpServletRequest request)
	{
		log.info("entering...AgtaImpowerController...findAgtImpowerByName()");
		//获取名称
		String imname="";
		try {
			request.setCharacterEncoding("utf-8");
			 imname=request.getParameter("imname");
		     imname= new String(imname.getBytes("ISO-8859-1"),"utf-8"); 
		   
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(imname);
		AgtImpower impower=agtimpowerservice.findAgtImpowerByName(imname);
		Gson gson=new Gson();
		String jsonp=request.getParameter("jsonpcallback");
		return  jsonp+"("+gson.toJson(impower)+")";
	}
	
	

}
