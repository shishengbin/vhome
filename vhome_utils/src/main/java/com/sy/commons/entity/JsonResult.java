package com.sy.commons.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code; // 提示编号
	private String message; // 提示信息
	private String data; // 数据

	public JsonResult() {
	}

	public JsonResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public JsonResult(String code, String message, String data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toJsonString() {
		if (StringUtils.isBlank(this.data))
			return "{\"code\":\"" + this.code + "\",\"message\":\""
					+ this.message + "\",\"data\":\"\"}";
		return "{\"code\":\"" + this.code + "\",\"message\":\"" + this.message
				+ "\",\"data\":" + this.data + "}";
	}

}
