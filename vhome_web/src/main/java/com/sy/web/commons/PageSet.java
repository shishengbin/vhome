package com.sy.web.commons;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
/**
 *分页基本设置
 *@author sss 2013-8-13 
 */
public class PageSet {
	private Integer currPage = 1;  // 当前页码，默认第一页
	private Integer pageSize = 10; // 每页的数量(结束索引)
	private Integer totalCount = 0;// 总数量
	private String sort = "desc";  // 排序规则
	
	private Integer wap_pageSize=6;//移动端每页显示的数量

	public Integer getWap_pageSize() {
		return wap_pageSize;
	}

	public void setWap_pageSize(Integer wap_pageSize) {
		this.wap_pageSize = wap_pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getLimit() {
		return pageSize * (currPage - 1);
	}
	public Integer getWapLimit() {
		return wap_pageSize * (currPage - 1);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	// 计算总页数
	public Integer getTotalPage() {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount/ pageSize + 1;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 分页设置
	 * @param request
	 * @param map
	 */
	protected void setPagination(HttpServletRequest request,
			Map<String, Object> map) {
		String page = request.getParameter("pageNum");//页码
		String rows = request.getParameter("numPerPage");//每页显示数量
	    
		Integer intPage = Integer.valueOf((StringUtils.isBlank(page) || "0"
				.equals(page)) ? currPage.toString() : page);
		this.setCurrPage(intPage);
		Integer number = Integer.valueOf((StringUtils.isBlank(rows) || "0"
				.equals(rows)) ? pageSize.toString() : rows);
		this.setPageSize(number);
		map.put("lowerLimit", this.getLimit());
		map.put("upperLimit", this.getPageSize());
		
		request.setAttribute("curPage", intPage);
		request.setAttribute("numPerPage", number);
	
	}
	
	protected void setWapPagination(HttpServletRequest request,
			Map<String, Object> map) {
		String page = request.getParameter("pageNum");//页码
		String rows = request.getParameter("numPerPage");//每页显示数量
	    
		Integer intPage = Integer.valueOf((StringUtils.isBlank(page) || "0"
				.equals(page)) ? currPage.toString() : page);
		this.setCurrPage(intPage);
		Integer number = Integer.valueOf((StringUtils.isBlank(rows) || "0"
				.equals(rows)) ? wap_pageSize.toString() : rows);
		this.setWap_pageSize(number);
		map.put("lowerLimit", this.getWapLimit());
		map.put("upperLimit", this.getWap_pageSize());
		
		request.setAttribute("curPage", intPage);
		request.setAttribute("numPerPage", number);
	
	}
	
}
