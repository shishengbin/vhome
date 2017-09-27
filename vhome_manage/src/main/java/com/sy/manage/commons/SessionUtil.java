package com.sy.manage.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sy.modules.entity.agt.AgtUser;

/**
 * 操作session
 */
public class SessionUtil {
	
	// 获取登录代理商
	public static AgtUser getAgtLoginUser(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		AgtUser user = null;
		if (session != null) {
			user = (AgtUser) session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY);
		}
		return user;
	}

	// 获取登录代理商ID
	public static Long getLoginId(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		AgtUser user = null;
		if (session != null){
			user = (AgtUser) session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY);
			if (user !=null) {
				if (user.getRole().getId() > 2) {
					return user.getFatherId();
				} else {
					return user.getId();
				}
			}
		}
		return null;
	}
}
