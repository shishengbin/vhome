package com.sy.modules.dao.wx;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.Sysuseraccount;
@Component
@MyBatisRepository
public interface SysuseraccountDao extends ParentDao<Sysuseraccount, Long> {
	
	//根据登陆账号id查询微信账号id
	
	public Long getAccountId(Long userId);

}
