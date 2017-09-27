package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import com.sy.modules.common.ParentDao;
/**
 * 产品分类基类
 * @author Administrator
 *
 */
public interface WsProductClassDao<T,Pk> extends ParentDao<T,Pk>{
	public Integer count(Map<String,Object> map);
	public List<T> findAllByPage(Map<String,Object> map);
	public List<T> getAll(Long id);
}
