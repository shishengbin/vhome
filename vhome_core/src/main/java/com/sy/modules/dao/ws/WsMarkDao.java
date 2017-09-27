package com.sy.modules.dao.ws;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.Mark;

@Component
@MyBatisRepository
public interface WsMarkDao extends ParentDao<Mark, Long> {

	public void create(List<Mark> list);
	
	public void update(Mark mark);
}
