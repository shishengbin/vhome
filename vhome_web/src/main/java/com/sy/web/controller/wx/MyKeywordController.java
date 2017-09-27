package com.sy.web.controller.wx;

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

import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.Articleaccount;
import com.sy.modules.entity.wx.MyKeyword;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.ArticleaccountService;
import com.sy.modules.service.wx.MyArticleService;
import com.sy.modules.service.wx.MyKeywordService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;
@Controller
@RequestMapping(value = "/wx")
public class MyKeywordController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(MyKeywordController.class);
	
	@Autowired
	private UserService userservice;
	@Autowired
	private MyKeywordService mykeywordservice;
	@Autowired
	private MyArticleService myarticleservice;
	@Autowired
	private AccountService accountservice;
	@Autowired
	private ArticleaccountService artaccountservice;

	
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
    
    @RequestMapping(value="/preAddKeyWord")
    public String preAddKeyWord(){
    	return "wx/addkeyword";
    }
    
	//添加关键字回复
	@RequestMapping(value="/addKeyword")
	public @ResponseBody String addKeyword(HttpServletRequest request)
	{
		log.info("entering...MyKeywordController...addKeyword()");
		SysUser user=SessionUtil.getLoginUser(request);
		MyKeyword mykeyword=null;
		MyArticle myarticle=null;
		Articleaccount articleaccount=null;
		Account account=this.findAccount(request);
		//获取关键字
		String keyname=request.getParameter("keyname");//关键字名称
		//判断当前关键字是否已经存在？
		MyKeyword mykeyword2=mykeywordservice.findKeyWordByName(keyname);
		if(mykeyword2==null){
			//回复类型
			//若是图文，直接关联id，若是文本，先创建文本再关联对应文本id
			int article_type=Integer.parseInt(request.getParameter("article_type"));
			int article_id=Integer.parseInt(request.getParameter("article_id"));
			
			//公众号id
			if(StringUtils.isNotEmpty(keyname)){
				mykeyword=new MyKeyword();
				mykeyword.setKeyname(keyname);
				mykeyword.setArticle_type(article_type);
				//若是文本类型先创建文本
				if(article_type==0){
					String replycontent=request.getParameter("replycontent");
					myarticle=new MyArticle();
					myarticle.setContent(replycontent);
					myarticleservice.create(myarticle);
		            mykeyword.setArticle_id(myarticle.getId());
				}else if(article_type==1){
					//单图文id
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("id", Long.valueOf(article_id));
					map.put("mutiarticle_id", 0);
					//根据图文id找到对应的主键id
					 articleaccount=artaccountservice.findInfoByAticleId(map);
					mykeyword.setArticle_id(articleaccount.getId());
				}else if(article_type==2){
					//多图文
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("id", Long.valueOf(article_id));
					map.put("mutiarticle_id", article_id);
					//根据图文id找到对应的主键id
					 articleaccount=artaccountservice.findInfoByAticleId(map);
					 mykeyword.setArticle_id(articleaccount.getId());
				}
	            mykeyword.setAccount_id(account.getId());
	            mykeyword.setCreateName(user.getUsername());
	            //创建关键字回复
	            mykeywordservice.create(mykeyword);
	            return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS,"page6", null, Constants.CLOSECURRENT, "wx/findAllMykeywordsByPage");
		   }
		}			
		return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_ADD_WX_KEY_WORD, null, null, null, null);
	}
	
	
	/**
	 *删除关键字回复 
	 */
	@RequestMapping(value="/delKeyword")
    public @ResponseBody String delKeyword(HttpServletRequest request){
		log.info("entering...MyKeywordController...delKeyword()");
		long keyId=Long.valueOf(request.getParameter("id"));
		mykeywordservice.delete(keyId);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, "page6", null, null, null);
	}	
	
	
	/**
	 *根据id查询单个关键字回复信息 
	 */
	
	@RequestMapping(value="/findKeywordById")
	public String findKeywordById(HttpServletRequest request){
		log.info("entering...MyKeywordController...findKeywordById()");
		long keyId=Long.valueOf(request.getParameter("id"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("accountId", this.findAccount(request).getId());
		map.put("keyId", keyId);
		MyKeyword mykeyword=mykeywordservice.findMyKeyWordById(map);
		request.setAttribute("mykeyword", mykeyword);
		return "wx/updatekeyword";
	}
		
	
	//分页查询所有关键字回复
	@RequestMapping(value="/findAllMykeywordsByPage")
	public String findAllMykeywordsByPage(HttpServletRequest request){
		log.info("entering...MyKeywordController...findAllMykeywordsByPage()");
		Map<String,Object> map=new HashMap<String,Object>();
		this.setPagination(request, map);
		map.put("accountId",this.findAccount(request).getId());
		List<MyKeyword> keywordList=mykeywordservice.findAllMykeywordsByPage(map);
		//总数
		long totalNum=mykeywordservice.findCountByParam(map);
		request.setAttribute("keywordList", keywordList);
		request.setAttribute("totalCount", totalNum);
		return "wx/mykeywords";

	}
	
	//查询关键字对应的微官网
	@RequestMapping(value="/findVSite")
	public String findVSite(HttpServletRequest request){
		log.info("entering...MyKeywordController...findVSite()");
	    //查询微官网对应的图文信息
		long userid = SessionUtil.getUserId(request);
		Account account = accountservice.findAccountInfo(userid);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userid", account.getId()+"");
		map.put("content", "微官网");
		List<MyArticle> alist= mykeywordservice.findKeyWordBySinArticle(map);
		if(alist!=null&& alist.size()>0){
			request.setAttribute("myarticle", alist.get(0));
			request.setAttribute("picurl", Constants.DB_IMAGE_FILE+"/"+alist.get(0).getPicUrl());
		}
		return "wap/vsite/vsite";
	}
	
	//创建关键字对应的微官网
	@RequestMapping(value = "/addVSite")
	public @ResponseBody
	String addVSite(HttpServletRequest request) {
		log.info("entering...MyKeywordController...addVSite()");
		MyKeyword mykeyword = null;
		long myarticleId=-1;
		String str=request.getParameter("id");
		if(StringUtils.isNotEmpty(str)){
			myarticleId=Long.valueOf(str);
		}
		// ①创建微官网对应的图文
		// 触发关键字
		String keysite = request.getParameter("keysite");
		long userid = SessionUtil.getUserId(request);
		SysUser user = userservice.findById(userid);
		// 获取公众号id
		Account account = accountservice.findAccountInfo(userid);
		// 官网图文标题
		String sitetitle = request.getParameter("sitetitle");
		// 图文图片地址
		String filename = request.getParameter("filename");
		String fileurl = null;
		if (filename != null) {
			int dex = filename.indexOf(Constants.APPIMAGES);
			fileurl = filename.substring(dex + Constants.APPIMAGES.length());
		}
		// 官网简介
		String sitedescription = request.getParameter("sitedescription");
		// 图文跳转地址
		String linkurl = request.getParameter("linkurl");
		MyArticle art = null;
		Articleaccount articleaccount = null;
		if (myarticleId==-1) {
			art = new MyArticle();
			art.setTitle(sitetitle);
			art.setPicUrl(fileurl);
			art.setDescription(sitedescription);

			if (StringUtils.isNotEmpty(linkurl)) {
				art.setUrl(linkurl);
			}
			art.setCreateName(user.getUsername());
			myarticleservice.create(art);

			// ②关联公众号
			if (art.getId() > 0) {
				articleaccount = new Articleaccount();
				articleaccount.setArtcleId(art.getId());
				// 单图文，mutiarticle_id用0表示
				articleaccount.setMutiArticle_id(0);
				// 关联公众号
				articleaccount.setAccountId(account.getId());
				artaccountservice.create(articleaccount);
			}

			// ③创建关键字回复
			MyKeyword mykeyword2 = mykeywordservice.findKeyWordByName(keysite);
			if (mykeyword2 == null) {
				// 回复类型是1代表单图文
				if (StringUtils.isNotEmpty(keysite)) {
					mykeyword = new MyKeyword();
					mykeyword.setKeyname(keysite);
					mykeyword.setArticle_type(1);// 单图文
					// 根据图文id找到对应的主键id
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("id", Long.valueOf(art.getId()));
					map.put("mutiarticle_id", 0);
					//根据图文id找到对应的主键id
					 articleaccount=artaccountservice.findInfoByAticleId(map);
					mykeyword.setArticle_id(articleaccount.getId());
					mykeyword.setAccount_id(account.getId());
					mykeyword.setCreateName(user.getUsername());
					// 创建关键字回复
					mykeywordservice.create(mykeyword);
				}
			}
		}else{
			art = new MyArticle();
			art.setId(myarticleId);
			art.setTitle(sitetitle);
			art.setPicUrl(fileurl);
			art.setDescription(sitedescription);

			if (StringUtils.isNotEmpty(linkurl)) {
				art.setUrl(linkurl);
			}
			art.setCreateName(user.getUsername());
			myarticleservice.update(art);
		}
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, "page42", null, null, null);
	}
}
