package com.sy.web.controller.ws;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsProType;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.ws.WsProTypeService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *产品分类
 *目前就支持二级分类，一级分类和二级分类都可以有自己的商品
 *#@author sss 2013-8-27 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsProductClassOneController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsProductClassOneController.class);
	@Autowired
	private UserService userservice;
	@Autowired
	private WsProTypeService  wsproservice;
	
	
	
	@RequestMapping(value="/preAddProType")
	public String preAddProType(){
		return "ws/product/addprotype";
	}
	
	/**
	 * 修改及增加一级分类
	 * @author sss
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addFirstType")
	@ResponseBody
	public String addFirstType(HttpServletRequest request,HttpServletResponse response){
		log.info("entering...WsProductClassOneController...addFirstType()");
		Long uid=SessionUtil.getUserId(request);
		SysUser u=userservice.findById(uid);
		String fileurl = null;
		// 分类名称
		String tname = request.getParameter("tname");
		String filename = request.getParameter("filename");
		WsProType partype = new WsProType();
		partype.setTname(tname);
		if (StringUtils.isNotEmpty(filename)) {
			// 拆分图文封面路径，存DB
			int dex = filename.indexOf(Constants.APPIMAGES);
			fileurl = filename.substring(dex+ Constants.APPIMAGES.length());
			partype.setPicurl(fileurl);
		}
		//一级分类的parentId默认是0
		partype.setParentId(0L);
		partype.setCreateName(u.getUsername());
		partype.setUserId(uid);
		wsproservice.create(partype);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS,Constants.REL_PRODUCTTYPE, null, Constants.CLOSECURRENT, "ws/findOneAllByPage");
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteProType")
	@ResponseBody
	public String deleteProType(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...deleteProType()");
		String flag=request.getParameter("flag");
		Long tid=Long.valueOf(request.getParameter("id"));
		//根据id查询是否有二级分类？
		//有，无法删除
		Integer num=wsproservice.findCountByParId(tid);
		if(flag.equals("fis")){
			if(null!=tid&&0==num){
				wsproservice.delete(tid);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, Constants.REL_PRODUCTTYPE, null, null, null);
			}
			return  JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_DEL_FAIL, Constants.REL_PRODUCTTYPE, null, null,null);
		}else if(flag.equals("sec")){
			
			if(null!=tid&&0==num){
				wsproservice.delete(tid);
				return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, "page88", null, null, null);
			}
			return  JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_DEL_FAIL, "page88", null, null,null);
		}
		return null;
	}
	
	/**
	 *修改一级分类 
	 */
	@RequestMapping(value="/updateProType")
	public @ResponseBody String updateProType(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...updateProType");
		String fileurl = null;
		Long tid=Long.valueOf(request.getParameter("tid"));
		// 分类名称
		String tname = request.getParameter("tname");
		String filename = request.getParameter("filename");
		WsProType partype = new WsProType();
		partype.setId(tid);
		partype.setTname(tname);
		if (StringUtils.isNotEmpty(filename)) {
			// 拆分图文封面路径，存DB
			int dex = filename.indexOf(Constants.APPIMAGES);
			fileurl = filename.substring(dex+ Constants.APPIMAGES.length());
			partype.setPicurl(fileurl);
			}
		wsproservice.update(partype);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS,Constants.REL_PRODUCTTYPE, "", Constants.CLOSECURRENT, "ws/findOneAllByPage");
	}
	
	//根据ID查询一级分类
	@RequestMapping(value="findProTypeById")
	public String findProTypeById(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findProTypeById");
		Long tid=Long.valueOf(request.getParameter("id"));
		WsProType wprotype=wsproservice.findById(tid);
		request.setAttribute("wprotype", wprotype);
		request.setAttribute("picurl",Constants.DB_IMAGE_FILE+"/"+wprotype.getPicurl());
		return "ws/product/updateprotype";
	}
	
	/**
	 * 查询一级产品分类
	 * 有分页
	 * 模糊查询，条件检索
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findOneAllByPage")
	public  String findOneAllByPage(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findOneAllByPage()");
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		String tname=request.getParameter("tname");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		map.put("tname", tname);
		List<WsProType> parentTypeList=wsproservice.findAllParentTypesByPage(map);
		//总数
		long totalNum=wsproservice.findAllParentTypeCount(map);
		//
		request.setAttribute("parentTypeList", parentTypeList);
		request.setAttribute("totalCount", totalNum);
		return "ws/product/protype";
	}
	/**
	 *查询一级分类
	 *return json 
	 */
	@RequestMapping(value="/findOneAllByPageJson")
	public  @ResponseBody String findOneAllByPageJson(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findOneAllByPageJson()");
		Long uid=SessionUtil.getUserId(request);
		//分类名称
		String tname=request.getParameter("tname");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("id", uid);
		map.put("tname", tname);
		List<WsProType> parentTypeList=wsproservice.findAllParentTypesByPage(map);
		Gson gson=new Gson();
		return gson.toJson(parentTypeList);
	}
	
	/**
	 *添加二级分类 
	 */
	@RequestMapping(value="/addSecondType")
	public @ResponseBody String addSecondType(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...addSecondType()");
		SysUser u=SessionUtil.getLoginUser(request);
		String fileurl = null;
		String flag=request.getParameter("flag");
		// 分类名称
		String tname = request.getParameter("tname");
		String filename = request.getParameter("filename");
		//获取一级分类ID
		long partid=Long.valueOf(request.getParameter("partid"));
		WsProType partype = new WsProType();
		partype.setTname(tname);
		if (StringUtils.isNotEmpty(filename)) {
			// 拆分图文封面路径，存DB
			int dex = filename.indexOf(Constants.APPIMAGES);
			fileurl = filename.substring(dex+ Constants.APPIMAGES.length());
			partype.setPicurl(fileurl);
		}
		
		partype.setParentId(partid);
		partype.setCreateName(u.getUsername());
        if(flag.equals("add")){
        	wsproservice.create(partype);
    		/*
    		 *page88 对应页面的rel属性
    		 *rel 感觉没用到 
    		 */
    		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS,"page88", "", Constants.CLOSECURRENT,"ws/findSecondAllByPage?id="+partid);
		}else if(flag.equals("upd")){
			partype.setId(partid);
			wsproservice.update(partype);
			//父id
			WsProType partype2=wsproservice.findById(partid);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS,"page88", "", Constants.CLOSECURRENT,"ws/findSecondAllByPage?id="+partype2.getParentId());
		}
		return null;
	}
	
	
	/**
	 *根据一级分类ID
	 *查询二级产品分类 
	 * 
	 */
	@RequestMapping(value="/findSecondAllByPage")
	public String findSecondAllByPage(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findSecondAllByPage()");
		//一级分类ID
		Long ptid=Long.valueOf(request.getParameter("id"));
		String tname=request.getParameter("tname");
		WsProType wprotype=wsproservice.findById(ptid);
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("tid", ptid);
		map.put("tname", tname);
		List<WsProType> secondtypelist=wsproservice.findAllSecondTypesByPage(map);
		//总数
		long totalNum=wsproservice.findAllSecondTypeCount(map);
		request.setAttribute("tid", ptid);
		request.setAttribute("secondtypelist", secondtypelist);
		request.setAttribute("totalCount", totalNum);
		request.setAttribute("ptname", wprotype.getTname());
		return "ws/product/secondprotype";
	}
	
	@RequestMapping(value="/findSecondAllByPageJson")
	public @ResponseBody String findSecondAllByPageJson(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findSecondAllByPageJson()");
		//一级分类ID
		Long ptid=Long.valueOf(request.getParameter("id"));
		String tname=request.getParameter("tname");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("tid", ptid);
		map.put("tname", tname);
		List<WsProType> secondtypelist=wsproservice.findAllSecondTypesByPage(map);
		Gson gson=new Gson();
		return gson.toJson(secondtypelist);
	}
	
	/**
	 *根据二级分类ID查询一级分类 
	 */
	@RequestMapping(value="/findParentTypeBySecondtid")
	public String findParentTypeBySecondtid(HttpServletRequest request){
		log.info("entering...WsProductClassOneController...findParentTypeBySecondtid");
		String flag=request.getParameter("flag");
		//获取分类ID
		long partid=Long.valueOf(request.getParameter("id"));
		//获取分类
		WsProType type=wsproservice.findById(partid);
		request.setAttribute("partype", type);
		if(flag.equals("addtype")){
			return "ws/product/addsecondtype";
		}else if(flag.equals("updtype")){
			//查询一级分类id
			WsProType partype=wsproservice.findById(type.getParentId());
			request.setAttribute("mpartype", partype);
			request.setAttribute("picurl",Constants.DB_IMAGE_FILE+"/"+type.getPicurl());
			return "ws/product/updatesecondtype";
		}
		return null;		
	}
	

//供微信接口调用------------------------------------------------------------------------------------	

//获取一级分类
@RequestMapping(value="appwx/findAllPtypesForWx")
public @ResponseBody String findAllPtypesForWx(HttpServletRequest request){
	log.info("entering...WsProductClassOneController...findAllPtypesForWx()");
	       long accountId=0;
	       String idstr=request.getParameter("id");
	       if(StringUtils.isNotEmpty(idstr)){
	    	   accountId=Long.valueOf(idstr); 
	    	 //分类名称
				String tname=request.getParameter("tname");
				Map<String,Object> map=new HashMap<String,Object>();
				this.setPagination(request, map);
				map.put("id", accountId);
				map.put("tname", tname);
				List<WsProType> parentTypeList=wsproservice.findAllParentTypesByPage(map);
				Gson gson=new Gson();
				return gson.toJson(parentTypeList);
	       }
	       return null;
   }

//获取二级分类

	@RequestMapping(value = "appwx/findAllStypesForWx")
	public @ResponseBody
	String findAllStypesForWx(HttpServletRequest request) {
		log.info("entering...WsProductClassOneController...findAllStypesForWx()");
		// 一级分类ID
		Long ptid = Long.valueOf(request.getParameter("fatherId"));
		String tname = request.getParameter("tname");
		Map<String, Object> map = new HashMap<String, Object>();
		this.setPagination(request, map);
		map.put("tid", ptid);
		map.put("tname", tname);
		List<WsProType> secondtypelist = wsproservice
				.findAllSecondTypesByPage(map);
		Gson gson = new Gson();
		return gson.toJson(secondtypelist);
	}

}
