package com.sy.modules.service.wx;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.MyKeywordDao;
import com.sy.modules.entity.wx.MyKeyword;
import com.sy.modules.entity.wx.resp.MyArticle;

@Component
public class MyKeywordService extends AbstractService<MyKeyword, Long, MyKeywordDao> {

	@Autowired
	private MyKeywordDao mykeyworddao;
	@Override
	protected MyKeywordDao getDao() {
		return mykeyworddao;
	}

	//根据关键词名称查询关键词信息
	public MyKeyword findKeyWordByName(String keyname){
		return mykeyworddao.findKeyWordByName(keyname);
	}
		
	//分页查询所有关键字回复
    public List<MyKeyword> findAllMykeywordsByPage(Map<String,Object> map){
    	return mykeyworddao.findAllMykeywordsByPage(map);
    }
		
	//查找总数
	public Long findCountByParam(Map<String,Object> map){
		return mykeyworddao.findCountByParam(map);
	}
	
	public MyKeyword findMyKeyWordById(Map<String,Object> map){
		
		return mykeyworddao.findMyKeyWordById(map);
	}
	
	public List<MyArticle> findKeyWordBySinArticle(Map<String,Object> map){
		return mykeyworddao.findKeyWordBySinArticle(map);
	}
	
}
