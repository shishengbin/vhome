package com.sy.web.controller.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsHuman;
import com.sy.modules.entity.ws.WsMessages;
import com.sy.modules.entity.ws.WsMessagesUser;
import com.sy.modules.service.ws.WsHumanService;
import com.sy.modules.service.ws.WsMessagesService;
import com.sy.modules.service.ws.WsReplyService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;

/**
 *用户反馈
 *#@author LiuCheng 2013-8-27 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsMessagesController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsMessagesController.class);
	
	@Autowired 
	private WsMessagesService messageservice;
	@Autowired
	private WsReplyService replyservice;
	@Autowired
	private WsHumanService humanService;
	
	/**
	 * 分页显示留言信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/showMessages")
	public String findAllMessagesByPage(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...findAllMessagesByPage()...");
		SysUser u=(SysUser) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION_KEY);
//		//获取用户ID
		Map<String,Object> param = DataTool.getParam(request, "beginDate","endDate");
		param.put("userid",u.getId());
		this.setPagination(request, param);
		List<WsMessages> messages = messageservice.findAllMessagesByPage(param);
		
		int count = messageservice.count(param);
		
		request.setAttribute("totalCount",count);
		request.setAttribute("messages",messages);
		
		String result = request.getParameter("result");
		if(result!=null){
			return "ws/feedback/show_message";
		}else
		return "ws/feedback/messages";//返回jsp页面
	}
	
	/**
	 * 添加回复
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param reply
	 * @param messageid
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/addReply")
	@ResponseBody
	public String addReply(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductController...addReply()...");
		String id = request.getParameter("messageId");
		Long mid = Long.valueOf(id);
		String content = request.getParameter("replyContent");
		
		//封装
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("replyContent",content);
		map.put("messageid",mid);
		
		replyservice.addReply(map);
		return "{\"statusCode\":\"200\",\"message\":\"发表成功\",\"ms\":"+mid+"}";
	}
	
	
	/**
	 * 删除
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param messageid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteMessage")
	@ResponseBody
	public String deleteMessage(HttpServletRequest request,HttpServletResponse response,@RequestParam("messageId")Long messageid) throws IOException{
		log.info("entering...WsProductController...deleteMessage()...");
		String success,message;
		if(messageservice.deleteMessages(messageid)){
			success = Constants.SUCCESS;
			message = Constants.MSG_DEL_SUCCESS;
		}else{
			success = Constants.ERROR;
			message = Constants.MSG_DEL_FAIL;
		}
		return JsonUtil.transferJsonResponse(success,message,null,null,"forward","ws/showMessages");
	}
	
	
	/**
	 * 单条留言
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param messageid
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/showReply")
	public String showMessage(HttpServletRequest request,HttpServletResponse response,@RequestParam("messageId")Long messageid,@RequestParam("result")String result) throws IOException{
		log.info("entering...WsProductController...showMessage()...");
		WsMessages me = messageservice.findById(messageid);
		request.setAttribute("messages",me);
		if(result.equals("show")){
			request.setAttribute("result","show");
			return "ws/feedback/reply";
		}else if(result.equals("simple")){
			request.setAttribute("result","simple");
			return "ws/feedback/reply";
		}else{
			return null;
		}
	}
	
	/**
	 * app接口  根据会员id
	 */
	@RequestMapping(value="/app/app2")
	public ModelAndView app2(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id,@RequestParam(value="humanId")Long humanId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid",id);
		map.put("humanId", humanId);
		List<WsMessages> message = messageservice.findAllMessagesByPage(map);
		request.setAttribute("userid", id);
		return new ModelAndView("ws/app/allMessage","list",message);
	}
	/**
	 * app接口
	 */
	@RequestMapping(value="/app/app2all")
	public ModelAndView app2all(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid",id);
		List<WsMessages> message = messageservice.findAllMessagesByPage(map);
		request.setAttribute("userid", id);
		return new ModelAndView("ws/app/allMessage_all","list",message);
	}
	/**
	 * app
	 * 根据id查找指定留言
	 */
	@RequestMapping(value="/app/app3")
	public ModelAndView app3(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")Long id){
		WsMessages me = messageservice.findById(id);
		return new ModelAndView("ws/app/messageDetail","messages",me);
	}
	/**
	 * app接口
	 * 发表留言
	 * get方式
	 */
	@RequestMapping(value="addMessages")
	@ResponseBody
	public String addMessages(HttpServletResponse response,HttpServletRequest request){
		long id=Long.valueOf(request.getParameter("id"));
		long humanId=Long.valueOf(request.getParameter("humanId"));
		WsMessagesUser wmu = new WsMessagesUser();
		wmu.setUserId(id);
		wmu.setHumanId(humanId);
		WsMessages wm=new WsMessages();
		WsHuman obj=humanService.findById(humanId);
		wm.setCreateName(obj.getHuman_account());
		try {
			wm.setMessageTitle(new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8"));
			wm.setMessageContent(new String(request.getParameter("content").getBytes("iso-8859-1"),"utf-8"));
			}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean success = messageservice.addMessages(wm, wmu);
		if(success){
			//成功结果
			return "success";
		}else{
			//失败结果
			return "fail";
		}
	}
	/**
	 * app接口
	 * 发表留言
	 * post方式
	 */
	@RequestMapping(value="addMessagesPost")
	@ResponseBody
	public String addMessagesPost(HttpServletResponse response,HttpServletRequest request){
		long id=Long.valueOf(request.getParameter("id"));
		long humanId=Long.valueOf(request.getParameter("humanId"));
		WsMessagesUser wmu = new WsMessagesUser();
		wmu.setUserId(id);
		wmu.setHumanId(humanId);
		WsMessages wm=new WsMessages();
		WsHuman obj=humanService.findById(humanId);
		wm.setCreateName(obj.getHuman_account());
		wm.setMessageTitle(request.getParameter("title"));
		wm.setMessageContent(request.getParameter("content"));
		boolean success = messageservice.addMessages(wm, wmu);
		if(success){
			//成功结果
			return "success";
		}else{
			//失败结果
			return "fail";
		}
	}
}
