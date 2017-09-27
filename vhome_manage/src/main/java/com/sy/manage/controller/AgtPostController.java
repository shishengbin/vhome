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
import com.sy.modules.entity.agt.AgtPost;
import com.sy.modules.service.agt.AgtPostService;

/**
 * 公告信息
 * @author zw 2013-10-17
 */
@Controller
public class AgtPostController extends PageSet {
	
	private static final Logger log = LoggerFactory.getLogger(AgtPostController.class);
	@Autowired
	private AgtPostService aps;

	// 查看公告条目
	@RequestMapping("/showPosts")
	public String showPosts(HttpServletRequest request,HttpServletResponse response) {
		log.info("entering...AgPostController...showPosts()");
		Long aid = SessionUtil.getAgtLoginUser(request, response).getFatherId();
		if (aid == 1) {
			aid = SessionUtil.getAgtLoginUser(request, response).getId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		this.setPagination(request, map);
		List<AgtPost> list = aps.showAgPostBypage(map);
		Long totalCount = aps.count();
		request.setAttribute("postList", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("aid", aid);
		return "post/show_post";
	}

	// 查看公告内容
	@RequestMapping("/onePost")
	public String onePost(HttpServletRequest request) {
		log.info("entering...AgPostController...onePost()");
		Long id = Long.parseLong(request.getParameter("id"));
		AgtPost ap = aps.findById(id);
		request.setAttribute("onePost", ap);
		return "post/one_post";
	}

	@RequestMapping(value="/preAddPost")
	public String preAddPost(){
		return "post/new_post";
	}
	
	// 新建公告
	@RequestMapping(value = "/addPost")
	@ResponseBody
	public String addPost(HttpServletRequest request) {
		log.info("entering...AgPostController...addPost()");
		AgtPost ap = new AgtPost();
		String content = request.getParameter("postcontent");
		String name = request.getParameter("postname");
		ap.setPostname(name);
		ap.setPostcontent(content);
		aps.create(ap);
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		map.put("message", Constants.MSG_ADD_SUCCESS);
		map.put("forward", "showPosts");
		return gson.toJson(map);
	}

	// 首页显示公告
	@RequestMapping(value = "/headPost")
	@ResponseBody
	public String headPost(HttpServletRequest request) {
		log.info("entering...AgPostController...headPost()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lowerLimit", 0);
		map.put("upperLimit", 3);
		List<AgtPost> list = aps.showAgPostBypage(map);
		request.setAttribute("postList", list);
		map.clear();
		map.put("postList", list);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	// 删除公告
	@RequestMapping(value = "/deletePost")
	@ResponseBody
	public String deletePost(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("entering...AgtPostController...deletePost()");
		aps.delete(Long.parseLong(request.getParameter("pid")));
		return JsonUtil.transferJsonResponse(1, Constants.MSG_DEL_SUCCESS,
				"showPosts");
	}

}
