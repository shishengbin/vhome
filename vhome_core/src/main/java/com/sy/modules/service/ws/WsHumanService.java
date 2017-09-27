package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsHumanDao;
import com.sy.modules.entity.ws.WsHuman;
import com.sy.modules.entity.ws.WsHumanUser;

@Component
public class WsHumanService extends AbstractService<WsHuman,Long,WsHumanDao> {
	@Autowired
	private WsHumanDao humanDao;
	@Override
	protected WsHumanDao getDao() {
		return this.humanDao;
	}	
	//分页查询会员
	public List<WsHuman> findAllHumanByPage(Map<String, Object> map){
		return humanDao.findAllHumanByPage(map);
	}

	//根据查询条件返回总数
	public long findCountByParam(Map<String,Object> map){
		return humanDao.findCountByParam(map);
	}
	//添加会员
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addHuman(WsHuman wn,WsHumanUser wnu){
		boolean success = false;
		humanDao.create(wn);
		wnu.setHumanId(wn.getId());
		humanDao.addHumanUser(wnu);
		success = true;
		return success;
	}
	/**
	 * wx访问
	 * 添加会员信息
	 * @param userid 
	 * @param fromUserName 
	 */
	public void wxRegister(String fromUserName, long userid){	
			//System.out.println("enter......");
			WsHuman wsHuman=new WsHuman();
			WsHumanUser wnu=new WsHumanUser();
			wsHuman.setHuman_account(fromUserName);
			humanDao.wxcreate(wsHuman);
			wnu.setUserId(userid);
			wnu.setHumanId(wsHuman.getId());
			humanDao.addHumanUser(wnu);
	}
	//根据会员账号查询会员信息
	public List<WsHuman> findByAccount(String account)
	{
		return humanDao.findByAccount(account);
	}
	//会员登录
	public WsHuman login(Long id,String username,String password)
	{
		return humanDao.login(id,username, password);
	}
	
	public WsHuman findById(Long humanId){
		return humanDao.findById(humanId);
	}
}
