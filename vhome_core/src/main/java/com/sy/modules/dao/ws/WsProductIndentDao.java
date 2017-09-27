package com.sy.modules.dao.ws;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProductIndnet;

@Component
@MyBatisRepository
public interface WsProductIndentDao extends ParentDao<WsProductIndnet, Long>{
    
    /**
     * 根据商家id查询购物车信息
     * 这块暂时这样，后面在深入研究下
     * @param humanidstr
     * @return list
     */
	List<WsMtProduct> getAll(String humanidstr);

	void addProductToCart(List<WsProductIndnet> wpi);

	void updateProductCart(WsProductIndnet wpi);

	List<WsProductIndnet> queryCart(WsProductIndnet wpi);

	void deleteById(WsProductIndnet wpi);
	
}
