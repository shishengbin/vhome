package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 留言回复
 * @author LiuCheng
 *
 */
public class WsReply extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String replyContent;
	public WsReply(){}
	public WsReply(String replyContent){
		this.replyContent=replyContent;
	}
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
}
