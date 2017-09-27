package com.sy.web.controller.ws;


import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.common.GlobalConstants;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsNews;
import com.sy.modules.entity.ws.WsNewsUser;
import com.sy.modules.service.ws.WsNewsService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *行业资讯
 *#@author LiuCheng 2013-8-27 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsNewsController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsNewsController.class);
	@Autowired 
	private WsNewsService newservice;

	/**
	 * 显示新闻信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param paging
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/showNews")
	public String showNews(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		log.info("entering...WsProductController...showNews()");
//		获取用户id
		Long userid = SessionUtil.getUserId(request);
		Map<String,Object> param = DataTool.getParam(request,"title","date","newsClass");
		Object date = param.get("date");
		if(date!=null)
		param.put("date2",DataTool.addDateNumber(date,5,1));
		param.put("userid",userid);
		this.setPagination(request, param);
		List<WsNews> list=newservice.findAllNewsByPage(param);
		//查询总数
		int count = newservice.count(param);
		request.setAttribute("newsList",list);
		request.setAttribute("totalCount",count);
		return "ws/news/news";
	}
	
	/**
	 * @author LiuCheng
	 * @param request
	 * @return
	 */
	@RequestMapping("/showNewsIndex")
	@ResponseBody
	public String showNewsIndex(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...showNewsIndex()");
		//获取用户id
		Long userid = SessionUtil.getUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid",userid);
		this.setPagination(request, map);
		List<WsNews> list = newservice.findIndexNewsByPage(map);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/preAddNews")
	public String preAddNews(){
		return "ws/news/add_news";
	}
	
	/**
	 * 添加修改新闻信息
	 * @author LiuCheng
	 * @param response
	 * @param request
	 * @param news
	 * @param cmf
	 * @throws IOException
	 */
	@RequestMapping(value="/addNews")
	@ResponseBody
	public String addNews(HttpServletResponse response,HttpServletRequest request,@ModelAttribute WsNews news) {
		log.info("entering...WsProductController...showNews()");
		String success=null;
		String message=null;
		String fileurl=null;
		String filename=request.getParameter("filename");
		if(StringUtils.isNotEmpty(filename)){
			//拆分图文封面路径，存DB
	    	int dex=filename.indexOf(Constants.APPIMAGES);
	    	fileurl=filename.substring(dex+Constants.APPIMAGES.length());
		}
//		验证
		if(news!=null){
			if(news.getNewsClass()==null || news.getNewsClass().getId()==null)
			{
				success = Constants.ERROR;
				message = Constants.MSG_NEWSCAT_NOTNULL;
			}else if(news.getNewsTitle()==null){
				success = Constants.ERROR;
				message = Constants.MSG_NEWSTITLE_NOTNULL;
			}else{
//				初始化 
				SysUser user = SessionUtil.getLoginUser(request);
				WsNewsUser u = new WsNewsUser();
				u.setCreateName(user.getUsername());
				u.setUserId(user.getId());
				u.setUpdateName(user.getUsername());
				news.setCreateName(user.getUsername());
				news.setUpdateName(user.getUsername());
				news.setNewsRemark(fileurl);
				
//				修改
				if(news.getId()!=null){
					newservice.update(news);
					success = Constants.SUCCESS;
					message = Constants.MSG_UPDATE_SUCCESS;
				}else{
//					增加
					if(newservice.addNews(news,u)){
						success = Constants.SUCCESS;
						message = Constants.MSG_ADD_SUCCESS;
					}else {
						success = Constants.ERROR;
						message = Constants.MSG_ADD_FAIL;
					}
				}
			}
		}
		return JsonUtil.transferJsonResponse(success,message,Constants.REL_NEWSMANAGER,null,Constants.CLOSECURRENT,"ws/showNews");
	}
	
	
	/**
	 * 删除新闻
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param newsid
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteNews")
	@ResponseBody
	public String deleteNews(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id",required=false)Long newsid){
		log.info("entering...WsProductController...deleteNews()");
		String success,message;
		if(newsid==null)
		{
			success = Constants.ERROR;
			message = Constants.MSG_NEWSID_NOTNULL;
		}else{
			if(newservice.deleteNews(newsid)){
				success = Constants.SUCCESS;
				message = Constants.MSG_DEL_SUCCESS;
			}else{
				success = Constants.ERROR;
				message = Constants.MSG_DEL_FAIL;
			}
		}
		return JsonUtil.transferJsonResponse(success,message,null,null,"forward","ws/showNews");
	}
	
	/**
	 * 单条新闻信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param newsid
	 * @return
	 */
	
	
	@RequestMapping(value="/newsDetails")
	public String showNews(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long newsid) {
		log.info("entering...WsProductController...showNews()");
		WsNews news = newservice.findById(newsid);
		request.setAttribute("news",news);
		if(request.getParameter("result").equals("xg")){
			request.setAttribute("newsRemark",GlobalConstants.DB_IMAGE_FILE+GlobalConstants.SEPARATOR+Constants.APPIMAGES+news.getNewsRemark());
			return "ws/news/add_news";
		}
		else
			return "ws/news/show_news";
	}

	/**
	 * 显示新闻LOGO
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param newsid
	 */
	@RequestMapping(value = "/showNewsImg")
	public void inputste(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "id") Long newsid) {
		log.info("entering...WsProductController...inputste()");
		response.setContentType("image/jpeg;charset=UTF-8");
		WsNews ne = newservice.findById(newsid);
	}
}
