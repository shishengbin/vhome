package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 短信
 * @author sss
 * 
 */
public class WsSms extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String sms_content;
	private String sms_title;
	private int sms_use;

	public String getSms_content() {
		return sms_content;
	}

	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}

	public String getSms_title() {
		return sms_title;
	}

	public void setSms_title(String sms_title) {
		this.sms_title = sms_title;
	}

	public int getSms_use() {
		return sms_use;
	}

	public void setSms_use(int sms_use) {
		this.sms_use = sms_use;
	}
}
