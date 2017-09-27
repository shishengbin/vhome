<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					关键词名称：<input type="text" name="title" value=""/>
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
			<li><a class="add" href="wx/preAddKeyWord" target="navTab" ><span>添加关键字</span></a></li>
			<li><a class="delete" href="wx/delKeyword?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除</span></a></li>
			<!-- <li><a class="edit" href="wx/findKeywordById?id={sid_user}" target="navTab" title="修改关键字" ><span>修改</span></a></li> -->
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" layoutH="112" style="width: 100%">
		<thead>
			<tr>
				<th width="15%">关键词名称</th>
				<th width="18%">回复标题</th>
				<th width="8%">回复类型</th>
				<th width="8%">匹配类型</th>
				<th width="10%">创建人</th>
				<th width="10%">创建时间</th>
				<th width="10%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.keywordList}" var="k">
				<tr target="sid_user" rel="${k.id }" >
					<td height="24px">${k.keyname }</td>
					<c:if test="${k.article_type==0 }">
					<td>${k.content }</td>
				    </c:if>
					<td>${k.title }</td>
					<c:if test="${k.article_type==0 }">
					 <td style="color: blue;">文本</td>
					</c:if>
					<c:if test="${k.article_type==1 }">
					 <td style="color: blue;">单图文</td>
					</c:if>
					<c:if test="${k.article_type==2}">
					 <td style="color: blue;">多图文</td>
					</c:if>
					
					<td style="color: red;">完全匹配</td>
					<td>${k.createName }</td>
					<td><fmt:formatDate value="${k.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
					<td><fmt:formatDate value="${k.updateTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../ws/pageControl/paging.jsp">
	</c:import>
</div>
