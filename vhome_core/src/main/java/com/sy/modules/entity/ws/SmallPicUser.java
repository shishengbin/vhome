package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

public class SmallPicUser extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	private Long user_id;
	private Long smallPic_id;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getSmallPic_id() {
		return smallPic_id;
	}
	public void setSmallPic_id(Long smallPic_id) {
		this.smallPic_id = smallPic_id;
	}
}
