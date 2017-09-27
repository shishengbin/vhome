package com.sy.modules.entity.ws;

import java.util.List;

import com.sy.commons.entity.ParentEntity;

/**
 * @ClassName: WsIndent
 * @Description:(订单信息)
 * @author sss
 * @date 2014年3月4日 下午3:44:36
 * 
 */
public class WsIndent extends ParentEntity {

	private static final long serialVersionUID = 1443512792937869952L;
	private Long id;
	private String name;// 订单名称
	private String number;// 订单编号
	private Double money;// 订单金额
	private IndentStatus status;// 订单状态:0未付款，1未发货，2已发货，3，交易完成
	private WsHuman human;// 会员信息
	//支付方式
	private Integer payway; //1：货到付款 2：支付宝
	// private WsPayment payment;//支付信息
	private String transportName;// 物流公司
	private String transportNumber;// 运单号
	private String transportDestination;// 发送目的地
	private String receivePerson;// 收货人
	private String receivePhone;// 收货电话
	private List<WsProductIndnet> productIndnet;// 关联产品信息
	private List<WsMtProduct> products;// 关联产品信息
	private String remark;
	private String sendtime;//派送时间

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public WsIndent() {
	}

	public List<WsMtProduct> getProducts() {
		return products;
	}

	public void setProducts(List<WsMtProduct> products) {
		this.products = products;
	}

	public void setStatus(IndentStatus status) {
		this.status = status;
	}

	public WsIndent(String out_trade_no, IndentStatus status) {
		this.number = out_trade_no;
		this.status = status;
	}

	public WsIndent(String out_trade_no) {
		this.number = out_trade_no;
	}

	public List<WsProductIndnet> getProductIndnet() {
		return productIndnet;
	}

	public void setProductIndnet(List<WsProductIndnet> productIndnet) {
		this.productIndnet = productIndnet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStatus() {
		if (this.status != null) {
			return status.getStatus();
		} else {
			return -1;
		}
	}

	public void setStatus(Integer status) {
		this.status = IndentStatus.getStatus(status);
	}

	public WsHuman getHuman() {
		return human;
	}

	public void setHuman(WsHuman human) {
		this.human = human;
	}

	/*
	 * public WsPayment getPayment() { return payment; } public void
	 * setPayment(WsPayment payment) { this.payment = payment; }
	 */
	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

	public String getTransportNumber() {
		return transportNumber;
	}

	public void setTransportNumber(String transportNumber) {
		this.transportNumber = transportNumber;
	}

	public String getTransportDestination() {
		return transportDestination;
	}

	public void setTransportDestination(String transportDestination) {
		this.transportDestination = transportDestination;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

}
