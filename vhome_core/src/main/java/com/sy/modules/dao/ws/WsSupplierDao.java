package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsSupplier;

@Component
@MyBatisRepository
public interface WsSupplierDao extends ParentDao<WsSupplier, Long> {
	
	//分页查询所有供应商
	public List<WsSupplier> findAllSupplierByPage(Map<String,Object> map);
	
	//根据条件查询总数
	public int findCountByParam(Map<String,Object> map);
	
	//查询所有供应商
    public List<WsSupplier> findAllSupplier(Map<String,Object> map);
}
