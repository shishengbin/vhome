package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 号码
 * @author sss
 * 
 */
public class WsPhone extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String phone_Name;
	private String phone_Number;

	public String getPhone_Name() {
		return phone_Name;
	}

	public void setPhone_Name(String phone_Name) {
		this.phone_Name = phone_Name;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}
}
