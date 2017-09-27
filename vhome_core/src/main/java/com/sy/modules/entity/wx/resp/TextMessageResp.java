package com.sy.modules.entity.wx.resp;

/** 
 * 文本消息 
 *  
 * @author sss 
 * @date 2013-05-19 
 */  
public class TextMessageResp extends BaseMessageResp {  
    // 回复的消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}  