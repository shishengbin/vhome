package com.sy.web.controller.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsProType;
import com.sy.modules.entity.ws.WsProductAndType;
import com.sy.modules.entity.ws.WsProductUser;
import com.sy.modules.service.ws.WsMtProductService;
import com.sy.modules.service.ws.WsProTypeService;
import com.sy.modules.service.ws.WsProductAndTypeService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 *管理产品 
 *#@author LiuCheng 2013-8-27 
 */
@Controller
@RequestMapping(value ="/ws")
public class WsProductController extends PageSet {
	
	private static final Logger log=LoggerFactory.getLogger(WsProductController.class);
	
	@Autowired 
	private WsMtProductService productservice;
	@Autowired
	private WsProTypeService wsprotypeservice;
	@Autowired
	private WsProductAndTypeService wsproandtypeservice;
	
	
	@RequestMapping(value="/preAddProduct")
	public String preAddProduct(){
		return "ws/product/update_product";
	}
	
	//修改产品这块待升级
	/**
	 * 增加产品信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addProduct")
	@ResponseBody
	public String addProduct(HttpServletRequest request,HttpServletResponse response, @ModelAttribute WsMtProduct product) throws Exception{
		log.info("entering...WsProductController...addProduct()...");
		long tid=-1;
		//取得用户ID
		SysUser user = (SysUser)SessionUtil.getLoginUser(request);
		String province = request.getParameter("province");
		String city1 = request.getParameter("city1");
		String city2 = request.getParameter("city2");
		String tidstr=request.getParameter("tid");
		if(StringUtils.isNotBlank(tidstr)){
			tid=Long.valueOf(tidstr);
		}
		//产品分类id
		Long fis_wptyeId=Long.valueOf(request.getParameter("fis_wptyeid"));//一级分类
		Long sec_wptyeId=Long.valueOf(request.getParameter("sec_wptyeid"));//二级分类
		
		StringBuffer sb = DataTool.stringConnect("-",province,city1,city2);
		product.setProductCity(sb.toString());
        //规格id
		long specid=Long.valueOf(request.getParameter("specname"));
		//供应商id
		long supp_id=Long.valueOf(request.getParameter("supplier"));
		product.setSpecid(specid);
		product.setSuppid(supp_id);
		String[] fileNames = request.getParameterValues("filename");
		product.setUpdateName(user.getUsername());
		product.setCreateName(user.getUsername());
		//如果比例为空，默认就为1，销售价就等于成本价
		if(product.getProductFavorable()==null)
		{
			product.setProductFavorable(1d);
		}
		//商品关联用户
		WsProductUser pu = new WsProductUser();
		pu.setUserId(user.getId());
		pu.setUpdateName(user.getUsername());
		pu.setCreateName(user.getUsername());
	//	List<WsMtPicture> wmp = new ArrayList<WsMtPicture>();
		// 加载所有图片
		if (fileNames != null) {
			for (String filename : fileNames) {
				if (filename != null && filename != "") {
					// 拆分图文封面路径，存DB
					int dex = filename.indexOf(Constants.APPIMAGES);
					String fileurl = filename.substring(dex
							+ Constants.APPIMAGES.length());
					//WsMtPicture f = new WsMtPicture();
					//f.setPicture(fileurl);
					//f.setPictureName(fileurl);
					product.setPropicpath(fileurl);
					//wmp.add(f);
				}
			}
			//product.setPicture(wmp);
		}
		String state,message=null;	
			if(product.getId()!=null){
				//修改
				if(productservice.updateProduct(product)){
					//修改关联分类
					WsProductAndType wpat=new WsProductAndType();
					if(sec_wptyeId>0){
						wpat.setPid(product.getId());
						wpat.setTid(sec_wptyeId);
					}else{
						wpat.setPid(product.getId());
						wpat.setTid(tid);
					}
					wsproandtypeservice.update(wpat);
					state = Constants.SUCCESS;
					message = Constants.MSG_UPDATE_SUCCESS;
				}else{
					state = Constants.ERROR;
					message = Constants.MSG_UPDATE_FAIL;
				}
			}else{
				//添加产品关联类型和用户
				if(productservice.create(product, pu)){
					//关联分类
					WsProductAndType wpat=new WsProductAndType();
					if(sec_wptyeId>0){
						wpat.setPid(product.getId());
						wpat.setTid(sec_wptyeId);
					}else{
						wpat.setPid(product.getId());
						wpat.setTid(fis_wptyeId);
					}
					wsproandtypeservice.create(wpat);
					state = Constants.SUCCESS;
					message = Constants.MSG_ADD_SUCCESS;
				}else{
					state = Constants.ERROR;
					message = Constants.MSG_ADD_FAIL;
				}
			}
		return JsonUtil.transferJsonResponse(state,message,Constants.REL_PRODUCTMANAGER,null,Constants.CLOSECURRENT,"ws/findAllProductsByPage");
	}
	
	/**
	 * 删除产品信息
	 * @author LiuCheng
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteProduct")
	@ResponseBody
	public String deleteById(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="id",required=false)Long id){
		log.info("entering...WsProductController...deleteById()...");
		String state,message;
		if(id==null){
			state = Constants.ERROR;
			message = Constants.MSG_PRODUCTID_NOTNULL;
		}else{
			if(productservice.deleteProduct(id)){
				state = Constants.SUCCESS;
				message = Constants.MSG_DEL_SUCCESS;
			}else{
				state = Constants.ERROR;
				message = Constants.MSG_DEL_FAIL;
			}
		}
		return JsonUtil.transferJsonResponse(state,message,null,null,Constants.FORWARD,"ws/findAllProductsByPage");
	}
	
	/**
	 * 商品下架
	 */
	@RequestMapping(value="/downProduct")
	@ResponseBody
	public String downProduct(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="id",required=false)Long id){
		log.info("entering...WsProductController...downProduct()...");
		String state,message;
		if(id==null){
			state = Constants.ERROR;
			message = Constants.MSG_PRODUCTID_NOTNULL;
		}else{
				productservice.downProduct(id);
				state = Constants.SUCCESS;
				message = Constants.MSG_DOWN_SUCCESS;
		}
		return JsonUtil.transferJsonResponse(state,message,null,null,Constants.FORWARD,"ws/findAllProductsByPage");
	}
	
	/**
	 * 商品上架
	 */
	@RequestMapping(value="/upProduct")
	@ResponseBody
	public String upProduct(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="id",required=false)Long id){
		log.info("entering...WsProductController...upProduct()...");
		String state,message;
		if(id==null){
			state = Constants.ERROR;
			message = Constants.MSG_PRODUCTID_NOTNULL;
		}else{
				productservice.upProduct(id);
				state = Constants.SUCCESS;
				message = Constants.MSG_UP_SUCCESS;
		}
		return JsonUtil.transferJsonResponse(state,message,null,null,Constants.FORWARD,"ws/findAllProductsByPage");
	}
	
	/**
	 * 商品促销
	 */
	@RequestMapping(value="/doSales")
	@ResponseBody
	public String doSales(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="id",required=false)Long id){
		log.info("entering...WsProductController...doSales()...");
		String state,message;
		if(id==null){
			state = Constants.ERROR;
			message = Constants.MSG_PRODUCTID_NOTNULL;
		}else{
				productservice.doSales(id);
				state = Constants.SUCCESS;
				message = Constants.MSG_SALES_SUCCESS;
		}
		return JsonUtil.transferJsonResponse(state,message,null,null,Constants.FORWARD,"ws/findAllProductsByPage");
	}
	
	
	/**
	 * 商品正常
	 */
	@RequestMapping(value="/doNormal")
	@ResponseBody
	public String doNormal(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="id",required=false)Long id){
		log.info("entering...WsProductController...doNormal()...");
		String state,message;
		if(id==null){
			state = Constants.ERROR;
			message = Constants.MSG_PRODUCTID_NOTNULL;
		}else{
				productservice.doNormal(id);
				state = Constants.SUCCESS;
				message = Constants.MSG_NORMAL_SUCCESS;
		}
		return JsonUtil.transferJsonResponse(state,message,null,null,Constants.FORWARD,"ws/findAllProductsByPage");
	}
	
	/**
	 *分页查询所有产品
	 *@author LiuCheng 
	 *@param request
	 *@return
	 */
	@RequestMapping(value="/findAllProductsByPage")
	public String findAllProductsByPage(HttpServletRequest request)
	{
		log.info("entering...WsProductController...findAllProductsByPage()...");
		//获取用户id
		Long userid = SessionUtil.getUserId(request);
		String usertext=request.getParameter("usertext");//产品名称
		//根据分类名称来差
		Long parentypeId=0L;
		Long secondtypeId=0L;
		String parstr=request.getParameter("parentype");
		if(StringUtils.isNotEmpty(parstr)){
			 parentypeId=Long.valueOf(parstr);
		}
		String secstr=request.getParameter("secondtype");
		if(StringUtils.isNotEmpty(secstr)){
			 secondtypeId=Long.valueOf(secstr);
		}
		
        //获取参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页
		this.setPagination(request, map);
		map.put("userid", userid);
		map.put("usertext", usertext);
		if (secondtypeId > 0) {
			map.put("typeId", secondtypeId);
		} else {
			map.put("typeId", parentypeId);
		}
		List<WsMtProduct> productlist=productservice.findAllProductByPage(map);
		//查询总数
		Long count = productservice.count(map);
		request.setAttribute("totalCount",count);
		request.setAttribute("productlist",productlist);
		return "ws/product/product"; //返回的jsp页面
	}
	
	
	/**
	 * 根据ID查找产品信息
	 * @author LiuCheng
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findById")
	public String findById(HttpServletRequest request,@RequestParam("id") Long id) {
		log.info("entering...WsProductController...findById()...");
        //单条产品
		WsMtProduct p = productservice.findById(id);
		//根据产品类型的id查询一级分类id
		WsProType wpt= wsprotypeservice.findById(p.getTid());
		//根据产品id查询对应的图片信息
		//List<WsMtPicture> piclist= productservice.findAllProductPic(p.getId());
		request.setAttribute("product", p);
		if(null!=wpt){
			request.setAttribute("parentid", wpt.getParentId());
		}
		request.setAttribute("relurl", Constants.DB_IMAGE_FILE);
		//request.setAttribute("piclist",piclist);
		return "ws/product/update_product";
	}

	
	
//---------------------------------------------------------------------------------wx--------------------------------------------------------
	//手机微信端显示分类下的上架产品
	@RequestMapping(value = "/appwx/getByClass1Forwx")
	@ResponseBody
	public String getByClass1Forwx(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="classid")Long classid) {
		Map<String,Object> map = new HashMap<String,Object>();
		this.setWapPagination(request, map);
		map.put("userid",id);
		map.put("typeId", classid);//分类id
		//获得一级产品
		List<WsMtProduct> list =productservice.findAllUpProductByJson(map);
		Gson gson=new Gson();
		return gson.toJson(list);
	}
	//查询促销商品
	@RequestMapping(value="/appwx/getSalesProductsForwx")
	@ResponseBody
	public String getSalesProductsForwx(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="classid")Long classid) {
		Map<String,Object> map = new HashMap<String,Object>();
		this.setWapPagination(request, map);
		map.put("userid",id);
	//	map.put("typeId", classid);//分类id
		//获得一级产品
		List<WsMtProduct> list =productservice.findAllSalesProductByJson(map);
		Gson gson=new Gson();
		return gson.toJson(list);
	}
	
	//微信端根据关键字模糊查询产品
	@RequestMapping(value = "/appwx/getByKeynameForwx")
	@ResponseBody
	public String getByKeynameForwx(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="keyname")String keyname) {
		Map<String,Object> map = new HashMap<String,Object>();
		this.setWapPagination(request, map);
		map.put("typeId", 0);
	    map.put("keyname", keyname);
		//获得一级产品
		List<WsMtProduct> list =productservice.findAllUpProductByJson(map);
		Gson gson=new Gson();
		return gson.toJson(list);
	}
	
	// 微信显示产品详细信息
	@RequestMapping(value = "/appwx/showProductForwx")
	@ResponseBody
	public String showProductForwx(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "id") Long id) {
		WsMtProduct p = productservice.findById(id);
		Gson gson=new Gson();
		return gson.toJson(p);
	}
	
}
