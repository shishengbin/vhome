package com.sy.modules.dao.wx;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.wx.FirstSubscribe;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.entity.wx.resp.TextMessageResp;

@Component
@MyBatisRepository
public interface FirstSubscribeDao extends ParentDao<FirstSubscribe, Long> {
	
	//根据公众号id查询首次订阅的信息
	
	public FirstSubscribe findFirstSubscribeInfo(long accountid);
	
	//根据公众号id查询当前是否存在首次订阅的信息
	
	public int findSubscribeCount(long accountid);
	
	public TextMessageResp findContentByid(long userid);
	
	public List<MyArticle> findArticleByid(long userid);

}
