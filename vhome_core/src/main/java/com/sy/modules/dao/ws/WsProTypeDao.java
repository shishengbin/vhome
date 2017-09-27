package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsProType;

@Component
@MyBatisRepository
public interface WsProTypeDao extends ParentDao<WsProType, Long> {

	// 分页查詢所有一级分类
	public List<WsProType> findAllParentTypesByPage(Map<String, Object> map);

	// 查询一级分类总数
	public Long findAllParentTypeCount(Map<String, Object> map);

	public Integer findCountByParId(Long id);

	// 分页查询二级分类
	public List<WsProType> findAllSecondTypesByPage(Map<String, Object> map);

	// 分页查询二级分类总数
	public Long findAllSecondTypeCount(Map<String, Object> map);
	

}
