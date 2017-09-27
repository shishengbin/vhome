package com.sy.modules.dao.wx;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.entity.wx.Human;

@Component
@MyBatisRepository
public interface HumanDao {

	void save(Human human);

	int count(String openid);
	
}
