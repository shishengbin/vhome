<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="ws/indent/findAllWaitSendByPage" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<ul class="searchContent" style="display:inline;">
			<li>
				<label>订单编号：</label>
				<input type="text" name="number" value="${number }"/>
			</li>
		</ul>
		<c:import url="../pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="ws/indent/indentDetails?number={sid_user}" target="navTab" rel="page85"><span>订单详情</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" style="width: 98%" layoutH="91.5">
		<thead>
			<tr>
				<th width="10"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>订单编号</th>
				<th>订单名称</th>
				<th>订单金额</th>
				<th>下单人</th>
				<th>订单状态</th>
				<th>下单时间</th>
				<th>确认发货</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${indents}" var="indent" varStatus="index">
				<tr target="sid_user" rel="${indent.number }" style="height: 30px;">
				<td width="10"><input name="ids" value="${indent.number}" type="checkbox"></td>
				<td>${indent.number}</td>
				<td>${indent.name }</td>
				<td>${indent.money }</td>
				<td>${indent.human.human_name }</td>
				<td>待发货</td>
				<td>
					<fmt:formatDate value="${indent.createTime }" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td><a href="ws/indent/updateIndentById?number=${indent.number}" target="ajaxTodo" title="确定要发货吗?"><span>确认发货</span></a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>