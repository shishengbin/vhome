package com.sy.modules.service.agt;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.RoleDao;
import com.sy.modules.entity.agt.Role;

@Component
public class RoleService extends AbstractService<Role, Long, RoleDao> {

	@Autowired
	private RoleDao roledao;
	@Override
	protected RoleDao getDao() {
		return roledao;
	}
	
	public List<Role> findRolesByPage(Map<String,Object> map)
	{
		return roledao.findRolesByPage(map);
	}
	
	public Long findCountByParam(Map<String,Object> map)
	{
		return roledao.findCountByParam(map);
	}

}
