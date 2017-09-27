package com.sy.modules.service.wx;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.ArticleDao;
import com.sy.modules.entity.wx.resp.Article;

@Component
public class ArticleService extends AbstractService<Article, Long, ArticleDao> {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	protected ArticleDao getDao() {
		return articleDao;
	}

	
	public List<Article> findAllArticleByPage(Map<String, Object> map){
		
		return articleDao.findAllArticleByPage(map);
	}
	public Long findCountByParam(Map<String,Object> map){
		
		return articleDao.findCountByParam(map);
	}
	
	
	
	public void saveArticle(Map<String, Object> map){
		articleDao.saveArticle(map);
	}
	public void updateArticle(Map<String, Object> map) {
		articleDao.updateArticle(map);
	}
	public void saveSinArticle(Article a) {
		articleDao.saveSinArticle(a);
	}
	public List<Article> findSinArticle(Map<String, Object> map) {
		return articleDao.findSinArticle(map);
	}
	public void saveSinRelArticle(Map<String, Object> map) {
		articleDao.saveSinRelArticle(map);
	}
	public void deleteSinArticle(Map<String, Object> map) {
		articleDao.deleteSinArticle(map);
	}
	public void deleteSinRelArticle(long article_id) {
		articleDao.deleteSinRelArticle(article_id);
	}
	public void updateSinArticle(Article a) {
		articleDao.updateSinArticle(a);
	}
	public void updateArticleForRelID(Map<String, Object> map) {
		articleDao.updataArticleMsgId(map);
		articleDao.updateArticleForRelID(map);
	}
	public int count(long kwd_id) {
		return articleDao.count(kwd_id);
	}
	public void saveMutiArticle(Article article) {
		articleDao.saveMutiArticle(article);
	}
	public List<Article> findMutiArticle(Map<String, Object> map) {
		return articleDao.findMutiArticle(map);
	}
	public void updateMutiArticleForRelID(Map<String, Object> map) {
		articleDao.updataArticleMsgId(map);
		articleDao.updateMutiArticleForRelID(map);
	}
	
	public Article findArticleByContent(String content){
		return articleDao.findArticleByContent(content);
	}

}
