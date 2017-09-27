<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="ws/showMessages" method="post">
		<div class="searchBar">
			<table class="searchContent" style="float: left;">
			<tr>
				<td>
					起始日期：<input type="text" class="date" readonly="true" name="qidate" value="${qidate }"/>
				</td>
				<td>
					终止日期：<input type="text" class="date" readonly="true" name="zhongdate" value="${zhongdate}"/>
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
			<li><a class="edit" href="ws/showReply?messageId={sid_user}&result=show" target="navTab" warn="请选择一条留言" rel="page3"><span>回复</span></a></li>
			<!-- <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="#" class="delete"><span>批量删除</span></a></li> -->
			<li><a class="delete" href="ws/deleteMessage?messageId={sid_user}"
				target="ajaxTodo" warn="请选择一条留言" title="确定删除吗？"><span>删除</span></a></li>
			<li><a class="edit" href="ws/showMessages?result=xq" target="navTab"><span>详情</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="5%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%">留言者</th>
				<th width="10%">主题</th>
				<th width="10%">留言内容</th>
				<th width="10%">留言时间</th>
				<th width="10%">手机号</th>
				<th width="10%">QQ</th>
				<th width="10%">邮箱</th>
				<th width="20%">回复信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messages}" var="message" varStatus="index">
				<tr target="sid_user" rel="${message.id }">
					<td><input name="ids" value="${message.id }" type="checkbox" style="width: 15px;"></td>
					<td><c:out value="${message.createName }"></c:out> </td>
					<td><c:out value="${message.messageTitle }"></c:out> </td>
					<td><c:out value="${message.messageContent }"></c:out> </td>
					<td>
						<fmt:formatDate value="${message.createTime}" pattern="yyy-MM-dd HH-mm-ss"/>
					</td>
					<td><c:out value="${message.messageQQ}"></c:out> </td>
					<td><c:out value="${message.messagePhone }"></c:out> </td>
					<td><c:out value="${message.messageEmail}"></c:out> </td>
					<td>
						<a href="ws/showReply?messageId=${message.id }&result=simple" target="dialog" title="留言板"><c:out value="${message.reply.size()}"></c:out></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="ws/showMessages"></c:param>
	</c:import>
</div>
