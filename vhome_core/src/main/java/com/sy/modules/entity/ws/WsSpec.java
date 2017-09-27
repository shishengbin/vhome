package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


/**
 * 产品规格
 * @author sssS
 */
public class WsSpec extends ParentEntity {

	private static final long serialVersionUID = -5422757572066808033L;
	//规格名称
	private String specname;
	//备注信息
	private String remark;
	public WsSpec(){}
	public WsSpec(String specname){
		this.specname=specname;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
