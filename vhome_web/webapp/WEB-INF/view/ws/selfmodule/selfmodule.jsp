<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" method="post" action="ws/getUserAndSelfModuleById">
	<input type="hidden" name="modulename" value="${requestScope.moduleName}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" />
	<input type="hidden" name="id" value="${requestScope.moduleId }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/getUserAndSelfModuleById" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					栏目名称：<input type="text" name="modulename" value="${requestScope.moduleName }" />
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
			<li><a class="add" href="ws/selfmodule/addSelfModule.jsp" title="添加栏目" target="dialog" width="650" height="400"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteSelfModuleInfo?id={sid_smod}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="ws/findSelfModuleById?id={sid_smod}" title="修改栏目" target="dialog" width="650" height="400"><span>修改</span></a></li>
			<li><a class="edit" href="ws/getSMContent?id={sid_smod}" title="栏目内容" target="navTab" rel="page44"><span>栏目内容</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>

				<th width="20%">栏目名称</th>
				<th width="20%">创建人</th>
				<th width="20%">创建时间</th>
				<th width="20%">修改时间</th>
				

			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.selfModuleList}" var="m" >
			<tr target="sid_smod" rel="${m.id}">
			    <td>${m.module_name}</td>
			    <td>${m.createName }</td>
				<td><fmt:formatDate value="${m.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
				<td><fmt:formatDate value="${m.updateTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp"></c:import>
</div>