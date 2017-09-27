<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="ws/indent/add_payment.jsp"
					target="dialog" rel="page123"><span>添加</span></a></li>
				<li><a class="edit" href="ws/indent/findById?id={sid_user}"
					target="dialog" rel="page124"><span>修改</span></a></li>
				<li><a class="delete" href="ws/indent/deletePayment?id={sid_user}"
					target="ajaxTodo" title="确定要删除吗?" rel="page84"><span>删除</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="list" style="width: 98%" layoutH="55">
			<thead>
				<tr>
					<th width="10"><input type="checkbox" group="ids"
						class="checkboxCtrl"></th>
					<th>名称</th>
					<th>创建时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${payments}" var="pay" varStatus="index">
					<tr target="sid_user" rel="${pay.id }" style="height: 30px;">
						<td width="10"><input name="ids" value="${pay.id}"
							type="checkbox"></td>
						<td>${pay.name}</td>
						<td><fmt:formatDate value="${pay.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
							<c:if test="${pay.state==1 }">
								<td>可用</td>
								<td>
								<a class="edit" href="ws/indent/updatePaymentState?id=${pay.id }&state=0"  target="ajaxTodo" style="font-size: 13px;">禁用</a>
								</td>
							</c:if>
							<c:if test="${pay.state==0 }">
								<td>不可以</td>
								<td>
								<a class="edit" href="ws/indent/updatePaymentState?id=${pay.id }&state=1"  target="ajaxTodo" style="font-size: 13px;">启用</a>
								</td>
							</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../pageControl/paging.jsp" />
	</div>
