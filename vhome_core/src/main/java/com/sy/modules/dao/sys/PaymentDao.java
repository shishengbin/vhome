package com.sy.modules.dao.sys;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.sys.Payment;

@Component
@MyBatisRepository
public interface PaymentDao extends ParentDao<Payment, Long> {
	
	//find all payments
	public List<Payment> findAllPayments();

}
