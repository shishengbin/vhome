package com.sy.web.controller.wx;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.modules.entity.wx.FirstResp;
import com.sy.modules.entity.wx.resp.TextMessageResp;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.ArticleService;
import com.sy.modules.service.wx.FirstRespService;
import com.sy.modules.service.wx.FirstSubscribeService;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class FirstRespController {
	private static final Logger log=LoggerFactory.getLogger(FirstRespController.class);
	@Autowired
	private FirstRespService firstRespService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private FirstSubscribeService firstsubscribeservice;
	
	@RequestMapping("/getFirstResp")
	@ResponseBody
	public String findFirstResp(HttpServletRequest request) {
		long userid=SessionUtil.getuserid(request);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userid", userid); 
		map.put("msg_type",0);
		FirstResp firstResp = firstRespService.findFirstResp(map);
		if (firstResp == null) {
			return "{\"state\":\"0\"}";
		} else {
			switch (firstResp.getMsg_type()) {
			case 0:
				TextMessageResp textMessageResp = firstRespService.findContentByid(userid);
				return "{\"content\":\""+textMessageResp.getContent().replace("\n","@#2")+"\",\"msg_id\":\""+firstResp.getMsg_id()+"\",\"state\":\"1\"}";
			case 1:
				return "单图文消息";
			}
		}
		return "";
	}
	
	@RequestMapping("/saveFirstResp")
	@ResponseBody
	public String saveFirstResp(HttpServletRequest request,HttpServletResponse response,@ModelAttribute FirstResp firstResp) throws UnsupportedEncodingException{
		long userid=SessionUtil.getuserid(request);
		String content=request.getParameter("content");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("content", content);
		firstResp.setAccount_id(userid);
		try{
			if(firstResp.getMsg_type()==0){
				firstRespService.saveFirstResp(firstResp);
				map.put("msg_id", firstResp.getMsg_id());
				articleService.saveArticle(map);
				return String.valueOf(firstResp.getMsg_id());
			}
			if(firstResp.getMsg_type()==1){
				long article_id=Long.parseLong(request.getParameter("article_id"));
				firstRespService.saveFirstResp(firstResp);	
				map.put("msg_id", firstResp.getMsg_id());
				map.put("article_id", article_id);
				articleService.updateArticleForRelID(map);//将回复ID与图文关联
				return String.valueOf(firstResp.getMsg_id());
			}
			if(firstResp.getMsg_type()==2){
				long article_id=Long.parseLong(request.getParameter("article_id"));
				firstRespService.saveFirstResp(firstResp);	
				map.put("msg_id", firstResp.getMsg_id());
				map.put("article_id", article_id);
				articleService.updateMutiArticleForRelID(map);//将回复ID与图文关联
				return String.valueOf(firstResp.getMsg_id());
			}
			else
				return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "保存失败";
		}	
	}
	
	@RequestMapping("/updateFirstResp")
	@ResponseBody
	public String updateFirstResp(HttpServletRequest request,HttpServletResponse response,@ModelAttribute FirstResp firstResp) throws UnsupportedEncodingException{
		long userid=SessionUtil.getuserid(request);
		String content=request.getParameter("content");
		long msg_id=Long.parseLong(request.getParameter("msg_id"));
		long article_id=Long.parseLong(request.getParameter("article_id"));
		Map<String,Object> map=new HashMap<String,Object>();
		firstResp.setAccount_id(userid);
		map.put("content", content);
		map.put("msg_id", msg_id);
		map.put("userid", userid);
		map.put("article_id", article_id);
		if(article_id==0){
			try{
				firstRespService.updateFirstRespstate(userid);
				articleService.updateArticle(map);
				firstRespService.updateFirstResp(map);
				return "保存成功";
			}catch (Exception e) {
				e.printStackTrace();
				return "保存失败";
			}
		}
		else{			
			try{	
				firstRespService.updateFirstRespstate(userid);//设置当前用户的关注回复信息为空
				if(firstRespService.count(firstResp)==0){//如果当前用户未设置过单图文回复
					firstRespService.saveFirstResp(firstResp);//保存首次单图文回复
					map.put("msg_id",firstResp.getMsg_id());
					if(firstResp.getMsg_type()==1)
						articleService.updateArticleForRelID(map);//将回复ID与图文关联
					if(firstResp.getMsg_type()==2)
						articleService.updateMutiArticleForRelID(map);
				}else{			
					map.put("msg_type", firstResp.getMsg_type());
					firstResp=firstRespService.findFirstResp(map);
					map.put("msg_id", firstResp.getMsg_id());
					firstRespService.updateFirstResp(map);
					if(firstResp.getMsg_type()==1)
						articleService.updateArticleForRelID(map);//将回复ID与图文关联
					if(firstResp.getMsg_type()==2)
						articleService.updateMutiArticleForRelID(map);
				}
				return "保存成功";
			}catch (Exception e) {
				e.printStackTrace();
				return "保存失败";
			}
		}
	}
	
	
	//---------------------------------------------sss---------------------------------------------------------

}
