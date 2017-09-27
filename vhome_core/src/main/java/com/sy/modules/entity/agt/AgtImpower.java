package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 *授权单位 
 */
public class AgtImpower extends ParentEntity {

	private static final long serialVersionUID = 9060128302181875598L;
	
	private String imname;//单位名称
	private String imperson;//联系人
	private String imphone;
	private String imfax;
	private String address;

	public String getImname() {
		return imname;
	}
	public void setImname(String imname) {
		this.imname = imname;
	}
	public String getImperson() {
		return imperson;
	}
	public void setImperson(String imperson) {
		this.imperson = imperson;
	}
	public String getImphone() {
		return imphone;
	}
	public void setImphone(String imphone) {
		this.imphone = imphone;
	}
	public String getImfax() {
		return imfax;
	}
	public void setImfax(String imfax) {
		this.imfax = imfax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
