package com.sy.modules.dao.ws;

import org.springframework.stereotype.Component;
import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsAbout;
import com.sy.modules.entity.ws.WsAboutUser;



@Component
@MyBatisRepository
public interface WsAboutDao extends ParentDao<WsAbout, Long> {
	/* 注册公司简介与用户关系 */
	void addAboutUser(WsAboutUser au);

	/* 查看公司简介 */
	WsAbout selectAbout(Long userid);
}
