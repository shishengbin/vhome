package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsIndent;

@Component
@MyBatisRepository
public interface WsIndentDao extends ParentDao<WsIndent, Long> {
    //分页查询所有订单 
	public List<WsIndent> findAllByPage(Map<String, Object> map);
    //订单总数
	public Integer count(Map<String, Object> map);
    //查询单个订单信息
	public WsIndent findById(String number);
    //创建订单
	public void addIndent(WsIndent indent);
    //修改订单信息
	public void updateIndentById(WsIndent wsIndent);
    //根据订单号查询订单信息
	public WsIndent findIndentById(String out_trade_no);
    
	public void deleteByNumber(String number);
}
  