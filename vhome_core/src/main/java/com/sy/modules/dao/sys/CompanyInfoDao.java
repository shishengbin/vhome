package com.sy.modules.dao.sys;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.sys.CompanyInfo;
import com.sy.modules.entity.sys.SysUserCompany;



/**
 *会员企业接口
 *@author sss 
 *@date 2013-8-9 
 */
@Component
@MyBatisRepository
public interface CompanyInfoDao extends ParentDao<CompanyInfo, Long> {
	
	//查询当前帐号下的企业信息
	public CompanyInfo findCompanyInfo(Long userId);
	
	//查询当前帐号对应的基本信息以及版本
	
	public CompanyInfo findAccoutAndType(Long userId);
	
	//查看当前代理商下所有客户
	public List<CompanyInfo> findClientByAgentId(Long id);
	
	public List<CompanyInfo> findComByAgent(Long id);
	
//	建站账号与客户信息关系
	public void createUserCompany(SysUserCompany uc);
	
	public List<CompanyInfo> queryAgent();
	
	public List<CompanyInfo> getAllAgent();
}
