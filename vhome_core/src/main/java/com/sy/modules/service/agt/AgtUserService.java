package com.sy.modules.service.agt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.common.GlobalConstants;
import com.sy.modules.dao.agt.AgtUserDao;
import com.sy.modules.entity.agt.Accounting;
import com.sy.modules.entity.agt.AgtCompany;
import com.sy.modules.entity.agt.AgtUser;
import com.sy.modules.service.sys.CompanyInfoService;

@Component
public class AgtUserService extends AbstractService<AgtUser, Long, AgtUserDao> {

	@Autowired
	private AgtUserDao agtuserdao;
	@Autowired
	private CompanyInfoService comservice;
	@Autowired
	private AccountingService aservice;
	@Autowired
	private AgtCompanyService acs;

	@Override
	protected AgtUserDao getDao() {
		return agtuserdao;
	}

	// find all users by page
	public List<AgtUser> findAllUserByPage(Map<String, Object> map) {
		return agtuserdao.findAllUserByPage(map);
	}

	// find user by username
	public AgtUser findUserByName(String username) {
		return agtuserdao.findUserByName(username);
	}

	// find count by param
	public Long findCountByParam(Map<String, Object> map) {
		return agtuserdao.findCountByParam(map);
	}

	// 登录
	public AgtUser agtLogin(String u_name) {
		return agtuserdao.agtLogin(u_name);
	}

	// 查看单条代理商信息
	public AgtUser findAgentById(Long id) {
		return agtuserdao.findAgentById(id);
	}

	// 修改代理商账户状态及企业名称
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateAgt(AgtUser au) {
		boolean success = false;
		comservice.update(au.getComs());
		agtuserdao.update(au);
		success = true;
		return success;
	}

	/*
	 * public String agtLogin(AgtUser au,HttpSession session){ AgtUser fau =
	 * null; AgtUser agtuser = agtuserdao.agtLogin(au.getU_name()); if (agtuser
	 * != null) { // 判断密码是否正确 if (au.getU_pass().equals(agtuser.getU_pass())) {
	 * // 帐号正确 if (agtuser.getRole().getId() <= 2) { fau =
	 * agtuserdao.findAgentById(agtuser.getId()); } else { fau =
	 * agtuserdao.findAgentById(agtuser.getFatherId()); }
	 * agtuser.setMoney_box(fau.getMoney_box()); agtuser.setComs(fau.getComs());
	 * session.setAttribute(Constants.USER_LOGIN_SESSION_KEY, agtuser); return
	 * JsonUtil.transferJsonResponse(1,Constants.MSG_LOGIN_SUCCESS,
	 * "index.html"); } else { // 密码错误 return
	 * JsonUtil.transferJsonResponse(0,Constants.MSG_NAMEPASS_FAIL, null); } }
	 * else { return JsonUtil.transferJsonResponse(0,Constants.MSG_LOGIN_FAIL,
	 * null); }
	 * 
	 * /* if(agtuser==null){ return
	 * JsonUtil.transferJsonResponse(0,Constants.MSG_USERNAME_FAIL,null); }else
	 * if(agtuser.getU_pass().equals(au.getU_pass())){
	 * if(agtuser.getRole().getId()<=2){ fau=
	 * agtuserdao.findAgentById(agtuser.getId()); }else{ fau=
	 * agtuserdao.findAgentById(agtuser.getFatherId()); }
	 * agtuser.setMoney_box(fau.getMoney_box()); agtuser.setComs(fau.getComs());
	 * session.setAttribute(Constants.USER_LOGIN_SESSION_KEY,agtuser); return
	 * JsonUtil
	 * .transferJsonResponse(1,Constants.MSG_LOGIN_SUCCESS,"index.html"); }else{
	 * return JsonUtil.transferJsonResponse(0,Constants.MSG_NAMEPASS_FAIL,null);
	 * }
	 * 
	 * }
	 */
	
	//修改密码
	@Transactional(propagation = Propagation.REQUIRED)
	 public boolean updatePass(Map<String,Object> map){
		try{
			agtuserdao.updatePass(map);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
			}
		}
	 
	// 修改余额
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateBalance(Accounting at) {
		boolean success = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", at.getAgt().getId());
		map.put("balance", at.getBalance());
		agtuserdao.updateBalance(map);
		aservice.create(at);
		success = true;
		return success;
	}

	public double queryBalance(Long id) {
		return agtuserdao.queryBalance(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addAgent(AgtCompany ac, AgtUser au) {
		try {
			agtuserdao.create(au);
			long aid = au.getId();
			acs.addCompany(ac, aid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public long findCompanyIdById(long aid) {
		return agtuserdao.findCompanyIdById(aid);
	}

	public long queryUserIdByKey(String keyword) {
		return agtuserdao.queryUserIdByKey(keyword);
	}
}
