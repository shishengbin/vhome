package com.sy.modules.dao.agt;


import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtImpower;

@Component
@MyBatisRepository
public interface AgtImpowerDao extends ParentDao<AgtImpower, Long> {
	
	//根据名称查询信息
	public AgtImpower findAgtImpowerByName(String imname);
	


}
