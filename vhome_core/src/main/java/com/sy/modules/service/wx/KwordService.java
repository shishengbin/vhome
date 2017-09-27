package com.sy.modules.service.wx;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.dao.wx.ArticleDao;
import com.sy.modules.dao.wx.KwdRespDao;
import com.sy.modules.entity.wx.Kword;

@Component
public class KwordService {
	@Autowired
	private KwdRespDao kwdRespDao;
	@Autowired
	private ArticleDao articleDao;
	
	public void saveKeyword(Kword kw) {
		kwdRespDao.saveKeyword(kw);
	}
	public void saveKeywordContent(Map<String, Object> map) {
		kwdRespDao.saveKeywordContent(map);
	}
	public List<Kword> findKwResp(long userid) {		
		return kwdRespDao.findKwResp(userid);
	}
	public void deleteKw(Map<String, Object> map) {
		kwdRespDao.deleteKw(map);
		articleDao.updataArticleMsgId(map);
	}
	public Kword findOneKw(long kwd_id) {
		return kwdRespDao.findOneKw(kwd_id);
	}
	public void updateKw(Kword kword) {
		kwdRespDao.updateKw(kword);
		kwdRespDao.updateKwContent(kword);
		
	}
	public void updateKwForArticle(Kword kword, Map<String, Object> map) {
		kwdRespDao.updateKw(kword);
		//kwdRespDao.updateKwRelID(map);
		articleDao.updateArticleForRelID(map);
	}
	public void updateKwRelID(Map<String, Object> map) {
		//kwdRespDao.updateKwRelID(map);
		articleDao.updateArticleForRelID(map);
	}
	public void updateKwForMutiArticle(Kword kword, Map<String, Object> map) {
		kwdRespDao.updateKw(kword);
		articleDao.updateMutiArticleForRelID(map);
	}	
	
}
