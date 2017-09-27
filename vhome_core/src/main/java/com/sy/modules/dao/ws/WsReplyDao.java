package com.sy.modules.dao.ws;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsMessagesReply;
import com.sy.modules.entity.ws.WsReply;

@Component
@MyBatisRepository
public interface WsReplyDao extends ParentDao<WsReply, Long> {
	// 注册回复信息与留言关联
	void addReplyMessages(WsMessagesReply mr);
}
