package com.sy.modules.service.agt;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.KeywordDao;
import com.sy.modules.entity.agt.Keyword;
import com.sy.modules.entity.agt.RelKeyType;
import com.sy.modules.entity.agt.UserKeyword;

@Component
public class KeywordService extends AbstractService<Keyword, Long, KeywordDao> {

	@Autowired
	private KeywordDao keywordDao;

	@Override
	protected KeywordDao getDao() {
		return keywordDao;
	}

	// fina all keywords by page
	public List<Keyword> findAllKeywordsByPage(Map<String, Object> map) {
		return keywordDao.findAllKeywordsByPage(map);
	}

	// find keyword by name
	public Keyword findKeywordByName(String kwname) {
		return keywordDao.findKeywordByName(kwname);
	}

	// find count by param
	public Long findCountByParam(Map<String, Object> map) {
		return keywordDao.findCountByParam(map);
	}
	public void createRelKeyType(RelKeyType key){
		keywordDao.createRelKeyType(key);
	}
	/**
	 * 查看所有有可撤单的关键词
	 * @describe  
	 * @param date
	 * @return List<Keyword>
	 * @author LiuCheng
	 * 2013年10月23日 上午9:42:00
	 */
	public List<Keyword> queryUndoKey(Map<String,Object> map){
		return keywordDao.queryUndoKey(map);
	}
//	客户与建站账号关系
	public void createUserKey(UserKeyword uk){
		keywordDao.createUserKey(uk);
	}
	
	public void updateUserKey(UserKeyword uk){
		keywordDao.updateUserKey(uk);
	}
	//指定关键词与建站账号关联ID；
	public Long findUserKeyByKeyId(Long id){
		return keywordDao.findUserKeyByKeyId(id);
	}
	public Long queryKey(String keyword){
		return keywordDao.queryKey(keyword);
	}
}
