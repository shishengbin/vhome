package com.sy.modules.dao.agt;


import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.Market;
/**
 * @describe 代理商
 * @author LiuCheng
 * 2013年10月16日 下午2:26:47
 * @version v1.0
 */
@Component
@MyBatisRepository
public interface MarketDao extends ParentDao<Market,Long> {

	
}
