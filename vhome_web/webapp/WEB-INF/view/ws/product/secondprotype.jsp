<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageHeader">
 <form id="pagerForm" onsubmit="return navTabSearch(this);" action="ws/findSecondAllByPage" method="post">
    <input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<input type="hidden" name="id" value="${tid}"/>
	<div class="searchBar">
		<ul class="searchContent" style="display: inline;">
			<li>
				<label>类型名称</label>
				<input type="text" name="tname" value="${tname}"/>
			</li>
		</ul>
		<c:import url="../pageControl/retrieval.jsp" />
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/findParentTypeBySecondtid?id=${tid}&flag=addtype" target="dialog" width="600" height="440" title="添加二级分类"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteProType?id={sid_user}&flag=sec" target="ajaxTodo" title="确定要删除吗?" rel="page32"><span>删除</span></a></li>
			<li><a class="edit" href="ws/findParentTypeBySecondtid?id={sid_user}&flag=updtype" target="dialog" width="600" height="440" title="修改二级分类"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="20%">类别名称</th>
				<th width="20%">上级分类</th>
				<th width="10%">产品总数</th>
				<th width="10%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后修改时间</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<c:forEach items="${requestScope.secondtypelist}" var="t">
			<tr target="sid_user" rel="${t.id }">
				<td>${t.tname }</td>
				<td>${ptname}</td>
				<td></td>
				<td>${t.createName}</td> 
				<td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${t.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <c:import url="../pageControl/paging.jsp">
	</c:import>
</div>
