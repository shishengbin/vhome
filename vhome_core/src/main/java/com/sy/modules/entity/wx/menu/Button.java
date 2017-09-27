package com.sy.modules.entity.wx.menu;

/**
 * 按钮的基类
 * 
 * @author sss
 * @date 2013-08-08
 */
public class Button {
	private long bt_id;  //菜单id
	private String name; //菜单名称
    private String type; //菜单类型  
    private String key;  //关键词,click类型对应
    private String url;  //view跳转路径
    private int status;  //状态
    private String remark;
    private long account_id; // 公众号id
    
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getBt_id() {
		return bt_id;
	}
	public void setBt_id(long bt_id) {
		this.bt_id = bt_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
