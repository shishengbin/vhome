<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>

<form id="pagerForm" method="post" action="ws/findAllPublicByPage">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="sys/findComPicByPage" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					友情提醒：为了达到最佳效果，请上传4张轮播图片！
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sys/addcompic.jsp" title="轮播图片" target="dialog"  width="650" height="510"><span>上传图片</span></a></li>
			<li><a class="delete" href="sys/delComPic?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="sys/findComPicById?id={sid_user}" title="修改轮播图片" target="dialog" width="650" height="500"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="20%" align="center">图片名称</th>
				<th width="20%" align="center">图片展示</th>
				<th width="10%" align="center">创建人</th>
				<th width="20%" align="center">创建时间</th>
				<th width="20%" align="center">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${requestScope.complist}" var="cp" >
			<tr target="sid_user" rel="${cp.id}">
			    <td>${cp.picname}</td>
			    <td><img src="http://localhost/appimages/${cp.picurl}" style="width: 85px;height: 65px"/></td>
			    <td>${cp.createName}</td>
				<td><fmt:formatDate value="${cp.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				<td><fmt:formatDate value="${cp.updateTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="/ws/pageControl/paging.jsp" />
</div>
<style>

</style>