package com.sy.modules.entity.sys;

import com.sy.commons.entity.ParentEntity;
import com.sy.modules.entity.agt.Keyword;

/**
 * 建站帐号
 * @author sss 2013-8-9
 */

public class SysUser extends ParentEntity {

	private static final long serialVersionUID = 7941086829855106914L;
	private String username;  // 帐号
	private String userpass;  // 密码
	private Integer userstuts;// 状态：1=有效，0=无效
	private String userremark;// 备注信息
	private String roleName;
	private Integer roleId;
	//支付宝参数
	private String alipayKey;
	private String alipayId;
	private String alipayAccount;
	private CompanyInfo company;
	private Keyword key;
	
	public SysUser(){}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public Integer getUserstuts() {
		return userstuts;
	}
	public void setUserstuts(Integer userstuts) {
		this.userstuts = userstuts;
	}
	public String getUserremark() {
		return userremark;
	}
	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getAlipayKey() {
		return alipayKey;
	}
	public void setAlipayKey(String alipayKey) {
		this.alipayKey = alipayKey;
	}
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public CompanyInfo getCompany() {
		return company;
	}
	public void setCompany(CompanyInfo company) {
		this.company = company;
	}
	public Keyword getKey() {
		return key;
	}

	public void setKey(Keyword key) {
		this.key = key;
	}

	

}
