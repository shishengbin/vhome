package com.sy.modules.entity.ws;

import java.util.List;

import com.sy.commons.entity.ParentEntity;
import com.sy.modules.entity.sys.SysUser;

/**
 * 产品类
 * @author sss
 */
public class WsMtProduct extends ParentEntity {

	private static final long serialVersionUID = -2371388615415514785L;

	private Long picId;
	// 所属城市
	private String productCity;
	//分类id
	private long tid;
	// 分类名称
	private String tname;
	// 产品名称
	private String productName;
	//产品图片途径
	private String propicpath;
	// 产品成本
	private Double productPrice;
	// 市场价
	private Double bazaarPrice;
	// 提升率
	private Double productFavorable;
	//保质期
	private int expdate;
	//生产日期
	private String producedate;
	//产地
	private String productaddress;
	// 备注
	private String productRemark;
	// 用户
	private SysUser user;
	// 上下架状态
	private Integer deleteStatus; //1：上架 0：下架
	// 产品图片
	private List<WsMtPicture> picture;
	// 产品图片总数
	private Integer pictureCount;
	// 商品总数
	private Integer count;
	// 产品图片路径
	private String picPath;
	//供应商id
	private Long suppid;
	//供应商名称
	private String suppcomany;
	//规格id
	private Long specid;
	//规格名称
	private String specname;
	//产品状态
	private Integer salesStatus;//1:正常 0：促销
	

	public String getSpecname() {
		return specname;
	}

	public void setSpecname(String specname) {
		this.specname = specname;
	}

	public Long getSpecid() {
		return specid;
	}

	public void setSpecid(Long specid) {
		this.specid = specid;
	}

	public Long getSuppid() {
		return suppid;
	}

	public void setSuppid(Long suppid) {
		this.suppid = suppid;
	}

	public String getSuppcomany() {
		return suppcomany;
	}

	public void setSuppcomany(String suppcomany) {
		this.suppcomany = suppcomany;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getBazaarPrice() {
		return bazaarPrice;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public List<WsMtPicture> getPicture() {
		return picture;
	}

	public Integer getPictureCount() {
		return pictureCount;
	}

	public String getProductCity() {
		return productCity;
	}

	public Double getProductFavorable() {
		return productFavorable;
	}

	public String getProductName() {
		return productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public SysUser getUser() {
		return user;
	}

	public void setBazaarPrice(Double bazaarPrice) {
		this.bazaarPrice = bazaarPrice;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public void setPicture(List<WsMtPicture> picture) {
		this.picture = picture;
	}

	public void setPictureCount(Integer pictureCount) {
		this.pictureCount = pictureCount;
	}

	public void setProductCity(String productCity) {
		this.productCity = productCity;
	}

	public void setProductFavorable(Double productFavorable) {
		this.productFavorable = productFavorable;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	public int getExpdate() {
		return expdate;
	}

	public void setExpdate(int expdate) {
		this.expdate = expdate;
	}

	public String getProducedate() {
		return producedate;
	}

	public void setProducedate(String producedate) {
		this.producedate = producedate;
	}

	public String getProductaddress() {
		return productaddress;
	}

	public void setProductaddress(String productaddress) {
		this.productaddress = productaddress;
	}
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}
	public String getPropicpath() {
		return propicpath;
	}

	public void setPropicpath(String propicpath) {
		this.propicpath = propicpath;
	}

	public Integer getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(Integer salesStatus) {
		this.salesStatus = salesStatus;
	}



}
