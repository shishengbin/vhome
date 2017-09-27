package com.sy.modules.entity.agt;

import java.util.Date;
import java.util.List;

import com.sy.commons.entity.ParentEntity;
import com.sy.modules.entity.sys.SysUser;
/**
 * 订单信息
 * @describe  
 * @author LiuCheng
 * 2013年10月16日 下午2:14:31
 * @version v1.0
 */
public class Indent extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private SysUser user; // 建站账号;
	private String keyword; //关键词
	private IndentType type;// 订单类型;
	private IndentStates states = IndentStates.审核中;// 订单状态；
	private int term;// 购买年限;
	private double monetary;// 购买金额;
	private int presentterm;// 赠送年限；
	private int sumterm;// 总服务年限；
	private int formalterm;// 合同签订年限；
	private String formalid;// 合同编号;
	private int agency;//是否代制作
	private List<Market> market; //发布到那些商城
	private String remark;//描述
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private double balance;//账号余额
	private String coding;//注册编码
	private double costPrice;//原价
	
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	private String firmName;
	
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Indent(){}
	public Indent(Long id,IndentStates status,Date date){
		this.id = id;
		this.states = status;
		this.beginDate = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getAgency() {
		return agency;
	}

	public void setAgency(int agency) {
		this.agency = agency;
	}

	public List<Market> getMarket() {
		return market;
	}

	public void setMarket(List<Market> market) {
		this.market = market;
	}

	

	public int getType() {
		if(type!=null)
		return type.getIt();
		return 0;
	}

	public void setType(int indent) {
		this.type = IndentType.getType(indent);
	}

	public int getStates() {
		return states.getStates();
	}

	public void setStates(int is) {
		this.states = IndentStates.getStringStates(is);
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getMonetary() {
		return monetary;
	}

	public void setMonetary(double monetary) {
		this.monetary = monetary;
	}

	public int getPresentterm() {
		return presentterm;
	}

	public void setPresentterm(int presentterm) {
		this.presentterm = presentterm;
	}

	public int getSumterm() {
		return sumterm;
	}

	public void setSumterm(int sumterm) {
		this.sumterm = sumterm;
	}

	public int getFormalterm() {
		return formalterm;
	}

	public void setFormalterm(int formalterm) {
		this.formalterm = formalterm;
	}

	public String getFormalid() {
		return formalid;
	}
	public void setFormalid(String formalid) {
		this.formalid = formalid;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
