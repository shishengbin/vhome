package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;
/**
 * 账号与客户信息
 * @describe  
 * @author LiuCheng
 * 2013年10月16日 下午3:34:11
 * @version v1.0
 */
public class UserCompany extends ParentEntity {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long companyId;
	
	public UserCompany(){}
	public UserCompany(Long userId,Long companyId){
		
		this.userId = userId;
		this.companyId = companyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
