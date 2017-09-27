package com.sy.modules.entity.ws;

import java.util.List;

import com.sy.commons.entity.ParentEntity;
/**
 * 留言
 * @author LiuCheng
 *
 */
public class WsMessages extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String messageTitle;
	private String messageContent;
	private String messageQQ;
	private String messageEmail;
	private String messagePhone;
	private List<WsReply> reply;
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageQQ() {
		return messageQQ;
	}
	public void setMessageQQ(String messageQQ) {
		this.messageQQ = messageQQ;
	}
	public String getMessageEmail() {
		return messageEmail;
	}
	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}
	public String getMessagePhone() {
		return messagePhone;
	}
	public void setMessagePhone(String messagePhone) {
		this.messagePhone = messagePhone;
	}
	public List<WsReply> getReply() {
		return reply;
	}
	public void setReply(List<WsReply> reply) {
		this.reply = reply;
	}
	
}
