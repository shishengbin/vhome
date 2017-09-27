package com.sy.modules.service.agt;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.agt.IndentDao;
import com.sy.modules.dao.ws.WsPictureDao;
import com.sy.modules.entity.agt.Accounting;
import com.sy.modules.entity.agt.AgtUser;
import com.sy.modules.entity.agt.Indent;
import com.sy.modules.entity.agt.Keyword;
import com.sy.modules.entity.agt.Market;
import com.sy.modules.entity.agt.RelIndentMarket;
import com.sy.modules.entity.agt.UserCompany;
import com.sy.modules.entity.agt.UserKeyword;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.entity.sys.SysUserCompany;
import com.sy.modules.service.sys.CompanyInfoService;
import com.sy.modules.service.sys.UserService;
import com.sy.modules.utils.DataTool;
@Component
public class IndentService extends AbstractService<Indent,Long,IndentDao> {

	@Autowired
	private IndentDao indentdao;
	@Autowired 
	private UserService userservice;
	@Autowired
	private AccountingService accountingservice;
	@Autowired
	private KeywordService keyservice;
	@Autowired
	private CompanyInfoService companyservice;
	@Autowired
	private AgtUserService auservice;
	@Autowired
	private WsPictureDao picturedao;
	
	public List<Accounting> getAll(Long id){
		return indentdao.getAll(id);
	}
	
	public List<Market> getMarket(){
		return indentdao.getMarket();
	}
	
	/**
	 * 下单
	 * @describe  
	 * @param indent
	 * @param s
	 * @param policy
	 * @param user
	 * @param keyword
	 * @return boolean
	 * @author LiuCheng
	 * 2013年10月22日 下午3:16:19
	 */
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean create(Indent indent,AgtUser u){
		boolean success = false;
		try {
			//Date enddate = DataTool.addDateNumber(indent.getBeginDate(),1,indent.getTerm());
			//indent.setEndDate(enddate);
			Long uid = 0L;
			if(u.getRole().getId()>1){
				uid = u.getFatherId();
			}else{
				uid= u.getId();
			}
			//double bs = auservice.queryBalance(uid);
			//if(bs<indent.getMonetary()){
			//	return success;
			//}
//			建站账号
			SysUser us = indent.getUser();
			us.setCreateName(u.getU_name());
			SysUserCompany sucompany=new SysUserCompany();
			sucompany.setCompanyId(us.getCompany().getId());
			userservice.saveSysUser(us,sucompany);
//			app版本
			/*
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("userId",us.getId());
			m.put("roleId",us.getRoleId());
			userservice.createRole(m);
			*/
//			注册关键词
//			验证关键词是否存在
			Long kId = keyservice.queryKey(indent.getKeyword());
			
			Keyword key = null;
			if(kId!=null){
				keyservice.update(new Keyword(kId,1));
				if(keyservice.findUserKeyByKeyId(kId)!=null)
					keyservice.updateUserKey(new UserKeyword(indent.getUser().getId(),kId));
				else
					keyservice.createUserKey(new UserKeyword(indent.getUser().getId(),kId));
			}else{
				key = new Keyword(indent.getKeyword());
				key.setCreateName(u.getU_name());
				keyservice.create(key);
				//keyservice.createRelKeyType(new RelKeyType(key.getId(),KeywordUtil.getKeywordType(key.getKw_name())));
				keyservice.createUserKey(new UserKeyword(indent.getUser().getId(),key.getId()));
			}
			
			//下订单
			indent.setCoding(DataTool.cretaeCoding(key.getId()));
			indent.setUser(us);
			indent.setCreateName(u.getU_name());
			indent.setBeginDate(new Date());
			indent.setEndDate(DataTool.addDateNumber(indent.getBeginDate(),1,indent.getTerm()));
			indentdao.create(indent);
			//建立带发布与订单关联关系
			/*List<RelIndentMarket> list = new ArrayList<RelIndentMarket>();
			if(s!=null){
			for(int i=0;i<s.length;i++){
				if(s[i]!=null){
					list.add(new RelIndentMarket(indent.getId(),Long.valueOf(s[i])));
				}
				}
			}
			if(list!=null&&list.size()!=0)
			indentdao.createRelMarket(list);
			*/
//			扣钱
			//double yue = bs-indent.getMonetary();
			//auservice.updateBalance(new Accounting(AccountingType.订单出账,indent.getMonetary(),yue,indent.getKeyword(),indent.getTerm(),null,uid,u.getU_name()));
			//下单成功
			indent.setStates(1);
			indentdao.update(indent);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	/**
	 * 分页查询
	 * @describe  
	 * @param map
	 * @return List<Indent>
	 * @author LiuCheng
	 * 2013年10月22日 下午3:12:54
	 */
	public List<Indent> findIndentByPage(Map<String,Object> map){
		return indentdao.findIndentByPage(map);
	}
	/**
	 * 总数
	 * @describe  
	 * @param map
	 * @return Integer
	 * @author LiuCheng
	 * 2013年10月22日 下午3:13:02
	 */
	public Integer count(Map<String,Object> map){
		return indentdao.count(map);
	}
	
	@Override
	protected IndentDao getDao() {
		return this.indentdao;
	}
	
	/**
	 * 
	* @Title: shangchuan 
	* @Description:(上传ICON,APP风格) 
	* @return void    返回类型 
	* @author LiuCheng
	* @throws
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean shangchuan(byte[] b,Long userId,Integer style,String name){
		boolean success = false;
//		WsMtPicture pic = new WsMtPicture(b,"Icon");
//		picturedao.create(pic);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("picId",pic.getId());
//		map.put("userId",userId);
//		picturedao.createIcon(map);
//		map.put("style",style);
//		map.put("appName",name);
//		userservice.updateStyle(map);
		success = true;
		return success;
	}
	

}
