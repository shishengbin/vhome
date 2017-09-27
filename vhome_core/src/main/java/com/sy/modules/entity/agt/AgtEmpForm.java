package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 * 代理商下员工信息表
 * @author zw
 * 2013-11-05
 */
public class AgtEmpForm extends ParentEntity{
	 private static final long serialVersionUID = 1L;
     private String username;       //用户帐号
     private String role;               //管理员角色
     private int state;                  //用户状态
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "AgtEmpForm [username=" + username + ", role=" + role
				+ ", state=" + state + "]";
	}
     
	
     
}
