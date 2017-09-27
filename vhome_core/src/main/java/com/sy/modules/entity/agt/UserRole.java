package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

public class UserRole extends ParentEntity{
	private static final long serialVersionUID = 1L;
	
	private Long user_id ;//用户id
	private Long role_id ;//角色id
	private Integer del_status ;//状态
	
	public UserRole(){};
	public UserRole(Long userid,Long roleid){
		this.user_id = userid;
		this.role_id = roleid;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public Integer getDel_status() {
		return del_status;
	}
	public void setDel_status(Integer del_status) {
		this.del_status = del_status;
	}

}
