package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsNews;
import com.sy.modules.entity.ws.WsNewsUser;

@Component
@MyBatisRepository
public interface WsNewsDao extends ParentDao<WsNews, Long> {

	/* 查看指定企业新闻总数 付查询条件*/
	int count(Map<String,Object> map);

	// 分页查询新闻
	List<WsNews> findAllNewsByPage(Map<String, Object> map);
	
	
	List<WsNews> findIndexNewsByPage(Map<String, Object> map);

	// 注册新闻与用户关联
	void addNewsUser(WsNewsUser nu);
	
	//清除新闻与用户关系
	void deleteNewsUser(Long id);
	//根据新闻分类查询新闻信息
	List<WsNews> getByClass(Long id);
}
