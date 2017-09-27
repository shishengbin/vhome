package com.sy.web.controller.ws;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsSpec;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.ws.WsSpecService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value ="/ws")
public class WsSpecController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsSpecController.class); 
	
	@Autowired
	private UserService userservice;
	@Autowired
	private WsSpecService wsspecservice; 
	
	
	@RequestMapping(value="/preAddSpec")
	public String preAddSpec(){
		return "ws/product/addspec";
	}
	/**
	 * 添加规格
	 * 修改规格
	 * @param request
	 * @return json
	 */
	@RequestMapping(value="/addSpec")
	@ResponseBody
	public String addSpec(HttpServletRequest request){
		log.info("entering...WsSpecController...addSpec()");
		long uid=SessionUtil.getUserId(request);
		SysUser u=userservice.findById(uid);
		WsSpec ws=null;
		//获取信息
		String specname=request.getParameter("specname");
		String flag=request.getParameter("flag");
		if(StringUtils.isNotEmpty(specname)){
			ws=new WsSpec();
			ws.setSpecname(specname);
			ws.setCreateName(u.getUsername());
			if("add".equals(flag)){
				wsspecservice.create(ws);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS, Constants.MSG_ADD_SUCCESS, Constants.REL_SPECMANAGER, null, Constants.CLOSECURRENT, "ws/findAllSpecByPage");	
			}else if("upd".equals(flag)){
				Long specid=Long.valueOf(request.getParameter("id"));
				ws.setId(specid);
				wsspecservice.update(ws);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS, Constants.MSG_UPDATE_SUCCESS, Constants.REL_SPECMANAGER, null, Constants.CLOSECURRENT, "ws/findAllSpecByPage");	
			}
		}
		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_OPERATIO_FAIL, null, null, null,null);
	} 
	
	/**
	 * 删除规格
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/delSpec")
	@ResponseBody
	public String delSpec(HttpServletRequest request){
		log.info("entering...WsSpecController...delSpec()");
		String specidstr=request.getParameter("id");
		if(StringUtils.isNotEmpty(specidstr)){
			Long specid=Long.valueOf(specidstr);
			wsspecservice.delete(specid);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, Constants.REL_SPECMANAGER, null, null, null);
		}
		log.info("end...WsSpecController...delSpec()");
		return  JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_DEL_FAIL, Constants.REL_SPECMANAGER, null, null,null);
	}
	
	/**
	 * 查询单条规格信息
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/findSpecById")
	public String findSpecById(HttpServletRequest request){
		log.info("entering...WsSpecController...findSpecById()");
		Long specid=Long.valueOf(request.getParameter("id"));
		WsSpec ws=wsspecservice.findById(specid);
		request.setAttribute("wsspec", ws);	
		log.info("end...WsSpecController...findSpecById()");
		return "ws/product/addspec";
	}
	
	//分页查询所有规格
	@RequestMapping(value="/findAllSpecByPage")
	public String findAllSpecByPage(HttpServletRequest request){
		log.info("entering...WsSpecController...findAllSpecByPage()");
		//获取用户id
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		String specname=request.getParameter("specname");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		map.put("specname", specname);
		List<WsSpec> speclist=wsspecservice.findAllSpecByPage(map);
		//查询总数
		int count = wsspecservice.findCount(map);
		request.setAttribute("speclist",speclist);
		request.setAttribute("totalCount",count);
		return "ws/product/specmanager";
	}
	
	/**
	 * 查询所有规格
	 * @param request
	 * @return json
	 */
	@RequestMapping(value="/findAllSpecByJson")
	public @ResponseBody String findAllSpecByJson(HttpServletRequest request){
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		List<WsSpec> speclist=wsspecservice.findAllSpec(map);
		Type type=new TypeToken<List<WsSpec>>(){}.getType();
		return JsonUtil.transformJson("200", "success", speclist, type);
	}

}
