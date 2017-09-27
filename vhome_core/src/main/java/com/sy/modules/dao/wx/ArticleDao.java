package com.sy.modules.dao.wx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.resp.Article;

@Component
@MyBatisRepository
public interface ArticleDao extends ParentDao<Article, Long> {

	void saveArticle(Map<String, Object> map);//单文本

	void updateArticle(Map<String, Object> map);

	void saveSinArticle(Article a);//单图文

	//根据关键词查询图文
	List<Article> findSinArticle(Map<String, Object> map);

	void saveSinRelArticle(Map<String, Object> map);

	void deleteSinArticle(Map<String, Object> map);

	void deleteSinRelArticle(long article_id);

	void updateSinArticle(Article a);

	void updateArticleForRelID(Map<String, Object> map);

	void updataArticleMsgId(Map<String, Object> map);

	int count(long kwd_id);

	void saveMutiArticle(Article article);//多图文

	List<Article> findMutiArticle(Map<String, Object> map);

	void updateMutiArticleForRelID(Map<String, Object> map);
	
	//--------------------------------------sss-------------------------------------
	public Article findArticleByContent(String content);
	
	public List<Article> findAllArticleByPage(Map<String, Object> map);
	
	public Long findCountByParam(Map<String,Object> map);
}
