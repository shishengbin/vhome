package com.sy.modules.dao.wx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.entity.wx.menu.Button;
import com.sy.modules.entity.wx.menu.CommonButton;
import com.sy.modules.entity.wx.menu.ComplexButton;

@Component
@MyBatisRepository
public interface MenuDao {
	public List<CommonButton> findMenu(long userid);

	public void saveMenu(List<CommonButton> cb);

	//添加本地主菜单
	public void savefatMenu(ComplexButton complexBtn);
    //添加子菜单
	public void saveSonMenu(CommonButton commonBtn);
	//修改菜单
	public void updateMenu(Button lcb);

	public void delSonMenu(long bt_id);

	public void delFatMenu(long bt_id);
	
	//-------------------------------------
	//查询主菜单
	public ComplexButton[] findParentMenu(long accountid);
	
	//查询主菜单总数
	public int findParentMenuCount(long accountid);
	
	//根据id查询单个菜单
	public CommonButton findMenuByid(long id);
	
	//根据主菜单id查询子菜单
	public CommonButton[] findSonMenusByFatId(@Param("fatid")long fatid,@Param("accountid")long accountid);
	
	//查询子菜单总数
	public int findSonMenuCountByParent(@Param("fatid")long fatid,@Param("accountid")long accountid);
	
}
