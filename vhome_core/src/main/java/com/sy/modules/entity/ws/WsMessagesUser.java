package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 留言与企业关联
 * LiuCheng
 */
public class WsMessagesUser extends ParentEntity{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long messageId;
	private Long humanId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getHumanId() {
		return humanId;
	}
	public void setHumanId(Long humanId) {
		this.humanId = humanId;
	}
}
