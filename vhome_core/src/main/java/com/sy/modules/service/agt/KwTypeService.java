package com.sy.modules.service.agt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.KwTypeDao;
import com.sy.modules.entity.agt.KwType;

@Component
public class KwTypeService extends AbstractService<KwType, Long, KwTypeDao> {
	@Autowired
	private KwTypeDao kwTypeDao;

	@Override
	protected KwTypeDao getDao() {
		return kwTypeDao;
	} 
	
	//根据类型id查询对应的价格
	public KwType findKwTypeById(Integer tid)
	{
		return kwTypeDao.findKwTypeById(tid);
	}

}
