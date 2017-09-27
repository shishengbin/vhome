<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" method="post" action="ws/findAllPublicByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/findAllPublicByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					图片名称：<input type="text" name="publictext" value="${publictext}" />
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
			<li><a class="add" href="ws/publicpic/loadbgpic.html" title="宣传图片" target="dialog"  width="650" height="510"><span>上传图片</span></a></li>
			<li><a class="delete" href="ws/deletepublic?id={sid_bgpic}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="ws/queryPublicById?id={sid_bgpic}&mark=xg" title="修改宣传图片" target="dialog" width="650" height="500"><span>修改</span></a></li>
			<li><a class="add" href="ws/queryPublicById?id={sid_bgpic}&mark=ck" title="查看宣传图片" target="dialog"  width="650" height="500"><span>查看</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%" >图片名称</th>
				<th width="20%"  align="center">图片描述</th>
				<th width="10%"  align="center">创建人</th>
				<th width="20%"  align="center">创建时间</th>
				<th width="20%"  align="center">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.piclist}" var="bg" >
			<tr target="sid_bgpic" rel="${bg.publicid}">
			    <td>${bg.publicname}</td>
			    <td>${bg.publicremark}</td>
			    <td>${bg.createName}</td>
				<td><fmt:formatDate value="${bg.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				<td><fmt:formatDate value="${bg.updateTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>