package com.sy.modules.dao.ws;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsNewsClass;
import com.sy.modules.entity.ws.WsNewsClassUser;


@Component
@MyBatisRepository
public interface WsNewsClassDao extends ParentDao<WsNewsClass, Long> {
//	附条件新闻分类结果集
	List<WsNewsClass> findAllNewsClassByPage(Map<String,Object> map);
//	附条件新闻分类总数量
	Integer count(Map<String,Object> map);
//	注册分类用户关联
	void createUserNewsCat(WsNewsClassUser ncu);
//	清除分类用户关联
	void deleteNewsCat(Long id);
// 新闻分类结果集
	List<WsNewsClass> getAllClass(Long id);
}
