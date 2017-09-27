package com.sy.modules.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.sys.CompanyInfoDao;
import com.sy.modules.entity.sys.CompanyInfo;
import com.sy.modules.entity.sys.SysUserCompany;

@Component
public class CompanyInfoService extends AbstractService<CompanyInfo, Long, CompanyInfoDao> {

	@Autowired
	private CompanyInfoDao companyinfodao;

	@Override
	protected CompanyInfoDao getDao() {
		return companyinfodao;
	}

	// 查询当前帐号下的企业信息
	public CompanyInfo findCompanyInfo(Long userId) {
		return companyinfodao.findCompanyInfo(userId);
	}
	public CompanyInfo findAccoutAndType(Long userId){
		return companyinfodao.findAccoutAndType(userId);
	}
	
//	代理商下客户列表
	public List<CompanyInfo> findClientByAgentId(Long id) {
		return companyinfodao.findClientByAgentId(id);
	}
	
	public List<CompanyInfo> findComByAgent(Long id){
		return companyinfodao.findComByAgent(id);
	}
//	建站账号与客户信息关系
	public void createUserCompany(SysUserCompany uc){
		companyinfodao.createUserCompany(uc);
	}
	
	public List<CompanyInfo> queryAgent(){
		return companyinfodao.queryAgent();
	}
	
	public List<CompanyInfo> getAllAgent(){
		return companyinfodao.getAllAgent();
	}

}
