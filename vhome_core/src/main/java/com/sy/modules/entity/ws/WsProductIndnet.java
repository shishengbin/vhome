package com.sy.modules.entity.ws;


/**
* 
* @ClassName: WsProductIndnet 
* @Description:(产品订单关联) 
* @author LiuCheng
* @date 2014年3月4日 下午4:53:16 
* 购物车
*/
public class WsProductIndnet {
	private Long id;
	private Long indentid;
	private WsMtProduct product;//商品信息
	private Long productid;
	private Integer count;//商品数量
	private String remark;//购物车
	
	public WsProductIndnet(){}
	public WsProductIndnet(Long iid,Integer count,String remark){
		this.count = count;
		this.indentid = iid;
		this.remark = remark;
	}
	public WsProductIndnet(Long pid,String remark){
		this.productid = pid;
		this.remark = remark;
	}
	public WsProductIndnet(Long pid,Long iid,Integer count){
		this.count = count;
		this.productid = pid;
		this.indentid = iid;
	}
	public Long getIndentid() {
		return indentid;
	}
	public void setIndentid(long indentid) {
		this.indentid = indentid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public WsMtProduct getProduct() {
		return product;
	}
	public void setProduct(WsMtProduct product) {
		this.product = product;
	}
	
	public Integer getCount() {
		return count;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
