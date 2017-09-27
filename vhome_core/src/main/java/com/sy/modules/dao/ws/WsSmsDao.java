package com.sy.modules.dao.ws;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsSms;
import com.sy.modules.entity.ws.WsSmsUser;


@Component
@MyBatisRepository
public interface WsSmsDao extends ParentDao<WsSms, Long> {
	
	//注册短信与用户关系
	void addSmsUser(WsSmsUser ws);
	//获取所有短信
	List<WsSms> findAllByPage(Map<String, Object> map);
	//根据主题查询短信 
	List<WsSms> findByTitle(Long id,String sms_title);
	//查看默认短信
	WsSms findUseSms(Long id);
}
