package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


public class WsSupplier extends ParentEntity {

	private static final long serialVersionUID = 4342131035349533466L;
	
	private String suppcompany;//供应商公司名称
	private String suppname;//负责人名称
	private String supptel;//联系电话
	private String remark;//备注
	
	public WsSupplier(){}
	public WsSupplier(String suppcompany,String suppname,String supptel){
		this.suppcompany=suppcompany;
		this.suppname=suppname;
		this.supptel=supptel;
	}
	public String getSuppcompany() {
		return suppcompany;
	}
	public void setSuppcompany(String suppcompany) {
		this.suppcompany = suppcompany;
	}
	public String getSuppname() {
		return suppname;
	}
	public void setSuppname(String suppname) {
		this.suppname = suppname;
	}
	public String getSupptel() {
		return supptel;
	}
	public void setSupptel(String supptel) {
		this.supptel = supptel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
