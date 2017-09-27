package com.sy.web.controller.ws;

import java.util.HashMap;
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

import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsSms;
import com.sy.modules.entity.ws.WsSmsUser;
import com.sy.modules.service.ws.WsSmsService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;


/**
 *短信管理
 *#@author lhl 2013-9-4 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsSmsController extends PageSet{
	
	private static final Logger log=LoggerFactory.getLogger(WsSmsController.class);
	@Autowired 
	private WsSmsService smsService;
	
	@RequestMapping(value="/preAddSms")
	public String preAddSms(){
		return "ws/sms/add_sms";
	}
	/**
	 * 添加短信信息
	 */	
	@RequestMapping(value = "/addsms")
	@ResponseBody
	public String addSms(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WsSms sms){
		WsSmsUser wsu=new WsSmsUser();
		SysUser u=SessionUtil.getLoginUser(request);
		wsu.setUser_id(u.getId());
		wsu.setCreateName(u.getUsername());
		sms.setCreateName(u.getUsername());
		List<WsSms> wsSms=smsService.findByTitle(u.getId(),sms.getSms_title());
		if(wsSms.size()>0)
			return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADDSMS_Exists, null, null, null,null);
		else
		{
			boolean s=smsService.addSms(sms, wsu);
			if(s)
				return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, "page12", null, Constants.CLOSECURRENT,"ws/showSms");
			else
				return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_FAIL, null, null, null,null);
		}
	}
	
	/**
	 * 删除信息
	 */	
	@RequestMapping(value = "/deleteSms")
	@ResponseBody
	public String deleteSms(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id){
		smsService.delete(id);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, null, null, Constants.FORWARD,"ws/showSms");
	}

	/**
	 * 修改默认选中状态
	 */	
	@RequestMapping(value = "/updateSmsStatus")
	@ResponseBody
	public String updateSmsStatus(HttpServletRequest request,HttpServletResponse response){
		try {
			Long user_id=SessionUtil.getUserId(request);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("userid",user_id);
			List<WsSms> list = smsService.finAllByPage(map);
			String sms_use=request.getParameter("id");
			int id=Integer.parseInt(sms_use);
			int i=0;
			while(i<list.size())//遍历所有号码，将原有选中设为非选中
			{
				WsSms sms=list.get(i);
				if(sms.getSms_use()==1)
				{
					sms.setSms_use(0);
					smsService.update(sms);
				}
				i++;
			}
			//将选中的信息 设为默认
			WsSms item=smsService.findById(Long.valueOf(id));
			item.setSms_use(1);
			smsService.update(item);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_Save_Success, null, null, Constants.FORWARD,"ws/showSms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_Save_Fail, null, null,null,null);
	}
	
	
	/**
	 * 获得所有短信信息
	 * @return 
	 */
	@RequestMapping(value="/showSms")
	public String showSms(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userid",SessionUtil.getUserId(request));
		String content = request.getParameter("content");
		if(content!=""){
			map.put("content",content);
		}
		List<WsSms> list = smsService.finAllByPage(map);
		this.setPagination(request, map);
		List<WsSms> list1=smsService.finAllByPage(map);
		request.setAttribute("list",list1);
		request.setAttribute("totalCount",list.size());
		request.setAttribute("content",content);
		return "ws/sms/sms";	
	}
	/**
	 * 查看短信详细信息
	 */
	@RequestMapping(value="/smsDetails")
	public String showSms(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id,@RequestParam(value="result")String result) {
		WsSms sms = smsService.findById(id);
		request.setAttribute("sms", sms);
		if(result.equals("xg"))
			return "ws/sms/add_sms";
		else
			return "ws/sms/show_sms";
	}
	/**
	 * 修改短信信息
	 */	
	@RequestMapping(value = "/updatesms")
	@ResponseBody
	public String updateSms(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WsSms sms){
		SysUser u=(SysUser) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION_KEY);
		sms.setUpdateName(u.getUsername());
		smsService.update(sms);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, "page12", null, Constants.CLOSECURRENT,"ws/showSms");
	}

	

}
