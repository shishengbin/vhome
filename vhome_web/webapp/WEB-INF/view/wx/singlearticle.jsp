<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					图文标题：<input type="text" name="title" value=""/>
				</td>
			</tr>
		</table>
		<c:import url="../ws/pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="wx/PreAddSinleArticle" target="navTab" title="添加单图文"><span>添加单图文</span></a></li>
			<li><a class="delete" href="wx/delSingleArticle?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="wx/findSingleArticleById?id={sid_user}" target="navTab" title="修改单图文"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" layoutH="112" style="width: 100%">
		<thead>
			<tr>
				<th width="15%">图文标题</th>
				<th width="20%">图文描述</th>
				<th width="10%">创建人</th>
				<th width="18%">创建时间</th>
				<th width="18%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.artList}" var="a">
				<tr target="sid_user" rel="${a.id}" >
					<td height="24px">${a.title}</td>
					<td>${a.description}</td>
					<td>${a.createName}</td>
					<td><fmt:formatDate value="${a.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
					<td><fmt:formatDate value="${a.updateTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../ws/pageControl/paging.jsp">
	</c:import>
</div>
