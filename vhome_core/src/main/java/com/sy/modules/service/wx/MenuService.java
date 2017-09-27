package com.sy.modules.service.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.dao.wx.MenuDao;
import com.sy.modules.entity.wx.AccessToken;
import com.sy.modules.entity.wx.Account;
import com.sy.modules.entity.wx.menu.Button;
import com.sy.modules.entity.wx.menu.CommonButton;
import com.sy.modules.entity.wx.menu.ComplexButton;
import com.sy.modules.utils.WeixinUtil;

@Component
public class MenuService {
	private Logger log = LoggerFactory.getLogger(MenuService.class);
	@Autowired
	private CMenuManager cMenuManager;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private AccountService accountService;

	public int createM(long userid) {
		Account account = accountService.findUserAppinfo(userid);
		int state = accountService.getATState(userid);// 1代表access_token失效，0代表有效
		// 第三方用户唯一凭证
		String appId = account.getAppId();
		// 第三方用户唯一凭证密钥
		String appSecret = account.getAppSecret();
		// 调用接口获取access_token
		if (state == 1) {
			AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("APPID", appId);
			map.put("access_token", at.getToken());
			accountService.saveAccessToken(map);
			if (null != at) {
				// 调用接口创建菜单
				return WeixinUtil.createMenu(cMenuManager.getMyMenu(userid),
						at.getToken());
			}
		} else {
			return WeixinUtil.createMenu(cMenuManager.getMyMenu(userid),
					account.getAccess_token());
		}
		return -1;
	}

	// 保存子菜单
	public void saveMenu(List<CommonButton> cb) {
		menuDao.saveMenu(cb);
	}

	// 保存父菜单
	public void savefatMenu(ComplexButton commonButton) {
		menuDao.savefatMenu(commonButton);
	}

	// 添加子菜单
	public void saveSonMenu(CommonButton commonBtn) {
		menuDao.saveSonMenu(commonBtn);
	}

	public List<CommonButton> findMenu(long userid) {
		return menuDao.findMenu(userid);
	}

	// 修改菜单
	public void updateMenu(Button lcb) {
		menuDao.updateMenu(lcb);
	}

	public void delSonMenu(long bt_id) {
		menuDao.delSonMenu(bt_id);
	}

	public void delFatMenu(long bt_id) {
		menuDao.delFatMenu(bt_id);
	}

	// ----------------------------------------------------------------------
	// 查询主菜单
	public ComplexButton[] findParentMenu(long accountid) {
		return menuDao.findParentMenu(accountid);
	}

	// 查询主菜单的总数
	public int findParentMenuCount(long accountid) {
		return menuDao.findParentMenuCount(accountid);
	}

	// 根据id查询菜单信息
	public CommonButton findMenuByid(long id) {

		return menuDao.findMenuByid(id);
	}

	public CommonButton[] findSonMenusByFatId(long fatid, long accountid) {
		return menuDao.findSonMenusByFatId(fatid, accountid);
	}

	// 查询子菜单总数
	public int findSonMenuCountByParent(@Param("fatid") long fatid,
			@Param("accountid") long accountid) {
		return menuDao.findSonMenuCountByParent(fatid, accountid);
	}
}
