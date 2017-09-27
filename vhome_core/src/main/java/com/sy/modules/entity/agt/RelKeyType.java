package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 * 关键词与类型关联
 * @describe  
 * @author LiuCheng
 * 2013年10月22日 上午10:47:23
 * @version v1.0
 */
public class RelKeyType extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long keyId;
	private Long typeId;
	
	public RelKeyType(){}
	public RelKeyType(Long id,Integer typeid){
		this.keyId = id;
		this.typeId = Long.valueOf(typeid);
	}
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
}
