package com.sy.manage.controller;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.manage.commons.PageSet;
import com.sy.modules.entity.agt.Keyword;
import com.sy.modules.entity.agt.KwType;
import com.sy.modules.service.agt.KeywordService;
import com.sy.modules.service.agt.KwTypeService;

/**
 * 管理系统所有用户
 * 
 * @author sss
 * @date 2013-8-15 
 */
@Controller
@RequestMapping(value = "/agt")
public class KeywordController extends PageSet {
	private static final Logger log = LoggerFactory.getLogger(KeywordController.class);

	@Autowired
	private KeywordService kwservice;

	@Autowired
	private KwTypeService ktservice;

	// 查询关键词
	/*@RequestMapping(value = "/findKeyWordNyName")
	@ResponseBody
	public String findKeyWordNyName(HttpServletRequest request) throws Exception {
		log.info("entering...KeywordController...getKeyWord()");
		request.setCharacterEncoding("utf-8");
		// 接收数据
		Gson gson = new Gson();
		String keyword = request.getParameter("keyword");
		String jsonstr = null;
		Keyword keyw=null;
		List<Keyword> kwlist=new ArrayList<Keyword>();
		if (!StringUtils.isBlank(keyword)) {
			String kw = null;
			String[] sarray = keyword.split(",");
			for (int i = 0; i < sarray.length; i++) {
				kw = sarray[i];
				Keyword key = kwservice.findKeywordByName(kw);
				if (key != null) {
					kwlist.add(key);
				} else {
					Integer in = KeywordUtil.getKeywordType(kw);
					KwType kt = ktservice.findKwTypeById(in);
					keyw=new Keyword();
	                keyw.setKw_name(kw);
	                keyw.setKw_status(0);
	                keyw.setTprice(kt.getTprice());
	                keyw.setTname(kt.getTname());
	                kwlist.add(keyw);
				}
			}
			jsonstr="{\"total\":"+kwlist.size()+",\"rows\":"+gson.toJson(kwlist)+"}";
		}
		return jsonstr;
	}*/
	
	
	/**
	 * @author XZX
	 * 
	 * 跨域关键词查询
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping(value ="/kyFindKeyWordNyName")
	@ResponseBody
	public String kyFindKeyWordNyName(HttpServletRequest request) throws Exception {
		log.info("entering...KeywordController...getKeyWord()");
		request.setCharacterEncoding("utf-8");
		// 接收数据
		Gson gson = new Gson();
		String keyword ="";
		try {
			request.setCharacterEncoding("utf-8");
			keyword=request.getParameter("keyword");
			keyword= new String(keyword.getBytes("ISO-8859-1"),"utf-8"); 
		   
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("关键词"+keyword);
		String jsonstr = null;
		Keyword keyw=null;
		List<Keyword> kwlist=new ArrayList<Keyword>();
		if (!StringUtils.isBlank(keyword)) {
			String kw = null;
			String[] sarray = keyword.split(",");
			for (int i = 0; i < sarray.length; i++) {
				kw = sarray[i];
				Keyword key = kwservice.findKeywordByName(kw);
				if (key != null) {
					kwlist.add(key);
				} else {
					Integer in = KeywordUtil.getKeywordType(kw);
					KwType kt = ktservice.findKwTypeById(in);
					keyw=new Keyword();
	                keyw.setKw_name(kw);
	                keyw.setKw_status(0);
	                keyw.setTprice(kt.getTprice());
	                keyw.setTname(kt.getTname());
	                kwlist.add(keyw);
				}
			}
			jsonstr="{\"total\":"+kwlist.size()+",\"rows\":"+gson.toJson(kwlist)+"}";
		}
		
	   String jsonp=request.getParameter("jsonpcallback");
	   return jsonp+"("+jsonstr+")";
	}*/
}
