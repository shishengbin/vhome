package com.sy.modules.service.agt;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AgtPostDao;
import com.sy.modules.entity.agt.AgtPost;

/**
 * 公告信息操作业务层
 * @author zw
 * 2013-10-16
 * */
@Component
public class AgtPostService extends AbstractService<AgtPost, Long, AgtPostDao>{
    @Autowired
    private AgtPostDao apd;
	@Override
	protected AgtPostDao getDao() {
		return apd;
	}
   
    //分页查找所有用户
    public List<AgtPost> showAgPostBypage(Map<String,Object> map){
    	return apd.showAgPostBypage(map);
    }
    //查询总数
	 public Long findCountByParam(Map<String,Object> map){
		 return apd.findCountByParam(map);
	 }   
	 //查看公告
	 public AgtPost findById(Long id){
		 return apd.findById(id);
	 }

	
}
