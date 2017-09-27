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
import com.sy.modules.entity.ws.WsPhone;
import com.sy.modules.entity.ws.WsPhoneUser;
import com.sy.modules.service.ws.WsPhoneService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *号码管理
 *#@author lhl 2013-9-4 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsPhoneController extends PageSet{
	
	private static final Logger log=LoggerFactory.getLogger(WsPhoneController.class);
	@Autowired 
	private WsPhoneService phoneservice;

	/**
	 * 删除号码
	 */
	@RequestMapping(value="/deletephone")
	@ResponseBody
	public String deletephone(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id){
		if(phoneservice.deletePhone(id))
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS,null,null,Constants.FORWARD,"ws/showphone");
		else
			return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_DEL_FAIL,null,null,"","");
	}
	/**
	 * 查看号码详细信息
	 */
	@RequestMapping(value="/phoneDetails")
	public String phoneDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id")Long id,@RequestParam(value="result")String result) {
		WsPhone phone = phoneservice.findById(id);
		request.setAttribute("phone",phone);
		if(result.equals("xg"))
			return "ws/phone/add_phone";
		else
			return "ws/phone/show_phone";
	}
	/**
	 * 修改号码信息
	 */	
	@RequestMapping(value = "/updatephone")
	@ResponseBody
	public String updatephone(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WsPhone phone){
		SysUser u=(SysUser) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION_KEY);
		phone.setUpdateName(u.getUsername());
		phoneservice.update(phone);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, Constants.REL_PHONEMANAGER, null, Constants.CLOSECURRENT,"ws/showphone");
	}
	
	
	@RequestMapping(value="/preAddPhone")
	public String preAddPhone(){
		return "ws/phone/add_phone";
	}
	/**
	 * 添加号码信息
	 */	
	@RequestMapping(value = "/addphone")
	@ResponseBody
	public String addphone(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WsPhone phone){
		WsPhoneUser wpu=new WsPhoneUser();
		SysUser u=SessionUtil.getLoginUser(request);
		wpu.setUserId(u.getId());
		wpu.setCreateName(u.getUsername());
		phone.setCreateName(u.getUsername());
		List<WsPhone> wsPhone=phoneservice.findByNumber(u.getId(),phone.getPhone_Number());
		if(wsPhone.size()>0)
			return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADDPhone_Exists, null, null, null,null);
		else
		{
			boolean s=phoneservice.addPhone(phone, wpu);
			if(s)
				return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, Constants.REL_PHONEMANAGER, null, Constants.CLOSECURRENT,"ws/showphone");
			else
				return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_FAIL, null, null, null,null);
		}
	}
	
	
	/**
	 * 分页查出所有号码
	 */
	@RequestMapping(value="/showphone")
	public String app4(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userid",SessionUtil.getUserId(request));
		String name = request.getParameter("name");
		if(name!=""){
			map.put("name",name);
		}
		List<WsPhone> list = phoneservice.getAll(map);
		this.setPagination(request, map);
		List<WsPhone> list1=phoneservice.getAll(map);
		request.setAttribute("phoneList",list1);
		request.setAttribute("totalCount",list.size());
		request.setAttribute("name",name);
		return "ws/phone/phoneNumber";
	}
}
