package com.sy.modules.entity.wx.resp;

import com.sy.commons.entity.ParentEntity;

/**
 * 图文model
 * 
 * @author sss
 * @date 2013-05-19
 */
public class Article extends ParentEntity{

	private static final long serialVersionUID = -4370048575657773081L;
	private long article_id;
	// 图文消息名称
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;

	private long mutiArticle_id; // 多图文关联ID

	private String content;//文本内容

	public long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return null == Description ? "" : Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return null == Url ? "" : Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public long getMutiArticle_id() {
		return mutiArticle_id;
	}

	public void setMutiArticle_id(long mutiArticle_id) {
		this.mutiArticle_id = mutiArticle_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}