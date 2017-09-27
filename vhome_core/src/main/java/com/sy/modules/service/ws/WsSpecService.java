package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsSpecDao;
import com.sy.modules.entity.ws.WsSpec;
@Component
public class WsSpecService extends AbstractService<WsSpec,Long,WsSpecDao>{

	@Autowired
	private WsSpecDao wsspecdao;
	@Override
	protected WsSpecDao getDao() {
		return wsspecdao;
	}
	
	  //分页查询所以规格
		public List<WsSpec> findAllSpecByPage(Map<String,Object> map){
			return wsspecdao.findAllSpecByPage(map);
		}
		
		//根据条件查询总数
		public int findCount(Map<String,Object> map){
			return wsspecdao.findCount(map);
		}


		public List<WsSpec> findAllSpec(Map<String,Object> map){
			return wsspecdao.findAllSpec(map);
		}
}
