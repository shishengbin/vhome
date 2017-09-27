package com.sy.modules.entity.wx;

import com.sy.commons.entity.ParentEntity;

public class Sysuseraccount extends ParentEntity {

	private static final long serialVersionUID = 3632292657154661008L;
	
	private Long user_id; //用户id
	private Long account_id;//微信公众号id
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	
	

}
