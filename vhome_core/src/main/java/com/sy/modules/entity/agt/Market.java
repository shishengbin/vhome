package com.sy.modules.entity.agt;

/**
 * 第三方商城
 * @describe  
 * @author LiuCheng
 * 2013年10月17日 下午2:02:05
 * @version v1.0
 */
public class Market {
	private Long id;
	private String name;
	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
