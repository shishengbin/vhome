package com.sy.manage.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sy.manage.commons.DataTool;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.Accounting;
import com.sy.modules.entity.sys.CompanyInfo;
import com.sy.modules.service.agt.AccountingService;
import com.sy.modules.service.sys.CompanyInfoService;
/**
 * 账务控制
 * @describe  
 * @author LiuCheng
 * 2013年10月22日 下午1:54:38
 * @version v1.0
 */
@Controller
public class AccountingController extends PageSet {

	private static final Logger log=LoggerFactory.getLogger(AccountingController.class);
	@Autowired
	private AccountingService aservice;
	@Autowired
	private CompanyInfoService comservice;
	
	/**
	 * 分页显示账务信息，类型匹配
	 * @describe  
	 * @param request
	 * @return String
	 * @author LiuCheng
	 * 2013年10月22日 下午1:54:51
	 */
	@RequestMapping("/findAccountingByPage")
	public String findAccountingByPage(HttpServletRequest request,HttpServletResponse response){
		log.info("entering....findAccountingByPage....");
		Map<String,Object> map = DataTool.getParam(request,"accountingType","agentName");
		map.put("id",SessionUtil.getLoginId(request, response));
		map.put("role",SessionUtil.getAgtLoginUser(request, response).getRole().getId());
		this.setPagination(request, map);
		List<Accounting> list = aservice.findAccountingBypage(map);
		List<CompanyInfo> coms = comservice.getAllAgent();
		Integer count = aservice.count(map);
		request.setAttribute("coms",coms);
		request.setAttribute("accountings",list);
		request.setAttribute("totalCount",count);
		log.info("end...findAccountingByPage...");
		return "accounting/show_accounting";
	}
}
