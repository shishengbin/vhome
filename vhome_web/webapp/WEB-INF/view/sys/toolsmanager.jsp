<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<form id="pagerForm" method="post" action="ws/findAllPublicByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="sys/findComPicByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					友情提醒：系统提供的一些常用小工具,点击路径直接访问！
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%" align="center">工具名称</th>
				<th width="20%" align="center">简介</th>
				<th width="20%" align="center">地址</th>
				<th width="10%" align="center">创建人</th>
				
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.toolslist}" var="t" >
			<tr target="sid_user" rel="${t.id}">
			    <td>${t.tname}</td>
			    <td>${t.tremark}</td>
			    <td><a target="_blank" href="${t.turl }">${t.turl }</a></td>
			    <td>${t.createName}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="/ws/pageControl/paging.jsp" />
</div>
<style>

</style>