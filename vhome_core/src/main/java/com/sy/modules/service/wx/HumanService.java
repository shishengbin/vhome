package com.sy.modules.service.wx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.dao.wx.HumanDao;
import com.sy.modules.entity.wx.AccessToken;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.Human;
import com.sy.modules.service.ws.WsHumanService;
import com.sy.modules.utils.WeixinUtil;

@Component
public class HumanService {
	@Autowired
	private AccountService accountService;
	@Autowired
	private WsHumanService wshumanService;
	@Autowired
	private HumanDao humanDao;
	public Human getHuman(String openid,long userid) {
		Account account=accountService.findUserAppinfo(userid);
		int state=accountService.getATState(userid);//1代表access_token失效，0代表有效
        // 第三方用户唯一凭证  
        String appId = account.getAppId();  
        // 第三方用户唯一凭证密钥 
        String appSecret = account.getAppSecret();  
        // 调用接口获取access_token
        if(state==1){
            AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
            Map<String,Object> map=new HashMap<String,Object>();
        	map.put("APPID", appId);
            map.put("access_token",at.getToken());
            accountService.saveAccessToken(map);
            if (null != at) {
                // 调用接口创建菜单  
            	return WeixinUtil.getHuman(openid, at.getToken(),userid);
            }
            return null;
        }else{
        	return WeixinUtil.getHuman(openid, account.getAccess_token(),userid);
        }
  	
	}
	
	public void saveHuman(String openid,long userid){
		int count=humanDao.count(openid);
		if(count==0){
			humanDao.save(getHuman(openid,userid));
			wshumanService.wxRegister(openid,userid);
			//System.out.println("会员保存成功！");
		}
	}
}
