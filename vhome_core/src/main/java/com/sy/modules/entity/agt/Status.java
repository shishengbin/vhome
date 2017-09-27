package com.sy.modules.entity.agt;


public enum Status {
	禁用(0),启用(1);
	
	private int status;
	private Status(int i){
		this.status = i;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static Status getStatus(int status){
		if(status==0) return Status.禁用; else return Status.启用;
	}
}
