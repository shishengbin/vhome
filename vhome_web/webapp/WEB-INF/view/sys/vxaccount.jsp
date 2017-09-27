<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return validateCallback(this, navTabAjaxDone)" action="sys/findVxaccount" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					<b>友情提醒：</b>公众号信息请保持与微信平台一致。
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" href="${pageContext.request.contextPath}/sys/findAccountById?id={sid_user}" title="编辑公众账号" target="navTab"><span>绑定公众账号</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="120" align="center">公众号名称</th>
				<th width="120" align="center">微信号</th>
				<th width="120" align="center">关注总数</th>
				<th width="120" align="center">本月关注</th>
				<th width="120" align="center">创建时间</th>
				<th width="120" align="center">更新时间</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="${account.id }">
				<td align="center" style="height: 180px">${account.ac_name }</td>
				<td align="center">${account.ac_wx_number }</td>
				<td align="center">暂无</td>
				<td align="center">暂无</td>
				<td align="center">
				  <fmt:formatDate value="${account.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate>
				</td>
				<td align="center"><fmt:formatDate value="${account.createTime}" pattern="yyyy--MM--dd"></fmt:formatDate></td>
			</tr>
		</tbody>
	</table>

</div>

