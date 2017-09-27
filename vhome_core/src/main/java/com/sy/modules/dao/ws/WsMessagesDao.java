package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsMessages;
import com.sy.modules.entity.ws.WsMessagesUser;

@Component
@MyBatisRepository
public interface WsMessagesDao extends ParentDao<WsMessages, Long> {

	/* 查看指定企业留言总数付查询条件 */
	int count(Map<String,Object> map);

	// 留言分页查看详细信息
	List<WsMessages> findAllMessagesByPage(Map<String, Object> map);
	
	//注册留言与用户关系
	void addMessagesUser(WsMessagesUser mu);
	
	//清除留言与用户关系
	void deleteMessagesUser(Long id);
}
