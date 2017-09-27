package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

public class SmallPic extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	private Long   smallPic_id ;
	private String smallPic_name;
	private String smallPic_url ;
	private byte[] smallPic_byte ;
	public Long getSmallPic_id() {
		return smallPic_id;
	}
	public void setSmallPic_id(Long smallPic_id) {
		this.smallPic_id = smallPic_id;
	}
	public String getSmallPic_name() {
		return smallPic_name;
	}
	public void setSmallPic_name(String smallPic_name) {
		this.smallPic_name = smallPic_name;
	}
	public String getSmallPic_url() {
		return smallPic_url;
	}
	public void setSmallPic_url(String smallPic_url) {
		this.smallPic_url = smallPic_url;
	}
	public byte[] getSmallPic_byte() {
		return smallPic_byte;
	}
	public void setSmallPic_byte(byte[] smallPic_byte) {
		this.smallPic_byte = smallPic_byte;
	}
}
