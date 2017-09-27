package com.sy.modules.service.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.SysuseraccountDao;
import com.sy.modules.entity.wx.Sysuseraccount;
@Component
public class SysuseraccountService extends AbstractService<Sysuseraccount, Long, SysuseraccountDao> {

	@Autowired
	private SysuseraccountDao sysuseraccoutdao;
	
	@Override
	protected SysuseraccountDao getDao() {
		return sysuseraccoutdao;
	}

	//根据登陆账号id查询微信账号id
	
	public Long getAccountId(Long userId) {
		return sysuseraccoutdao.getAccountId(userId);
	}
}
