package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 *系统角色表
 *@author sss 
 */
public class Role extends ParentEntity{

	private static final long serialVersionUID = 1L;

	private String role_name ;
	private String permissions ;

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
}
