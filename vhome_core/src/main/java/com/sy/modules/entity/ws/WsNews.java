package com.sy.modules.entity.ws;

import com.sy.commons.entity.ParentEntity;

/**
 * 行业资讯
 * 新闻
 * @author LiuCheng
 *
 */
public class WsNews  extends ParentEntity{
	private static final long serialVersionUID = 1L;
	private String newsTitle;//新闻标题
	private String newsContent;//新闻内容
	private String newsAuthor;//新闻作者
	private String newsRemark;//新闻logo路径
	private WsNewsClass newsClass; 
	
	public WsNewsClass getNewsClass() {
		return newsClass;
	}
	public void setNewsClass(WsNewsClass newsClass) {
		this.newsClass = newsClass;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public String getNewsContent() {
		return newsContent;
	}

	public String getNewsRemark() {
		return newsRemark;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public void setNewsRemark(String newsRemark) {
		this.newsRemark = newsRemark;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
}
