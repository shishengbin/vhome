package com.sy.modules.dao.agt;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.agt.AgtCompany;
import com.sy.modules.entity.agt.RelAgtCompany;

/**
 * 客户信息操作接口
 * @author zw
 * 2013-10-28
 * */
@Component
@MyBatisRepository
public interface AgtCompanyDao extends ParentDao<AgtCompany, Long>{	
    //分页查找所有用户
    public List<AgtCompany> showAgCompanyBypage(Map<String, Object> map);        
    //查询总数
    public Integer findCountByParam(Map<String,Object> map);
    //模糊查询客户
    public List<AgtCompany> searchCompany(Map<String, Object> map);
    //根据公司名称查找
    public AgtCompany findCompanyByName(Map<String, Object> map);
    //简单根据公司名称查找
    public AgtCompany findCompanyByName2(Map<String, Object> map);
    //插入关系表
    public void addRAC(RelAgtCompany rac);
    //删除对应关系
    public void delRAC(RelAgtCompany rac);
    //通过客户ID查找是否有建站帐号
    public Integer  findUidByCid(Long cid);
}
