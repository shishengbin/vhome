package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsNewsDao;
import com.sy.modules.entity.ws.WsNews;
import com.sy.modules.entity.ws.WsNewsUser;

/**
 * 新闻业务
 * @author LiuCheng
 *
 */
@Component
public class WsNewsService extends AbstractService<WsNews,Long,WsNewsDao> {
	@Autowired
	private WsNewsDao newsdao;
	@Override
	protected WsNewsDao getDao() {
		return this.newsdao;
	}
	//新闻总数
	public int count(Map<String,Object> map){
		return newsdao.count(map);
	}
	
	//分页查询新闻
	public List<WsNews> findAllNewsByPage(Map<String, Object> map){
		return newsdao.findAllNewsByPage(map);
	}
	
	public List<WsNews> findIndexNewsByPage(Map<String, Object> map){
		
		return newsdao.findIndexNewsByPage(map);
	}

	//添加新闻
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addNews(WsNews wn,WsNewsUser wnu){
		boolean success = false;
		newsdao.create(wn);
		wnu.setNewsId(wn.getId());
		newsdao.addNewsUser(wnu);
		success = true;
		return success;
	}
	
	//删除新闻
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteNews(Long id){
		boolean success = false;
		newsdao.deleteById(id);
		newsdao.deleteNewsUser(id);
		success = true;
		return success;
	}
	//根据新闻分类查询新闻信息
	public List<WsNews> getByClass(Long id)
	{
		return newsdao.getByClass(id);
	}
}
