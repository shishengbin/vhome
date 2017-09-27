package com.sy.modules.dao.ws;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sy.modules.common.MyBatisRepository;
import com.sy.modules.common.ParentDao;
import com.sy.modules.entity.ws.UserPic;
import com.sy.modules.entity.ws.WsMtPicture;

@Component
@MyBatisRepository
public interface WsPictureDao extends ParentDao<WsMtPicture,Long> {
	//注册图片和企业关系 
	void create_user_pic(UserPic obj);
	//根据user_id查找单张图片
	WsMtPicture findByUserId(Long id);
	
	public void createIcon(Map<String,Object> map);
}
