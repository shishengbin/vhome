package com.sy.modules.service.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsMtProductDao;
import com.sy.modules.entity.ws.WsMtPicture;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProductUser;

/**
 *产品业务层
 *@author LiuCheng 2013-8-27 
 */
@Component
public class WsMtProductService extends AbstractService<WsMtProduct,Long,WsMtProductDao>{

	@Autowired
	private WsMtProductDao productdao;

	@Override
	protected WsMtProductDao getDao() {
		return productdao;
	}

	public List<WsMtPicture> findAllProductPic(long proid){
		return productdao.findAllProductPic(proid);
	}
	
	//分页查询所有产品
	public List<WsMtProduct> findAllProductByPage(Map<String,Object> map)
	{
		return productdao.findAllProductByPage(map);
	}
	//分页查询所有产品 按名称分组
	public List<WsMtProduct> findAllProductByPageGroupByName(Map<String,Object> map)
	{
		return productdao.findAllProductByPageGroupByName(map);
	}
	//增加产品
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean create(WsMtProduct product,WsProductUser productUser){
		boolean success = false;
		productdao.create(product);
		/*
		//添加图片
		if(product.getPicture()!=null){
			for(WsMtPicture picture:product.getPicture()){
				picturedao.create(picture);
				//添加产品and图片关系
				WsProductPicture pp = new WsProductPicture();
				pp.setProductId(product.getId());
				pp.setPictureId(picture.getId());
				productdao.addProductPicture(pp);
			}
		}
		*/
		//新增关系产品与企业
		productUser.setProductId(product.getId());
		productdao.addProductUser(productUser);
		success = true;
		return success;
	}
	//修改产品
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateProduct(WsMtProduct product) {
		boolean success = false;
		//修改产品信息
		productdao.update(product);
		/*
		//删除产品关联的所有图片
		productdao.deleteProductIdPicture(product.getId());
		//删除产品和图片的关联表
		productdao.deleteProductPicture(product.getId());
		if (product.getPicture() != null) {
			for (WsMtPicture picture : product.getPicture()) {
				picturedao.create(picture);
				//添加产品and图片关系
				WsProductPicture wpp = new WsProductPicture();
				wpp.setPictureId(picture.getId());
				wpp.setProductId(product.getId());
				productdao.addProductPicture(wpp);
			}
		}
		*/
		success = true;
		return success;
	}
	//根据用户id查找产品
	public Long count(Map<String,Object> map){
		return productdao.count(map);
	}
	//删除产品
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteProduct(Long id){
		boolean success = false;
		productdao.deleteById(id);
		//productdao.deleteProductIdPicture(id);
		//productdao.deleteProductPicture(id);
		productdao.deleteProductUser(id);
		success = true;
		return success;
	}
	
	public List<WsMtPicture> findAllPictureByPid(Map<String, Object> map){
		return productdao.findAllPictureByPid(map);
	}
	
	public WsMtProduct findProductById(Map<String, Object> map){
		return productdao.findProductById(map);
	}
	
	//商品下架
	public void downProduct(Long id){
		productdao.downProduct(id);
	}
	//商品上架
	public void upProduct(Long id){
		productdao.upProduct(id);
	}
	
	//商品促销状态
	public void doSales(Long id){
		 productdao.doSales(id);
	}
	
	//商品正常状态
	public void doNormal(Long id){
		productdao.doNormal(id);
	}
	
	public List<WsMtProduct> findAllUpProductByJson(Map<String, Object> map){
		return productdao.findAllUpProductByJson(map);
	}
	
	//查询促销商品
	public List<WsMtProduct> findAllSalesProductByJson(Map<String, Object> map){
		return productdao.findAllSalesProductByJson(map);
	}
}
