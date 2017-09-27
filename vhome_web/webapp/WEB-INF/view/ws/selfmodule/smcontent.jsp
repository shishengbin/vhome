<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" method="post" action="ws/getSMContent">
	<input type="hidden" name="smctitle" value="${requestScope.smcTitle}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" />
	<input type="hidden" name="id" value="${requestScope.moduleId }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/getSMContent" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					标题：<input type="text" name="smctitle"  />
					<input type="hidden" name="id" value="${requestScope.moduleId }"/>
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
			<li><a class="add" href="ws/skipSmContentAdd?id=${moduleId}" title="添加栏目内容" target="navTab" rel="page42"><span>添加</span></a></li>
			<li><a class="delete" href="ws/removeSmContentById?id={sid_smod}" target="ajaxTodo" title="确定要删除吗?" rel="page45"><span>删除</span></a></li>
			<li><a class="edit" href="ws/getSmContentOnly?id={sid_smod}&identify=xg" title="修改栏目内容" target="navTab" rel="page40"><span>修改</span></a></li>
			<li><a class="edit" href="ws/getSmContentOnly?id={sid_smod}&identify=ck" title="查看栏目内容" target="dialog" rel="page41"  width="750" height="420"><span>查看</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="20%">标题</th>
				<th width="20%">内容</th>
				<th width="20%">创建人</th>
				<th width="20%">创建时间</th>
				<th width="20%">修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.smclist }" var="s" >
			<tr target="sid_smod" rel="${s.id }">
			    <td>
			    <c:choose>
    						<c:when test="${fn:length(s.scm_title) > 15}">
     							<c:out value="${fn:substring(s.scm_title, 0, 15)}......" />
   							</c:when>
    						<c:otherwise>
     							<c:out value="${s.scm_title}" />
    						</c:otherwise>
   						</c:choose>
			    </td>
			    <td>
			     <c:choose>
    						<c:when test="${fn:length(s.smc_content) > 15}">
     							<c:out value="${fn:substring(s.smc_content, 0, 15)}......" />
   							</c:when>
    						<c:otherwise>
     							<c:out value="${s.smc_content}" />
    						</c:otherwise>
   						</c:choose>
			    </td>
				<td>${s.createName }</td>
				<td><fmt:formatDate value="${s.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
				<td><fmt:formatDate value="${s.updateTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
			</tr>
		</c:forEach> 
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp"></c:import>
</div>