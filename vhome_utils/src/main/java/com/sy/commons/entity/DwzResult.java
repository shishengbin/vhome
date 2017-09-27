package com.sy.commons.entity;

/**
 *通用json对象 
 *@author sss 2013-8-15 
 */
public class DwzResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String statusCode; //是否操作成功
	private String message;    //返回页面信息
	private String navTabId;   //窗口
	private String rel;        //
	private String callbackType;//操作后
	private String forwardUrl;  //跳转路径

	public DwzResult() {
	}
	public DwzResult(String statusCode, String message, String forwardUrl){
		this.statusCode = statusCode;
		this.message = message;
		this.forwardUrl = forwardUrl;
	}
	public DwzResult(String statusCode, String message, String navTabId, String rel,
			String callbackType, String forwardUrl) {
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String toJsonString() {
		return "{\"statusCode\":\"" + this.statusCode + "\",\"message\":\""
				+ this.message + "\",\"navTabId\":\"" + this.navTabId
				+ "\",\"rel\":\"" + this.rel + "\",\"callbackType\":\""
				+ this.callbackType + "\",\"forwardUrl\":\"" + this.forwardUrl+"\"}";
	}
	
	public String toJson() {
		return "{\"state\":\"" + this.statusCode + "\",\"message\":\""
				+ this.message + "\",\"forward\":\"" + this.forwardUrl+"\"}";
	}
}
