<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>

<form id="pagerForm" method="post" action="sys/findAllSiteModulesByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="sys/findAllSiteModulesByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					模块名：<input type="text" name="modulename" value="${modulename }" />
				</td>
			</tr>
		</table>
		<c:import url="/ws/pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sys/addSiteModule.jsp" title="添加栏目" target="navTab"  width="650" height="510"><span>添加栏目</span></a></li>
			<li><a class="delete" href="sys/delSiteModule?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="sys/findComPicById?id={sid_user}" title="修改栏目" target="dialog" width="650" height="500"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%" align="center">名称</th>
				<th width="20%" align="center">类型</th>
				<th width="10%" align="center">创建人</th>
				<th width="20%" align="center">创建时间</th>
				<th width="20%" align="center">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.moduleList}" var="m" >
			<tr target="sid_user" rel="${m.id}">
			    <td>${m.f_name}</td>
			    <td>${m.f_type==1?"自主栏目":"系统栏目"}</td>
			    <td>${m.createName}</td>
				<td><fmt:formatDate value="${m.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				<td><fmt:formatDate value="${m.updateTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="/ws/pageControl/paging.jsp" />
</div>
<style>

</style>