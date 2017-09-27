<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" method="post" action="ws/findAllApplogoByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/findAllApplogoByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					图片名称：<input type="text" name="bgtext" value="${bgtext}" />
				</td>
			</tr>
		</table>
		<div class="subBar" style="float: right;">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/applogo/loadbgpic.html" title="首页logo图片" target="dialog"  width="650" height="500"><span>上传图片</span></a></li>
			<li><a class="delete" href="ws/deleteApplogo?id={sid_bgpic}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="ws/queryidApplogo?id={sid_bgpic}&mark=xg" title="修改图片" target="dialog"><span>修改</span></a></li>
			<li><a class="add" href="ws/queryidApplogo?id={sid_bgpic}&mark=ck" title="查看图片" target="dialog"  width="650" height="400"><span>查看</span></a></li>
			<li><a class="edit" href="ws/updateApplogoStatus?id={sid_bgpic}" title="确定设为默认吗？" target="ajaxTodo" ><span>设为默认</span></a></li>
			
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="5%" align="center">默认</th>
				<th width="20%" align="center">图片名称</th>
				<th width="20%" align="center">图片描述</th>
				<th width="10%" align="center">创建人</th>
				<th width="20%" align="center">创建时间</th>
				<th width="20%" align="center">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.piclist}" var="bg" >
			<tr target="sid_bgpic" rel="${bg.bgid}">
				<td>
					<c:if test="${bg.bg_use>0}">
						<label><input type="radio" name="bguse" value="${bg.bgid}" checked="checked"/></label>
					</c:if>
					<c:if test="${bg.bg_use==0}">
						<label><input type="radio" name="bguse" value="${bg.bgid}"/></label>
					</c:if>
				</td>
			    <td>${bg.bgname}</td>
			    <td>${bg.bgremark}</td>
			    <td>${bg.createName}</td>
				<td><fmt:formatDate value="${bg.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				<td><fmt:formatDate value="${bg.updateTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp"></c:import>

</div>

