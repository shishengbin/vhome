package com.sy.modules.entity.ws;

/**
 * 
* @ClassName: Status 
* @Description: TODO(状态是否可以) 
* @author LiuCheng
* @date 2014年3月4日 下午2:02:19 
*
 */
public enum Usable {
	禁用(0),启用(1);
	
	public static Integer getNumStatus(String s){
		Usable d = null;
		try {
			d = Usable.valueOf(s);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return d.status;
	}
	public static Usable getStatus(Integer i){
		if(i==0){
			return Usable.禁用;
		}else{
			return Usable.启用;
		}
	}
	private Integer status;
	
	private Usable(Integer i){
		this.status = i;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
