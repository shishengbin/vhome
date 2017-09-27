package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsPhone;
import com.sy.modules.entity.ws.WsPhoneUser;

@Component
@MyBatisRepository
public interface WsPhoneDao extends ParentDao<WsPhone, Long> {

	/* 查看指定企业电话总数 */
	int count(Long id);

	// 查看指定企业所有电话
	List<WsPhone> findAllByPage(Map<String, Object> map);
	
	//注册电话与用户关系
	void addPhoneUser(WsPhoneUser wp);
	//根据号码查询号码
	List<WsPhone>findByNumber(Long id,String phone_number);
}
