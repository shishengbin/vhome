package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsSmsDao;
import com.sy.modules.entity.ws.WsSms;
import com.sy.modules.entity.ws.WsSmsUser;

@Component
public class WsSmsService extends AbstractService<WsSms,Long,WsSmsDao> {
	@Autowired
	private WsSmsDao smsdao;
	@Override
	protected WsSmsDao getDao() {
		return this.smsdao;
	}
	
	//添加短信
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addSms(WsSms ws,WsSmsUser wsu){
		boolean success = false;
		smsdao.create(ws);
		wsu.setSms_id(ws.getId());
		smsdao.addSmsUser(wsu);
		success = true;
		return success;
	}
	//获取所有
	public List<WsSms>finAllByPage(Map<String, Object> map)
	{
		return smsdao.findAllByPage(map);
	}
	//根据主题查询短信 
	public List<WsSms> findByTitle(Long id,String sms_title)
	{
		return smsdao.findByTitle(id,sms_title);
	}
	//查看默认短信
	public WsSms findUseSms(Long id)
	{
		return smsdao.findUseSms(id);
	}
}
