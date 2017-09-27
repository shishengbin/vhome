package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsNewsClassDao;
import com.sy.modules.entity.ws.WsNewsClass;
import com.sy.modules.entity.ws.WsNewsClassUser;

/**
 * 新闻分类业务
 * @author LiuCheng
 *
 */
@Component
public class WsNewsClassService extends AbstractService<WsNewsClass,Long,WsNewsClassDao> {
	@Autowired
	private WsNewsClassDao newsclassdao;
	@Override
	protected WsNewsClassDao getDao() {
		return this.newsclassdao;
	}
	public List<WsNewsClass> findAllNewsClassByPage(Map<String,Object> map){
		return newsclassdao.findAllNewsClassByPage(map);
	}
	public Integer count(Map<String,Object> map){
		return newsclassdao.count(map);
	}
	/**
	 * 增加新闻分类
	 * @param nc 新闻分类
	 * @param ncu 新闻分类与用户关联
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean create(WsNewsClass nc,WsNewsClassUser ncu){
		boolean success = false;
		newsclassdao.create(nc);
		ncu.setNewsCatId(nc.getId());
		newsclassdao.createUserNewsCat(ncu);
		success = true;
		return success;
	}
//	删除
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteNewsCat(Long id){
		boolean success = false;
		newsclassdao.deleteById(id);
		newsclassdao.deleteNewsCat(id);
		success = true;
		return success;
	}
	// 新闻分类结果集
	public List<WsNewsClass> getAllClass(Long id)
	{
		return newsclassdao.getAllClass(id);
	}
}

