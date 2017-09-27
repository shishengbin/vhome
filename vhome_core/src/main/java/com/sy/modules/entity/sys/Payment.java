package com.sy.modules.entity.sys;

import com.sy.commons.entity.ParentEntity;
/**
 * 支付方式
 * @author sss
 */
public class Payment extends ParentEntity {
	
	private static final long serialVersionUID = -211635850072166498L;
	//支付接口名称
	private String daoname; 
	//支付方式名称
	private String modename;
	//启动状态
	private int status;
	
	public Payment(){}
	public Payment(String daoname,String modename,int status){
		this.daoname=daoname;
		this.modename=modename;
		this.status=status;
	}
	
	public String getDaoname() {
		return daoname;
	}
	public void setDaoname(String daoname) {
		this.daoname = daoname;
	}
	public String getModename() {
		return modename;
	}
	public void setModename(String modename) {
		this.modename = modename;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
