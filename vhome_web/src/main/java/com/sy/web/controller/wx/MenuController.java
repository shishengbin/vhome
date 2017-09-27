package com.sy.web.controller.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.menu.Button;
import com.sy.modules.entity.wx.menu.CommonButton;
import com.sy.modules.entity.wx.menu.ComplexButton;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.MenuService;
import com.sy.web.commons.ConstantURL;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class MenuController {
	
	private static final Logger log=LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private AccountService accountService;
		
    /**
     * 生成微信菜单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="/createMenu")
    @ResponseBody
    public String createMenu(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	try{
    		long userid=SessionUtil.getUserId(request);
    		if(menuService.createM(userid)==0)
    			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_WX_PARENT_MENU_SUCCESS, null, null, null,null);
    		else
    			return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_WX_PARENT_MENU_FAIL, null, null, null,null);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_WX_PARENT_MENU_FAIL, null, null, null,null);
		}   	
    }
    
    /**
     *获取登陆账号id
     *获取公众号  
     */
    private Account findAccount(HttpServletRequest request){
    	log.info("entering...MenuController...findAccount()");
    	long userid = SessionUtil.getUserId(request);
    	//获取公众号id
    	Account account=accountService.findAccountInfo(userid);
    	return account;
    }
    /**
     *根据公众号id查询所有主菜单 
     */
    @RequestMapping(value="/findParentMenus")
    public String findParentMenus(HttpServletRequest request){
    	log.info("entering...MenuController...findAllMenus()");
    	Account account=this.findAccount(request);
    	//查询父菜单
    	ComplexButton[] parentlist=menuService.findParentMenu(account.getId());
    	request.setAttribute("parentlist", parentlist);
    	return "wx/menuinfo";
    }
    
    /**
     *根据id查询单个主菜单信息 
     *@return 返回添加主菜单页面
     */
    
    @RequestMapping(value="findMenuById")
    public String findMenuById(HttpServletRequest request){
    	log.info("entering...MenuController...findMenuById");
    	Long menuid=Long.valueOf(request.getParameter("id"));
    	CommonButton menu=menuService.findMenuByid(menuid);
    	request.setAttribute("menu", menu);
    	return "wx/updatefirstmenu";
    }
    
    @RequestMapping(value="/PreAddParentMenu")  
    public String PreAddParentMenu(){
    	return "wx/addfirstmenu";
    }
    
    /**
     *添加本地主菜单
     *①.判断当前公众号下主菜单个数是否大于3，是：则不能创建
     *②.跳转创建主菜单页面 
     */
    @RequestMapping(value="/addParentMenu")
    public @ResponseBody String addParentMenu(HttpServletRequest request){
    	log.info("entering...MenuController...addParentMenu()");
    	String temp="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String REDIRECT_URI="";
    	Account account=this.findAccount(request);
    	//查询当前公众号下主菜单的总数
    	int count=menuService.findParentMenuCount(account.getId());
    	if(count>=3){
    		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_WX_PARENT_MENU_MAX_COUNT, null, null, null, null);
    	}else{
    		//获取主菜单名称
    		String parentmenuname=request.getParameter("parentmenuname");
    		//关键词或者ulr
    		//根据接收字符串来判断到底是什么类型
    		String keyorurl=request.getParameter("keyorurl");
    		ComplexButton combtn=null;
    		if(keyorurl.contains("http")){
    			combtn=new ComplexButton();
    			combtn.setType("view");
    			if(keyorurl.contains("wxpages/index.html")){
    				try {
						REDIRECT_URI=java.net.URLEncoder.encode(keyorurl+"?accountid="+account.getId(),"utf-8");
						combtn.setUrl(temp.replace("APPID",account.getAppId()).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE","snsapi_base").replace("STATE", "123"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}	
    			}
    			combtn.setUrl(keyorurl);
    		}else{
    			combtn=new ComplexButton();
    			combtn.setType("click");
    			combtn.setKey(keyorurl);
    		}
    		combtn.setName(parentmenuname);
    		combtn.setAccount_id(account.getId());
    		menuService.savefatMenu(combtn);
    		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, Constants.REL_PARENTMENU, null, Constants.CLOSECURRENT, ConstantURL.PARENTFORWARDURL);
    	}
    }
    
    
    /**
     *修改主菜单
     *@return json 
     */
    @RequestMapping(value="/updateMenu")
    public @ResponseBody String updateMenu(HttpServletRequest request){
    	log.info("entering...MenuController...updateMenu()");
    	String temp="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String REDIRECT_URI="";
		Long fatid=-1L;
    	Account account=this.findAccount(request);
    	String flag=request.getParameter("flag");
    	long bt_id=Long.valueOf(request.getParameter("bt_id"));
    	//类型
    	String remark=request.getParameter("remark");
    	//获取主菜单名称
		String parentmenuname=request.getParameter("parentmenuname");
		//主菜单id
		String fatIdstr=request.getParameter("fatid");
		if(StringUtils.isNotEmpty(fatIdstr)){
			fatid=Long.valueOf(fatIdstr);
		}
		//获取子菜单名称
		String sonmenuname=request.getParameter("sonmenuname");
		//关键词或者ulr
		//根据接收字符串来判断到底是什么类型
		String keyorurl=request.getParameter("keyorurl");
	    Button combtn=null;
		if(keyorurl.contains("http")){
			combtn=new Button();
			combtn.setType("view");
			if(keyorurl.contains("wxpages/index.html")){
				try {
					REDIRECT_URI=java.net.URLEncoder.encode(keyorurl+"?accountid="+account.getId(),"utf-8");
					combtn.setUrl(temp.replace("APPID",account.getAppId()).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE","snsapi_base").replace("STATE", "123"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}	
			}
			combtn.setUrl(keyorurl);
		}else{
			combtn=new Button();
			combtn.setType("click");
			combtn.setKey(keyorurl);
		}
		combtn.setRemark(remark);
		combtn.setBt_id(bt_id);
		if(flag.equals("fat")){
			combtn.setName(parentmenuname);
			menuService.updateMenu(combtn);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, Constants.REL_PARENTMENU, null, Constants.CLOSECURRENT, "wx/findParentMenus");
		}
		if(flag.equals("son")){
			combtn.setName(sonmenuname);
			menuService.updateMenu(combtn);
			//修改子菜单就跳到子菜单列表
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, "page100", null, Constants.CLOSECURRENT, "wx/findSonMenusByFatId?id="+fatid);
		}
		
	return null;	
		
    }
    
    /**
     *删除主菜单 
     */
    @RequestMapping(value="/deletParentMenu")
    public @ResponseBody String deletParentMenu(HttpServletRequest request){
    	log.info("entering...MenuController...deletParentMenu()");
    	long bt_id=Long.valueOf(request.getParameter("id"));//主菜单的id
    	//查询当前公众号下主菜单的总数
    	Account account=this.findAccount(request);
    	int count=menuService.findSonMenuCountByParent(bt_id,account.getId());
    	if(count==0){
    		menuService.delFatMenu(bt_id);
    		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS,Constants.REL_PARENTMENU,null, null, ConstantURL.PARENTFORWARDURL);
    	}else{

    		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_WX_DEL_FAIL,Constants.REL_PARENTMENU,null, null, ConstantURL.PARENTFORWARDURL);

    	}
    }
    
    
    /**
     *添加本地子菜单
     *判断当前主菜单下的子菜单是否大于等于5，是：则不能创建 
     */
    
    @RequestMapping(value="/addSonMenu")
    public @ResponseBody String addSonMenu(HttpServletRequest request){
    	log.info("entering...MenuController...addSonMenu()");
    	String temp="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String REDIRECT_URI="";
    	Account account=this.findAccount(request);
    	Long fatId=Long.valueOf(request.getParameter("bt_id"));
    	//查询当前公众号下主菜单的总数
    	int count=menuService.findSonMenuCountByParent(fatId,account.getId());
    	if(count>=5){
    		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_WX_SON_MENU_MAX_COUNT, null, null, null, null);
    	}else{
    		//获取子菜单名称
    		String sonmenuname=request.getParameter("sonmenuname");
    		//关键词或者ulr
    		//根据接收字符串来判断到底是什么类型
    		String keyorurl=request.getParameter("keyorurl");
    		CommonButton combtn=null;
    		if(keyorurl.contains("http")){
    			combtn=new CommonButton();
    			combtn.setType("view");
    			if(keyorurl.contains("wxpages/index.html")){
    				try {
						REDIRECT_URI=java.net.URLEncoder.encode(keyorurl+"?accountid="+account.getId(),"utf-8");
						combtn.setUrl(temp.replace("APPID",account.getAppId()).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE","snsapi_base").replace("STATE", "123"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}	
    			}
    			combtn.setUrl(keyorurl);
    		}else{
    			combtn=new CommonButton();
    			combtn.setType("click");
    			combtn.setKey(keyorurl);
    		}
    		combtn.setName(sonmenuname);
    		combtn.setAccount_id(account.getId());
    		combtn.setFat_bt_id(fatId);
    		menuService.saveSonMenu(combtn);
    		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, "page10", "", Constants.CLOSECURRENT, "wx/findSonMenusByFatId?id="+fatId);
    	}
    }
    
    
    
    
    /**
     *根据菜单id查询菜单信息
     *@return 添加子菜单页面 
     */
    
    @RequestMapping(value="/findMenuByid")
    public String findMenuByid(HttpServletRequest request){
    	log.info("entering...MenuController...findMenuByid()");
    	//获取菜单id
    	Long menuid=Long.valueOf(request.getParameter("id"));
    	//获取标识，判断跳转的页面
    	String flag=request.getParameter("flag");
    	//查询菜单信息
    	CommonButton commonmenu=menuService.findMenuByid(menuid);
    	request.setAttribute("commonmenu", commonmenu);
    	if(flag.equals("add")){
    		return "wx/addsecondmenu";
    	}
    	if(flag.equals("update")){
    		//父菜单，这里有问题，下次重构
    		CommonButton btn=menuService.findMenuByid(commonmenu.getFat_bt_id());
    		request.setAttribute("complexMenuName", btn.getName());
    		request.setAttribute("fatid", commonmenu.getFat_bt_id());
    		return "wx/updatesecondmenu";
    	}
		return null;
    }
    
    
    
    
    
    /**
     *根据主菜单id查询子菜单 
     */
    
	@RequestMapping(value="/findSonMenusByFatId")
	public String findSonMenusByFatId(HttpServletRequest request){
		log.info("entering...MenuController...findSonMenusByFatId()");
		Long accountid = SessionUtil.getUserId(request);
		//获取主菜单id
    	Long fatid=Long.valueOf(request.getParameter("id"));
    	//查询菜单信息
    	CommonButton commonmenu=menuService.findMenuByid(fatid);
    	CommonButton[] commonbtn=menuService.findSonMenusByFatId(fatid,accountid);
    	request.setAttribute("commonbtn", commonbtn);
    	request.setAttribute("fatmenu", commonmenu.getName());
    	return "wx/findsecondmenu";
	}
	
	/**
	 *删除子菜单
	 *@return json 
	 */
	@RequestMapping(value="/deleteSonMenu")
	@ResponseBody
	public String deleteSonMenu(HttpServletRequest request){
		log.info("entering...MenuController...deleteSonMenu");
		//获取子菜单id
		long sonMenuId=Long.valueOf(request.getParameter("id"));
		if(sonMenuId>0){
			menuService.delSonMenu(sonMenuId);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS,null,Constants.REL_PARENTMENU, Constants.CLOSECURRENT, ConstantURL.PARENTFORWARDURL);
		}else{
			return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_DEL_FAIL,null,Constants.REL_PARENTMENU,null,null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 
}
