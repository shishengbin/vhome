package com.sy.modules.service.agt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.MarketDao;
import com.sy.modules.entity.agt.Market;
/**
 * 代理商账户管理
 * @describe  
 * @author LiuCheng
 * 2013年10月21日 上午11:11:00
 * @version v1.0
 */
@Component
public class MarketService extends AbstractService<Market,Long,MarketDao> {

	@Autowired
	private MarketDao marketdao;
	
	@Override
	protected MarketDao getDao() {
		return this.marketdao;
	}
}

