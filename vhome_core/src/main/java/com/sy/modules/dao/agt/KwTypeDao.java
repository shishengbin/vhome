package com.sy.modules.dao.agt;


import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.KwType;
@Component
@MyBatisRepository
public interface KwTypeDao extends ParentDao<KwType, Long> {
	
	//根据类型id查询对应的价格
	public KwType findKwTypeById(Integer tid);

}
