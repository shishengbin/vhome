package com.sy.modules.dao.sys;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.sys.SysUserCompany;

/**
 * 用户接口
 * 
 * @author sss
 * @date 2013-8-9
 */
@Component
@MyBatisRepository
public interface UserDao extends ParentDao<SysUser, Long> {
	
	public SysUser queryById(Long id);
	
	// find all users by page
	public List<SysUser> findAllUserByPage(Map<String, Object> map);

	// find user by username
	public SysUser findUserByName(String username);
	
	// find count by param
	public Long findCountByParam(Map<String, Object> map);
	
	// find total number with self modules by user id

	public Long findCountWithSelfModule(Map<String, Object> map);

	//public List<Accounting> queryAgent();

	public void createUserCom(SysUserCompany uc);

	public void createRole(Map<String, Object> map);

	public void updateStyle(Map<String, Object> map);

	public void updateUserAlipay(SysUser user);


}
