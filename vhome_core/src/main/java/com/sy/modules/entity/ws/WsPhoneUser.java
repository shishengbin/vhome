package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;
/**
 * 号码与企业关联
 * 
 */
public class WsPhoneUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long phoneId;

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
