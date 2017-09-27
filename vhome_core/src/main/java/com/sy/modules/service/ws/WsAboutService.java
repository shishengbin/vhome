package com.sy.modules.service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsAboutDao;
import com.sy.modules.entity.ws.WsAbout;
import com.sy.modules.entity.ws.WsAboutUser;

@Component
public class WsAboutService extends AbstractService<WsAbout, Long, WsAboutDao> {
	@Autowired
	private WsAboutDao aboutdao;

	@Override
	protected WsAboutDao getDao() {
		return this.aboutdao;
	}

	public WsAbout selectAbout(Long userid) {
		return getDao().selectAbout(userid);
	}

	// 增加
	public boolean addAbout(WsAbout about, WsAboutUser au) {
		boolean success = false;
		aboutdao.create(about);
		au.setAboutId(about.getId());
		aboutdao.addAboutUser(au);
		success = true;
		return success;
	}
}
