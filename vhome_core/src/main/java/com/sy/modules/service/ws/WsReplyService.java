package com.sy.modules.service.ws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsReplyDao;
import com.sy.modules.entity.ws.WsMessagesReply;
import com.sy.modules.entity.ws.WsReply;
/**
 * 留言业务类
 * @author LiuCheng
 *
 */
@Component
public class WsReplyService extends AbstractService<WsReply,Long,WsReplyDao> {
	@Autowired
	private WsReplyDao messagesdao;
	@Override
	protected WsReplyDao getDao() {
		return this.messagesdao;
	}
	/**
	 * 发表回复信息
	 * @author LiuCheng
	 * @param map
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addReply(Map<String,Object> map){
		WsReply wr = new WsReply(map.get("replyContent").toString());
		wr.setCreateName("管理员");
		getDao().create(wr);
		getDao().addReplyMessages(new WsMessagesReply((Long)map.get("messageid"),wr.getId()));
	}
}
