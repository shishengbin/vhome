package com.sy.modules.service.agt;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.AgtCompanyDao;
import com.sy.modules.entity.agt.AgtCompany;
import com.sy.modules.entity.agt.RelAgtCompany;
/**
 * 客户信息操作业务层
 * @author zw
 * 2013-10-28
 * */
@Component
public class AgtCompanyService extends AbstractService<AgtCompany, Long, AgtCompanyDao>{
    @Autowired
    private AgtCompanyDao acd ;
    @Override
    protected AgtCompanyDao getDao() {
    	// TODO Auto-generated method stub
    	return acd;
    }
    //分页查找所有客户
    public List<AgtCompany> showAgCompanyBypage(Map<String, Object> map){    	
 	  return acd.showAgCompanyBypage(map);
    }
    //查询客户总数
    public Integer findCountByParam(Map<String,Object> map)
 	{
 		return acd.findCountByParam(map);
 	}
    //搜索客户
    public List<AgtCompany> searchCompany(Map<String, Object> map){
 	   return acd.searchCompany(map);
    }
    //根据公司名称查找
    public boolean findCompanyByName(Map<String, Object> map){
    AgtCompany ac = 	acd.findCompanyByName(map);
    	if(ac==null){
    		return true;
    	}else{
    		return false;
    	}    	
    }
   //增加客户
    //@Transactional(propagation = Propagation.REQUIRED)
     @Transactional(rollbackFor=Exception.class)
     public boolean addCompany(AgtCompany ac,Long aid){
    	 try{
	     acd.create(ac);
    	 Map<String, Object> map = new HashMap<String,Object>();
    	 String companyName = ac.getCompanyName();
    	 Date   createTime = ac.getCreateTime();
    	 map.put("createTime", createTime);
    	 map.put("companyName", companyName);
    	 long cid = 	acd.findCompanyByName(map).getCompanyId();
    	 RelAgtCompany rac = new RelAgtCompany();
    	 rac.setAid(aid);
    	 rac.setCid(cid);
    	 acd.addRAC(rac);
    	 return true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 return false;
    	 }
     }
  //删除客户
    //@Transactional(propagation = Propagation.REQUIRED)
    @Transactional(rollbackFor=Exception.class)
    public void delCompany(Long cid,Long aid){
    	this.delete(cid);
    	 RelAgtCompany rac = new RelAgtCompany();
    	 rac.setAid(aid);
    	 rac.setCid(cid);
    	 acd.delRAC(rac);
    }
    //判断客户是否可以删除
    public boolean findUidByCid(long cid){
    	int num =  acd.findUidByCid(cid);
    	 if(num==0){
    		 return true;
    	 }else{
    		 return false;
    	 }
    }
}
