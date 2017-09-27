package com.sy.modules.entity.ws;

/**
 * 
* @ClassName: OaIndentStatus 
* @Description:(交易状态) 
* @author LiuCheng
* @date 2013年12月11日 下午4:04:06 
*
 */
public enum IndentStatus {
	未付款(0),待发货(1),待收货(2),交易关闭(3),交易完成(4);
	
	public static Integer getNumStatus(String s){
		IndentStatus d = null;
		try {
			d = IndentStatus.valueOf(s);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return d.status;
	}
	public static IndentStatus getStatus(Integer i){
		switch(i){
		case 0:
			return IndentStatus.未付款;
		case 1:
			return IndentStatus.待发货;
		case 2:
			return IndentStatus.待收货;
		case 3:
			return IndentStatus.交易关闭;
		case 4:
			return IndentStatus.交易完成;
			default:
				return null;
		}
	}
	private Integer status;
	
	private IndentStatus(Integer i){
		this.status = i;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
