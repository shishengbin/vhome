package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 * 消费明细
 * 
 * @describe
 * @author LiuCheng 2013年10月16日 下午2:15:40
 * @version v1.0
 */
public class Accounting extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private AgtUser agt;//那个代理商下的
	private String firmName;//公司名称
	private String antistop;// 关键字
	private double balance;// 账户余额
	private double rebate;//返款金额
	private String remark;
	private double sum;// 消费金额
	private int term; // 年限
	private AccountingType type;// 账务类型
	
	
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public AgtUser getAgt() {
		return agt;
	}
	public void setAgt(AgtUser agt) {
		this.agt = agt;
	}
	public Accounting(){}
	public Accounting(AccountingType type,double sum,double balance,String antistop,int term,String remark,Long agtid,String createName){
		this.type = type;this.sum = sum;this.balance = balance;this.antistop = antistop;this.term = term;this.remark = remark;
		this.agt = new AgtUser(agtid);
		this.createName = createName;
	}
	
	public String getAntistop() {
		return antistop;
	}
	public double getBalance() {
		return balance;
	}
	public double getRebate() {
		return rebate;
	}
	public String getRemark() {
		return remark;
	}

	public double getSum() {
		return sum;
	}

	public int getTerm() {
		return term;
	}

	public int getType() {
		return type.getType();
	}

	public void setAntistop(String antistop) {
		this.antistop = antistop;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public void setType(int type) {
		this.type = AccountingType.getStringType(type);
	}

}
