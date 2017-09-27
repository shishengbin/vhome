package com.sy.web.controller.ws;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsNewsClass;
import com.sy.modules.entity.ws.WsNewsClassUser;
import com.sy.modules.service.ws.WsNewsClassService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 * 新闻分类管理
 * @author LiuCheng 2013 09 07 
 *
 */
@Controller
@RequestMapping(value ="/ws")
public class WsNewsClassController extends PageSet {
//	日志记录
	private static final Logger log=LoggerFactory.getLogger(WsNewsClassController.class);
	
	@Autowired
	private WsNewsClassService newsclass;
	
	/**
	 * 所有新闻类型
	 * 无分页
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public String getAll(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...getAll()");
//		获取企业ID
		Long userid = SessionUtil.getUserId(request);
		Gson gson = new Gson();
		List<WsNewsClass> nc = newsclass.getAllClass(userid);
		return gson.toJson(nc);
	}
	
	
	/**
	 * 查询新闻分类
	 * 有分页
	 * 模糊查询，条件检索
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/newsClassAll")
	public String newsClassAll(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...newsClassAll()");
//		获取登录企业账户ID
		Long userid = SessionUtil.getUserId(request);
		Map<String,Object> param = DataTool.getParam(request,"newsCatText");
		param.put("userid",userid);
		this.setPagination(request, param);
		List<WsNewsClass> nc = newsclass.findAllNewsClassByPage(param);
		Integer count = newsclass.count(param);
		request.setAttribute("totalCount",count);
		request.setAttribute("newsClass",nc);
		return "ws/news/news_class";
	}
	
	
	/**
	 * 删除新闻类型
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param id 新闻ID
	 * @return
	 */
	@RequestMapping("/deleteNewsClass")
	@ResponseBody
	public String deleteNewsClass(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id){
		log.info("entering...WsProductController...deleteNewsClass()");
		WsNewsClass cl = newsclass.findById(id);
		String success,message;
		if (cl.getCount() == 0) {
			if (newsclass.deleteNewsCat(id)) {
				success = Constants.SUCCESS;
				message = Constants.MSG_DEL_SUCCESS;
			} else {
				success = Constants.ERROR;
				message = Constants.MSG_DEL_FAIL;
			}
		} else {
			success = Constants.ERROR;
			message = Constants.MSG_ERROR_exit;
		}
		return JsonUtil.transferJsonResponse(success,message, null, null,Constants.FORWARD, "ws/newsClassAll");
	}
	
	@RequestMapping(value="/preAddNewsCategory")
	public String preAddNewsCategory(){
		return "ws/news/add_news_class";
	}

	/**
	 * 增加修改新闻类型
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param wnc 新闻分类Bean
	 * @return
	 */
	@RequestMapping("/updateNewsClass")
	@ResponseBody
	public String updateNewsClass(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WsNewsClass wnc){
		log.info("entering...WsProductController...updateNewsClass()");
//		获取企业账户ID
		SysUser user = SessionUtil.getLoginUser(request);
		WsNewsClassUser cu = new WsNewsClassUser();
		cu.setCreateName(user.getUsername());
		cu.setUpdateName(user.getUsername());
		wnc.setCreateName(user.getUsername());
		wnc.setUpdateName(user.getUsername());
		cu.setUserId(user.getId());
		String str=null;
		if(wnc.getId()!=null){
//			修改
			newsclass.update(wnc);
			str = Constants.MSG_UPDATE_SUCCESS;
		}else{
//			增加
			newsclass.create(wnc,cu);
			str = Constants.MSG_ADD_SUCCESS;
		}
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,str,Constants.REL_NEWSCLASS,null,Constants.CLOSECURRENT,"ws/newsClassAll");
	}
	
	
	/**
	 * 单条新闻类型信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param id 新闻ID
	 * @return
	 */
	@RequestMapping("/showIdNewsClass")
	public String showIdNewsClass(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id){
		log.info("entering...WsProductController...showIdNewsClass()");
		WsNewsClass nc = newsclass.findById(id);
		request.setAttribute("nc",nc);
		return "ws/news/add_news_class";
	}
}
