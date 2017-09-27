<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ws/findAllSupplierByPage" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent"  style="float: left;">
			<tr>
				<td>
					供应商名称：
					<input type="text" id="suppcompany" name="suppcompany" value="">
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
			<li><a class="add" href="ws/preAddSupplier" target="dialog" width="620" height="360" title="添加供应商"><span>添加</span></a></li>
			<!-- <li><a class="delete" href="ws/deleteSupp?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除</span></a></li> -->
			<li><a class="edit" href="ws/findSuppById?id={sid_user}" target="dialog" width="620" height="360" title="修改供应商" ><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%">供应商名称</th>
				<th width="20%">负责人</th>
				<th width="20%">联系电话</th>
				<th width="10%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.supplist}" var="sp">
				<tr target="sid_user" rel="${sp.id }">
					<td>${sp.suppcompany}</td>
					<td>${sp.suppname}</td>
					<td>${sp.supptel}</td>
					<td>${sp.createName }</td>
					<td><fmt:formatDate value="${sp.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${sp.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<c:import url="../pageControl/paging.jsp" />
</div>