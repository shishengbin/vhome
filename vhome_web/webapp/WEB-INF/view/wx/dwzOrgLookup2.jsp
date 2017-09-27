<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<form id="pagerForm" action="wx/findSingleArticlesByPage">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage }" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="wx/findSingleArticlesByPage" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>图文标题:</label>
				<input class="textInput" name="orgName" value="" type="text">
			</li>	  
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="orgId" warn="请选择部门">选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="orgId" /></th>
				<th orderfield="orgName">图文标题</th>
				<th orderfield="orgNum">图文描述</th>
				<th orderfield="leader">创建人</th>
				<th orderfield="creator">创建时间</th>
				<th orderfield="creator">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		     <c:forEach items="${requestScope.artList}" var="a">
			<tr>
				<td><input type="checkbox" name="orgId" value="{id:'${a.id }', orgName:'${a.title }'}"/></td>
				<td>${a.title }</td>
				<td>${a.description }</td>
				<td>${a.createName }</td>
				<td><fmt:formatDate value="${a.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
				<td><fmt:formatDate value="${a.updateTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
			</tr>
			</c:forEach>
		
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select  name="numPerPage" style="width:28px;" onchange="dwzPageBreak({targetType:dialog, numPerPage:'${numPerPage}'})">
				<option value="10" selected="selected">10</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${curPage}"></div>
	</div>
</div>