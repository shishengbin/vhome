package com.sy.manage.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.sy.manage.commons.Constants;
import com.sy.manage.commons.DataTool;
import com.sy.manage.commons.JsonUtil;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.AgtUser;
import com.sy.modules.entity.agt.Indent;
import com.sy.modules.entity.agt.Keyword;
import com.sy.modules.entity.agt.Market;
import com.sy.modules.entity.sys.CompanyInfo;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.service.agt.AgtUserService;
import com.sy.modules.service.agt.IndentService;
import com.sy.modules.service.agt.KeywordService;
import com.sy.modules.service.agt.MarketService;
import com.sy.modules.service.sys.CompanyInfoService;
import com.sy.modules.service.sys.UserService;

@Controller
public class IndentController extends PageSet {

	private static final Logger log = LoggerFactory.getLogger(IndentController.class);

	@Autowired
	private IndentService indentservice;
	@Autowired
	private CompanyInfoService com;
	@Autowired
	private UserService userservice;
	@Autowired
	private KeywordService keyservice;
	@Autowired
	private MarketService marketservice;
	@Autowired
	private AgtUserService auservice;

	/**
	 * 初始化增加订单页面，客户列表,服务政策
	 * @describe
	 * @param request
	 * @return String
	 * @author LiuCheng 2013年10月17日 上午10:06:59
	 */
	@RequestMapping("/initAddIndent")
	@ResponseBody
	public String initAddIndent(HttpServletRequest request,HttpServletResponse response) {
		Long id = SessionUtil.getLoginId(request, response);
		// 客户列表
		List<CompanyInfo> list = com.findClientByAgentId(id);
		// 三方商城列表
		List<Market> markets = marketservice.getAll();
		Gson gson = new Gson();
		Map<String, Object> zong = new HashMap<String, Object>();
		zong.put("markets", markets);
		zong.put("clients", list);
		return gson.toJson(zong);
	}
	
	/**
	 * prepare add indent
	 * @return page
	 */
	@RequestMapping(value="/preAddIndent")
	public String preAddIndent(){
		return "indent/add_indent";
	}

	/**
	 * 增加订单
	 * @describe
	 * @param request
	 * @param market
	 * @param indent
	 * @return String
	 * @author LiuCheng 2013年10月17日 下午2:43:42
	 */
	
	@RequestMapping("/addIndent")
	@ResponseBody
	public String addIndent(HttpServletRequest request,HttpServletResponse response, @ModelAttribute Indent indent) {
		//第三方商城列表 
		//String[] s = request.getParameterValues("mm"); // 数据验证 
		boolean success = false;
		String message = "";
		Integer state = 0;
		if (indent == null || indent.getKeyword() == null) {
			success = false;
			state = 0;
			message = Constants.MSG_INDENT_BROKEN;
		} else {
			SysUser user = userservice.finUserByName(indent.getUser().getUsername());
			if(null!=user){
				message = Constants.MSG_USERNAME_REDO_FAIL;
			}else{
				success = indentservice.create(indent,SessionUtil.getAgtLoginUser(request, response));
				if (success) {
					message = Constants.MSG_ADD_INDENT_SUCCESS;
					state = 1;
				} else {
					message = Constants.MSG_ADD_INDENT_ERROR;
					state = 0;
				}
			}
		}
		return JsonUtil.transferJsonResponse(state, message, "findIndentByPage");
	}
	 

	/**
	 * 关键词验证，与定价
	 * @describe
	 * @param request
	 * @param key
	 * @return String
	 * @author LiuCheng 2013年10月21日 下午3:03:45
	 */
	/*
	 @RequestMapping("/verifyKeyword")
	  @ResponseBody
	  public String verifyKeyword(HttpSession session,HttpServletRequest request,
	  @RequestParam(value="key",required=false)String key,
	  @RequestParam(value="agency",required=false)String agency,
	  @RequestParam(value="term",required=false)String term,
	  @RequestParam(value="markets")String a){ 
		AgtUser agtuser=(AgtUser)session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY);
		Gson gson =new Gson(); 
		Integer[] markets = gson.fromJson(a,Integer[].class);
		double p = 0.0; 
		double costPrice=0.0; 
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(key!=null&&key!=""){
			Keyword keyword =keyservice.findKeywordByName(key);
			if(keyword!=null){
				map.put("message",Constants.MSG_KEYWORD_RETURN);
				map.put("statue",1);
			}else{ 
				map.put("message", Constants.MSG_KEYWORD_USABLE);
				map.put("statue",0); 
				KwType kt =kwtypeservice.findKwTypeById(Integer.valueOf(KeywordUtil.getKeywordType(key).toString()));
				p =kt.getTprice()*agtuser.getDiscount(); 
				costPrice=kt.getTprice();
				}
			}
				if(term!=null){ p = p*Long.valueOf(term);
					costPrice=costPrice*Long.valueOf(term); } if(agency!=null){
						if(Long.valueOf(agency)!=0){ p+=Constants.AGENCY_MAKE_PRICE;
						costPrice+=Constants.AGENCY_MAKE_PRICE;
					}else{
					} 
			} if(markets!=null&&markets.length>0){ 
				p+=(Constants.AGENCY_ISSUE_PRICE*markets.length);costPrice+=(Constants.AGENCY_ISSUE_PRICE*markets.length); }
				map.put("price",(int)p); map.put("costPrice", costPrice); 
				return gson.toJson(map);
		}
	 */
	/**
	 * 分页查询 多种条件组合查询
	 * @describe
	 * @param request
	 * @return String
	 * @author LiuCheng 2013年10月22日 下午3:20:07
	 */
	@RequestMapping("/findIndentByPage")
	public String findIndentByPage(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = DataTool.getParam(request, "keyword","indentType", "beginDate", "endDate");
		map.put("user", SessionUtil.getAgtLoginUser(request, response));
		this.setPagination(request, map);
		List<Indent> list = indentservice.findIndentByPage(map);
		Integer count = indentservice.count(map);
		request.setAttribute("indents", list);
		request.setAttribute("totalCount", count);
		return "indent/show_indent";
	}

	/**
	 * 关键词id查询订单信息
	 * 
	 * @describe
	 * @param request
	 * @return String
	 * @author Liuhongliang 2013年10月22日 下午3:20:07
	 */
	@RequestMapping("/findIndentByKeyWordId")
	@ResponseBody
	public String findIndentByKeyWordId(HttpServletRequest request,HttpServletResponse response) {
		AgtUser au = auservice.findAgentById(1L);
		String zcbh = null;
		String result = "{\"id\":0}";
		zcbh = request.getParameter("zcbh");
		if (zcbh != null)
			if (zcbh.length() == 15) {
				String tou = zcbh.substring(0, 2);
				if (tou.equals("HK")) {
					String key_id = zcbh.substring(10);
					try {
						Keyword kw = null;
						kw = keyservice.findById(Long.valueOf(key_id) - 9558);
						if (kw != null) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("user", au);
							map.put("keyword", kw.getKw_name());
							List<Indent> list = indentservice.findIndentByPage(map);
							if (list != null)
								if (list.size() > 0) {
									Indent i = indentservice.findById(list.get(0).getId());
									String riqi = (new java.text.SimpleDateFormat("yyyyMMdd")).format(i.getBeginDate());
									String priqi = zcbh.substring(2, 10);
									if (riqi.equals(priqi))
										result = "{\"id\":"
												+ key_id
												+ 9558
												+ ",\"keyword\":\""
												+ i.getKeyword()
												+ "\",\"person\":\""
												+ i.getUser().getCompany().getCompanyperson()
												+ "\",\"beginTime\":\""
												+ i.getBeginDate()
												+ "\",\"endTime\":\""
												+ i.getEndDate() + "\"}";
								}
						}
					} catch (Exception e) {
						return result;
					}
				}
			}
		return result;
	}
	/**
	 * 
	 * 跨域 关键词id查询订单信息
	 * 
	 * @describe
	 * @param request
	 * @return String
	 * @author XZX 2013年10月22日 下午3:20:07
	 */
	@RequestMapping("/kyFindIndentByKeyWordId")
	@ResponseBody
	public String kyFindIndentByKeyWordId(HttpServletRequest request,HttpServletResponse response) {
	//	Gson gson = new Gson();
		String jsonp = request.getParameter("jsonpcallback");
		AgtUser au = auservice.findAgentById(1L);
		String zcbh = null;
		String result = "{\"id\":0}";
		try {
			request.setCharacterEncoding("utf-8");
			zcbh = request.getParameter("zcbh");
			zcbh = new String(zcbh.getBytes("ISO-8859-1"), "utf-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("订单编号" + zcbh);
		if (zcbh != null)
			if (zcbh.length() == 15) {
				String tou = zcbh.substring(0, 2);
				if (tou.equals("HK")) {
					String key_id = zcbh.substring(10);
					try {
						Keyword kw = null;
						kw = keyservice.findById(Long.valueOf(key_id) - 9558);
						if (kw != null) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("user", au);
							map.put("keyword", kw.getKw_name());
							List<Indent> list = indentservice
									.findIndentByPage(map);
							if (list != null)
								if (list.size() > 0) {
									Indent i = indentservice.findById(list.get(
											0).getId());
									String riqi = (new java.text.SimpleDateFormat(
											"yyyyMMdd")).format(i
											.getBeginDate());
									String priqi = zcbh.substring(2, 10);
									if (riqi.equals(priqi))
										result = "{\"id\":"
												+ key_id
												+ 9558
												+ ",\"keyword\":\""
												+ i.getKeyword()
												+ "\",\"person\":\""
												+ i.getUser().getCompany().getCompanyperson()
												+ "\",\"beginTime\":\""
												+ i.getBeginDate()
												+ "\",\"endTime\":\""
												+ i.getEndDate() + "\"}";
								}
						}
					} catch (Exception e) {
						return jsonp + "(" + result + ")";
					}
				}
			}
		return jsonp + "(" + result + ")";
	}

	/**
	 * 查询有可撤单的关键词
	 * @describe
	 * @param request
	 * @return String
	 * @author LiuCheng 2013年10月23日 上午9:50:25
	 */
	@RequestMapping("/queryUndoKey")
	@ResponseBody
	public String queryUndoKey(HttpServletRequest request,HttpServletResponse response) {
		Date date = DataTool.addDateNumber(new Date(), 5, -7);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("id", SessionUtil.getLoginId(request, response));
		List<Keyword> list = keyservice.queryUndoKey(map);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	/**
	 * 初始化附加订单页面
	 * 
	 * @describe
	 * @param request
	 * @return String
	 * @author LiuCheng 2013年10月28日 上午11:25:07
	 */
	@RequestMapping("/initContinuePage")
	@ResponseBody
	public String initContinuePage(HttpServletRequest request,HttpServletResponse response) {
		List<CompanyInfo> coms = com.findClientByAgentId(SessionUtil.getLoginId(request, response));
		// 三方商城列表
		List<Market> markets = indentservice.getMarket();
		List<Object> list = new ArrayList<Object>();
		list.add(coms);
		list.add(markets);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	/**
	 * 订单详情
	 */
	@RequestMapping("/queryDetailsIndent")
	public String queryDetailsIndent(HttpServletResponse response,HttpServletRequest request, @RequestParam("indentId") Long id) {
		Indent indent = indentservice.findById(id);
		request.setAttribute("indent",indent);
		return "indent/detaile_indent";
	}

	@RequestMapping("/guodudab")
	public String guodudab(HttpServletResponse response,HttpServletRequest request) {
		DataTool.getParam(request, "userId", "roleId", "indentId");
		return "indent/app_compress";
	}

}
