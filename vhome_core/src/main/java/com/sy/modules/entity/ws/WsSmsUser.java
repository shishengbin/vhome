package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 短信与企业关联
 * 
 */
public class WsSmsUser extends ParentEntity{
	private static final long serialVersionUID = 1L;
	private Long user_id;
	private Long sms_id;
	public Long getSms_id() {
		return sms_id;
	}
	public void setSms_id(Long sms_id) {
		this.sms_id = sms_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
