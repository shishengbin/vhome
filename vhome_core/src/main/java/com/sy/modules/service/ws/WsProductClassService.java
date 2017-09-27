package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsProductClassDao;
/**
 * 产品分类service层基类
 * @author LiuCheng
 * @param <D>
 *
 * @param <T>
 * @param <Pk>
 * @param <D>
 */
public abstract class WsProductClassService<T,Pk,D extends WsProductClassDao<T, Pk>> extends AbstractService<T, Pk, D>{
	public List<T> findAllByPage(Map<String,Object> map){
		return getDao().findAllByPage(map);
	}
	public Integer count(Map<String,Object> map){
		return getDao().count(map);
	}
	public List<T> getAll(Long id){
		return getDao().getAll(id);
	}
}

