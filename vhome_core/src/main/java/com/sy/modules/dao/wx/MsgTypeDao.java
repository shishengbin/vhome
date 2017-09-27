package com.sy.modules.dao.wx;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;

@Component
@MyBatisRepository
public interface MsgTypeDao {

	public int findFRMType(long userid);

	public int findKwMType(Map<String, Object> map);
	
}
