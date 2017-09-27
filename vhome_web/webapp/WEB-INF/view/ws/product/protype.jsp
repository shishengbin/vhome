<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="ws/findOneAllByPage"  method="post">
		<input type="hidden" name="pageNum" value="1"  /><!-- 代表当前页 -->
		<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
		<input type="hidden" value="${id}" name="id">
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
			<li><a class="add" href="ws/preAddProType" target="dialog" width="600" height="440" title="添加分类"><span>添加</span></a></li>
			<li><a class="delete" href="ws/deleteProType?id={sid_user}&flag=fis" target="ajaxTodo" title="确定要删除吗?" rel="page32"><span>删除</span></a></li>
			<li><a class="edit" href="ws/findProTypeById?id={sid_user}" target="dialog" width="600" height="400" title="修改分类"><span>修改</span></a></li>
			<li><a class="edit" href="ws/findSecondAllByPage?id={sid_user}" target="navTab" rel="page88" ><span>二级分类</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%">类别名称</th>
				<!-- <th width="10%">下级总数</th> -->
				<th width="10%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后修改时间</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<c:forEach items="${requestScope.parentTypeList}" var="t">
			<tr target="sid_user" rel="${t.id }">
				<td>${t.tname }</td>
				<td>${t.createName}</td> 
				<td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${t.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>
