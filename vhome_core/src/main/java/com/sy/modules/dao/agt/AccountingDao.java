package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.Accounting;

/**
 * 账务记录
 * 
 * @describe
 * @author LiuCheng 2013年10月21日 上午11:27:30
 * @version v1.0
 */
@Component
@MyBatisRepository
public interface AccountingDao extends ParentDao<Accounting, Long> {
//	某代理商下所有账务信息
	public List<Accounting> getAll(Long id);
//	分页查询
	public List<Accounting> findAccountingBypage(Map<String,Object> map);
//	总数
	public Integer count(Map<String,Object> map);
	
	public Accounting queryNewestOne(Long id);
}
