package com.sy.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sy.manage.commons.Constants;
import com.sy.manage.commons.DataTool;
import com.sy.manage.commons.JsonUtil;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.AgtCompany;
import com.sy.modules.service.agt.AgtCompanyService;

@Controller
public class AgtCompanyController extends PageSet {
	private static final Logger log = LoggerFactory.getLogger(AgtCompanyController.class);
	@Autowired
	private AgtCompanyService acs;

	// 查看客户信息
	@RequestMapping("/showCompanys")
	public String showCompanys(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...AgtCompanyController...showCompanys()");
		Long aid = SessionUtil.getAgtLoginUser(request, response).getFatherId();
		if (aid == 1) {
			aid = SessionUtil.getAgtLoginUser(request, response).getId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		this.setPagination(request, map);
		map.put("aid", aid);
		List<AgtCompany> list = acs.showAgCompanyBypage(map);
		Integer totalNum = acs.findCountByParam(map);
		request.setAttribute("companyList", list);
		request.setAttribute("totalCount", totalNum);
		return "company/show_company";
	}

	// 模糊查询客户
	@RequestMapping(value = "/searchCompany")
	public String searchCompany(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...AgtCompanyController...searchCompany()");
		Map<String, Object> map = DataTool.getParam(request, "info");
		Long aid = SessionUtil.getAgtLoginUser(request, response).getFatherId();
		if (aid == 1) {
			aid = SessionUtil.getAgtLoginUser(request, response).getId();
		}
		map.put("aid", aid);
		this.setPagination(request, map);
		List<AgtCompany> list = acs.searchCompany(map);
		long totalNum = acs.findCountByParam(map);
		request.setAttribute("companyList", list);
		request.setAttribute("totalCount", totalNum);
		String cl = request.getParameter("clientList");
		if (cl != null && cl.equals("true")) {
			return "company/client_list";
		}
		return "company/show_company";
	}

	/**
	 * prepare add company
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preAddCompany")
	public String preAddCompany() {
		return "company/new_company";
	}

	// 增加客户
	@RequestMapping(value = "/addCompany")
	@ResponseBody
	public String addCompany(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...AgtCompanyController...addCompany()");
		Integer statusCode = 0;
		String message = Constants.MSG_UNKNOWN_OPERATION;
		Long aid = SessionUtil.getAgtLoginUser(request, response).getFatherId();
		if (aid == 1l) {
			aid = SessionUtil.getAgtLoginUser(request, response).getId();
		}
		AgtCompany ac = new AgtCompany();
		ac.setCompanyBusiness(request.getParameter("companyBusiness"));
		ac.setCompanyDescibe(request.getParameter("companyDescibe"));
		ac.setCompanyEmail(request.getParameter("companyEmail"));
		ac.setCompanyName(request.getParameter("companyName"));
		ac.setCompanyPerson(request.getParameter("companyPerson"));
		ac.setCompanyPhone(request.getParameter("companyPhone"));
		if (acs.addCompany(ac, aid)) {
			statusCode = 1;
			message = Constants.MSG_OPERATIO_SUCCESS;
		} else {
			message = Constants.MSG_OPERATIO_FAIL;
		}
		return JsonUtil.transferJsonResponse(statusCode, message,"showCompanys");
	}

	// 修改客户信息
	@RequestMapping(value = "/modifyCompany")
	// @ResponseBody
	public String modifyCompany(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...AgtCompanyController...modifyCompany");
		Long id = Long.parseLong(request.getParameter("companyId"));
		AgtCompany ac = acs.findById(id);
		ac.setCompanyBusiness(request.getParameter("companyBusiness"));
		ac.setCompanyDescibe(request.getParameter("companyDescibe"));
		ac.setCompanyEmail(request.getParameter("companyEmail"));
		ac.setCompanyName(request.getParameter("companyName"));
		ac.setCompanyPerson(request.getParameter("companyPerson"));
		ac.setCompanyPhone(request.getParameter("companyPhone"));
		acs.update(ac);
		// return "company/show_company";
		return "redirect:/showCompanys";
	}

	// 删除客户信息
	@RequestMapping(value = "/deleteCompany")
	@ResponseBody
	public String deleteCompany(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...AgtCompanyController...deleteCompany()");
		Long cid = Long.parseLong(request.getParameter("id"));
		Long aid = SessionUtil.getAgtLoginUser(request, response).getFatherId();
		if (aid == 1l) {
			aid = SessionUtil.getAgtLoginUser(request, response).getId();
		}
		if (acs.findUidByCid(cid)) {
			acs.delCompany(cid, aid);
			return JsonUtil.transferJsonResponse(1, Constants.MSG_DEL_SUCCESS,
					"showCompanys");
		} else {
			return JsonUtil.transferJsonResponse(0, "该客户有订单，无法删除", "");
		}

	}

	// 显示单个客户信息
	@RequestMapping(value = "/oneCompany")
	public String oneCompany(HttpServletRequest request) {
		log.info("entering...AgtCompanyController...oneCompany()");
		Long id = Long.parseLong(request.getParameter("id"));
		AgtCompany ac = acs.findById(id);
		request.setAttribute("ac", ac);
		return "company/one_company";
	}

	// 检查公司名称是否重复
	@RequestMapping(value = "/checkCompany")
	@ResponseBody
	public String checkCompany(HttpServletRequest request) {
		log.info("entering...AgtCompanyController...request()");
		boolean success = false;
		String message = "";
		Integer state = 0;
		Map<String, Object> checkMap = new HashMap<String, Object>();
		String companyName = request.getParameter("companyName");
		checkMap.put("companyName", companyName);
		success = acs.findCompanyByName(checkMap);
		if (success) {
			state = 1;
		} else {
			state = 0;
		}
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("state", state.toString());
		return gson.toJson(map);
	}
}
