package com.sy.modules.entity.ws;

/**
* @ClassName: LgsState 
* @Description:(邮费支付方式) 
* @author LiuCheng
* @date 2013年12月11日 下午2:42:56 
*
*/
public enum LgsState {
	包邮(0),不包邮(1),无邮费(2);
	
	public static Integer getNumStatus(String s){
		LgsState d = null;
		try {
			d = LgsState.valueOf(s);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return d.status;
	}
	public static LgsState getStatus(Integer i){
		switch(i){
		case 0:
			return LgsState.包邮;
		case 1:
			return LgsState.不包邮;
		case 2:
			return LgsState.无邮费;
		default:
			return null;
		}
	}
	private Integer status;
	
	private LgsState(Integer i){
		this.status = i;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
