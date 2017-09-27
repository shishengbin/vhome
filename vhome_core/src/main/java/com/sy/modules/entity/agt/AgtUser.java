package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;
import com.sy.modules.entity.sys.CompanyInfo;

/**
 * 代理商系统帐号
 * @author sss 2013-10-29
 */
public class AgtUser extends ParentEntity {

	private static final long serialVersionUID = 6507016619198570674L;
	
	private String u_name ; //帐号
	private String u_pass ; //密码S
	private Integer u_status ; //状态 0:失效，1:激活
	private Long fatherId; //关联对应代理商关系
	private Long roleId; //关联对应角色ID
	private String u_remark ; //备注
	private double money_box;//钱包
	private float discount;
	private Role role;
	
	private Integer indentCount;//订单总数
	private Integer clientCount;//客户总数
	private CompanyInfo coms;
	public AgtUser(){
		
	}	
	public AgtUser(Long id){
		this.id = id;
	}
	public Integer getClientCount() {
		return clientCount;
	}
	public CompanyInfo getComs() {
		return coms;
	}
	public Long getFatherId() {
		return fatherId;
	}
	public Integer getIndentCount() {
		return indentCount;
	}
	public double getMoney_box() {
		return money_box;
	}
	public Role getRole() {
		return role;
	}
	public Long getRoleId() {
		return roleId;
	}
	public String getU_name() {
		return u_name;
	}
	
	

	public String getU_pass() {
		return u_pass;
	}
	public String getU_remark() {
		return u_remark;
	}
	public Integer getU_status() {
		return u_status;
	}
	public void setClientCount(Integer clientCount) {
		this.clientCount = clientCount;
	}
	public void setComs(CompanyInfo coms) {
		this.coms = coms;
	}
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	public void setIndentCount(Integer indentCount) {
		this.indentCount = indentCount;
	}
	public void setMoney_box(double money_box) {
		this.money_box = money_box;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}
	public void setU_remark(String u_remark) {
		this.u_remark = u_remark;
	}
	public void setU_status(Integer u_status) {
		this.u_status = u_status;
	}
	
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "AgtUser [u_name=" + u_name + ", u_pass=" + u_pass
				+ ", u_status=" + u_status + ", fatherId=" + fatherId
				+ ", roleId=" + roleId + ", u_remark=" + u_remark
				+ ", money_box=" + money_box + ", indentCount=" + indentCount
				+ ", clientCount=" + clientCount + ", coms=" + coms + "]";
	}
	
	
	

}
