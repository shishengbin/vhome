package com.sy.modules.dao.ws;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsProductAndType;
@Component
@MyBatisRepository
public interface WsProductAndTypeDao extends ParentDao<WsProductAndType, Long> {

}
