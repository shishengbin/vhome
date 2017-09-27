package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 新闻and用户关系类
 * @author LiuCheng
 *
 */
public class WsNewsUser extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private long newsId;
	public WsNewsUser(){}
	public WsNewsUser(Long userid){
		this.userId = userid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}
	
}
