package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


/**
 * 图片
 * @author sss
 */
public class WsMtPicture extends ParentEntity {

	private static final long serialVersionUID = -3765377255350747067L;
	
	private String pictureName;// 图片名称
	private String picture; // 图片路径

	public WsMtPicture() {
	}

	public WsMtPicture(String b, String name) {
		this.picture = b;
		this.pictureName = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
}
