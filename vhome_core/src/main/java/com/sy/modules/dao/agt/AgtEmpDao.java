package com.sy.modules.dao.agt;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtEmpForm;
import com.sy.modules.entity.agt.AgtUser;
@Component
@MyBatisRepository
public interface AgtEmpDao extends ParentDao<AgtUser,Long>{
    //根据用户名查找用户
	public AgtUser findEmpByParam(String username);
    //分页查找所有管理员（返回页面显示form）
    public List<AgtEmpForm> showEmpFormBypage(Map<String,Object> map);
    //根据代理商ID查询总数
    public Integer findCountByParam(Long aid);
	
}
