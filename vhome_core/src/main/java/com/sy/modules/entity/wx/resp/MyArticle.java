package com.sy.modules.entity.wx.resp;

import com.sy.commons.entity.ParentEntity;

public class MyArticle extends ParentEntity {

	private static final long serialVersionUID = -772480792187657208L;
	// 图文标题
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;
	// 多图文关联ID
	private long mutiArticle_id;// 若是单图文，那么mutiArticle_id就是自身
	// 图文详情
	private String detail;
	// 标识,1:图文详情页显示封面图片,0：不显示
	private int flag;
	// 文本内容
	private String content;
	
	private int atype;//图文外链类型 1：普通链接 ，2：业务模块

	public int getAtype() {
		return atype;
	}

	public void setAtype(int atype) {
		this.atype = atype;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
