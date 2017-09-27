package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 *菜单表
 *@author sss 
 */
public class AgtMenu extends ParentEntity {
	private static final long serialVersionUID = 2017434710927699191L;
	
	private String mname ; //菜单名称
	private String muri ;  //访问路径
	private Integer fatherid ;//上一层菜单id
	private String remark ;   //备注
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMuri() {
		return muri;
	}
	public void setMuri(String muri) {
		this.muri = muri;
	}
	public Integer getFatherid() {
		return fatherid;
	}
	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
