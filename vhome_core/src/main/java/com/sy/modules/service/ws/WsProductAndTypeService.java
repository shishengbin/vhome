package com.sy.modules.service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsProductAndTypeDao;
import com.sy.modules.entity.ws.WsProductAndType;
@Component
public class WsProductAndTypeService extends AbstractService<WsProductAndType, Long, WsProductAndTypeDao> {

	@Autowired
	private WsProductAndTypeDao wsproandtydao;
	
	@Override
	protected WsProductAndTypeDao getDao() {
		return wsproandtydao;
	}

}
