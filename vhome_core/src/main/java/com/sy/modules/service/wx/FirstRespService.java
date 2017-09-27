package com.sy.modules.service.wx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.dao.wx.FirstRespDao;
import com.sy.modules.entity.wx.FirstResp;
import com.sy.modules.entity.wx.resp.TextMessageResp;

@Component
public class FirstRespService {
	@Autowired
	private FirstRespDao firstRespDao;
	public TextMessageResp findContentByid(long userid) {
		return firstRespDao.findContentByid(userid);
	}
	public void saveFirstResp(FirstResp firstResp) {
		firstRespDao.saveFirstResp(firstResp);
	}
	public int count(FirstResp firstResp) {
		return firstRespDao.count(firstResp);
	}
	public FirstResp findFirstResp(Map<String, Object> map) {
		return firstRespDao.findFirstResp(map);
	}
	public void updateFirstRespstate(long userid) {
		firstRespDao.updateFirstRespstate(userid);
	}
	public void updateFirstResp(Map<String, Object> map) {
		firstRespDao.updateFirstResp(map);		
	}
	
}
