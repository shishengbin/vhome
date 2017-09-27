package com.sy.modules.dao.ws;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsPayment;

@Component
@MyBatisRepository
public interface WsPaymentDao extends ParentDao<WsPayment, Long> {


	public List<WsPayment> getPayment(Long userId);
	
	public void deletePayment(Long id);
	
	public void updatePayment(WsPayment pay);
	
	public void createPayment(WsPayment pay);
}
