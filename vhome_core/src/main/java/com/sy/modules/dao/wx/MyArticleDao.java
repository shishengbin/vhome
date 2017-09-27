package com.sy.modules.dao.wx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.resp.MyArticle;

@Component
@MyBatisRepository
public interface MyArticleDao extends ParentDao<MyArticle, Long> {

	public MyArticle findArticleByContent(String content);
	
	//分页查找所有单图文
	public List<MyArticle> findAllArticleByPage(Map<String,Object> map);
	
	//查找总数
	public Long findCountByParam(Map<String,Object> map);
	
	//分页查询多图文的
	public List<MyArticle> findAllManyArticleByPage(Map<String,Object> map);
	
	//多图文总数
	public Long findManyArticleCountByParam(Map<String,Object> map);
	
	

}
