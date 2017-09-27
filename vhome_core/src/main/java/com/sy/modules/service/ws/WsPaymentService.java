package com.sy.modules.service.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsMarkDao;
import com.sy.modules.dao.ws.WsPaymentDao;
import com.sy.modules.entity.ws.Mark;
import com.sy.modules.entity.ws.WsPayment;

@Component
public class WsPaymentService extends AbstractService<WsPayment, Long, WsPaymentDao> {
	@Autowired
	private WsPaymentDao paymentdao;

	@Autowired
	private WsMarkDao markdao;

	@Override
	protected WsPaymentDao getDao() {
		return this.paymentdao;
	}

	/**
	 * 
	 * @Title: getPayment
	 * @Description: TODO(获取所有支付费方式)
	 * @return List<WsIndent> 返回类型
	 * @author LiuCheng
	 * @throws
	 */
	public List<WsPayment> getPayment(Long userId) {
		return paymentdao.getPayment(userId);
	}

	public void deletePayment(Long id) {
		paymentdao.deletePayment(id);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePayment(WsPayment pay) {
		paymentdao.updatePayment(pay);
		List<Mark> m = pay.getMark();
		if (m != null) {
			for (int i = 0; i < m.size(); i++) {
				if (m.get(i).getId() != null) {
					markdao.update(m.get(i));
					m.remove(i);
					i--;
				} else {
					m.get(i).setPaymentId(pay.getId());
				}
			}
			if (m.size() > 0) {
				markdao.create(m);
			}
		}
	}

	public void updatePaymentState(WsPayment pay) {
		paymentdao.updatePayment(pay);
	}

	/**
	 * 
	 * @Title: createPayment
	 * @Description: TODO(创建支付方式)
	 * @return void 返回类型
	 * @author LiuCheng
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void createPayment(WsPayment pay) {
		paymentdao.createPayment(pay);
		if (pay.getMark() != null) {
			for (Mark m : pay.getMark()) {
				m.setPaymentId(pay.getId());
			}
			markdao.create(pay.getMark());
		}

	}
}
