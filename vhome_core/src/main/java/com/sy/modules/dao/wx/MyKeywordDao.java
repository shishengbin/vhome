package com.sy.modules.dao.wx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.MyKeyword;
import com.sy.modules.entity.wx.resp.MyArticle;

@Component
@MyBatisRepository
public interface MyKeywordDao extends ParentDao<MyKeyword, Long> {
	//根据关键词名称查询关键词信息
	public MyKeyword findKeyWordByName(String keyname);
	
	public MyKeyword findMyKeyWordById(Map<String,Object> map);
	
	//分页查询所有关键字回复
	public List<MyKeyword> findAllMykeywordsByPage(Map<String,Object> map);
	
	//查找总数
	public Long findCountByParam(Map<String,Object> map);
	
	//关键词对应的单图文
	
	public List<MyArticle> findKeyWordBySinArticle(Map<String,Object> map);
	
	//查询关键词对应的多图文id
	public long findKeyWordForManArticleId(Map<String,Object> map);
	
	//根据关键词对应的多图文id对应的多图文
	public List<MyArticle> findKeyWordByManyArticle(long manarticleId);
	
}
