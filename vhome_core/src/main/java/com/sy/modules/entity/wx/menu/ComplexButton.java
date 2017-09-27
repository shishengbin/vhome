package com.sy.modules.entity.wx.menu;

/**
 * 复杂按钮（父按钮）
 * 
 * @author sss
 * @date 2013-08-08
 */
public class ComplexButton extends Button {

	private Button[] sub_button;  //包含有二级菜单项的一级菜单

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

}