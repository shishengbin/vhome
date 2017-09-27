package com.sy.modules.entity.wx;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Kword {
	private long Kwd_id;
	private String Kwd_name;
	private int article_type;
	private long account_id;
	private String content;
	private String title;
	private String update_time;
	public long getKwd_id() {
		return Kwd_id;
	}
	public void setKwd_id(long kwd_id) {
		Kwd_id = kwd_id;
	}
	public String getKwd_name() {
		return Kwd_name;
	}
	public void setKwd_name(String kwd_name) {
		Kwd_name = kwd_name;
	}
	public int getArticle_type() {
		return article_type;
	}
	public void setArticle_type(int article_type) {
		this.article_type = article_type;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间   
		this.update_time = sdf.format(update_time);
	}
	
}
