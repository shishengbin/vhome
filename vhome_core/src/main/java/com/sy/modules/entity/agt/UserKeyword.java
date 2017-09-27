package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;


/**
 * 账号与客户信息
 * @describe  
 * @author LiuCheng
 * 2013年10月16日 下午3:34:11
 * @version v1.0
 */
public class UserKeyword extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long keywordId;
	
	public UserKeyword(){}
	public UserKeyword(Long userid,Long keywordid){
		this.userId = userid;
		this.keywordId = keywordid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	
}
