package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


/**
 * 产品分类中间表
 */
public class WsProductAndType extends ParentEntity {

	private static final long serialVersionUID = -6584771069260470854L;

	private Long pid;// 产品id
	private Long tid;// 分类id

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

}
