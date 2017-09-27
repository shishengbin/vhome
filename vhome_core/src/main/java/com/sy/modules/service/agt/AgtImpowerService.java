package com.sy.modules.service.agt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AgtImpowerDao;
import com.sy.modules.entity.agt.AgtImpower;

@Component
public class AgtImpowerService extends AbstractService<AgtImpower, Long,AgtImpowerDao> {
	
	@Autowired
	private AgtImpowerDao agtImpowerDao;
	
	@Override
	protected AgtImpowerDao getDao() {
		return agtImpowerDao;
	}
	
	public AgtImpower findAgtImpowerByName(String imname)
	{
		return agtImpowerDao.findAgtImpowerByName(imname);
	}


}
