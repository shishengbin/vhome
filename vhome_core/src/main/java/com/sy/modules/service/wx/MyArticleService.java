package com.sy.modules.service.wx;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.MyArticleDao;
import com.sy.modules.entity.wx.resp.MyArticle;
@Component
public class MyArticleService extends AbstractService<MyArticle, Long, MyArticleDao> {

	@Autowired
	private MyArticleDao myarticleDao;
	@Override
	protected MyArticleDao getDao() {
		return myarticleDao;
	}
	public MyArticle findArticleByContent(String content){
		return myarticleDao.findArticleByContent(content);
	}
	
	//分页查找所有单图文
	public List<MyArticle> findAllArticleByPage(Map<String,Object> map){
		return myarticleDao.findAllArticleByPage(map);
	}
	
	//查找单图文总数
	public Long findCountByParam(Map<String,Object> map){
		return myarticleDao.findCountByParam(map);
	}
	
	//分页查询多图文的
	public List<MyArticle> findAllManyArticleByPage(Map<String,Object> map){
		return myarticleDao.findAllManyArticleByPage(map);
	}
	//多图文总数
	public Long findManyArticleCountByParam(Map<String,Object> map){
		return myarticleDao.findManyArticleCountByParam(map);
	}
	

}
