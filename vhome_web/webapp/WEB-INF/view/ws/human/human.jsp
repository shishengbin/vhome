<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/showHuman" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					饭店名称：<input type="text" name="account" value="${account}"/>
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
			<li><a class="add" href="ws/preAddHuman" target="dialog" title="添加商家信息" width="720" height="450"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteHuman?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" rel="page2"><span>删除</span></a></li>
			<li><a class="edit" href="ws/humanDetails?id={sid_user}&result=xg" target="dialog" title="修改商家信息" width="600" height="360"><span>修改</span></a></li>
		<%--<li><a class="edit" href="ws/humanDetails?id={sid_user}&result=ck" target="dialog" width="600" height="360"><span>查看</span></a></li>--%>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="12%">商家账号</th>
				<th width="10%">手机号</th>
				<th width="10%">联系人</th>
				<th width="12%">商家名称</th>
				<th width="15%">地址</th>
				<th width="12%">入驻时间</th>
				<th width="12%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${humanList}" var="human">
				<tr target="sid_user" rel="${human.id }">
					<td>${human.human_account}</td>
					<td>${human.human_phone }</td>
					<td>${human.human_name }</td>
					<td>${human.human_question }</td>
					<td>${human.human_address }</td>
					<td><fmt:formatDate value="${human.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${human.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="ws/showHuman"></c:param>
	</c:import>
</div>
