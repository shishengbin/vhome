package com.sy.modules.service.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsHumanUserDao;
import com.sy.modules.dao.ws.WsIndentDao;
import com.sy.modules.dao.ws.WsProductIndentDao;
import com.sy.modules.entity.ws.WsIndent;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProductIndnet;
import com.sy.modules.exception.IndentException;

@Component
public class WsIndentService extends AbstractService<WsIndent, Long, WsIndentDao> {
	@Autowired
	private WsIndentDao indentdao;
	
	@Autowired
	private WsProductIndentDao ipdao;
	
	@Autowired
	private WsHumanUserDao wsHumanUserDao;
	
	@Override
	protected WsIndentDao getDao() {
		return this.indentdao;
	}
	
	//分页查询所有订单
	public List<WsIndent> findAllByPage(Map<String,Object> map){
		return indentdao.findAllByPage(map);
	}
	public Integer count(Map<String,Object> map){
		return indentdao.count(map);
	}

	public WsIndent findById(String number) {
		return indentdao.findById(number);
	}

	/**
	 * @throws Exception 
	 * @Title: addIndent 
	 * @Description:(APP下单) 
	 * @return WsIndent    返回类型 
	 * @author BJH
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public WsIndent addIndent(WsIndent indent,List<WsMtProduct> products){
		//IndentUtil iu = new IndentUtil();
		//获取商家id
		String cartNumber = indent.getHuman().getId()+"";
		//数量
		Integer count=0;
		//订单总金额
		double total =0;
		if(products==null||products.size()==0){//购物车
			
			List<WsMtProduct> ps = ipdao.getAll(cartNumber);
			if(ps==null||ps.size()==0){
				throw new IndentException();
			}
			for(WsMtProduct p:ps){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("humanId", indent.getHuman().getId());
				map.put("productId",p.getId());
				count+=p.getCount();
				total += p.getCount()*p.getProductPrice();
			}
			indent.setName("批量下单："+count+"个商品");
		}else{//单笔订单
			for(WsMtProduct product:products){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("humanId",wsHumanUserDao.findByHumanId(indent.getHuman().getId()));
				map.put("productId",product.getId());
				count += product.getCount();
				total +=product.getProductPrice()*product.getCount();
			}
			
			if(products.size()>1){
				//indent.setName(indentName+"下单"+count+"个商品:");
				String indentName="";
				indentName=indentName+"APP下单"+count+"个商品:";
				for(WsMtProduct wp:products ){
					indentName+=wp.getProductName()+";";
				}
				indent.setName(indentName);
			}else{
				indent.setName(products.get(0).getProductName());
			}
		}
		if(indent.getMoney()!=total){
			throw new IndentException();
		}
		
/*		//邮费处理
		if(indent.getLgsState()==1){
			indent.setOrderTotal(total+indent.getLgsPay());
		}else{
			indent.setOrderTotal(total);
		}*/
		
		indentdao.addIndent(indent);
		
		//单笔提交还是购物车提交
		if(products!=null&&products.size()>0){
			List<WsProductIndnet> oip = new ArrayList<WsProductIndnet>();
			for(int i=0;i<products.size();i++){
				if(products.get(i)!=null&&products.get(i).getCount()>0){
					//oip.add(new WsProductIndnet(products.get(i).getProductId(),indent.getId(),products.get(i).getCount()));
					//System.out.print("id:"+indent.getId());
					oip.add(new WsProductIndnet(products.get(i).getId(),indent.getId(),products.get(i).getCount()));
				}
			}
			ipdao.addProductToCart(oip);
		}else if(products==null||products.size()==0){
			ipdao.updateProductCart(new WsProductIndnet(indent.getId(),null,cartNumber));
		}
		return indent;
	}
	public void updateIndentById(WsIndent wsIndent) {
		indentdao.updateIndentById(wsIndent);
	}
	public WsIndent findIndentById(String out_trade_no) {
		return indentdao.findIndentById(out_trade_no);
	}
	public void deleteByNumber(String number) {
		indentdao.deleteByNumber(number);
		
	}
}
