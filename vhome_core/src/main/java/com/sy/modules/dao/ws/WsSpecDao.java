package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsSpec;
@Component
@MyBatisRepository
public interface WsSpecDao extends ParentDao<WsSpec,Long>{
	
	//分页查询所以规格
	public List<WsSpec> findAllSpecByPage(Map<String,Object> map);
	
	//根据条件查询总数
	public int findCount(Map<String,Object> map);
	
	//查询所有规格
	public List<WsSpec> findAllSpec(Map<String,Object> map);

}
