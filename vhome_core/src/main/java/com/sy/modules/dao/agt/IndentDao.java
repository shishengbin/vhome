package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.Accounting;
import com.sy.modules.entity.agt.Indent;
import com.sy.modules.entity.agt.Market;
import com.sy.modules.entity.agt.RelIndentMarket;
/**
 * @describe 订单  
 * @author LiuCheng
 * 2013年10月16日 下午2:26:47
 * @version v1.0
 */
@Component
@MyBatisRepository
public interface IndentDao extends ParentDao<Indent,Long> {
//	查看某代理商下所有信息
	public List<Accounting> getAll(Long id);
//	第三方市场列表
	public List<Market> getMarket();
//	创建订单与三方市场关联
	public void createRelMarket(List<RelIndentMarket> list);
//	分页查询
	public List<Indent> findIndentByPage(Map<String,Object> map);
//	总记录数
	public Integer count(Map<String,Object> map);
	
}
