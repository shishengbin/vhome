package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 公司简介
 */
public class WsAbout extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private String about; //公司简介
	private String company_name;
	private String company_address;
	private String company_person;
	private String company_email;
	private String company_fax;
	private String company_phone;
	private String company_site;
	private String company_trade;
	private String aboutRemark;//备注信息
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getAboutRemark() {
		return aboutRemark;
	}
	public void setAboutRemark(String aboutRemark) {
		this.aboutRemark = aboutRemark;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_person() {
		return company_person;
	}
	public void setCompany_person(String company_person) {
		this.company_person = company_person;
	}
	public String getCompany_email() {
		return company_email;
	}
	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}
	public String getCompany_fax() {
		return company_fax;
	}
	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}
	public String getCompany_phone() {
		return company_phone;
	}
	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}
	public String getCompany_site() {
		return company_site;
	}
	public void setCompany_site(String company_site) {
		this.company_site = company_site;
	}
	public String getCompany_trade() {
		return company_trade;
	}
	public void setCompany_trade(String company_trade) {
		this.company_trade = company_trade;
	}
	
}
