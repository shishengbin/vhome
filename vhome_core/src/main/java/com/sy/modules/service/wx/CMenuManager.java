package com.sy.modules.service.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.dao.wx.MenuDao;
import com.sy.modules.entity.wx.menu.CommonButton;
import com.sy.modules.entity.wx.menu.ComplexButton;
import com.sy.modules.entity.wx.menu.Menu;

@Component
public class CMenuManager {
	@Autowired
	private MenuDao menuDao;
	public Menu getMyMenu(long userid){
		//查找所有父菜单
		ComplexButton[] parentbtnlist= menuDao.findParentMenu(userid);
		for(int i=0;i<parentbtnlist.length;i++){		
			CommonButton[] combtn=menuDao.findSonMenusByFatId(parentbtnlist[i].getBt_id(), userid);	
			parentbtnlist[i].setSub_button(combtn);
		}
		//封装自定义菜单
		Menu menu = new Menu();
		menu.setButton(parentbtnlist);
		return menu;
	
	}
}
