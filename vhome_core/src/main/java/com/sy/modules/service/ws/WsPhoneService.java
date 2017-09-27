package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsPhoneDao;
import com.sy.modules.entity.ws.WsPhone;
import com.sy.modules.entity.ws.WsPhoneUser;

@Component
public class WsPhoneService extends AbstractService<WsPhone,Long,WsPhoneDao> {
	@Autowired
	private WsPhoneDao phonedao;
	@Override
	protected WsPhoneDao getDao() {
		return this.phonedao;
	}
	//号码总数
	public int count(Long id){
		return phonedao.count(id);
	}
	
	//添加号码
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addPhone(WsPhone wn,WsPhoneUser wnu){
		boolean success = false;
		phonedao.create(wn);
		wnu.setPhoneId(wn.getId());
		phonedao.addPhoneUser(wnu);
		success = true;
		return success;
	}
	
	//删除号码
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deletePhone(Long id){
		boolean success = false;
		phonedao.deleteById(id);
		success = true;
		return success;
	}
	//修改号码
	public void updatePhone(WsPhone obj){
		phonedao.update(obj);
	}
	//查询所有号码
	public List<WsPhone> getAll(Map<String, Object> map)
	{
		return phonedao.findAllByPage(map);	
	}
	//根据号码查询号码
	public List<WsPhone>findByNumber(Long id,String phone_number)
	{
		return phonedao.findByNumber(id,phone_number);
	}
}
