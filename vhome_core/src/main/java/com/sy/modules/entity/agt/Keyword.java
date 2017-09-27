package com.sy.modules.entity.agt;

import com.sy.commons.entity.ParentEntity;


public class Keyword extends ParentEntity{

	private static final long serialVersionUID = 1L;
	private String kw_number ; //关键词编号
	private String kw_name ;   //关键词名称
	private Long kw_type ;     //关键词类型
	private Integer kw_status ;//关键词状态:0：未注册 ，1：已注册，2：禁止注册
	private String  tname ;     //类型名称
	private Integer tprice ;    //类型价格
	private KwType kwType;
	

	public KwType getKwType() {
		return kwType;
	}
	public void setKwType(KwType kwType) {
		this.kwType = kwType;
	}
	public Keyword(){}
	public Keyword(Long id,Integer status){
		this.id = id;this.kw_status=status;
	}
	public Keyword(String key){
		this.kw_name = key;
	}
	public String getKw_name() {
		return kw_name;
	}
	public String getKw_number() {
		return kw_number;
	}

	public Integer getKw_status() {
		return kw_status;
	}

	public Long getKw_type() {
		return kw_type;
	}

	public String getTname() {
		return tname;
	}

	public Integer getTprice() {
		return tprice;
	}

	public void setKw_name(String kw_name) {
		this.kw_name = kw_name;
	}

	public void setKw_number(String kw_number) {
		this.kw_number = kw_number;
	}
	
	public void setKw_status(Integer kw_status) {
		this.kw_status = kw_status;
	}

	public void setKw_type(Long kw_type) {
		this.kw_type = kw_type;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public void setTprice(Integer tprice) {
		this.tprice = tprice;
	}

}
