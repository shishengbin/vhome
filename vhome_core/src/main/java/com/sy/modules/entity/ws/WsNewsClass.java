package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 新闻分类
 * @author LiuCheng
 *
 */
public class WsNewsClass extends ParentEntity {

	private static final long serialVersionUID = -2371388615415514785L;
	private String remark;//类别名称
	private Integer count;//新闻总数
	
	public Integer getCount() {
		return count;
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
