package com.sy.web.commons;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.service.sys.UserService;



@Component
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		if (userName != null && !"".equals(userName)) {
			SysUser user = userService.finUserByName(token.getUsername());// 用户名登录
			if (user != null)
				return new SimpleAuthenticationInfo(user.getUsername(),
						user.getUserpass(), getName());
		}
		return null;
	}
	

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = userService.findByUserName(shiroUser.userName);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Role role : user.getRoleList()) {
			// 基于Role的权限信息
			info.addRole(role.getRoleName());
			// 基于Permission的权限信息
			info.addStringPermissions(role.getPermissionList());
		}
		return info;
		*/
		return null;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		//HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.HASH_ALGORITHM);
		//matcher.setHashIterations(Constants.HASH_INTERATIONS);
		//setCredentialsMatcher(matcher);
	}

}
