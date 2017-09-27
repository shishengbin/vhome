package com.sy.modules.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.commons.exception.UserAbsence;
import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.sys.UserDao;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.sys.SysUserCompany;

@Component
public class UserService extends AbstractService<SysUser, Long, UserDao> {

	@Autowired
	private UserDao userDao;

	@Override
	protected UserDao getDao() {
		return userDao;
	}

	public List<SysUser> findAllUserByPage(Map<String, Object> map) {
		return userDao.findAllUserByPage(map);
	}

	public Long findCountByParam(Map<String, Object> map) {
		return userDao.findCountByParam(map);
	}
	
	public SysUser finUserByName(String username) {
		return userDao.findUserByName(username);
	}
	
	public SysUser queryById(Long id) {
		return userDao.queryById(id);
	}

	// login
	public SysUser login(String username, String userpass) {
		// 根据登录账号查询用户信息
		SysUser user = finUserByName(username);
		if (user != null) {
			// 校验密码
			if (!userpass.equals(user.getUserpass())) {
				throw new UserAbsence("密码错误");
			} else if (user.getUserstuts() != 1) {
				throw new UserAbsence("帐号未激活");
			}
		} else {
			throw new UserAbsence("用户名不存在");
		}
		return user;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveSysUser(SysUser user,SysUserCompany uc){
		boolean success = false;
		userDao.create(user);
		uc.setUserId(user.getId());
		userDao.createUserCom(uc);
		success = true;
		return success;
	}
	/**
	 * 
	* @Title: createRole 
	* @Description:(建站账号与角色关联) 
	* @return void    返回类型 
	* @author LiuCheng
	* @throws
	 */
	public void createRole(Map<String,Object> m){
		userDao.createRole(m);
	}
	/*public List<Accounting> queryAgent(){
		return userDao.queryAgent();
	}*/
	
	public void updateStyle(Map<String,Object> map){
		userDao.updateStyle(map);
	}
	
	public void updateUserAlipay(SysUser user) {
		userDao.updateUserAlipay(user);
	}

}
