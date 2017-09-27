<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<script type="text/javascript" src="../../scripts/news.js"></script>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ws/showNews" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					新闻标题：<input type="text" name="title" value="${title}"/>
				</td>
				<td>
					新闻日期：<input type="text" class="date" readonly="true" name="date" value="${date}"/>
				</td>
				<td>
					新闻类型：
					<select name="newsClass" id="newsClass" zhi="${newsClass}">
						<option value="0">请选择</option>
					</select>
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
			<li><a class="add" href="ws/preAddNews" target="navTab" rel="page34" title="添加新闻"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteNews?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" rel="page2"><span>删除</span></a></li>
			<li><a class="edit" href="ws/newsDetails?id={sid_user}&result=xg" target="navTab" rel ="page33" title="修改新闻"><span>修改</span></a></li>
			<li><a class="edit" href="ws/newsDetails?id={sid_user}&result=ck" target="navTab" rel="page35"><span>查看</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" layoutH="112" style="width: 100%">
		<thead>
			<tr>
				<th width="15%">标题</th>
				<th width="15%">内容</th>
				<th width="12%">分类</th>
				<th width="10%">作者</th>
				<th width="10%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newsList}" var="news">
				<tr target="sid_user" rel="${news.id }">
					<td>
						<c:choose>
    						<c:when test="${fn:length(news.newsTitle) > 15}">
     							<c:out value="${fn:substring(news.newsTitle, 0, 15)}......" />
   							</c:when>
    						<c:otherwise>
     							<c:out value="${news.newsTitle}" />
    						</c:otherwise>
   						</c:choose>
					</td>
					<td>
						<c:choose>
    						<c:when test="${fn:length(news.newsContent) > 15}">
     							<c:out value="${fn:substring(news.newsContent, 0, 15)}......" />
   							</c:when>
    						<c:otherwise>
     							<c:out value="${news.newsContent}" />
    						</c:otherwise>
   						</c:choose>
					</td>
					<td>
						${news.newsClass.remark }
					</td>
					<td>${news.newsAuthor}</td>
					<td>${news.createName }</td>
					<td>
						<fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td><fmt:formatDate value="${news.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
	</c:import>
</div>
<script type="text/javascript">
	$(function(){
		LoadNewsList("newsClass");
	});
</script>