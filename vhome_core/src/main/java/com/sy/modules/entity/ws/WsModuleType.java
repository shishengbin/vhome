package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;
/**
 * 
 * @author LiuCheng
 *
 */
public class WsModuleType extends ParentEntity {
	private static final long serialVersionUID = 1L;
	// 类型名称
	private String mtName;

	public WsModuleType() {
	}

	public WsModuleType(Long typeid) {
		id = typeid;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

}
