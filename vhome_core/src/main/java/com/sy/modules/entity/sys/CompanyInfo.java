package com.sy.modules.entity.sys;
import com.sy.commons.entity.ParentEntity;

/**
 *公司详情 
 *@author sss 2013-8-30 
 */
public class CompanyInfo extends ParentEntity {

	private static final long serialVersionUID = -8295398864027632738L;
	private String companyname;//企业名称
	private String companytrade;//所属行业
	private String companyperson;//联系人
	private String companyphone;//联系电话
	private String companyfax;//传真
	private String companyaddress;//地址
	private String companyemail;//邮箱
	private String companysite;//主站
	private String companybusiness;//主要业务
	private String companydescribe;//企业描述
    private Integer del_status;//状态
    private String qqnumber;//qq号码
    private String edtype;//对应的版本
   // private List<ComPicture> complist=new ArrayList<ComPicture>();
    
    public CompanyInfo(){}
    
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanytrade() {
		return companytrade;
	}
	public void setCompanytrade(String companytrade) {
		this.companytrade = companytrade;
	}
	public String getCompanyperson() {
		return companyperson;
	}
	public void setCompanyperson(String companyperson) {
		this.companyperson = companyperson;
	}
	public String getCompanyphone() {
		return companyphone;
	}
	public void setCompanyphone(String companyphone) {
		this.companyphone = companyphone;
	}
	public String getCompanyfax() {
		return companyfax;
	}
	public void setCompanyfax(String companyfax) {
		this.companyfax = companyfax;
	}
	public String getCompanyaddress() {
		return companyaddress;
	}
	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}
	public String getCompanyemail() {
		return companyemail;
	}
	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}
	public String getCompanysite() {
		return companysite;
	}
	public void setCompanysite(String companysite) {
		this.companysite = companysite;
	}
	public String getCompanybusiness() {
		return companybusiness;
	}
	public void setCompanybusiness(String companybusiness) {
		this.companybusiness = companybusiness;
	}
	public String getCompanydescribe() {
		return companydescribe;
	}
	public void setCompanydescribe(String companydescribe) {
		this.companydescribe = companydescribe;
	}
	public Integer getDel_status() {
		return del_status;
	}
	public void setDel_status(Integer del_status) {
		this.del_status = del_status;
	}
	public String getQqnumber() {
		return qqnumber;
	}
	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}
	public String getEdtype() {
		return edtype;
	}
	public void setEdtype(String edtype) {
		this.edtype = edtype;
	}

}
