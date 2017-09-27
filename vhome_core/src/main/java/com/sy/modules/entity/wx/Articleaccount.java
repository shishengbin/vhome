package com.sy.modules.entity.wx;

import com.sy.commons.entity.ParentEntity;

/**
 *图文与公众号关联表
 *@author sss
 *@Date 2014-8-25 
 */
public class Articleaccount extends ParentEntity {

	private static final long serialVersionUID = 5204165091561739339L;

	private long artcleId;// 图文id

	// 多图文关联ID
	private long mutiArticle_id;// 若是多图文，那么mutiArticle_id就是自身

	private long accountId;// 公众号id

	public long getArtcleId() {
		return artcleId;
	}

	public void setArtcleId(long artcleId) {
		this.artcleId = artcleId;
	}

	public long getMutiArticle_id() {
		return mutiArticle_id;
	}

	public void setMutiArticle_id(long mutiArticle_id) {
		this.mutiArticle_id = mutiArticle_id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
