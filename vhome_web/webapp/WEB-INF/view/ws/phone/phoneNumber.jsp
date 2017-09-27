<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<form id="pagerForm" method="post" action="ws/showphone">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/showphone" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					名称：<input type="text" name="name" value="${name}"/>
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
			<li><a class="add" href="ws/preAddPhone" target="dialog" title="添加号码" ><span>添加</span></a></li>
			<li><a class="delete" href="ws/deletephone?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" rel="page2"><span>删除</span></a></li>
			<li><a class="edit" href="ws/phoneDetails?id={sid_user}&result=xg" target="dialog" title="修改号码"><span>修改</span></a></li>
		<!--<li><a class="edit" href="ws/phoneDetails?id={sid_user}&result=ck" target="dialog" width="750" height="400"><span>查看</span></a></li>-->	
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%">名称</th>
				<th width="20%">号码</th>
				<th width="10%">创建人</th>
				<th width="20%">创建时间</th>
				<th width="20%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${phoneList}" var="phone">
				<tr target="sid_user" rel="${phone.id }">
					<td>${phone.phone_Name}</td>
					<td>${phone.phone_Number}</td>
					<td>${phone.createName}</td>
					<td><fmt:formatDate value="${phone.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
					<td><fmt:formatDate value="${phone.updateTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp"></c:import>
</div>
