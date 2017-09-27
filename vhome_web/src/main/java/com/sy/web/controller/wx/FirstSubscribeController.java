package com.sy.web.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.FirstSubscribe;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.service.wx.AccountService;
import com.sy.modules.service.wx.FirstSubscribeService;
import com.sy.modules.service.wx.MyArticleService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *首次关注 
 */

@Controller
@RequestMapping(value = "/wx")
public class FirstSubscribeController extends PageSet {

	private static final Logger log = LoggerFactory.getLogger(FirstSubscribeController.class);
	@Autowired
	private MyArticleService myarticleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private FirstSubscribeService firstsubscribeservice;

	/**
	 * 获取登陆账号id 获取公众号
	 */
	private Account findAccount(HttpServletRequest request) {
		log.info("entering...FirstRespController...findAccount()");
		long userid = SessionUtil.getUserId(request);
		// 获取公众号id
		Account account = accountService.findAccountInfo(userid);
		return account;
	}

	//
	@RequestMapping(value = "/findFirstSubscribeInfo")
	public String findFirstSubscribeInfo(HttpServletRequest request) {
		log.info("entering...FirstRespController...findFirsrSubscribeInfo()");
		Account account = this.findAccount(request);
		FirstSubscribe fs = firstsubscribeservice.findFirstSubscribeInfo(account.getId());
		if(null!=fs){
			MyArticle art = myarticleService.findById(fs.getArticle_id());
			request.setAttribute("art", art);
			request.setAttribute("fs", fs);
		}
		return "wx/followme";
	}

	// 首次订阅
	@RequestMapping(value = "/createFirstSubscribe")
	public @ResponseBody
	String createFirstSubscribe(HttpServletRequest request) {
		log.info("entering...FirstRespController...createFirstSubscribe()");
		// 获取公众号信息
		Account account = this.findAccount(request);
		// 获取首次订阅回复的类型
		int msgtype = Integer.parseInt(request.getParameter("msgtype"));
		// 获取首次回复的的文本article_id
		String article_id=request.getParameter("article_id");
		long articleId=0;
		if(StringUtils.isNotEmpty(article_id)){
			articleId=Long.valueOf(article_id);
		}
		//回复的文本内容
		String replycontent = request.getParameter("replycontent");
		// 若是首次直接创建,先在article里创建
		int num = firstsubscribeservice.findSubscribeCount(account.getId());
		MyArticle art = null;
		FirstSubscribe fs = null;
		// 首次创建
		if (num == 0) {
			if(msgtype==0){
				art = new MyArticle();
				art.setContent(replycontent);
				myarticleService.create(art);
				fs = new FirstSubscribe();
				fs.setMsg_type(msgtype);
				fs.setArticle_id(art.getId());
				fs.setAccount_id(account.getId());
				firstsubscribeservice.create(fs);
				
			}if(msgtype==1){
				fs = new FirstSubscribe();
				fs.setMsg_type(msgtype);
				fs.setArticle_id(articleId);
				fs.setAccount_id(account.getId());
				firstsubscribeservice.create(fs);
			}
		
		} else {
			// 已经有首次订阅回复数据
			FirstSubscribe fs2 = firstsubscribeservice.findFirstSubscribeInfo(account.getId());
			//修改文本
			if(msgtype==0){
				// 根据article_id找到对应的图文数据
				MyArticle art2 = myarticleService.findById(fs2.getArticle_id());
				art2.setContent(replycontent);
				myarticleService.update(art2);
				
				fs2.setMsg_type(msgtype);
				fs2.setAccount_id(account.getId());
				firstsubscribeservice.update(fs2);
				
			}
			//修改图文
			if(msgtype==1){
				fs2.setMsg_type(msgtype);
				fs2.setArticle_id(articleId);
				fs2.setAccount_id(account.getId());
				firstsubscribeservice.update(fs2);
			}
		}
		// 首次关注回复的值可能存在一种，若已有就是修改现有的
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, null, null, null, null);
	}

}
