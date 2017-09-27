package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsMessagesDao;
import com.sy.modules.entity.ws.WsMessages;
import com.sy.modules.entity.ws.WsMessagesUser;
/**
 * 留言业务类
 * @author LiuCheng
 *
 */
@Component
public class WsMessagesService extends AbstractService<WsMessages,Long,WsMessagesDao> {
	@Autowired
	private WsMessagesDao messagesdao;
	@Override
	protected WsMessagesDao getDao() {
		return this.messagesdao;
	}
	//分页查询所有留言
	public List<WsMessages> findAllMessagesByPage(Map<String, Object> map){
		return messagesdao.findAllMessagesByPage(map);
	}
	//注册留言与用户关联
	public void addMessagesUser(WsMessagesUser mu){
		messagesdao.addMessagesUser(mu);
	}
	//查看某企业留言总数
	public int count(Map<String,Object> map){
		return messagesdao.count(map);
	}
	//发表留言
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addMessages(WsMessages wm,WsMessagesUser wmu){
		boolean success = false;
		messagesdao.create(wm);
		wmu.setMessageId(wm.getId());
		messagesdao.addMessagesUser(wmu);
		success = true;
		return success;
	}
	//删除留言
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteMessages(Long id){
		boolean success= false;
		messagesdao.deleteById(id);
		messagesdao.deleteMessagesUser(id);
		success = true;
		return success;
	}
}
