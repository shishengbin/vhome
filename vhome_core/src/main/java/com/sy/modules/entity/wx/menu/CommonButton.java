package com.sy.modules.entity.wx.menu;

/**
 * 普通按钮（子按钮）
 * 
 * @author sss
 * @date 2013-08-08
 */
public class CommonButton extends Button {

	private long fat_bt_id; // 父菜单id

	public long getFat_bt_id() {
		return fat_bt_id;
	}

	public void setFat_bt_id(long fat_bt_id) {
		this.fat_bt_id = fat_bt_id;
	}

}
