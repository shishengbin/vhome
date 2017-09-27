package com.sy.modules.dao.ws;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsMtPicture;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProductPicture;
import com.sy.modules.entity.ws.WsProductUser;

/**
 * @author lc 2013-8-27
 **/
@Component
@MyBatisRepository
public interface WsMtProductDao extends ParentDao<WsMtProduct, Long> {

	public List<WsMtPicture> findAllProductPic(long proid);

	// 分页查询所有产品
	List<WsMtProduct> findAllProductByPage(Map<String, Object> map);

	// 分页查询所有产品 按名称分组
	List<WsMtProduct> findAllProductByPageGroupByName(Map<String, Object> map);

	// 删除与某一产品相关的所有图片
	void deleteProductIdPicture(Long product_id);

	// 清除产品与图片关系
	void deleteProductPicture(Long id);

	// 清除产品与用户关系
	void deleteProductUser(Long id);

	// 注册产品与图片信息关联
	void addProductPicture(WsProductPicture wpp);

	// 注册产品与企业信息关联
	void addProductUser(WsProductUser wpu);

	/*
	 * 查看指定企业产品 及加上查询条件
	 */
	Long count(Map<String, Object> map);

	List<WsMtPicture> findAllPictureByPid(Map<String, Object> map);

	WsMtProduct findProductById(Map<String, Object> map);
	
	//商品下架
	void downProduct(Long id);
	
	//商品上架
	void upProduct(Long id);
	
	//商品促销状态
	void doSales(Long id);
	
	//商品正常状态
	void doNormal(Long id);
	
	//-------------------------wap--------------------------------------
	//手机端显示所有上架的商品
	List<WsMtProduct> findAllUpProductByJson(Map<String, Object> map);
	
	//查询促销商品
	List<WsMtProduct> findAllSalesProductByJson(Map<String, Object> map);
}
