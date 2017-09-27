package com.sy.modules.dao.ws;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsHumanUser;

@Component
@MyBatisRepository
public interface WsHumanUserDao extends ParentDao<WsHumanUser, Long> {

	WsHumanUser findByHumanId(long humanId);
		
}
