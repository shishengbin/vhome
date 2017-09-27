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
import com.sy.modules.entity.ws.WsSupplier;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.ws.WsSupplierService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value ="/ws")
public class WsSupplierController extends PageSet {
	private static final Logger log=LoggerFactory.getLogger(WsSupplierController.class);
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private WsSupplierService suppservice;
	
	@RequestMapping(value="/preAddSupplier")
	public String preAddSupplier(){
		return "ws/supplier/addsupp";
	}
	
	/**
	 * 添加供应商
	 * @param request
	 * @return json
	 */
	@RequestMapping(value="/addSupplier")
	@ResponseBody
	public String addSupplier(HttpServletRequest request){
		log.info("entering...WsSupplierController...addSupplier()");
		long uid=SessionUtil.getUserId(request);
		SysUser u=userservice.findById(uid);
		WsSupplier ws=null;
		//获取信息
		String suppcompany=request.getParameter("suppcompany");
		String suppname=request.getParameter("suppname");
		String supptel=request.getParameter("supptel");
		String remark=request.getParameter("suppremark");
		String flag=request.getParameter("flag");
		if(StringUtils.isNotEmpty(suppcompany)){
			ws=new WsSupplier();
			ws.setSuppcompany(suppcompany);
			ws.setSuppname(suppname);
			ws.setSupptel(supptel);
			ws.setRemark(remark);
			ws.setCreateName(u.getUsername());
			if("add".equals(flag)){
				suppservice.create(ws);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS, Constants.MSG_ADD_SUCCESS, Constants.REL_SUPPLIERMANAGER, null, Constants.CLOSECURRENT, "ws/findAllSupplierByPage");	
			}else if("upd".equals(flag)){
				Long suppid=Long.valueOf(request.getParameter("id"));
				ws.setId(suppid);
				suppservice.update(ws);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS, Constants.MSG_UPDATE_SUCCESS, Constants.REL_SUPPLIERMANAGER, null, Constants.CLOSECURRENT, "ws/findAllSupplierByPage");	
			}
		}
		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_OPERATIO_FAIL, null, null, null,null);
	}
	
	/**
	 * 删除供应商
	 * @param request
	 * @return
	 */
	
	
	/**
	 * 查询单个供应商
	 * @param request
	 * @return obj
	 */
	@RequestMapping(value="/findSuppById")
	public String findSuppById(HttpServletRequest request){
		log.info("entering...WsSupplierController...findSuppById()");
		Long suppid=0L;
		//获取id
		String str=request.getParameter("id");
		if(StringUtils.isNotEmpty(str)){
			suppid=Long.valueOf(str);
			WsSupplier w=suppservice.findById(suppid);
			request.setAttribute("wssupp", w);
		}
		log.info("end...WsSupplierController...findSuppById()");
		return "ws/supplier/addsupp";
	}
	
	// 分页查询所有供应商
	@RequestMapping(value = "/findAllSupplierByPage")
	public String findAllSupplierByPage(HttpServletRequest request) {
		log.info("entering...WsSupplierController...findAllSupplierByPage()");
		//获取用户id
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		String suppcompany=request.getParameter("suppcompany");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		map.put("suppcompany", suppcompany);
		List<WsSupplier> supplist=suppservice.findAllSupplierByPage(map);
		//查询总数
		int count = suppservice.findCountByParam(map);
		request.setAttribute("supplist",supplist);
		request.setAttribute("totalCount",count);
		return "ws/supplier/suppmanager";
	}


	// 查询所有供应商
	@RequestMapping(value = "/findAllSupplierByJson")
	public @ResponseBody String  findAllSupplier(HttpServletRequest request) {
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		List<WsSupplier> supplist=suppservice.findAllSupplier(map);
		Type type=new TypeToken<List<WsSupplier>>(){}.getType();
		return JsonUtil.transformJson(Constants.SUCCESS, Constants.MSG_GET_SUCCESS, supplist, type);
	}

}
