package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsHuman;
import com.sy.modules.entity.ws.WsHumanUser;

@Component
@MyBatisRepository
public interface WsHumanDao extends ParentDao<WsHuman, Long> {

	// 分页查询会员
	List<WsHuman> findAllHumanByPage(Map<String, Object> map);
	//根据查询条件返回总数
	long findCountByParam(Map<String,Object> map);
	
	// 注册会员与用户关联
	void addHumanUser(WsHumanUser whu);
	//根据会员账号查询会员信息
	List<WsHuman> findByAccount(String account);
	//会员登录
	WsHuman login(Long id,String username,String password);

	WsHuman findOneByid(Long humanId);
	
	void wxcreate(WsHuman wsHuman);
}
