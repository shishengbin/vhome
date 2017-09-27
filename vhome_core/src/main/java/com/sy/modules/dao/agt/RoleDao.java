package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.Role;

/**
 *角色 
 */
@Component
@MyBatisRepository
public interface RoleDao extends ParentDao<Role,Long> {
	
	//find all roles by page
	public List<Role> findRolesByPage(Map<String,Object> map);
	
	//find total number with roles by param
	public Long findCountByParam(Map<String,Object> map);
	

}
