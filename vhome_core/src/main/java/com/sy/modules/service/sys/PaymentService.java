package com.sy.modules.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.sys.PaymentDao;
import com.sy.modules.entity.sys.Payment;

@Component
public class PaymentService extends AbstractService<Payment, Long, PaymentDao> {

	@Autowired
	private PaymentDao paymentdao;

	@Override
	protected PaymentDao getDao() {
		return paymentdao;
	}

	// find all payments
	public List<Payment> findAllPayments() {
		return paymentdao.findAllPayments();
	}
}
