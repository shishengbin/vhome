package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;
/**
 *@author sss 2013-10-17
 *关键词类型 
 */
public class KwType extends ParentEntity{

	private static final long serialVersionUID = 1L;
	private String tname ;   //类型名称
	private Integer tprice ; //类型价格
	private String tremark ;  //备注
	

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getTprice() {
		return tprice;
	}

	public void setTprice(Integer tprice) {
		this.tprice = tprice;
	}

	public String getTremark() {
		return tremark;
	}

	public void setTremark(String tremark) {
		this.tremark = tremark;
	}

}
