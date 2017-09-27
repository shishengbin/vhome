package com.sy.modules.dao.wx;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.Articleaccount;

@Component
@MyBatisRepository
public interface ArticleaccountDao extends ParentDao<Articleaccount,Long> {
	
	//删除关联多图文
	public void deleteByMutiId(long id);
	
	public Articleaccount findInfoByAticleId(Map<String,Object> map);

}
