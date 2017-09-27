package com.sy.web.controller.wx;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.resp.Article;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.ArticleService;
import com.sy.modules.service.wx.ArticleaccountService;
import com.sy.modules.service.wx.MyArticleService;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class ArticleController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(ArticleController.class);
	
	private static final String APPIMAGES="appimages/";
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MyArticleService myarticleservice;
	@Autowired
	private ArticleaccountService articleaccountservice;

	@RequestMapping("/saveSinArticle")
	@ResponseBody

	public String saveSinArticle(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		long userid=SessionUtil.getuserid(request);
		Account ac = accountService.findUserAppinfo(userid);
		//InetAddress addr = InetAddress.getLocalHost();
		//String ip=addr.getHostAddress().toString();//获得本机IP
		String title=request.getParameter("title");
		String picurl=request.getParameter("picurl");
		String description=request.getParameter("description");
		String url=request.getParameter("url");
		long article_id=Long.parseLong(request.getParameter("article_id"));
		Article a=new Article();
		Map<String,Object> map=new HashMap<String,Object>();
		//获取openiD的 url
		String temp="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String REDIRECT_URI="";
		if(url.contains("product_list.html")){
			REDIRECT_URI=java.net.URLEncoder.encode(url+"&accountid="+userid,"utf-8");
			a.setUrl(temp.replace("APPID",ac.getAppId()).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE","snsapi_base").replace("STATE", "123"));
		}else{
			a.setUrl(url);
		}	
		a.setTitle(title);
		a.setDescription(description);
		//a.setPicUrl("http://localhost:8080/vhome/wx/"+picurl);//本地测试
		a.setPicUrl("http://www.szsuoyuan.com/vhome/wx/"+picurl);//服务器上使用(项目发布时改成微信服务器IP)				
		try{  
			if(article_id==0){
				articleService.saveSinArticle(a);
				map.remove(map);
				map.put("article_id", a.getArticle_id());
				map.put("account_id",userid);
				map.put("mutiarticle_id", 0);
				articleService.saveSinRelArticle(map);
				return String.valueOf(a.getArticle_id());//添加成功，返回添加的图文ID
			}else{
				a.setArticle_id(article_id);
				articleService.updateSinArticle(a);
				return "-1";//更新成功时，返回-1
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "-2";//出现错误时，返回-2
			}
	}
	
	@RequestMapping("/findSinArticle")
	@ResponseBody
	public String findSinArticle(HttpServletRequest request,HttpServletResponse response){
		long userid=SessionUtil.getuserid(request);
		long kwd_id=Long.parseLong(request.getParameter("kwd_id"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", userid);
		map.put("kwd_id", kwd_id);
		List<Article> article=articleService.findSinArticle(map);
		Gson gson=new Gson();
		return gson.toJson(article);
	}
	
	@RequestMapping("/deleteSinArticle")
	@ResponseBody
	public String deleteSinArticle(HttpServletRequest request,HttpServletResponse response){	
		long article_id=Long.parseLong(request.getParameter("article_id"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("article_id", article_id);
		try{
			articleService.deleteSinArticle(map);
			articleService.deleteSinRelArticle(article_id);
			return "删除成功！";
		}catch (Exception e) {
			e.printStackTrace();
			return "删除失败！请重新操作！";
		}
	}
	
	@RequestMapping("/saveMutiArticle")
	@ResponseBody
	public String saveMutiArticle(HttpServletRequest request) throws UnsupportedEncodingException{
		long userid=SessionUtil.getuserid(request);
		Account ac = accountService.findUserAppinfo(userid);
		String[] title=request.getParameterValues("muti_title");
		String[] picurl=request.getParameterValues("muti_picurl");
		String[] url=request.getParameterValues("muti_url");
		String[] muti_ChildmodelName=request.getParameterValues("muti_ChildmodelName");
		String[] muti_modelName=request.getParameterValues("muti_modelName");
		String[] muti_type=request.getParameterValues("muti_type");
		int length=title.length;
		Article[] arrArticle=new Article[length];
		List<Article> articlelist=new ArrayList<Article>();
		for(int i=0;i<length;i++){
			arrArticle[i]=new Article();
			arrArticle[i].setTitle(title[i]);
			if(muti_type[i].equals("0")){
				if(muti_modelName[i].equals("0")){
					if(muti_ChildmodelName[i].equals("-1"))
						arrArticle[i].setUrl("http://www.szsuoyuan.com/vhome/ws/firminfo?id="+userid);
					if(muti_ChildmodelName[i].equals("-2"))
						arrArticle[i].setUrl("http://www.szsuoyuan.com/vhome/ws/appShowPicture?id="+userid);
				}
				if(muti_modelName[i].equals("1")){
					arrArticle[i].setUrl("http://www.szsuoyuan.com/vhome/ws/app/newsClass?classid="+muti_ChildmodelName[i]);
				}
				if(muti_modelName[i].equals("2")){
					String temp="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
					String REDIRECT_URI="";
					String temp_url="http://www.szsuoyuan.com/vhome/ws/wxpages/product_list.html?class1id="+muti_ChildmodelName[i]+"&accountid="+userid;
					REDIRECT_URI=java.net.URLEncoder.encode(temp_url,"utf-8");
					arrArticle[i].setUrl(temp.replace("APPID",ac.getAppId()).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE","snsapi_base").replace("STATE", "123"));
				}
				if(muti_modelName[i].equals("3")){
					arrArticle[i].setUrl("http://www.szsuoyuan.com/vhome/ws/getSMContent?id="+muti_ChildmodelName[i]);
				}
			}
			else
				arrArticle[i].setUrl(url[i]);
			//arrArticle[i].setPicUrl("http://localhost:8080/vhome/wx/"+picurl[i]);	
			arrArticle[i].setPicUrl("http://www.szsuoyuan.com/vhome/wx/"+picurl[i]);//服务器上使用(项目发布时改成微信服务器IP)
			articlelist.add(arrArticle[i]);
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		//保存第一个大图
		try{
			articleService.saveMutiArticle(arrArticle[0]);
			long firstID=arrArticle[0].getArticle_id();//第一个大图的ID
			map.put("article_id", arrArticle[0].getArticle_id());
			map.put("account_id",userid);
			map.put("mutiarticle_id",firstID);
			articleService.saveSinRelArticle(map);
			//保存其余小图
			for(int i=1;i<length;i++){
				articleService.saveMutiArticle(arrArticle[i]);
				map.put("article_id", arrArticle[i].getArticle_id());
				map.put("account_id",userid);
				map.put("mutiarticle_id",firstID);	
				articleService.saveSinRelArticle(map);
			}
			return findMutiArticle(request);
		}catch (Exception e) {
			e.printStackTrace();
			return "保存失败！请重新操作！";
		}		
	}
	
	@RequestMapping("/findMutiArticle")
	@ResponseBody
	public String findMutiArticle(HttpServletRequest request){
		long userid=SessionUtil.getuserid(request);
		long kwd_id=Long.parseLong(request.getParameter("kwd_id"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", userid);
		map.put("kwd_id", kwd_id);
		List<Article> a=articleService.findMutiArticle(map);
		Gson gson=new Gson();
		return gson.toJson(a);
	}
	
	@RequestMapping("/deleteMutiArticle")
	@ResponseBody
	public String deleteMutiArticle(HttpServletRequest request){
		String type=request.getParameter("picType");
		Map<String,Object> map=new HashMap<String,Object>();
		if(type.equals("smallPic")){//删除小图
			long article_id=Long.parseLong(request.getParameter("article_id"));
			map.put("article_id", article_id);
			try{
				articleService.deleteSinArticle(map);
				articleService.deleteSinRelArticle(article_id);
				return "删除成功！";
			}catch (Exception e) {
				e.printStackTrace();
				return "删除失败！";
			}		
		}else{//删除第一张大图
			long article_id=Long.parseLong(request.getParameter("article_id"));
			map.put("mutiarticle_id", article_id);
			List<Article> a=articleService.findMutiArticle(map);
			long[] articleIDs=new long[a.size()];
			for(int i=0;i<articleIDs.length;i++){
				articleIDs[i]=a.get(i).getArticle_id();
			}
			try{
				for(int i=0;i<articleIDs.length;i++){
					map.put("article_id", articleIDs[i]);
					articleService.deleteSinArticle(map);
					articleService.deleteSinRelArticle(articleIDs[i]);	
				}
				return "删除成功！";
			}catch (Exception e) {
				e.printStackTrace();
				return "删除失败！";
			}
		}
	}	
	
	
	
	//----------------------------------------------------sss-------------------------------------------------------------------------
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
