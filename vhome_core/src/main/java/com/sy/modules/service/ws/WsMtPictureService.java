package com.sy.modules.service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sy.modules.common.AbstractService;
import com.sy.modules.dao.ws.WsPictureDao;
import com.sy.modules.entity.ws.WsMtPicture;

/**
 *图片业务层
 *@author LiuCheng 2013-8-27 
 */
@Component
public class WsMtPictureService extends AbstractService<WsMtPicture,Long,WsPictureDao>{

	@Autowired
	private WsPictureDao picturedao;
	@Override
	protected WsPictureDao getDao() {
		return picturedao;
	}
}
