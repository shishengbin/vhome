package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtUser;


/**
 *代理商帐号管理
 *@author sss  2013-10-29
 */ 
@Component
@MyBatisRepository
public interface AgtUserDao extends ParentDao<AgtUser,Long>{

	//find all users by page
	public List<AgtUser> findAllUserByPage(Map<String,Object> map);
		
	//find user by username
	public AgtUser findUserByName(String username);
		
	//find count by param
	public Long findCountByParam(Map<String,Object> map);
	
	public AgtUser findAgentById(Long id);
	//登录
	public AgtUser agtLogin(String u_name);
	//修改密码
	public void updatePass(Map<String,Object> map);
	//修改余额
	public void updateBalance(Map<String,Object> map);
	//查看余额
	public double queryBalance(Long id);
    //查找关联表找到companyId
	public long findCompanyIdById(long aid);
	
	//根据关键词查找用户ID
	public long queryUserIdByKey(String keyword);
	
}
