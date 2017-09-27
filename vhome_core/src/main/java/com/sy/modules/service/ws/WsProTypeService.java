package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsProTypeDao;
import com.sy.modules.entity.ws.WsProType;

@Component
public class WsProTypeService extends
		AbstractService<WsProType, Long, WsProTypeDao> {

	@Autowired
	private WsProTypeDao wsprotypedao;

	@Override
	protected WsProTypeDao getDao() {
		return wsprotypedao;
	}

	// 分页查詢所有一级分类
	public List<WsProType> findAllParentTypesByPage(Map<String, Object> map) {
		return wsprotypedao.findAllParentTypesByPage(map);
	}

	// 查询一级分类总数
	public Long findAllParentTypeCount(Map<String, Object> map) {
		return wsprotypedao.findAllParentTypeCount(map);
	}
	
	public Integer findCountByParId(Long id){
		return wsprotypedao.findCountByParId(id);
	}

	// 根据一级分类ID分页查询所有二级分类
	public List<WsProType> findAllSecondTypesByPage(Map<String, Object> map) {
		return wsprotypedao.findAllSecondTypesByPage(map);
	}

	// 分页查询二级分类总数
	public Long findAllSecondTypeCount(Map<String, Object> map) {
		return wsprotypedao.findAllSecondTypeCount(map);
	}

}
