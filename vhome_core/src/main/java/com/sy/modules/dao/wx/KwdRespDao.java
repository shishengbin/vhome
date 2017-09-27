package com.sy.modules.dao.wx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.entity.wx.Kword;
import com.sy.modules.entity.wx.resp.Article;
import com.sy.modules.entity.wx.resp.TextMessageResp;

@Component
@MyBatisRepository
public interface KwdRespDao {

	public TextMessageResp findRespByKw(Map<String, Object> map);

	//根据关键字查询图文
	public List<Article> findArticleRespByKw(Map<String, Object> map);
    
	public void saveKeyword(Kword kw);

	public void saveKeywordContent(Map<String, Object> map);

	public List<Kword> findKwResp(long userid);

	public void deleteKw(Map<String, Object> map);

	public void deleteKwContent(int kwd_id);

	public Kword findOneKw(long kwd_id);

	public void updateKw(Kword kword);

	public void updateKwContent(Kword kword);

	public void updateKwRelID(Map<String, Object> map);
	
}
