package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;


//轮播广告图
public class WsPublic extends ParentEntity {

	private static final long serialVersionUID = 1L;
	private Long publicid;
	private String publicname;
	private String publicremark;
	private String publicpic;// 图片路径

	public WsPublic() {
	}

	public WsPublic(Long publicid, String publicname, String publicremark) {
		this.publicid = publicid;
		this.publicname = publicname;
		this.publicremark = publicremark;
	}

	public Long getPublicid() {
		return publicid;
	}

	public void setPublicid(Long publicid) {
		this.publicid = publicid;
	}

	public String getPublicname() {
		return publicname;
	}

	public void setPublicname(String publicname) {
		this.publicname = publicname;
	}

	public String getPublicremark() {
		return publicremark;
	}

	public void setPublicremark(String publicremark) {
		this.publicremark = publicremark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPublicpic() {
		return publicpic;
	}

	public void setPublicpic(String publicpic) {
		this.publicpic = publicpic;
	}
}
