package com.sy.modules.entity.ws;

import java.util.Date;
import java.util.List;

import com.sy.modules.entity.sys.SysUser;

/**
 * 
* @ClassName: WsIndent 
* @Description:(支付方式) 
* @author LiuCheng
* @date 2014年3月4日 上午11:41:50 
*
 */
public class WsPayment {
	private Long id;
	private SysUser user;
	private String name;
	private Usable state;
	private Date createTime = new Date();
	private List<Mark> mark;
	public List<Mark> getMark() {
		return mark;
	}
	public void setMark(List<Mark> mark) {
		this.mark = mark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getState() {
		if(this.state!=null){
			return state.getStatus();
		}else{
			return -1;
		}
	}
	public SysUser getUser() {
		return user;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setState(Integer state) {
		this.state = Usable.getStatus(state);
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	
}
