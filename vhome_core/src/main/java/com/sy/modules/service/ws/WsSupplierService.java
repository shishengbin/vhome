package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsSupplierDao;
import com.sy.modules.entity.ws.WsSupplier;

@Component
public class WsSupplierService extends AbstractService<WsSupplier, Long, WsSupplierDao> {

	@Autowired
	private WsSupplierDao supplierdao;
	@Override
	protected WsSupplierDao getDao() {
		return supplierdao;
	}

	//分页查询所有供应商
	public List<WsSupplier> findAllSupplierByPage(Map<String,Object> map){
		return supplierdao.findAllSupplierByPage(map);
	}
	//根据条件查询总数
	public int findCountByParam(Map<String,Object> map){
		return supplierdao.findCountByParam(map);
	}
	
	//查询所有供应商
    public List<WsSupplier> findAllSupplier(Map<String,Object> map){
    	return supplierdao.findAllSupplier(map);
    }
}
