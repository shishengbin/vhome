package com.sy.web.controller.sys;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.modules.entity.sys.CompanyInfo;
import com.sy.modules.service.sys.CompanyInfoService;
import com.sy.web.commons.Constants;
import com.sy.web.commons.JsonUtil;
import com.sy.web.commons.PageSet;
import com.sy.web.commons.SessionUtil;

/**
 * company information
 * @author sss
 * @date 2013-8-15
 */
@Controller
@RequestMapping(value = "/sys")
public class CompanyInfoController extends PageSet {
	private static final Logger log = LoggerFactory.getLogger(CompanyInfoController.class);
	
	@Autowired
	private CompanyInfoService companyinfoservice;

	// 查询商家基本以及版本信息
	@RequestMapping(value = "/findAccoutAndType")
	public String findAccoutAndType(HttpServletRequest request) {
		log.info("entering...CompanyInfoController...findAccoutAndType()");
		Long userId = SessionUtil.getUserId(request);
		CompanyInfo companyinfo = companyinfoservice.findAccoutAndType(userId);
		request.setAttribute("companyinfo", companyinfo);
		return "sys/userInfo";
	}

	// 查询账户对应的企业信息
	@RequestMapping(value = "/findCompanyInfo")
	public String findCompanyInfo(HttpServletRequest request) {
		log.info("entering...CompanyInfoController...findCompanyInfo()");
		Long userId = SessionUtil.getUserId(request);
		CompanyInfo companyinfo = companyinfoservice.findCompanyInfo(userId);
		request.setAttribute("companyinfo", companyinfo);
		return "sys/company";
	}

	// 增加企业信息
	// 删除企业信息
	// 修改企业信息

	@RequestMapping(value = "/updateCompanyInfo")
	public @ResponseBody
	String updateCompanyInfo(HttpServletRequest request) {
		log.info("entering...CompanyInfoController...updateCompanyInfo()");
		Long id = Long.valueOf(request.getParameter("id"));
		String companyName = request.getParameter("company_name");
		String companyAddre = request.getParameter("company_address");
		String companyEmail = request.getParameter("company_email");
		String qqnumber = request.getParameter("qqnumber");
		String companyPerson = request.getParameter("company_person");
		String companyPhone = request.getParameter("company_phone");
		String companyFax = request.getParameter("company_fax");
		String companySite = request.getParameter("company_site");
		String companyBusiness = request.getParameter("company_business");
		String companyDescribe = request.getParameter("company_describe");
		CompanyInfo companyinfo = new CompanyInfo();
		companyinfo.setId(id);
		companyinfo.setCompanyname(companyName);
		companyinfo.setCompanyaddress(companyAddre);
		companyinfo.setCompanyemail(companyEmail);
		companyinfo.setQqnumber(qqnumber);
		companyinfo.setCompanyperson(companyPerson);
		companyinfo.setCompanyphone(companyPhone);
		companyinfo.setCompanyfax(companyFax);
		companyinfo.setCompanysite(companySite);
		companyinfo.setCompanybusiness(companyBusiness);
		companyinfo.setCompanydescribe(companyDescribe);
		companyinfoservice.update(companyinfo);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,
				Constants.MSG_UPDATE_SUCCESS, null, null, null, null);
	}
}
