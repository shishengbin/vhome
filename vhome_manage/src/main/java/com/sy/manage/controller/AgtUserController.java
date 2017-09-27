package com.sy.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.manage.commons.Constants;
import com.sy.manage.commons.DataTool;
import com.sy.manage.commons.JsonUtil;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.Accounting;
import com.sy.modules.entity.agt.AgtCompany;
import com.sy.modules.entity.agt.AgtUser;
import com.sy.modules.service.agt.AccountingService;
import com.sy.modules.service.agt.AgtCompanyService;
import com.sy.modules.service.agt.AgtUserService;

/**
 * @author sss
 * @date 2013-8-15
 */
@Controller
public class AgtUserController extends PageSet {

	private static final Logger log = LoggerFactory.getLogger(AgtUserController.class);
	@Autowired
	private AgtUserService agtuserservice;
	@Autowired
	private AccountingService accountingservice;
	@Autowired
	private AgtCompanyService acs;

	/**
	 * 登录
	 * @param request
	 * @param session
	 * @param au
	 * @return
	 */
	@RequestMapping(value = "/agtLogin")
	public String agtLogin(HttpServletRequest request, HttpSession session,@ModelAttribute AgtUser au) {
		if (au != null && au.getU_name() != null && au.getU_pass() != null) {
			AgtUser fau = null;
			AgtUser agtuser = agtuserservice.agtLogin(au.getU_name());
			if (agtuser != null) {
				// 判断密码是否正确
				if (au.getU_pass().equals(agtuser.getU_pass())) {
					// 帐号正确
					if (agtuser.getRole().getId() == 1) {
						fau = agtuserservice.findAgentById(agtuser.getId());
					} else {
						fau = agtuserservice.findAgentById(agtuser.getFatherId());
					}
					//agtuser.setMoney_box(fau.getMoney_box());
					//agtuser.setComs(fau.getComs());
					session.setAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY,fau);
					// return
					// JsonUtil.transferJsonResponse(1,Constants.MSG_LOGIN_SUCCESS,
					// "index");
					return "index";
				} else {
					// 密码错误
					// return
					// JsonUtil.transferJsonResponse(0,Constants.MSG_NAMEPASS_FAIL,
					// null);
					return "../../login";
				}
			} else {
				// return
				// JsonUtil.transferJsonResponse(0,Constants.MSG_LOGIN_FAIL,
				// null);
				return "../../login";
			}
		} else {
			// return JsonUtil.transferJsonResponse(0,Constants.MSG_LOGIN_FAIL,
			// null);
			return "../../login";
		}
	}
	
	@RequestMapping(value="/findHomePage")
	public String findHomePage(){
		return "homepage";
	}

	/**
	 * 分页查询所有用户
	 */
	@RequestMapping(value = "/findAllUsersByPage")
	public String findAllUsersByPage(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...SysUserController...findAllUsersByPage()");
		Long id = SessionUtil.getLoginId(request, response);
		Map<String, Object> map = DataTool.getParam(request, "username");
		map.put("id", id);
		// 用户名
		String userName = request.getParameter("username");
		this.setPagination(request, map);
		map.put("userName", userName);
		List<AgtUser> userList = agtuserservice.findAllUserByPage(map);
		// 总数
		long totalNum = agtuserservice.findCountByParam(map);
		request.setAttribute("userList", userList);
		request.setAttribute("totalCount", totalNum);
		return "admin/show_user";
	}

	/**
	 * 初始化首页
	 */
	@RequestMapping(value = "/initIndex")
	@ResponseBody
	public String initIndex(HttpServletRequest request,HttpServletResponse response) {
		AgtUser au =SessionUtil.getAgtLoginUser(request, response);
		double bal = agtuserservice.queryBalance(SessionUtil.getLoginId(request, response));
		au.setMoney_box(bal);
		Gson gson = new Gson();
		return gson.toJson(au);
	}
	/**
	 * 退出
	 */
	@RequestMapping("/invalidate")
	public void invalidate(HttpServletRequest request, HttpSession session,HttpServletResponse response) {
		session.removeAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY);
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/preUpdatePass")
	public String preUpdatePass(){
		return "admin/update_pass";
	}
	
	/**
	 * 修改密码
	 */
	
	@RequestMapping("/updatePass")
	@ResponseBody
	public String updatePass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("pass")String pass,
			@RequestParam("pass2")String pass2){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",SessionUtil.getAgtLoginUser(request, response).getU_name());
		map.put("pass",pass); map.put("pass2",pass2); 
		
		 AgtUser au =agtuserservice.agtLogin(map.get("name").toString());
		 if(au!=null){
			 if(!au.getU_pass().equals(map.get("pass").toString())){ 
				 return JsonUtil.transferJsonResponse(0,Constants.MSG_PASS_FAIL,null); 
			 	}
			 }else{
				 return JsonUtil.transferJsonResponse(0,Constants.MSG_USERNAME_FAIL,null);
			} 
		 agtuserservice.updatePass(map); 
		 return JsonUtil.transferJsonResponse(1,Constants.MSG_UPDATE_SUCCESS,null);
		}
	 
	/**
	 * 充值
	 */
//	@RequestMapping("/agentPay")
//	@ResponseBody
//	public String agentPay(HttpServletRequest request,
//			HttpServletResponse response, @RequestParam("id") Long id,
//			@RequestParam("money") double money) {
//		double mo = agtuserservice.queryBalance(id);
//		mo += money;
//		boolean success = agtuserservice.updateBalance(new Accounting(
//				AccountingType.银行入账, money, mo, null, 0, null, id, SessionUtil
//						.getLoginUser(request, response).getU_name()));
//		if (!success) {
//			return JsonUtil.transferJsonResponse(0,Constants.MSG_OPERATIO_FAIL, null);
//		}
//		return JsonUtil.transferJsonResponse(1, Constants.MSG_OPERATIO_SUCCESS,"findAllUsersByPage");
//	}

	/**
	 * 查找指定代理商
	 */
	@RequestMapping("/findAgentById")
	public String findAgentById(HttpServletRequest request) {
		Map<String, Object> map = DataTool.getParam(request, "id", "name", "xc", "comid");
		if (map.get("xc").equals("c")) {
			// 最近一次充值记录
			Accounting a = accountingservice.queryNewestOne(Long.valueOf(map.get("id").toString()));
			request.setAttribute("accounting", a);
		}
		return "admin/control_user";
	}

	@RequestMapping("/updateAgentIsAdmin")
	@ResponseBody
	public String updateAgentIsAdmin(HttpServletRequest request,@ModelAttribute AgtUser au) {
		Integer statusCode = 0;
		String message = Constants.MSG_UNKNOWN_OPERATION;
		if (au != null && au.getComs() != null && au.getComs().getCompanyname() != null) {
			if (agtuserservice.updateAgt(au)) {
				statusCode = 1;
				message = Constants.MSG_OPERATIO_SUCCESS;
			} else {
				message = Constants.MSG_OPERATIO_FAIL;
			}
		}
		return JsonUtil.transferJsonResponse(statusCode, message,"findAllUsersByPage");
	}

	/**
	 * prepare add agent
	 */
	@RequestMapping("/preAddAgent")
	public String preAddAgent() {
		return "admin/new_agent";
	}

	@RequestMapping("/addAgent")
	@ResponseBody
	public String addAgent(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...AgtUserController...addAgent()");
		Integer statusCode = 0;
		String message = Constants.MSG_UNKNOWN_OPERATION;
		AgtUser au = new AgtUser();
		au.setU_name(request.getParameter("username"));
		au.setU_pass(request.getParameter("passwords"));
		//au.setDiscount(Float.parseFloat(request.getParameter("discount")));
		au.setRoleId(Constants.SUB_COMPANY);
		au.setFatherId(Constants.FATHERID);
		AgtCompany ac = new AgtCompany();
		ac.setCompanyName(request.getParameter("companyName"));
		ac.setCompanyPerson(request.getParameter("companyPerson"));
		ac.setCompanyPhone(request.getParameter("companyPhone"));
		ac.setCompanyEmail(request.getParameter("companyEmail"));
		ac.setCompanyBusiness(request.getParameter("companyBusiness"));
		ac.setCompanyState(1);
		if (agtuserservice.addAgent(ac, au)) {
			statusCode = 1;
			message = Constants.MSG_OPERATIO_SUCCESS;
		} else {
			message = Constants.MSG_OPERATIO_FAIL;
		}
		return JsonUtil.transferJsonResponse(statusCode, message,"findAllUsersByPage");
	}

	/**
	 * 修改代理商信息
	 */
	@RequestMapping(value = "/findOneAgent")
	public String findOneAgent(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...AgtUserController...findOneAgent()");
		AgtUser au = SessionUtil.getAgtLoginUser(request, response);
		// 获取当前帐号的Id
		long aid = 1l;
		if (au.getFatherId() == 1) {
			aid = au.getId();
		} else {
			aid = au.getFatherId();
		}
		long cid = agtuserservice.findCompanyIdById(aid);
		AgtCompany ac = acs.findById(cid);
		request.setAttribute("ac", ac);
		return "company/one_company";
	}

	/**
	 * 手机端访问 根据关键词查找用户ID
	 */
	@RequestMapping(value = "/queryUserIdByKey")
	@ResponseBody
	public String queryUserIdByKey(HttpServletRequest request,
			@RequestParam("keyword") String keyword) {
		Gson gson = new Gson();
		Long id = null;
		try {
			id = agtuserservice.queryUserIdByKey(keyword);
			if (id != null)
				return gson.toJson(id);
		} catch (Exception e) {
		}
		return null;
	}
}
