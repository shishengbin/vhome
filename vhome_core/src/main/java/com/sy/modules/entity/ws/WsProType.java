package com.sy.modules.entity.ws;

import java.util.ArrayList;
import java.util.List;

import com.sy.commons.entity.ParentEntity;

public class WsProType extends ParentEntity {

	private static final long serialVersionUID = 1050295492599662525L;
	
	private String tname;//类型名称
	private String picurl;//图片路径
	private Long parentId;//父类id
	private WsProType wprotype;//父类分类
	private List<WsProType> sontypes=new ArrayList<WsProType>();//子类集合
	private Long userId;
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public WsProType getWprotype() {
		return wprotype;
	}
	public void setWprotype(WsProType wprotype) {
		this.wprotype = wprotype;
	}
	public List<WsProType> getSontypes() {
		return sontypes;
	}
	public void setSontypes(List<WsProType> sontypes) {
		this.sontypes = sontypes;
	}

	

}
