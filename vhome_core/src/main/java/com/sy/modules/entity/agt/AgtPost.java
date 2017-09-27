package com.sy.modules.entity.agt;
import com.sy.commons.entity.ParentEntity;
/**
 * 公告信息实体类
 * @author zw
 * 2013-10-16
 * */
public class AgtPost extends ParentEntity {
	private static final long serialVersionUID = 1L;
    private String postname;  //公告名称
	private String postcontent; //公告内容
	
	
    public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
}
