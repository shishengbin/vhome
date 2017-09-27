package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 用户and产品分类关联类
 * @author LiuCheng 2013-8-27
 *
 */
public class WsProductClassUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long oneId;
	private Long userId;
	public WsProductClassUser(Long userid){
		this.userId = userid;
	}
	public WsProductClassUser(){}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOneId() {
		return oneId;
	}
	public void setOneId(Long oneId) {
		this.oneId = oneId;
	}
	
	
}
