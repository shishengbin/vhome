package com.sy.modules.dao.wx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.entity.wx.FirstResp;
import com.sy.modules.entity.wx.resp.Article;
import com.sy.modules.entity.wx.resp.TextMessageResp;

@Component
@MyBatisRepository
public interface FirstRespDao {
	//查找文本内容
	public TextMessageResp findContentByid(long userid);

	public List<Article> findArticleByid(long userid);

	public void saveFirstResp(FirstResp firstResp);

	public int count(FirstResp firstResp);

	public FirstResp findFirstResp(Map<String, Object> map);

	public void updateFirstRespstate(long userid);

	public void updateFirstResp(Map<String, Object> map);
	
}
