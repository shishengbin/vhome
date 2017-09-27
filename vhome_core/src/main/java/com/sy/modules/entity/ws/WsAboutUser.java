package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 公司简介与用户关联类
 * @author L C
 *
 */
public class WsAboutUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long aboutId;
	public WsAboutUser(Long userid,String createName){
		this.userId = userid;
		this.createName = createName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAboutId() {
		return aboutId;
	}
	public void setAboutId(Long aboutId) {
		this.aboutId = aboutId;
	}
	
}
