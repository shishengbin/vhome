package com.sy.modules.service.wx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.ArticleaccountDao;
import com.sy.modules.entity.wx.Articleaccount;


@Component
public class ArticleaccountService extends AbstractService<Articleaccount, Long, ArticleaccountDao> {

	@Autowired
	private ArticleaccountDao araccountDao;
	@Override
	protected ArticleaccountDao getDao() {
		return araccountDao;
	}
	
	//删除关联多图文
		public void deleteByMutiId(long id){
			 araccountDao.deleteByMutiId(id);
		}
		
		public Articleaccount findInfoByAticleId(Map<String,Object> map){
			return araccountDao.findInfoByAticleId(map);
		}

}
