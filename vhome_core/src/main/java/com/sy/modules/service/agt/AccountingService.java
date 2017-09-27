package com.sy.modules.service.agt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AccountingDao;
import com.sy.modules.entity.agt.Accounting;

/**
 * 账务管理
 * @describe  
 * @author LiuCheng
 * 2013年10月21日 上午11:29:10
 * @version v1.0
 */
@Component
public class AccountingService extends AbstractService<Accounting,Long,AccountingDao> {
	@Autowired
	private AccountingDao accountingdao;
	/**
	 * 所有信息
	 * @describe  
	 * @param id
	 * @return List<Accounting>
	 * @author LiuCheng
	 * 2013年10月22日 下午3:16:37
	 */
	public List<Accounting> getAll(Long id){
		return accountingdao.getAll(id);
	}
	/**
	 * 分页查询
	 * @describe  
	 * @param map
	 * @return List<Accounting>
	 * @author LiuCheng
	 * 2013年10月22日 下午3:16:46
	 */
	public List<Accounting> findAccountingBypage(Map<String,Object> map){
		return accountingdao.findAccountingBypage(map);
	}
	/**
	 * 总数
	 * @describe  
	 * @param map
	 * @return Integer
	 * @author LiuCheng
	 * 2013年10月22日 下午3:16:54
	 */
	public Integer count(Map<String,Object> map){
		return accountingdao.count(map);
	}
	
	public Accounting queryNewestOne(Long id){
		return accountingdao.queryNewestOne(id);
	}
	@Override
	protected AccountingDao getDao() {
		return accountingdao;
	}
}
