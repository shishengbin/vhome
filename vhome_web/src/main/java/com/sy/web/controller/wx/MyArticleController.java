package com.sy.web.controller.wx;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.Articleaccount;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.ArticleaccountService;
import com.sy.modules.service.wx.MyArticleService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

@Controller
@RequestMapping(value = "/wx")
public class MyArticleController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(ArticleController.class);
	private static final String APPIMAGES="appimages/";
	
	@Autowired
	private MyArticleService myarticleservice;
	@Autowired
	private ArticleaccountService articleaccountservice;
	@Autowired
	private AccountService accountservice;
	
	/**
     *获取登陆账号id
     *获取公众号  
     *@param request
     *@return Account obejct
     */
    public  Account findAccount(HttpServletRequest request){
    	long userid = SessionUtil.getUserId(request);
    	//获取公众号id
    	Account account=accountservice.findAccountInfo(userid);
    	return account;
    }
	
	
	/**
	 *添加单图文,关联公众号
	 *@param request
	 *@return string 
	 */
	@RequestMapping(value="/addSingleArticle")
	public @ResponseBody String addSingleArticle(HttpServletRequest request){
		log.info("entering...ArticleController...addSingleArticle");
		long userid = SessionUtil.getUserId(request);
		SysUser user = SessionUtil.getLoginUser(request);
    	//获取公众号id
    	Account account=accountservice.findAccountInfo(userid);
    	//图文信息
    	String title=request.getParameter("artitle");//标题
    	String filename=request.getParameter("filename");//封面图片路径
    	//拆分图文封面路径，存DB
    	int dex=filename.indexOf(MyArticleController.APPIMAGES);
    	String fileurl=filename.substring(dex+MyArticleController.APPIMAGES.length());
    	
    	String description=request.getParameter("description");//图文描述文件
    	int flag=Integer.parseInt(request.getParameter("flag"));//标识
    	String artdetail=request.getParameter("artdetail");//图文详情
    	//
    	int atype=Integer.parseInt(request.getParameter("test"));	
    	//自定义链接
    	String linkurl=request.getParameter("linkurl");
    	//图文跳转页面
    	String url=request.getParameter("modulecontent");
    		
    	MyArticle art=null;
        Articleaccount articleaccount=null;
    	if(StringUtils.isNotEmpty(title)&&StringUtils.isNotEmpty(fileurl)){
    		art=new MyArticle();
    		art.setTitle(title);
        	art.setPicUrl(fileurl);
        	art.setDescription(description);
        	art.setFlag(flag);
        	art.setDetail(artdetail);
        	if(StringUtils.isNotEmpty(url)){
        		art.setUrl(url+"?id="+userid);
        	}
        	if(StringUtils.isNotEmpty(linkurl)){
        		art.setUrl(linkurl);
        	}
        	art.setAtype(atype);
        	art.setCreateName(user.getUsername());
        	myarticleservice.create(art);
        	//当采取默认方式，点击图文对应文本编译器里的内容
	        if(atype==0){
        		String myurl="wap/showArticleInfo.html"+"?id="+art.getId();
        		art.setUrl(myurl);
        		myarticleservice.update(art);
        	}
        	 	
        	//关联公众号
            //	articleaccountservice.create(obj)
        	if(art.getId()>0){
        		articleaccount=new Articleaccount();
        		articleaccount.setArtcleId(art.getId());
        		//单图文，mutiarticle_id用0表示
        		articleaccount.setMutiArticle_id(0);
        		//关联公众号
        		articleaccount.setAccountId(account.getId());
        		articleaccountservice.create(articleaccount);
        		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS,"page7", null, Constants.CLOSECURRENT, "wx/findAllArticlesByPage");
        	}
    	}
    	return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_SUCCESS, null, null, null, null);
	}
	
	
	/**
	 *删除单图文 
	 */
	@RequestMapping(value="/delSingleArticle")
	public @ResponseBody String delSingleArticle(HttpServletRequest request)
	{
		log.info("entering...ArticleController...delSingleArticle()");
		//删除关联表数据
		long artcleId=Long.valueOf(request.getParameter("id"));
		articleaccountservice.delete(artcleId);
		//删除图文数据
		myarticleservice.delete(artcleId);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, "page7", null, null, null);
	}
	
	
	/**
	 *修改单图文 
	 */
	
	@RequestMapping(value="/updateSingleArticle")
	public @ResponseBody String updateSingleArticle(HttpServletRequest request){
		log.info("entering...ArticleController...updateSingleArticle()");
		long userid = SessionUtil.getUserId(request);
		SysUser user = SessionUtil.getLoginUser(request);
		long articleId=Long.valueOf(request.getParameter("articleid"));
		
		MyArticle art=myarticleservice.findById(articleId);
		//图文信息
    	String title=request.getParameter("artitle");//标题
    	String filename=request.getParameter("filename");//封面图片路径
    	//拆分图文封面路径，存DB
    	int dex=filename.indexOf(MyArticleController.APPIMAGES);
    	String fileurl=filename.substring(dex+MyArticleController.APPIMAGES.length());
    	
    	String description=request.getParameter("description");//图文描述文件
    	int flag=Integer.parseInt(request.getParameter("flag"));//标识
    	String artdetail=request.getParameter("artdetail");//图文详情
    	//
    	//int atype=Integer.parseInt(request.getParameter("test"));	
    	//自定义链接
    	String linkurl=request.getParameter("linkurl");
    	//图文跳转页面
    	String url=request.getParameter("modulecontent");
    	
    	if(StringUtils.isNotEmpty(title)&&StringUtils.isNotEmpty(fileurl)){
    		art.setTitle(title);
        	art.setPicUrl(fileurl);
        	art.setDescription(description);
        	art.setFlag(flag);
        	art.setDetail(artdetail);
        	if(StringUtils.isNotEmpty(url)){
        		art.setUrl(url+"?id="+userid);
        	}
        	if(StringUtils.isNotEmpty(linkurl)){
        		art.setUrl(linkurl);
        	}
        	art.setCreateName(user.getUsername());
        	myarticleservice.update(art);
        	return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS,"page7", null, Constants.CLOSECURRENT, "wx/findAllArticlesByPage");
        }
		
    	return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_UPDATE_FAIL,"page7", null, Constants.CLOSECURRENT, "wx/findAllArticlesByPage");
	}
	
	
	/**
	 *根据id查询单个图文信息 
	 */
	@RequestMapping(value="/findSingleArticleById")
	public String findSingleArticleById(HttpServletRequest request){
		log.info("entering...ArticleController...findSingleArticleById()");
		long articleId=Long.valueOf(request.getParameter("id"));
		//根据id查询单个图文信息
		MyArticle myarticle= myarticleservice.findById(articleId);
		request.setAttribute("myarticle", myarticle);
		request.setAttribute("picurl", Constants.DB_IMAGE_FILE+"/"+myarticle.getPicUrl());
		return "wx/updatesinglearticle";
	}
	

	//分页查询所有单图文
	@RequestMapping(value="/findAllArticlesByPage")
	public String findAllArticlesByPage(HttpServletRequest request){
		log.info("entering...ArticleController...findAllArticlesByPage()");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("accountId",this.findAccount(request).getId());
		List<MyArticle> artList=myarticleservice.findAllArticleByPage(map);
		//总数
		long totalNum=myarticleservice.findCountByParam(map);
		request.setAttribute("artList", artList);
		request.setAttribute("totalCount", totalNum);
		return "wx/singlearticle";
		
	}
	
	@RequestMapping(value="/PreAddSinleArticle")
	public String PreAddSinleArticle(){
		return "wx/addsingleArticle";
	}
	
	
	/**
	 *添加多图文,关联公众号
	 *多图文的定义：一个默认的单图文再关联多个单图文,关联的数量不能超过9个
	 *@param request
	 *@return string 
	 */
	@RequestMapping(value="/addManyarticle")
	public @ResponseBody String addManyarticle(HttpServletRequest request){
		log.info("entering...ArticleController...addManyarticle()");
		//①.添加第一个作为封面的单图文
		//②.关联多个单图文
		//③.关联公众号
		
		long userid = SessionUtil.getUserId(request);
		SysUser user = SessionUtil.getLoginUser(request);
    	//获取公众号id
    	Account account=accountservice.findAccountInfo(userid);
    	//图文信息
    	String title=request.getParameter("artitle");//标题
    	String filename=request.getParameter("filename");//封面图片路径
    	//拆分图文封面路径，存DB
    	int dex=filename.indexOf(MyArticleController.APPIMAGES);
    	String fileurl=filename.substring(dex+MyArticleController.APPIMAGES.length());
    	String description=request.getParameter("description");//图文描述文件
    	int flag=Integer.parseInt(request.getParameter("flag"));//标识
    	String artdetail=request.getParameter("artdetail");//图文详情
    	int atype=Integer.parseInt(request.getParameter("test"));	
    	//获取多个单图文id
    	String manyarticle=request.getParameter("org3.id");
    	String[] artstr=manyarticle.split(",");
    	MyArticle art=null;
        Articleaccount articleaccount=null;
    	if(StringUtils.isNotEmpty(title)&&StringUtils.isNotEmpty(fileurl)){
    		art=new MyArticle();
    		art.setTitle(title);
        	art.setPicUrl(fileurl);
        	art.setDescription(description);
        	art.setFlag(flag);
        	art.setDetail(artdetail);
        	art.setCreateName(user.getUsername());
        	myarticleservice.create(art);
        	//当采取默认方式，点击图文对应文本编译器里的内容
	        if(atype==0){
        		String myurl="wap/showArticleInfo.html"+"?id="+art.getId();
        		art.setUrl(myurl);
        		myarticleservice.update(art);
        	}
        	
        	//关联公众号
            //	articleaccountservice.create(obj)
        	if(art.getId()>0){
        		articleaccount=new Articleaccount();
        		articleaccount.setArtcleId(art.getId());
        		//单图文，mutiarticle_id就是本身的article_id
        		articleaccount.setMutiArticle_id(art.getId());
        		//关联公众号
        		articleaccount.setAccountId(account.getId());
        		articleaccountservice.create(articleaccount);
        		
        	}
        	//根据id查找到多图文的默认的单图文
        	Articleaccount articleaccount2=articleaccountservice.findById(articleaccount.getId());
        	
        	//---------------------------------------以上为默认的单图文创建完毕-----------------------------------------------
        	 Articleaccount articleaccount3=null;
        	//关联多个单图文
        	for(String artId:artstr){
        		articleaccount3=new Articleaccount();
        		//System.out.println(">>>>>:"+artId);
        		articleaccount3.setArtcleId(Integer.parseInt(artId));
        		//System.out.println("父id<<<："+articleaccount2.getMutiArticle_id());
        		articleaccount3.setMutiArticle_id(articleaccount2.getMutiArticle_id());
        		articleaccount3.setAccountId(account.getId());
        		articleaccountservice.create(articleaccount3);
        	}
        	return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS,"page8", null,Constants.CLOSECURRENT, "wx/findManyArticleByPage");
    	}
    	return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_SUCCESS, null, null, null, null);
	
	}
	
	/**
	 *删除多图文 
	 */
	@RequestMapping(value="/delManArticle")
	public @ResponseBody String delManArticle(HttpServletRequest request)
	{
		log.info("entering...ArticleController...delManArticle");
		//删除默认的第一个单图文
		long articleId=Long.valueOf(request.getParameter("id"));
		myarticleservice.delete(articleId);
		//删除关联的其他的单图文
		articleaccountservice.deleteByMutiId(articleId);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, "page8", null, null, null);
	}
	
	//根据id查询多图文
	@RequestMapping(value="/findManArticleById")
	public String findManArticleById(HttpServletRequest request){
		log.info("entering...ArticleController...findManArticleById()");
		long articleId=Long.valueOf(request.getParameter("id"));
		//根据id查询单个图文信息
		MyArticle myarticle= myarticleservice.findById(articleId);
		request.setAttribute("myarticle", myarticle);
		request.setAttribute("picurl", Constants.DB_IMAGE_FILE+"/"+myarticle.getPicUrl());
		return "wx/updatemanarticle";
	}
	
	/**
	 *修改多图文 
	 */
	@RequestMapping(value="/updateManArticle")
	public @ResponseBody String updateManArticle(HttpServletRequest request){
		log.info("entering...ArticleController...updateManArticle()");
		long userid = SessionUtil.getUserId(request);
		SysUser user = SessionUtil.getLoginUser(request);
		long articleId=Long.valueOf(request.getParameter("articleid"));
		
		MyArticle art=myarticleservice.findById(articleId);
		//图文信息
    	String title=request.getParameter("artitle");//标题
    	String filename=request.getParameter("filename");//封面图片路径
    	//拆分图文封面路径，存DB
    	int dex=filename.indexOf(MyArticleController.APPIMAGES);
    	String fileurl=filename.substring(dex+MyArticleController.APPIMAGES.length());
    	
    	String description=request.getParameter("description");//图文描述文件
    	int flag=Integer.parseInt(request.getParameter("flag"));//标识
    	String artdetail=request.getParameter("artdetail");//图文详情
    	//
    	//int atype=Integer.parseInt(request.getParameter("test"));	
    	//自定义链接
    	String linkurl=request.getParameter("linkurl");
    	//图文跳转页面
    	String url=request.getParameter("modulecontent");
    	
    	if(StringUtils.isNotEmpty(title)&&StringUtils.isNotEmpty(fileurl)){
    		art.setTitle(title);
        	art.setPicUrl(fileurl);
        	art.setDescription(description);
        	art.setFlag(flag);
        	art.setDetail(artdetail);
        	if(StringUtils.isNotEmpty(url)){
        		art.setUrl(url+"?id="+userid);
        	}
        	if(StringUtils.isNotEmpty(linkurl)){
        		art.setUrl(linkurl);
        	}
        	art.setCreateName(user.getUsername());
        	myarticleservice.update(art);
        	return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS,"page8", null, Constants.CLOSECURRENT, "wx/findManyArticleByPage");
        }
		
    	return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_UPDATE_FAIL,"page8", null, Constants.CLOSECURRENT, "wx/findManyArticleByPage");
	}
	
	
	
	//同上，为了跳转不同的页面
	@RequestMapping(value="/findSingleArticlesByPage")
	public String findSingleArticlesByPage(HttpServletRequest request){
		log.info("entering...ArticleController...findAllArticlesByPage()");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("accountId",this.findAccount(request).getId());
		List<MyArticle> artList=myarticleservice.findAllArticleByPage(map);
				//总数
		long totalNum=myarticleservice.findCountByParam(map);
		request.setAttribute("artList", artList);
		request.setAttribute("totalCount", totalNum);
		return "wx/dwzOrgLookup2";
	}

	//分页查询所有多图文
	@RequestMapping(value="/findManyArticleByPage")
	public String findManyArticleByPage(HttpServletRequest request){
		log.info("entering...MyArticleController...findManyArticleByPage()");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("accountId",this.findAccount(request).getId());
		List<MyArticle> artList=myarticleservice.findAllManyArticleByPage(map);
		//总数
		long totalNum=myarticleservice.findManyArticleCountByParam(map);
		request.setAttribute("artList", artList);
		request.setAttribute("totalCount", totalNum);
		return "wx/manyarticles";
		
	}
	
	@RequestMapping(value="/PreManyArticle")
	public String PreManyArticle(){
		return "wx/addmanyarticle";
	}
	
	//--------------------------------------------------wap--------------------------------------------------------------
	
	@RequestMapping(value="/findAllArticlesByPageJson")
	public @ResponseBody String findAllArticlesByPageJson(HttpServletRequest request){
		log.info("entering...ArticleController...findAllArticlesByPage()");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("accountId",this.findAccount(request).getId());
		List<MyArticle> artList=myarticleservice.findAllArticleByPage(map);
		Gson gson=new Gson();
		return gson.toJson(artList);
		
	}
	
	//分页查询所有多图文
		@RequestMapping(value="/findManyArticleByPageJson")
		public @ResponseBody String findManyArticleByPageJson(HttpServletRequest request){
			log.info("entering...MyArticleController...findManyArticleByPage()");
			Map<String,Object> map=new HashMap<String,Object>();
			this.setPagination(request, map);
			map.put("accountId",this.findAccount(request).getId());
			List<MyArticle> artList=myarticleservice.findAllManyArticleByPage(map);
			Gson gson=new Gson();
			return gson.toJson(artList);
		}
	
	//根据id查询图文信息
	@RequestMapping(value="wap/findArticleInfoById")
	@ResponseBody
	public String findArticleInfoById(HttpServletRequest request){
		log.info("entering...MyArticleController...findArticleInfoById()");
		long articleId=Long.valueOf(request.getParameter("id"));
		MyArticle myarticle=myarticleservice.findById(articleId);
		request.setAttribute("picurl", Constants.DB_IMAGE_FILE+"/"+myarticle.getPicUrl());
		Type type=new TypeToken<MyArticle>(){}.getType();
		return JsonUtil.transformJson(Constants.SUCCESS, Constants.MSG_GET_SUCCESS, myarticle, type);
	}
}
