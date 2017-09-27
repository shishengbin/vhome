package com.sy.modules.entity.wx;

import com.sy.commons.entity.ParentEntity;

public class FirstSubscribe extends ParentEntity {

	private static final long serialVersionUID = -4316216049978136655L;
	
	//回复的类型
	private int msg_type;
	//回复的内容id关联article
    private long article_id;
    //账号id
    private long account_id;
    //备注
    private String remark;
    
	public int getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}
	public long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
