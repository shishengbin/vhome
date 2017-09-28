<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ws/newsClassAll" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent"  style="float: left;">
			<tr>
				<td>
					新闻类型：
					<input id="newsCatText" type="text" name="newsCatText" value="${newsCatText }">
				</td>
			</tr>
		</table>
		<c:import url="../pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/preAddNewsCategory" target="dialog" width="500" height="240" title="添加分类"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteNewsClass?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除</span></a></li>
			<li><a class="edit" href="ws/showIdNewsClass?id={sid_user}" target="dialog" width="500" height="240" title="添加新闻类别" ><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%">类别名称</th>
				<th width="10%">新闻总数</th>
				<th width="10%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newsClass}" var="nc">
				<tr target="sid_user" rel="${nc.id }">
					<td><a class="sortfont" href="ws/showNews?newsClass=${nc.id }" target="navTab" style="color: #945305;font-family: '宋体';font-size: 13px">${nc.remark }</a></td>
					<td>${nc.count }</td>
					<td>${nc.createName }</td>
					<td><fmt:formatDate value="${nc.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${nc.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<c:import url="../pageControl/paging.jsp" />
</div>