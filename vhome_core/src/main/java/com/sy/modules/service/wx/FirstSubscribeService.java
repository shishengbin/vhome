package com.sy.modules.service.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.wx.FirstSubscribeDao;
import com.sy.modules.entity.wx.FirstSubscribe;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.entity.wx.resp.TextMessageResp;

@Component
public class FirstSubscribeService extends
		AbstractService<FirstSubscribe, Long, FirstSubscribeDao> {

	@Autowired
	private FirstSubscribeDao firstsubscribedao;

	@Override
	protected FirstSubscribeDao getDao() {
		return firstsubscribedao;
	}

	// 根据公众号id查询首次订阅的信息

	public FirstSubscribe findFirstSubscribeInfo(long accountid) {

		return firstsubscribedao.findFirstSubscribeInfo(accountid);
	}

	// 根据公众号id查询当前是否存在首次订阅的信息

	public int findSubscribeCount(long accountid) {

		return firstsubscribedao.findSubscribeCount(accountid);
	}
	
	public TextMessageResp findContentByid(long userid){
		return firstsubscribedao.findContentByid(userid);
	}
	
	public List<MyArticle> findArticleByid(long userid){
		return firstsubscribedao.findArticleByid(userid);
	}

}
