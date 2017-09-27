package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 * 客户信息实体类
 * @author zw
 * 2013-10-28
 * */
public class AgtCompany extends ParentEntity{
	 private static final long serialVersionUID = 1L;
	 private long companyId;              //客户ID
     private String companyName;   //公司名
     private String companyPerson;  //联系人
     private String companyPhone; //联系电话
     private String companyBusiness; //主营业务
     private String companyEmail; //邮箱
     private String companyDescibe; //备注     
     private int  companyState;    
     
	public int getCompanyState() {
		return companyState;
	}
	public void setCompanyState(int companyState) {
		this.companyState = companyState;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPerson() {
		return companyPerson;
	}
	public void setCompanyPerson(String companyPerson) {
		this.companyPerson = companyPerson;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyBusiness() {
		return companyBusiness;
	}
	public void setCompanyBusiness(String companyBusiness) {
		this.companyBusiness = companyBusiness;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}	
	public String getCompanyDescibe() {
		return companyDescibe;
	}
	public void setCompanyDescibe(String companyDescibe) {
		this.companyDescibe = companyDescibe;
	}
	@Override
	public String toString() {
		return "AgtCompany [companyId=" + companyId + ", companyName="
				+ companyName + ", companyPerson=" + companyPerson
				+ ", companyPhone=" + companyPhone + ", companyBusiness="
				+ companyBusiness + ", companyEmail=" + companyEmail
				+ ", companyDescibe=" + companyDescibe + ", companyState="
				+ companyState + "]";
	}

}
