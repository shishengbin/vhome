package com.sy.modules.service.wx;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.GlobalConstants;
import com.sy.modules.dao.wx.FirstSubscribeDao;
import com.sy.modules.dao.wx.KwdRespDao;
import com.sy.modules.dao.wx.MsgTypeDao;
import com.sy.modules.dao.wx.MyKeywordDao;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.resp.MyArticle;
import com.sy.modules.entity.wx.resp.NewsMessage;
import com.sy.modules.entity.wx.resp.TextMessageResp;
import com.sy.modules.utils.MessageUtil;


/** 
 * 核心服务类 
 *  
 * @author sss
 * @date 2013-05-20 
 */  
@Component
public class CoreService {  
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
	
	@Autowired
	private KwdRespDao kwdRespDao;
	@Autowired
	private MsgTypeDao msgTypeDao;
	@Autowired
	private HumanService humanService;
	
	@Autowired
	private MyKeywordDao mykeywordao;
	
	@Autowired
	private AccountService accountservice;
	
	@Autowired
	private FirstSubscribeDao firstsubscribedao;
	
	String respContent = "请求处理异常，请稍候尝试！"; 
    public String processRequest(HttpServletRequest request,long userid) {  
        String respMessage = null; 
        Account account=null;
     // 默认返回的文本消息内容      
        try {             
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName"); 
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
            
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	//消息内容
                String content=requestMap.get("Content");           
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("userid", userid);
                map.put("content", content);
                
                 //获得返回的消息类型；0:文本；1：单图文：;2:多图文；3：语音；4：视频
            	int kwMsgType=msgTypeDao.findKwMType(map);
            	switch(kwMsgType){
            	case 0:
            		// 回复文本消息           		
                    respMessage = MessageUtil.textMessageToXml(responseText(fromUserName,toUserName,map));  
            		break;
            	case 1:	
                    respMessage = MessageUtil.newsMessageToXml(responseSinArticle(fromUserName,toUserName,map));
            		break;
            	case 2:
                    respMessage = MessageUtil.newsMessageToXml(responseManArticle(fromUserName,toUserName,map));
            		break;
            	case 3:
            		break;
            	case 4:
            		break;
            	}   	
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                //respContent = "您发送的是图片消息！";  
            	
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅                  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                	//判断是否认证：默认0没认证，1代表认证
                	account =accountservice.findAccountInfo(userid);
                	if(account.getConfirm()==1){
                		humanService.saveHuman(fromUserName,userid);//首次关注时获取用户信息，微信认证的帐号才有此接口功能
                	}else{
                		//获得返回的消息类型；0:文本；1：图文：2:语音；3：视频
                    	int frMsgType=msgTypeDao.findFRMType(userid);
                    	
                    	switch(frMsgType){
                    	case 0:
                    		// 回复文本消息  
                    		//System.out.println("进入文本订阅回复");                      
                            respMessage = MessageUtil.textMessageToXml(responseTextForsub(fromUserName,toUserName,userid));  
                    		break;
                    	case 1:
                    		//System.out.println("进入单图文订阅回复");           		
                            respMessage = MessageUtil.newsMessageToXml(responseArticleForsub(fromUserName,toUserName,userid));
                    		break;
                    	case 2:
                    		//respMessage = MessageUtil.newsMessageToXml(responseArticleForsub(fromUserName,toUserName,userid));
                    		break;
                    	case 3:
                    		break;
                    	case 4:
                    		break;
                    	}  
                	}
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    //  取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {   //||eventType.equals(MessageUtil.EVENT_TYPE_VIEW)
                    String content=requestMap.get("EventKey");
                    Map<String,Object> map=new HashMap<String,Object>();
                    map.put("userid", userid);
                    map.put("content", content);                    
                    //获得返回的消息类型；0:文本；1：单图文：;2:多图文；3：语音；4：视频
                	int kwMsgType=msgTypeDao.findKwMType(map);
                	switch(kwMsgType){
                	case 0:
                		// 回复文本消息           		
                        respMessage = MessageUtil.textMessageToXml(responseText(fromUserName,toUserName,map));  
                		break;
                	case 1:	
                        respMessage = MessageUtil.newsMessageToXml(responseSinArticle(fromUserName,toUserName,map));
                		break;
                	case 2:
                        respMessage = MessageUtil.newsMessageToXml(responseManArticle(fromUserName,toUserName,map));
                		break;
                	case 3:
                		break;
                	case 4:
                		break;
                	}  	                     
                }  
            }             
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }
  //关键字回复使用,纯文本
    public TextMessageResp responseText(String fromUserName,String toUserName,Map<String,Object> map){
        TextMessageResp textMessage = new TextMessageResp();  
        textMessage.setToUserName(fromUserName);  
        textMessage.setFromUserName(toUserName);  
        textMessage.setCreateTime(new Date().getTime());  
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        textMessage.setFuncFlag(0);  
        respContent=kwdRespDao.findRespByKw(map).getContent(); 
		textMessage.setContent(respContent);
		return textMessage;
    }
    //关键字回复单图文
    public NewsMessage responseSinArticle(String fromUserName,String toUserName,Map<String,Object> map){
    	List<MyArticle> articleList = new ArrayList<MyArticle>();  
		NewsMessage newsMessage = new NewsMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        newsMessage.setFuncFlag(0);  
        articleList =mykeywordao.findKeyWordBySinArticle(map);
        for(MyArticle m :articleList){
        	m.setPicUrl(GlobalConstants.DB_IMAGE_FILE+"/"+m.getPicUrl());
        	m.setUrl(GlobalConstants.SERVER_PROJECT_URL+"/"+m.getUrl());
        }
        newsMessage.setArticles(articleList);
        newsMessage.setArticleCount(articleList.size());
        return newsMessage;
    }
    
    //关键字回复多图文
    public NewsMessage responseManArticle(String fromUserName,String toUserName,Map<String,Object> map){
    	List<MyArticle> articleList = new ArrayList<MyArticle>();  
		NewsMessage newsMessage = new NewsMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        newsMessage.setFuncFlag(0);
        long manarticleId=mykeywordao.findKeyWordForManArticleId(map);
        articleList =mykeywordao.findKeyWordByManyArticle(manarticleId);
        for(MyArticle m :articleList){
        	m.setPicUrl(GlobalConstants.DB_IMAGE_FILE+"/"+m.getPicUrl());
        	m.setUrl(GlobalConstants.SERVER_PROJECT_URL+"/"+m.getUrl());
        }
        newsMessage.setArticles(articleList);
        newsMessage.setArticleCount(articleList.size());
        return newsMessage;
    }
    
    
    
    //订阅回复文本
    public TextMessageResp responseTextForsub(String fromUserName,String toUserName,long userid){
    	TextMessageResp textMessage = new TextMessageResp();  
        textMessage.setToUserName(fromUserName);  
        textMessage.setFromUserName(toUserName);  
        textMessage.setCreateTime(new Date().getTime());  
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        textMessage.setFuncFlag(0);  
		respContent=firstsubscribedao.findContentByid(userid).getContent();
		textMessage.setContent(respContent);
		return textMessage;
    }
   
    //订阅回复图文
    public NewsMessage responseArticleForsub(String fromUserName,String toUserName,long userid){
    	List<MyArticle> articleList = new ArrayList<MyArticle>();  
		NewsMessage newsMessage = new NewsMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        newsMessage.setFuncFlag(0); 
        articleList=firstsubscribedao.findArticleByid(userid);
        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
    }
  
}  
