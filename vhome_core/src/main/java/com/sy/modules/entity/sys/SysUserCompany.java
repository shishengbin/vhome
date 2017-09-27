package com.sy.modules.entity.sys;
import com.sy.commons.entity.ParentEntity;

/**
 * 帐号关联详情
 * @author LiuCheng 2013/10/16 3:34:11
 */
public class SysUserCompany extends ParentEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long companyId;

	public SysUserCompany() {
	}

	public SysUserCompany(Long userId, Long companyId) {

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
