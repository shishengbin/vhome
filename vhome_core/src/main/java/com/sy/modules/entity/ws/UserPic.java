package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 二维码与企业关联
 * 
 */
public class UserPic extends ParentEntity{
	private static final long serialVersionUID = 1L;
	private Long user_id;
	private Long picture_id;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(Long picture_id) {
		this.picture_id = picture_id;
	}
}
