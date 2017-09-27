package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtPost;


/**
 * 公告信息操作接口
 * @author zw
 * 2013-10-16
 * */
@Component
@MyBatisRepository
public interface AgtPostDao extends ParentDao<AgtPost, Long>{
	  //分页查找所有用户
    public List<AgtPost> showAgPostBypage(Map<String, Object> map);    
     //查询当前页总数 
	 public Long findCountByParam(Map<String,Object> map);

}

