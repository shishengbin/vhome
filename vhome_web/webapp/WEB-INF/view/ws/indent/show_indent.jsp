<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="ws/indent/findAllByPage" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<ul class="searchContent" style="display:inline;">
			<li>
				<label>订单编号：</label>
				<input type="text" name="number" value="${number }"/>
			</li>
			<li style="width: 700px;">
				<label>订单状态：</label>
				<select name="state">
					<option value="-1">全部</option>
					<option value="0" ${state==0?"selected=selected":"" }>未付款</option>
					<option value="1" ${state==1?"selected=selected":"" }>待发货</option>
					<option value="2" ${state==2?"selected=selected":"" }>待收货</option>
					<option value="3" ${state==3?"selected=selected":"" }>交易关闭</option>
					<option value="4" ${state==4?"selected=selected":"" }>交易完成</option>
				</select>
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
			<li><a class="delete" href="ws/indent/deleteIndent?number={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" style="width: 100%" layoutH="112">
		<thead>
			<tr>
				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%">订单编号</th>
				<th width="15%">订单名称</th>
				<th width="8%">订单金额</th>
				<th width="10%">下单人</th>
				<th width="8%">支付方式</th>
				<th width="8%">订单状态</th>
				<th width="10%">下单时间</th>
				<th width="10%">完成时间</th>
				<th width="6%">操作</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${indents}" var="indent" varStatus="index">
				<tr target="sid_user" rel="${indent.number }">
				<td width="10"><input name="ids" value="${indent.number}" type="checkbox"></td>
				<td>${indent.number}</td>
				<td>${indent.name }</td>
				<td>${indent.money }</td>
				<td>${indent.human.human_name }</td>
				<td style="color:red;">${indent.payway==1?"货到付款":"支付宝"}</td>
				<td>
					<c:choose>
						<c:when test="${indent.status ==0}">
							<c:out value="未付款"></c:out>
						</c:when>
						<c:when test="${indent.status ==1}">
							<c:out value="已付款待发货"></c:out>
						</c:when>
						<c:when test="${indent.status ==2}">
							<c:out value="等待买家收货"></c:out>
						</c:when>
						<c:when test="${indent.status ==3}">
							<c:out value="交易关闭"></c:out>
						</c:when>
						<c:when test="${indent.status ==4}">
							<c:out value="交易成功"></c:out>
						</c:when>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${indent.createTime }" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>${indent.sendtime}</td>
				<td>
				<c:if test="${indent.status ==1}">
				<a href="ws/indent/add_transportation.jsp?indentNumber=${indent.number}" target="dialog" title="确定要发货吗?"><span>确认发货</span></a>
				</c:if>
				<c:if test="${indent.status ==2}">
				<a href="ws/indent/updateIndentById?number=${indent.number}&status=4" target="ajaxTodo" title="确定完成交易吗?"><span>完成交易</span></a>
				</c:if>
				<c:if test="${indent.status ==3}">
				交易关闭
				</c:if>
				<c:if test="${indent.status ==4}">
				交易完成
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>