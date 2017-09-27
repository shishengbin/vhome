package com.sy.modules.dao.agt;



import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtMenu;

/**
 *系统菜单
 *@author sss 
 */
@Component
@MyBatisRepository
public interface AgtMenuDao extends ParentDao<AgtMenu,Long> {
	//find menus bu roleId
	public List<AgtMenu> findMenusByRoleId(Long roleId);


}
