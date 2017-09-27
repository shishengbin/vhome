<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<form id="pagerForm" method="post" action="ws/findAllAdpicByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/findAllAdpicByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					图片名称：<input type="text" name="publictext" value="${publictext}" />
				</td>
			</tr>
		</table>
		<c:import url="../pageControl/retrieval.jsp" />
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/preAddWsAdpic" title="上传图片" target="dialog"  width="650" height="400"><span>上传图片</span></a></li>
			<li><a class="delete" href="ws/deleteAdpic?id={sid_bgpic}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="ws/queryAdpicById?id={sid_bgpic}&mark=xg" title="修改图片" target="dialog" width="650" height="400"><span>修改</span></a></li>
		<!--<li><a class="add" href="ws/queryAdpicById?id={sid_bgpic}&mark=ck" title="查看图片" target="dialog"  width="650" height="400"><span>查看</span></a></li>  -->
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%"  align="center">图片名称</th>
				<th width="10%"  align="center">创建人</th>
				<th width="15%"  align="center">创建时间</th>
				<th width="15%"  align="center">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.piclist}" var="bg" >
			<tr target="sid_bgpic" rel="${bg.publicid}">
			    <td>${bg.publicname}</td>
			    <td>${bg.createName}</td>
				<td><fmt:formatDate value="${bg.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				<td><fmt:formatDate value="${bg.updateTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>