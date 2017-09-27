package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 商家管理
 * 
 * @author LHL
 */
public class WsHuman extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String human_account; // 账号
	private String human_password; // 密码
	private String human_question; // 饭店名称
	private String human_answer;
	private String human_phone; // 手机号
	private String human_qq;
	private String human_name; // 联系人
	private String human_address; // 饭店地址
	private WsHumanUser wsHumanUser;

	public String getHuman_account() {
		return human_account;
	}

	public void setHuman_account(String human_account) {
		this.human_account = human_account;
	}

	public String getHuman_password() {
		return human_password;
	}

	public void setHuman_password(String human_password) {
		this.human_password = human_password;
	}

	public String getHuman_question() {
		return human_question;
	}

	public void setHuman_question(String human_question) {
		this.human_question = human_question;
	}

	public String getHuman_answer() {
		return human_answer;
	}

	public void setHuman_answer(String human_answer) {
		this.human_answer = human_answer;
	}

	public String getHuman_phone() {
		return human_phone;
	}

	public void setHuman_phone(String human_phone) {
		this.human_phone = human_phone;
	}

	public String getHuman_qq() {
		return human_qq;
	}

	public void setHuman_qq(String human_qq) {
		this.human_qq = human_qq;
	}

	public String getHuman_name() {
		return human_name;
	}

	public void setHuman_name(String human_name) {
		this.human_name = human_name;
	}

	public String getHuman_address() {
		return human_address;
	}

	public void setHuman_address(String human_address) {
		this.human_address = human_address;
	}

	public WsHumanUser getWsHumanUser() {
		return wsHumanUser;
	}

	public void setWsHumanUser(WsHumanUser wsHumanUser) {
		this.wsHumanUser = wsHumanUser;
	}
}
