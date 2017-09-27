package com.sy.modules.entity.wx;

import com.sy.commons.entity.ParentEntity;

public class Account extends ParentEntity {

	private static final long serialVersionUID = 2842403088393695308L;
	//private String u_name;
	//private String u_passwd;
	private String ac_name; //公众号名称
	private String ac_address; //地址
	private String ac_pic;  //icon路径
	private String ac_original_id; //原始id
	private String ac_wx_number;   //微信号 
	private String ac_interface;   //微信接口路径
	private String ac_token;       //随机token
	private String ac_email;       //公众号邮箱
	private String appId;     //开发者id
	private String appSecret; //默认生成的
	private String access_token; //
	private Integer confirm;//0:没认证 1：已认证
	// 所属城市
	private String ac_city;
	private Integer ac_type;

	public Integer getConfirm() {
		return confirm;
	}
	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}
	public String getAc_name() {
		return ac_name;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	public String getAc_original_id() {
		return ac_original_id;
	}
	public void setAc_original_id(String ac_original_id) {
		this.ac_original_id = ac_original_id;
	}
	public String getAc_wx_number() {
		return ac_wx_number;
	}
	public void setAc_wx_number(String ac_wx_number) {
		this.ac_wx_number = ac_wx_number;
	}
	public String getAc_interface() {
		return ac_interface;
	}
	public void setAc_interface(String ac_interface) {
		this.ac_interface = ac_interface;
	}
	public String getAc_token() {
		return ac_token;
	}
	public void setAc_token(String ac_token) {
		this.ac_token = ac_token;
	}
	public String getAc_address() {
		return ac_address;
	}
	public void setAc_address(String ac_address) {
		this.ac_address = ac_address;
	}
	public String getAc_email() {
		return ac_email;
	}
	public void setAc_email(String ac_email) {
		this.ac_email = ac_email;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAc_pic() {
		return ac_pic;
	}
	public void setAc_pic(String ac_pic) {
		this.ac_pic = ac_pic;
	}
	public Integer getAc_type() {
		return ac_type;
	}
	public void setAc_type(Integer ac_type) {
		this.ac_type = ac_type;
	}

	public String getAc_city() {
		return ac_city;
	}
	public void setAc_city(String ac_city) {
		this.ac_city = ac_city;
	}
	
}
