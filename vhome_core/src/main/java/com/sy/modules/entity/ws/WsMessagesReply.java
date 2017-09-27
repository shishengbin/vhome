package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;
/**
 * 留言与回复信息关联类
 * @author LiuChneg
 *
 */
public class WsMessagesReply extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long messageId;
	private Long replyId;
	public WsMessagesReply(){}
	public WsMessagesReply(Long mid,Long rid){
		this.messageId = mid;
		this.replyId = rid;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	
}
