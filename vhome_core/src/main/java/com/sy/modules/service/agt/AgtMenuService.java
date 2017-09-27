package com.sy.modules.service.agt;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AgtMenuDao;
import com.sy.modules.entity.agt.AgtMenu;

@Component
public class AgtMenuService extends AbstractService<AgtMenu, Long, AgtMenuDao> {

	@Autowired
	private AgtMenuDao agtmenudao;
	
	@Override
	protected AgtMenuDao getDao() {
		return agtmenudao;
	}
	
	public List<AgtMenu> findMenusByRoleId(Long roleId)
	{
		return agtmenudao.findMenusByRoleId(roleId);
	}
}
