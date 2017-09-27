package com.sy.web.controller.sys;

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

import com.sy.commons.entity.HResult;
import com.sy.modules.entity.sys.Payment;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.service.sys.PaymentService;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.wx.AccountService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 * 管理系统所有用户
 * 
 * @author sss
 * @date 2013-8-15
 */
@Controller
@RequestMapping(value = "/sys")
public class SysUserController extends PageSet {
	private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private UserService userservice;

	@Autowired
	private AccountService acservice;

	@Autowired
	private PaymentService paymentservice;

	/**
	 * add user account
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUserInfo")
	public @ResponseBody String addUserInfo(HttpServletRequest request) {
		log.info("entering...SysUserController...addUserInfo()");
		// 获取表单信息
		String userName = request.getParameter("username"); // 帐号
		String userPass = request.getParameter("userpass"); // 密码
		String userRemark = request.getParameter("userremark");// 其他信息

		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = null;
		if (StringUtils.isNotBlank(userName)) {
			// 根据用户帐号查询用户
			SysUser u = userservice.finUserByName(userName);
			if (u != null) {
				return JsonUtil.transferJsonResponse(Constants.ERROR,Constants.MSG_USER_EXIST, null, null, null, null);
			} else {
				user = new SysUser();
				user.setUsername(userName);
				user.setUserpass(userPass);
				user.setUserremark(userRemark);
				userservice.create(user);
				map.put("user", user);
			}
		}
		
		return JsonUtil.transferJsonResponse(Constants.SUCCESS, Constants.MSG_ADD_SUCCESS, Constants.REL_USERMANAGER, null,
				Constants.CLOSECURRENT, "sys/findAllUsersByPage");
	}
	
	/**
	 * delete user account
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteUserInfo")
	public @ResponseBody
	String deleteUserInfo(HttpServletRequest request) {
		log.info("entering...SysUserController...deleteUserInfo()");
		Long userId = Long.valueOf(request.getParameter("id"));
		if (userId != null) {
			userservice.delete(userId);
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,
					Constants.MSG_DEL_SUCCESS, Constants.REL_USERMANAGER, null,
					null, null);
		}
		return JsonUtil.transferJsonResponse(Constants.ERROR,
				Constants.MSG_DEL_FAIL, Constants.REL_USERMANAGER, null, null,
				null);
	}

	/**
	 * prepare update
	 * @return page
	 */
	@RequestMapping(value = "/updatePass")
	public String updatePass() {
		return "sys/updatePass";
	}

	/**
	 * update user account
	 * @param request
	 * @return page
	 */
	@RequestMapping(value = "/updateUserInfo")
	public @ResponseBody
	String updateUserInfo(HttpServletRequest request) {
		log.info("entering...SysUserController...updateUserInfo()");
		// 获取状态
		Integer userstuts = Integer.valueOf(request.getParameter("stuts"));
		// 获取Id
		Long userId = Long.valueOf(request.getParameter("userId"));
		SysUser user = userservice.findById(userId);
		if (user != null) {
			Integer ss = userstuts == 0 ? 1 : 0;
			user.setUserstuts(ss);
			System.out.println("stuts:" + user.getUserstuts());
			userservice.update(user);
			String xx = null;
			if (ss == 0)
				xx = Constants.MSG_INVALID_SUCCESS;
			else if (ss == 1)
				xx = Constants.MSG_ACTIVATE_SUCCESS;
			return JsonUtil.transferJsonResponse(Constants.SUCCESS, xx, null,"page11", Constants.FORWARD, null);
		} else {
			return JsonUtil.transferJsonResponse(Constants.ERROR,
					Constants.MSG_OPERATIO_FAIL, null, null, null, null);
		}
	}

	/**
	 * search userinfo by userid
	 * @param request
	 * @return page
	 */
	@RequestMapping(value = "/findUserById")
	public String findUserById(HttpServletRequest request) {
		log.info("entering...SysUserController...findUserById()");
		// 获取userId
		Long userId = Long.valueOf(request.getParameter("id"));
		SysUser user = userservice.findById(userId);
		request.setAttribute("user", user);
		return "sys/updateUserInfo";
	}

	/**
	 * searcha users by pagination
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAllUsersByPage")
	public String findAllUsersByPage(HttpServletRequest request) {
		log.info("entering...SysUserController...findAllUsersByPage()");
		// 用户名
		String userName = request.getParameter("username");
		Map<String, Object> map = new HashMap<String, Object>();
		this.setPagination(request, map);
		map.put("userName", userName);
		List<SysUser> userList = userservice.findAllUserByPage(map);
		// 总数
		long totalNum = userservice.findCountByParam(map);
		request.setAttribute("userList", userList);
		request.setAttribute("count", totalNum);
		request.setAttribute("username", userName);
		return "sys/UserManager";
	}

	/**
	 * update account pass
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toUpdPass")
	public @ResponseBody
	String toUpdPass(HttpServletRequest request) {
		log.info("entering...SysUserController...toUpdPass()");
		// 获取老密码
		String oldpass = request.getParameter("oldPassword");
		// 获取新密码
		String newpass = request.getParameter("newPassword");
		// 验证老密码是否正确
		// ①.获取当前用户
		SysUser user = userservice.findById(SessionUtil.getUserId(request));
		if (!oldpass.equals(user.getUserpass())) {
			return "wx/menuinfo";
		} else {
			user.setUserpass(newpass);
			userservice.update(user);
			// 修改密码
			return JsonUtil.transferJsonResponse(Constants.SUCCESS,
					Constants.MSG_UPDATE_SUCCESS, null, null, null, null);
		}
	}

	@RequestMapping(value = "/findAliPay")
	public String findAliPay() {
		return "ws/zhifubao/zhifubao";
	}

	@RequestMapping(value = "/findVchatpay")
	public String findVchatpay() {
		return "ws/zhifubao/vchatpay";
	}

	/**
	 * search all payments
	 * @param request
	 * @return page
	 */
	@RequestMapping(value = "/findAllPayment")
	public String findAllPayment(HttpServletRequest request) {
		log.info("entering...SysUserController..findAllPayment");
		List<Payment> paylist = paymentservice.findAllPayments();
		request.setAttribute("paylist", paylist);
		return "ws/zhifubao/payments";
	}

	/**
	 * 根据用户id设置支付宝
	 */

	@RequestMapping(value = "/updateUserAlipay")
	@ResponseBody
	public String updateUserAlipay(HttpServletRequest request, SysUser user) {
		Long userid = SessionUtil.getUserId(request);
		user.setId(userid);
		userservice.updateUserAlipay(user);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,
				Constants.MSG_UPDATE_SUCCESS, null, null,
				Constants.CLOSECURRENT, null);
	}

	/**
	 * show company info
	 * @param request
	 * @return json
	 */
	@RequestMapping(value = "/showCompany")
	@ResponseBody
	public HResult<SysUser> showCompany(HttpServletRequest request) {
		HResult<SysUser> result = new HResult<SysUser>(true, "");
		Long userid = SessionUtil.getUserId(request);
		SysUser user = userservice.queryById(userid);
		result.setObjValue(user);
		return result;
	}

	// -------------------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------------------

	/**
	 * search vchat account
	 */
	@RequestMapping(value = "/findVxaccount")
	public String findVxaccount(HttpServletRequest request) {
		log.info("entering...SysUserController...findVxaccount()");
		Long userid = SessionUtil.getUserId(request);
		// 根据登陆账号查询微信公众账号信息
		Account account = acservice.findAccountInfo(userid);
		if (null != account) {
			request.setAttribute("account", account);
			return "sys/vxaccount";
		} else {
			return "system/error";
		}
	}

	/**
	 * search vchat account by id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAccountById")
	public String findAccountById(HttpServletRequest request) {
		log.info("entering...SysUserController...findAccountById()");
		// 获取id
		Long accountId = Long.valueOf(request.getParameter("id"));
		Account account = acservice.findAccountInfo(accountId);
		if (null != account) {
			request.setAttribute("account", account);
			if (StringUtils.isNotEmpty(account.getAc_pic())) {
				request.setAttribute("picurl", Constants.DB_IMAGE_FILE + "/"+ account.getAc_pic());
			}
			return "sys/updateaccount";
		} else {
			return "system/error";
		}
	}

}
