package com.sy.web.controller.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
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
import com.sy.modules.entity.ws.IndentStatus;
import com.sy.modules.entity.ws.WsHuman;
import com.sy.modules.entity.ws.WsIndent;
import com.sy.modules.entity.ws.WsMtProduct;
import com.sy.modules.entity.ws.WsPayment;
import com.sy.modules.entity.ws.WsProductIndnet;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.service.ws.WsHumanService;
import com.sy.modules.service.ws.WsHumanUserService;
import com.sy.modules.service.ws.WsIndentService;
import com.sy.modules.service.ws.WsMtProductService;
import com.sy.modules.service.ws.WsPaymentService;
import com.sy.modules.service.ws.WsProductIndentService;
import com.sy.web.alipay.config.AlipayConfig;
import com.sy.web.alipay.config.IndentUtil;
import com.sy.web.alipay.util.AlipayNotify;
import com.sy.web.alipay.util.AlipaySubmit;
import com.sy.web.alipay.util.UtilDate;
import com.sy.web.commons.Constants;
import com.sy.web.commons.DataTool;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;
/**
 *@ClassName: WsIndentController 
 *@Description:(订单管理) 
 *@author LiuCheng
 *@date 2014年3月4日 上午11:37:50 
 */
@Controller
@RequestMapping(value ="/ws/indent")
public class WsIndentController extends PageSet  {
	private static final Logger log=LoggerFactory.getLogger(WsProductController.class);

	@Autowired
	private WsPaymentService paymentservice;

	@Autowired
	private WsIndentService indentservice;

	@Autowired
	private WsMtProductService wsMtProductService;

	@Autowired
	private WsHumanService wsHumanService;// App会员service

	@Autowired
	private WsHumanUserService wsHumanUserService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private WsProductIndentService wpiService;
	@Autowired
	private WsMtProductService productservice;
	private IndentUtil indentutil = new IndentUtil();
	/**
	 * 
	* @Title: getPayment 
	* @Description:(读取支付方式列表) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/getPayment")
	public String getPayment(HttpServletRequest request){
		Long userId = SessionUtil.getUserId(request);
		List<WsPayment> list = paymentservice.getPayment(userId);
		request.setAttribute("payments",list);
		return "ws/indent/payment";
	}
	
	@RequestMapping("/findById")
	public String findById(HttpServletRequest request,@RequestParam("id") Long id){
		WsPayment p = paymentservice.findById(id);
		request.setAttribute("payment",p);
		return "ws/indent/add_payment";
	}
	/**
	 * 
	* @Title: deletePayment 
	* @Description:(删除支付方式) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/deletePayment")
	@ResponseBody
	public String deletePayment(HttpServletRequest request,@RequestParam("id")Long id){
		paymentservice.deletePayment(id);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page80",null,null,null);
	}
	
	/**
	 * 
	* @Title: updatePayment 
	* @Description:(修改支付信息) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/updatePayment")
	@ResponseBody
	public String updatePayment(HttpServletRequest request,@ModelAttribute WsPayment pay){
		paymentservice.updatePayment(pay);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page80","",Constants.CLOSECURRENT,"ws/indent/getPayment");
	}
	/**
	 * 
	* @Title: updatePaymentState 
	* @Description:(修改状态) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/updatePaymentState")
	@ResponseBody
	public String updatePaymentState(HttpServletRequest request,@ModelAttribute WsPayment pay){
		paymentservice.updatePaymentState(pay);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page80",null,null,null);
	}
	
	/**
	 * 
	* @Title: createPayment 
	* @Description:(添加支付方式) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/createPayment")
	@ResponseBody
	public String createPayment(HttpServletRequest request,@ModelAttribute WsPayment pay){
		SysUser user = SessionUtil.getLoginUser(request);
		pay.setUser(user);
		paymentservice.createPayment(pay);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"","",Constants.CLOSECURRENT,"ws/indent/getPayment");
	}
	
	/**
	 * 
	* @Title: findAllByPage 
	* @Description: (订单列表) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/findAllByPage")
	public String findAllByPage(HttpServletRequest request){
	//	User user = SessionUtil.getLoginUser(request);
		Map<String,Object> map = DataTool.getParam(request,"number","state");
	//	map.put("userId",user.getId());
		this.setPagination(request, map);
		List<WsIndent> list = indentservice.findAllByPage(map);
		Integer count = indentservice.count(map);
		request.setAttribute("indents",list);
		request.setAttribute("totalCount",count);
		return "ws/indent/show_indent";
	}
	/**
	 * 
	* @Title: findAllByPage 
	* @Description:(待发货订单列表) 
	* @return String    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/findAllWaitSendByPage")
	public String findAllWaitSendByPage(HttpServletRequest request){
		SysUser user = SessionUtil.getLoginUser(request);
		Map<String,Object> map = DataTool.getParam(request,"number","state");
		map.put("userId",user.getId());
		this.setPagination(request, map);
		map.put("state",1);
		List<WsIndent> list = indentservice.findAllByPage(map);
		Integer count = indentservice.count(map);
		request.setAttribute("indents",list);
		request.setAttribute("count",count);
		return "ws/indent/show_indent";
	}
	
	/**
	 * 
	* @Title: updateIndentById
	* @Description:(修改订单状态) 
	* @return String    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/updateIndentById")
	@ResponseBody
	public String updateIndentById(HttpServletRequest request,WsIndent wsIndent){
		indentservice.updateIndentById(wsIndent);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page81",null,null,"ws/indent/findAllWaitSendByPage");
	}
	
	/**
	 * 
	* @Title: updateIndentPostById
	* @Description:(修改订单状态，确认发货使用) 
	* @return String    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/updateIndentPostById")
	@ResponseBody
	public String updateIndentPostById(HttpServletRequest request,WsIndent wsIndent){
		indentservice.updateIndentById(wsIndent);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page81",null,Constants.CLOSECURRENT,"ws/indent/findAllWaitSendByPage");
	}
	/**
	 * 
	* @Title: indentDetails 
	* @Description:(订单详情) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/indentDetails")
	public String indentDetails(HttpServletRequest request,@RequestParam("number")String number){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("number",number);
		List<WsIndent> list = indentservice.findAllByPage(map);
		if(list!=null&&list.size()==1){
			request.setAttribute("indent",list.get(0));
		}else{
			request.setAttribute("indent",null);
		}
		return "ws/indent/indent_details";
	}
	
	/**
	 * 
	* @Title: deleteIndent
	* @Description:(删除订单) 
	* @return void    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/deleteIndent")
	@ResponseBody
	public String deleteIndent(HttpServletRequest request,@RequestParam("number")String number){
		indentservice.deleteByNumber(number);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_OPERATIO_SUCCESS,"page81",null,null,null);
	}
	/**
	 * 
	* @Title: buyOnce 
	* @Description:(立刻购买) 
	* @return String    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/buyOnce")
	public String buyOnce(HttpServletResponse response,HttpServletRequest request,@ModelAttribute WsMtProduct product)//产品信息
	{
		response.setCharacterEncoding("utf-8");
		Map<String,Object> map = new HashMap<String,Object>();
		long productId=Long.valueOf(request.getParameter("id"));
		map.put("productId",productId);
		WsMtProduct obj = wsMtProductService.findAllProductByPage(map).get(0);
		obj.setCount(product.getCount());
		request.setAttribute("product", obj);
		String humanId=request.getParameter("humanId");
		request.setAttribute("humanId", humanId);
		WsHuman wshuman=wsHumanService.findById(Long.valueOf(humanId));
		request.setAttribute("human", wshuman);
		return "ws/app/addIndent";
	}
	
	/**
	 * @throws IOException 
	 * @Title: addIndent 
	 * @Description: (单笔购买) 
	 * @return void    返回类型 
	 * @author BJH
	 * @throws
	 */
	
	@RequestMapping("/addIndent")
	public String addIndent(HttpServletResponse response,HttpServletRequest request,
				@ModelAttribute WsIndent indent,//订单信息
				@ModelAttribute WsMtProduct product) throws IOException//产品信息
	{
		log.info("entering...WsIndentController...addIndent()");
		WsHuman wsHuman = null;
		String accountidstr=request.getParameter("accountid");
		String paystr=request.getParameter("payway");
		//数据验证
		if(indent!=null){
			if(indent.getNumber()!=null){
				indent = indentservice.findById(indent.getNumber());
				try {
					request.getRequestDispatcher("alipay_api?out_trade_no="+indent.getNumber()+"&subject="+indent.getName()+"&total_fee=0.01&humanId="+indent.getHuman().getId()).forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
			//手机访问
			String id = request.getParameter("humanId");
			if(StringUtils.isNotEmpty(id)){
				wsHuman = wsHumanService.findById(Long.valueOf(id));
				if(null!=wsHuman&&null!=wsHuman.getId()){
					indent.setHuman(wsHuman);
					indent.setName(wsHuman.getHuman_name());
					//生成订单编号
					indent.setNumber(indentutil.yieldIndentNumber(wsHuman.getId()).toString());
				}
			 }
			List<WsMtProduct> wmp = new ArrayList<WsMtProduct>();
			if(product!=null&&product.getProductName()!=null&&product.getProductPrice()!=null)
			{
				String pId=	request.getParameter("productId");
				product.setId(Long.valueOf(pId));
				wmp.add(product);
			}
			WsIndent wi = indentservice.addIndent(indent,wmp);
			//手机订单
			if(paystr.equals("alipay")){
				try 
				{
					request.getRequestDispatcher("alipay_api?out_trade_no="+wi.getNumber()+"&subject="+wi.getName()+"&total_fee=0.01&humanId="+wsHuman.getId()+"").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				//PrintWriter out = response.getWriter();
				//out.println("{\"statusCode\":\"200\", \"message\":\"success\", \"navTabId\":\"page101\", \"forwardUrl\":\"login.jsp\", \"callbackType\":\"closeCurrent\"}");
            request.setAttribute("accountid", accountidstr);
            request.setAttribute("humanId", id);
			return "ws/wxpages/result";			
		}else{
			return "ws/wxpages/result_faile";
		  }
		}
	
	/**
	 * 
	* @Title: alipayApi 
	* @Description: (与支付宝交接初始化) 
	* @return String    返回类型 
	* @author LiuCheng
	 * @throws IOException 
	* @throws
	* 参数一：请求对象
	* 参数二：商户订单号
	* 参数三：订单名称
	* 参数四：订单总额
	 */
	@RequestMapping("/alipay_api")
	public void alipayApi(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("out_trade_no")String out_trade_no,
			@RequestParam("subject")String subject,
			@RequestParam("total_fee")String total_fee,
			@RequestParam("humanId")Long id
			) throws IOException{
			PrintWriter out = response.getWriter();
			/*OaUser user = SessionUtil.getMyOaSession(request);*/
			/*Long id = SessionUtil.getMyOaSessionId(request);*/
			SysUser user=userService.findById((wsHumanUserService.findByHumanId(id).getUserId()));
			//调用授权接口alipay.wap.trade.create.direct获取授权码token
			//请求号
			String req_id = UtilDate.getOrderNum();
			//必填，须保证每次请求都是唯一	
			//req_data详细信息		
			//请求业务参数详细
			String  req_dataToken = "<direct_trade_create_req><notify_url>" + IndentUtil.notify_url + "</notify_url><call_back_url>" + IndentUtil.call_back_url + "</call_back_url><seller_account_name>" + user.getAlipayAccount().trim() + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + IndentUtil.merchant_url + "</merchant_url></direct_trade_create_req>";
			//必填		
			//把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", user.getAlipayId());
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", IndentUtil.format);
			sParaTempToken.put("v", IndentUtil.v);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);
			
			String request_token = "";
			try {
				//System.out.println("key:"+user.getAlipayKey().trim());
				//建立请求
				String sHtmlTextToken = AlipaySubmit.buildRequest(IndentUtil.ALIPAY_GATEWAY_NEW,"", "",sParaTempToken,user.getAlipayKey().trim());
				//URLDECODE返回的信息
				sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
				//获取token
				request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			} catch (Exception e) {
				System.out.println("订单支付失败！");
			}
			//out.println(request_token);
			
			//根据授权码token调用交易接口alipay.wap.auth.authAndExecute
			
			//业务详细
			String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
			//必填
			
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", user.getAlipayId().trim());
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", IndentUtil.format);
			sParaTemp.put("v", IndentUtil.v);
			sParaTemp.put("req_data", req_data);
			
			//建立请求
//			System.out.println("key1:"+user.getAlipayKey().trim());
			String sHtmlText = AlipaySubmit.buildRequest(IndentUtil.ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认",user.getAlipayKey().trim());
			String tou = "<html><head><meta charset=\"UTF-8\"></head><body>";
			String wei = "</body></html>";
			out.println(tou+sHtmlText+wei);
	}
	/**
	 * @throws UnsupportedEncodingException 
	 * @Title: async_inform 
	 * @Description: (支付宝异步通知) 
	 * @return String    返回类型 
	 * @author LiuCheng
	 * @throws
	 */
	@RequestMapping("/async_inform")
	@ResponseBody
	@SuppressWarnings("all")
	public String async_inform(HttpServletRequest request) throws UnsupportedEncodingException{
		String success = "";
		//System.out.println("异步方法。。");
		/*OaUser user = SessionUtil.getMyOaSession(request);*/
		/*Long id = SessionUtil.getMyOaSessionId(request);
		OaSubbranch sub = subservice.findSubByUserId(id);*/
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			/*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");*/
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		boolean bool=false;
		String trade_status = null,trade_no,out_trade_no = null;
		try {
			//XML解析notify_data数据
			Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
			//商户订单号
			out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
			//支付宝交易号
			trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
			//交易状态
			trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)	
			WsIndent wi = indentservice.findIndentById(out_trade_no);
			
			//User user = wsHumanUserService.findByHumanId((wi.getHuman().getId());
			SysUser user=userService.findById((wsHumanUserService.findByHumanId(wi.getHuman().getId())).getUserId());
			System.out.println(user.getAlipayId()+"  "+user.getAlipayAccount()+"  "+user.getAlipayAccount());
			bool = AlipayNotify.verifyNotify(params,user.getAlipayKey().trim(),user.getAlipayId().trim());
		} catch (Exception e) {
			
		}
		//System.out.println("bool。。"+bool);
		if(bool){//验证成功
			if(trade_status.equals("TRADE_FINISHED")){
				//购买以后改变订单状态
				indentservice.updateIndentById(new WsIndent(out_trade_no,IndentStatus.待发货));
				success="success";	//请不要修改或删除
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				success="success";	//请不要修改或删除
			}

		}else{//验证失败
			success = "fail";
		}
		return success;
	}
	
	/**
	* @throws UnsupportedEncodingException 
	* @Title: syn_inform 
	* @Description: (支付宝同步通知) 
	* @return String    返回类型 
	* @author LiuCheng
	* @throws
	*/
	@RequestMapping("/syn_inform")
	@ResponseBody
	public String syn_inform(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
			return "购买成功!";
	}
	
	/**
	* @Title: alipayBreak 
	* @Description: (支付宝支付中断) 
	* @return void    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@RequestMapping("/alipay_break")
	public void alipayBreak(HttpServletRequest request,HttpServletResponse response){
		System.out.println("支付宝请求中断!");
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------
  //购物车的相关操作               	
  	
//--------------------------------------------------------------------------------------------------------------------------------------------	
	
	/**
	 * @Title: addCart 
	 * @Description:(添加购物车，接收两个参数：count数量，productId产品id,登陆商家id)
	 * @return String    返回类型 
	 * @author sss
	 * @throws
	 */
	@RequestMapping("/addCart")
	@ResponseBody
	public String addCart(HttpServletRequest request,@ModelAttribute WsProductIndnet oip){
		log.info("entering...OaStockController...addCart()");
		String humanidStr=request.getParameter("humanId");
		if(StringUtils.isNotEmpty(humanidStr)){
			if(oip.getProductid()==null||oip.getProductid()==0||oip.getCount()==null||oip.getCount()<1||humanidStr==null){
				return "false";
			}else{
				oip.setRemark(humanidStr);
				List<WsProductIndnet> l = new ArrayList<WsProductIndnet>();
				l.add(oip);
				wpiService.addProductToCart(l);
				return "true";
			}
		}
		return "false";
	}
	
    /**
     * 手机端根据不同商家id查询购物车里的商品信息
     * @param request
     * @return string
     */
	@RequestMapping("/appCart")
	public String appCart(HttpServletRequest request){
		log.info("entering...OaStockController...appCart()");
		long humanid=0;
		//商品总价
		double sunprice=0;
		String humanidstr=request.getParameter("id");//商家id
		if(StringUtils.isNotBlank(humanidstr)){
			humanid=Long.valueOf(humanidstr);
			//查询出购物车中的所有商家的商品信息
			List<WsMtProduct> proList = wpiService.getAll(humanidstr);
			if(null!=proList&&proList.size()>0){
				for(WsMtProduct p:proList){
					sunprice+=p.getProductPrice()*p.getCount();
				}
				//商品总数
				request.setAttribute("sum", proList.size());
			}
			request.setAttribute("price", sunprice);
			request.setAttribute("proList", proList);
			request.setAttribute("humanid", humanid);
			request.setAttribute("picurl", Constants.DB_IMAGE_FILE);
			return "ws/app/appCart";
		}
		return null;
	}
	/**
	 * 手机端根据不同商家id修改购物车里的商品信息
	 * 修改购物车
	 * Liu hongliang
	 */
	@RequestMapping("/cartUpdate")
	@ResponseBody
	public String cartUpdate(HttpServletResponse response,HttpServletRequest request)
	{ 
		String humanidstr= request.getParameter("humanId");
		long productId=Long.valueOf(request.getParameter("proId"));
		int count=Integer.parseInt(request.getParameter("count"));
		//String remark=indentutil.cartNumber(humanId);
		WsProductIndnet op=new WsProductIndnet();
		op.setRemark(humanidstr);
		op.setProductid(productId);
		op.setCount(count);
		wpiService.updateProductCart(op);
		
		//重新计算总价和数量
		List<WsMtProduct>proList = wpiService.getAll(humanidstr);
		//商品总数
		//商品总价
		double sumprice=0;
		for(WsMtProduct p:proList){
			sumprice+=p.getProductPrice()*p.getCount();
		}
		return "{\"price\":"+sumprice+",\"sum\":"+proList.size()+"}";
	}
	/**
	 * 手机端根据不同商家id删除购物车里的商品信息
	 * Liu hongliang
	 */
	@RequestMapping("/cartDelete")
	@ResponseBody
	public String cartDelete(HttpServletResponse response,HttpServletRequest request)
	{
		String humanidstr= request.getParameter("humanId");
		long productId=Long.valueOf(request.getParameter("proId"));
		WsProductIndnet op=new WsProductIndnet();
		op.setRemark(humanidstr);
		op.setProductid(productId);
		wpiService.deleteById(op);
		//重新计算总价和数量
		List<WsMtProduct>proList = wpiService.getAll(humanidstr);
		//商品总数
		//商品总价
		double sumprice=0;
		for(WsMtProduct p:proList){
			sumprice+=p.getProductPrice()*p.getCount();
		}
		return "{\"price\":"+sumprice+",\"sum\":"+proList.size()+"}";
	}
	/**
	 * 手机端立即购买商品
	 * 记录产品id和数量 
	 */
	@RequestMapping("/cartBuy")
	public String cartBuy(HttpServletResponse response,HttpServletRequest request)//产品信息
	{
		response.setCharacterEncoding("utf-8");
		String price=request.getParameter("price");
		String humanId=request.getParameter("humanId");
		String sum=request.getParameter("sum");
		request.setAttribute("humanId", humanId);
		WsHuman wshuman=wsHumanService.findById(Long.valueOf(humanId));
		request.setAttribute("wshuman", wshuman);
		request.setAttribute("price", price);
		request.setAttribute("sum", sum);
		return "ws/app/addIndentCart";		
	}
	/**
	 * app访问
	 * 查看订单列表
	 * Liu hongliang
	 */
	@RequestMapping("/appOrder")
	public String appOrder(HttpServletResponse response,HttpServletRequest request)//产品信息
	{
		String userId=request.getParameter("id");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("humanId",userId);
		List<WsIndent> list =null;
		list=indentservice.findAllByPage(map);
		request.setAttribute("list", list);
		return "ws/app/appIndent";	
	}
	/**
	 * app访问
	 * 查看订单详情
	 * Liu hongliang
	 */
	@RequestMapping("/appOrderDetail")
	public String appOrderDetail(HttpServletResponse response,HttpServletRequest request)//产品信息
	{
		String orderNum=request.getParameter("orderNum");
		WsIndent obj = indentservice.findIndentById(orderNum);
		if(obj!=null)
		{
			List<WsMtProduct>prolist= obj.getProducts();
			if(prolist!=null)
			{
				int i=0;
				while(i<prolist.size())
				{
					WsMtProduct pro= prolist.get(i);
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("productId",pro.getId());
					WsMtProduct p = productservice.findAllProductByPage(map).get(0);
					prolist.get(i).setPicture(p.getPicture());
					i++;
				}
				obj.setProducts(prolist);
				request.setAttribute("obj", obj);
			}
		}
		return "ws/app/orderDetail";	
	}
	
	/**
	 * 
	* @Title: buyOnce 
	* @Description:(立刻购买) 
	* @return String    返回类型 
	* @author BJH
	* @throws
	 */
	@RequestMapping("/appwx/buyOnceForwx")
	@ResponseBody
	public String buyOnceForwx(HttpServletResponse response,HttpServletRequest request,@ModelAttribute WsMtProduct product)//产品信息
	{
		response.setCharacterEncoding("utf-8");
		Map<String,Object> map = new HashMap<String,Object>();
		long productId=Long.valueOf(request.getParameter("id"));
		map.put("productId",productId);
		WsMtProduct obj = wsMtProductService.findAllProductByPage(map).get(0);
		obj.setCount(product.getCount());
		//request.setAttribute("product", obj);
		String humanId=request.getParameter("humanId");
		//request.setAttribute("humanId", humanId);
		WsHuman wshuman=wsHumanService.findById(Long.valueOf(humanId));
		request.setAttribute("human", wshuman);
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("product", obj);
		map1.put("human", wshuman);
		map1.put("humanId", humanId);
		Gson gson=new Gson();
		return gson.toJson(map1);
	}
	
}
