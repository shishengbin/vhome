package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


/**
 * 产品and图片关联类
 * 
 * @author LiuCheng 2013-8-27
 * 
 */
public class WsProductPicture extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long pictureId; // 图片id
	private Long productId; // 产品id

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
