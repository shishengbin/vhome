package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

public class PublicUser extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long publicId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPublicId() {
		return publicId;
	}
	public void setPublicId(Long publicId) {
		this.publicId = publicId;
	}
}
