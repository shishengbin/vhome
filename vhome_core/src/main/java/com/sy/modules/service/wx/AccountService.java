package com.sy.modules.service.wx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.AccountDao;
import com.sy.modules.entity.wx.Account;

@Component
public class AccountService extends AbstractService<Account, Long, AccountDao> {
	@Autowired
	private AccountDao accountDao;
	@Override
	protected AccountDao getDao() {
		return accountDao;
	}
	
	//根据登陆账号查询微信公众号信息
	
	public Account findAccountInfo(long userid){
		
		return accountDao.findAccountInfo(userid);
	}
	
	public Account findUserAppinfo(long userid) {	
		return accountDao.findUserAppinfo(userid);
	}
	
	public void saveAccessToken(Map<String,Object> map){
		accountDao.saveAccessToken(map);
	}

	public int getATState(long userid) {
		return accountDao.getATState(userid);
	}

	public void saveUserInfo(Account account) {	
		accountDao.saveUserInfo(account);
	}

	public long findUserID(Account account) {
		return accountDao.findUserID(account);
	}

	
}
