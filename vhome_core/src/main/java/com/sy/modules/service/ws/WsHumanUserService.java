package com.sy.modules.service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsHumanUserDao;
import com.sy.modules.entity.ws.WsHumanUser;
@Component
public class WsHumanUserService extends AbstractService<WsHumanUser, Long, WsHumanUserDao> {
	@Autowired
	private WsHumanUserDao wsHumanUserDao;
	@Override
	protected WsHumanUserDao getDao() {
		return this.wsHumanUserDao;
	}
	
	public WsHumanUser findByHumanId(long humanId){
		return wsHumanUserDao.findByHumanId(humanId);
	}
	
}
