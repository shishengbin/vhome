package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;

/**
 * 代理商账户表
 * @describe  
 * @author LiuCheng
 * 2013年10月16日 下午3:34:11
 * @version v1.0
 */
public class UserAgent extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userid;
	private Long agtid;
	public UserAgent(){}
	public UserAgent(Long userid,Long agtid){
		this.userid = userid;
		this.agtid = agtid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getAgtid() {
		return agtid;
	}
	public void setAgtid(Long agtid) {
		this.agtid = agtid;
	}
	
}
