package com.sy.commons.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *通用类
 *@author sss 2013-8-9 
 */
public abstract class ParentEntity implements Serializable {
	
	private static final long serialVersionUID = 6773158647698223204L;
	protected  Long id;
	protected  String createName="";
	protected  Date createTime = new Date();
	protected  String updateName;
	protected  Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
