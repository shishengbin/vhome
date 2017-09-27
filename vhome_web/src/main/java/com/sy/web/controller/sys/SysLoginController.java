package com.sy.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.service.sys.UserService;
import com.sy.web.commons.Constants;

/**
 * 系统登录
 * 
 * @author sss
 * @date 2013-8-15
 */
@Controller
public class SysLoginController {
	private static final Logger log = LoggerFactory.getLogger(SysLoginController.class);

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 判断用户是否登录
	 * 
	 * @param currUser
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public String login(SysUser currUser, HttpSession session) {
		log.info("enrering...SysLoginController...login()");
		Subject sub = SecurityUtils.getSubject();
		String username = currUser.getUsername();
		UsernamePasswordToken token = new UsernamePasswordToken(
				currUser.getUsername(), currUser.getUserpass());
		token.setRememberMe(true);
		try {
			SysUser user = userService.finUserByName(username);
			sub.login(token);
			session.setAttribute(Constants.USER_LOGIN_SESSION_KEY, user);
			return "index";
		} catch (AuthenticationException e) {
			token.clear();
			return "../../login";
		}
	}
}
