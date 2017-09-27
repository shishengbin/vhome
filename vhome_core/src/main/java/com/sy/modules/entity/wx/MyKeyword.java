package com.sy.modules.entity.wx;
import com.sy.commons.entity.ParentEntity;
public class MyKeyword extends ParentEntity {

	private static final long serialVersionUID = -8107339035559360449L;

	//关键字名称
	private String keyname;
	//回复的类型
	private int article_type;
	//回复的内容id关联article
	private long article_id;
	//账号id
	private long account_id;
	//备注
	//回复标题
	private String title;
	
	private String content;//文本内容
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String remark;

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public int getArticle_type() {
		return article_type;
	}

	public void setArticle_type(int article_type) {
		this.article_type = article_type;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
