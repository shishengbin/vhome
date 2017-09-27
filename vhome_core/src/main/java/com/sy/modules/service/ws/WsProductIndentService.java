package com.sy.modules.service.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsProductIndentDao;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProductIndnet;
@Component
public class WsProductIndentService extends AbstractService<WsProductIndnet,Long,WsProductIndentDao> {
	@Autowired
	private WsProductIndentDao wsProductIndentDao;
	
	@Override
	protected WsProductIndentDao getDao() {	
		return this.wsProductIndentDao;
	}
	//根据登录商家id查询出所有商品信息
	public List<WsMtProduct> getAll(String humanidstr){
		return wsProductIndentDao.getAll(humanidstr);
	}
	
	public void addProductToCart(List<WsProductIndnet> wpi){
		for(WsProductIndnet op:wpi){
			if(op!=null){
				List<WsProductIndnet> list = wsProductIndentDao.queryCart(op);
				
				//判断购物车内是否存在某件商品
				if(list!=null&&list.size()>0){
					op.setCount(op.getCount()+list.get(0).getCount());
					wsProductIndentDao.updateProductCart(op);
				}else{
					List<WsProductIndnet> l = new ArrayList<WsProductIndnet>();
					l.add(op);
					wsProductIndentDao.addProductToCart(l);
				}
			}
		}
	}
	
	public void updateProductCart(WsProductIndnet wpi){
		wsProductIndentDao.updateProductCart(wpi);
	}
	
	public List<WsProductIndnet> queryCart(WsProductIndnet wpi){
		return wsProductIndentDao.queryCart(wpi);
	}
	
	public void deleteById(WsProductIndnet wpi){
		wsProductIndentDao.deleteById(wpi);
	}
}
