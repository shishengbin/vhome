package com.sy.web.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.entity.wx.Kword;
import com.sy.modules.service.wx.ArticleService;
import com.sy.modules.service.wx.KwordService;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class KWordController {
	@Autowired
	private KwordService kwdService;
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/saveKeyword")
	@ResponseBody
	public String saveKeyword(HttpServletRequest request,HttpServletResponse response) {
		long userid=SessionUtil.getuserid(request);
		Map<String, Object> map = new HashMap<String, Object>();
		int article_type = Integer.parseInt(request.getParameter("article_type"));
		String kwd_name = request.getParameter("Kwd_name");
		String content = request.getParameter("content");
		long article_id=Long.parseLong(request.getParameter("article_id"));
		Kword kw = new Kword();
		kw.setArticle_type(article_type);
		kw.setKwd_name(kwd_name);
		kw.setAccount_id(userid);
		try {
			kwdService.saveKeyword(kw);	
			map.put("kwd_id", kw.getKwd_id());
			if(article_type==0){
				map.put("content", content);
				kwdService.saveKeywordContent(map);
			}
			if(article_type==1){
				map.put("article_id", article_id);
				articleService.updateArticleForRelID(map);
			}
			if(article_type==2){
				map.put("article_id", article_id);
				articleService.updateMutiArticleForRelID(map);
			}
		}catch (Exception e) {
				e.printStackTrace();
				return "保存失败！";
			}
			return String.valueOf(kw.getKwd_id());
	}
	
	@RequestMapping("/findKwResp")
	@ResponseBody
	public String findKwResp(HttpServletRequest request){
		long userid=SessionUtil.getuserid(request);
		List<Kword> kword=kwdService.findKwResp(userid);
		Gson gson=new Gson();
		return gson.toJson(kword);
	}
	
	@RequestMapping("/findOneKw")
	@ResponseBody
	public String findOneKw(HttpServletRequest request,HttpServletResponse response){
		long kwd_id=Long.parseLong(request.getParameter("kwd_id"));
		Gson gson=new Gson();
		Kword kword=kwdService.findOneKw(kwd_id);
		return gson.toJson(kword);
	}
	
	@RequestMapping("/updateKw")
	@ResponseBody
	public String updateKw(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Kword kword){
		long article_id=Long.parseLong(request.getParameter("article_id"));
		long kwd_id=Long.parseLong(request.getParameter("kwd_id"));
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			if(article_id==-1){//文本
				kwdService.updateKw(kword);			
				return findKwResp(request);
			}
			if(kword.getArticle_type()==1){//单图文
				if(articleService.count(kwd_id)==1){//第一次文本回复转图文回复
					map.put("kwd_id", kwd_id);
					articleService.deleteSinArticle(map);		
					map.put("article_id", article_id);		
					kwdService.updateKwForArticle(kword,map);
					return findKwResp(request);
				}else{
					map.put("kwd_id", kwd_id);
					map.put("article_id", article_id);	
					kwdService.updateKw(kword);
					articleService.updateArticleForRelID(map);
					return findKwResp(request);
				}			
			}else{//多图文
				if(articleService.count(kwd_id)==1){//第一次文本回复转图文回复
					map.put("kwd_id", kwd_id);
					articleService.deleteSinArticle(map);		
					map.put("article_id", article_id);		
					kwdService.updateKwForMutiArticle(kword,map);
					return findKwResp(request);
				}else{
					map.put("kwd_id", kwd_id);
					map.put("article_id", article_id);	
					kwdService.updateKw(kword);
					articleService.updateMutiArticleForRelID(map);
					return findKwResp(request);
				}
			}
				
		}catch (Exception e) {
			e.printStackTrace();
			return "更新失败！";
		}	
	}
	
	@RequestMapping("/deleteKw")
	@ResponseBody
	public String deleteKw(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("kwd_id", Integer.parseInt(request.getParameter("kwd_id")));
		try{
			kwdService.deleteKw(map);
			return findKwResp(request);
		}catch (Exception e) {
			e.printStackTrace();
			return "删除失败！请重新操作！";
		}
	}
}
