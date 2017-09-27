package com.sy.modules.entity.wx.resp;

import java.util.List;

/**
 * 文本消息
 * 
 * @author sss
 * @date 2013-05-19
 */
public class NewsMessage extends BaseMessageResp {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;

	//private MyArticle myarticle;

	// 多条图文消息信息，默认第一个item为大图
	private List<MyArticle> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<MyArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<MyArticle> articles) {
		Articles = articles;
	}
}
