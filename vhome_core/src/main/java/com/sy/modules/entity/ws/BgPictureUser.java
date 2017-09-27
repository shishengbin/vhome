package com.sy.modules.entity.ws;
import com.sy.commons.entity.ParentEntity;

public class BgPictureUser extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long bgId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBgId() {
		return bgId;
	}
	public void setBgId(Long bgId) {
		this.bgId = bgId;
	}

}
