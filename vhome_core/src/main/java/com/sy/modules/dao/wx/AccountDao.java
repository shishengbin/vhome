package com.sy.modules.dao.wx;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.Account;

@Component
@MyBatisRepository
public interface AccountDao extends ParentDao<Account,Long> {
	
	//根据登陆账号查询微信公众号信息
	public Account findAccountInfo(long userid);
	
	public Account findUserAppinfo(long userid);
	
	public void saveAccessToken(Map<String,Object> map);

	public int getATState(long userid);

	public void saveUserInfo(Account account);

	public long findUserID(Account account);
}
