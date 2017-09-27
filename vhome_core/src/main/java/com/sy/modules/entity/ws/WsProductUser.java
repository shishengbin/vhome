package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 用户and产品关联类
 * @author LiuCheng 2013-8-27
 *
 */
public class WsProductUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId; //用户id
	private Long productId;//产品id
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
