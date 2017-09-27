package com.sy.web.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sy.modules.entity.sys.SysUser;

/**
 * 操作session
 */
public class SessionUtil {
	// 获取登录用户
	public static SysUser getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser user = null;
		if (session != null) {
			user = (SysUser) session.getAttribute(Constants.USER_LOGIN_SESSION_KEY);
		}
		return user;
	}

	// 获取登录用户id
	public static Long getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser user = null;
		if (session != null) {
			user = (SysUser) session.getAttribute(Constants.USER_LOGIN_SESSION_KEY);
		}
		return Long.valueOf(user.getId());
	}

	// 微信后台用户ID
	public static Long getuserid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Long) session.getAttribute("userid");
	}

	/*
	 * // 获取登录代理商 public static AgtUser getLoginUser(HttpServletRequest request,
	 * HttpServletResponse response) { HttpSession session =
	 * request.getSession(); AgtUser user = null; if (session != null) { user =
	 * (AgtUser) session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY); }
	 * if(user==null){ try {
	 * request.getRequestDispatcher("login.html").forward(request, response); }
	 * catch (ServletException e) { e.printStackTrace(); } catch (IOException e)
	 * { e.printStackTrace(); } } return user; }
	 * 
	 * // 获取登录代理商ID public static Long getLoginId(HttpServletRequest request,
	 * HttpServletResponse response) { HttpSession session =
	 * request.getSession(); AgtUser user = null; if (session != null) user =
	 * (AgtUser) session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY);
	 * if(user==null){ try {
	 * request.getRequestDispatcher("login.html").forward(request, response); }
	 * catch (ServletException e) { e.printStackTrace(); } catch (IOException e)
	 * { e.printStackTrace(); } return null; }else{ if(user.getRole().getId()>
	 * 2){ return user.getFatherId(); }else{ return user.getId(); } } }
	 */
}
