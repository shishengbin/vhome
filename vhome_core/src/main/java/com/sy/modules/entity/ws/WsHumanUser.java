package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 商家关联用户关系类
 * @author LHL
 */
public class WsHumanUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private long userId;
	private long humanId;

	public WsHumanUser() {
	}

	public long getHumanId() {
		return humanId;
	}

	public void setHumanId(long humanId) {
		this.humanId = humanId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
