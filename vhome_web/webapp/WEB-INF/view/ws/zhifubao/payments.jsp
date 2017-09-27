<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return validateCallback(this, navTabAjaxDone)" action="sys/findVxaccount" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					<b>友情提醒：</b>若将支付方式删除需要重新配置
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sys/findAliPay" target="dialog"  title="添加支付"><span>支付宝</span></a></li>
			<li><a class="edit" href="sys/findVchatpay" target="dialog"  title="添加支付"><span>微支付</span></a></li>
		<!-- <li><a class="delete" href="ws/deleteNews?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除</span></a></li>-->
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="120" align="center">接口名称</th>
				<th width="120" align="center">支付方式</th>
				<th width="120" align="center">启用状态</th>
				<th width="120" align="center">创建时间</th>
				<th width="120" align="center">更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${paylist}" var="p">
			<tr target="sid_user" rel="${p.id}">
				<td align="center">${p.daoname }</td>
				<td align="center">${p.modename }</td>
				<td align="center">${p.status==0?"失效":"启用"}</td>
				<td align="center">
				  <fmt:formatDate value="${p.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate>
				</td>
				<td align="center"><fmt:formatDate value="${p.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>

</div>

