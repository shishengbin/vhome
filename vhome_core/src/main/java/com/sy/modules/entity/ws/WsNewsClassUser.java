package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;
/**
 * 新闻分类与用户关系
 * @author LiuCheng 2013 09 07
 * @version v1.0
 */
public class WsNewsClassUser extends ParentEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long newsCatId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewsCatId() {
		return newsCatId;
	}
	public void setNewsCatId(Long newsCatId) {
		this.newsCatId = newsCatId;
	}
	
}
