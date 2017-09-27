package com.sy.modules.service.agt;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AgtEmpDao;
import com.sy.modules.entity.agt.AgtEmpForm;
import com.sy.modules.entity.agt.AgtUser;

@Component
public class AgtEmpService extends AbstractService<AgtUser, Long, AgtEmpDao>{
	
	@Autowired
	private AgtEmpDao aed;

	@Override
	protected AgtEmpDao getDao() {
		return aed;
	}
	//检查员工用户名
	public boolean checkUsername(String username){
		AgtUser ae = aed.findEmpByParam(username);
		if(ae==null){
			return true;
		}else{
			return false;
		}
	}

    //分页查找所有管理员（返回页面显示form）
    public List<AgtEmpForm> showEmpFormBypage(Map<String,Object> map){
         return  aed.showEmpFormBypage(map);	
    }
    public Integer findCountByParam(Long aid)
 	{
 		return aed.findCountByParam(aid);
 	}
}
