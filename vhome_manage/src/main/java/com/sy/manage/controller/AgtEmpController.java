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
import com.sy.manage.commons.JsonUtil;
import com.sy.manage.commons.PageSet;
import com.sy.manage.commons.SessionUtil;
import com.sy.modules.entity.agt.AgtEmpForm;
import com.sy.modules.entity.agt.AgtUser;
import com.sy.modules.entity.agt.Role;
import com.sy.modules.service.agt.AgtEmpService;
import com.sy.modules.service.agt.RoleService;

/**
 * @author sss 2013-11-05
 */
@Controller
public class AgtEmpController extends PageSet {
	private static final Logger log = LoggerFactory.getLogger(AgtEmpController.class);
	@Autowired
	private AgtEmpService aes;
	@Autowired
	private RoleService ros;

	/**
	 * search all roles
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/searchRole")
	@ResponseBody
	public String searchRole(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...searchRole...request()");
		List<Role> list = ros.getAll();
		/*long maxId = SessionUtil.getLoginUser(request, response).getRoleId();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() <= maxId) {
				list.remove(i);
				i--;
			} else if (list.get(i).getId() > 4 && maxId < 5) {
				list.remove(i);
				i--;
			}
		}*/
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// 检查代理商下管理员名称是否重复
	@RequestMapping(value = "/checkUsername")
	@ResponseBody
	public String checkUsername(HttpServletRequest request) {
		boolean success = false;
		Integer state = 0;
		log.info("entering...checkUsername...request()");
		String username = request.getParameter("username");
		success = aes.checkUsername(username);
		state = success ? 1 : 0;
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		map.put("state", state.toString());
		return gson.toJson(map);
	}

	/**
	 * preAddEmp
	 * @return page
	 */
	@RequestMapping(value = "/preAddEmp")
	public String preAddEmp(HttpServletRequest request) {
		List<Role> list = ros.getAll();
		request.setAttribute("rolelist", list);
		return "emp/new_emp";
	}

	/**
	 * create an employee
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addEmp")
	@ResponseBody
	public String addEmp(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...addEmp...request()");
		Long fartherId = SessionUtil.getAgtLoginUser(request, response).getId();
		AgtUser au = new AgtUser();
		au.setU_name(request.getParameter("username"));
		au.setU_pass(request.getParameter("passwords"));
		au.setU_status(Integer.parseInt(request.getParameter("state")));
		au.setFatherId(fartherId);
		au.setRoleId(Long.parseLong(request.getParameter("rid")));
		aes.create(au);
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("message", Constants.MSG_ADD_SUCCESS);
		map.put("forward", "showEmp");
		return gson.toJson(map);
	}

	/**
	 * search all employees by pagination
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "showEmp")
	public String showEmp(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...showEmp...request()");
		Long fatherId = SessionUtil.getLoginId(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fatherId", fatherId);
		this.setPagination(request, map);
		List<AgtEmpForm> list = aes.showEmpFormBypage(map);
		Integer totalNum = aes.findCountByParam(fatherId);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).getId() == 1l) {
				list.remove(i);
				totalNum--;
				break;
			}
		}
		request.setAttribute("empList", list);
		request.setAttribute("totalCount", totalNum);
		return "emp/show_emp";
	}

	/**
	 * delete an employee
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteEmp")
	public String deleteEmp(HttpServletRequest request) {
		log.info("entering...deleteEmp...request()");
		Long id = Long.parseLong(request.getParameter("id"));
		aes.delete(id);
		return "redirect:showEmp";
	}

	/**
	 * search an employee
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oneEmp")
	public String oneCompany(HttpServletRequest request) {
		log.info("entering...oneCompany...request()");
		String temp = request.getParameter("id");
		Long id = Long.parseLong(temp);
		AgtUser au = aes.findById(id);
		List<Role> list = ros.getAll();
		request.setAttribute("rolelist", list);
		request.setAttribute("agtuser", au);
		return "emp/one_emp";
	}

	// 验证原始密码
	@RequestMapping(value = "checkOldPassword")
	@ResponseBody
	public String checkOldPassword(HttpServletRequest request) {
		log.info("entering...checkOldPassword...request()");
		Integer state = 0;
		String temp = request.getParameter("id");
		Long id = Long.parseLong(temp);
		String oldPwd = request.getParameter("oldPwd");
		AgtUser ae = aes.findById(id);
		if (ae.getU_pass().equals(oldPwd)) {
			state = 1;
		}
		Gson gson = new Gson();
		return gson.toJson(state.toString());
	}

	/**
	 * update employ info
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "modifyEmp")
	@ResponseBody
	public String modifyEmp(HttpServletRequest request) {
		log.info("entering...modifyEmp...request()");
		String temp = request.getParameter("empid");
		Long id = Long.parseLong(temp);
		AgtUser au = aes.findById(id);
		au.setU_pass(request.getParameter("passwords"));
		au.setU_status(Integer.parseInt(request.getParameter("state")));
		au.setRoleId(Long.parseLong(request.getParameter("rid")));
		aes.update(au);
		return JsonUtil.transferJsonResponse(1, Constants.MSG_UPDATE_SUCCESS, "showEmp");
	}
}
