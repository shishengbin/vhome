package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

public class BgPicture extends ParentEntity{
	
	private static final long serialVersionUID = 1L;
	private Long   bgid ;
	private String bgname ;
	private String bgremark ;
	private byte[] bgpic ;
	private int bg_use;
	public BgPicture(){}
	public int getBg_use() {
		return bg_use;
	}
	public void setBg_use(int bg_use) {
		this.bg_use = bg_use;
	}
	public BgPicture(Long id,String name,String remark,int bg_use){
		this.bgid = id;
		this.bgname = name;
		this.bgremark = remark;
		this.bg_use=bg_use;
	}
	public Long getBgid() {
		return bgid;
	} 
	public void setBgid(Long bgid) {
		this.bgid = bgid;
	} 
	public String getBgname() {
		return bgname;
	} 
	public void setBgname(String bgname) {
		this.bgname = bgname;
	} 
	public String getBgremark() {
		return bgremark;
	} 

	public void setBgremark(String bgremark) {
		this.bgremark = bgremark;
	} 
	public byte[] getBgpic() {
		return bgpic;
	} 
	public void setBgpic(byte[] bgpic) {
		this.bgpic = bgpic;
	} 
}
