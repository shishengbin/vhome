package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.Keyword;
import com.sy.modules.entity.agt.RelKeyType;
import com.sy.modules.entity.agt.UserKeyword;
@Component
@MyBatisRepository
public interface KeywordDao extends ParentDao<Keyword, Long> {
    //fina all keywords by page
	public List<Keyword> findAllKeywordsByPage(Map<String,Object> map);
	
	//find keyword by name
	public Keyword findKeywordByName(String kwname);
		
	//find count by param
	public Long findCountByParam(Map<String,Object> map);
	
	//与类型关联关系
	public void createRelKeyType(RelKeyType key);
	
	public List<Keyword> queryUndoKey(Map<String,Object> map);
	
//	建站账号与关键词关系
	public void createUserKey(UserKeyword uk);
	
	public void updateUserKey(UserKeyword uk);
	
	public Long findUserKeyByKeyId(Long id);
	
	public Long queryKey(String keyword);
}
