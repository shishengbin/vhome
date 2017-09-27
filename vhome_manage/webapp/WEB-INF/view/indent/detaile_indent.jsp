<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/indent_detail.css" />
</head>
<body>
<img alt="" src="images/agent023.png" width="15%">
<input style="float: right;margin-top: 8px;margin-right: 15px;" class="fanh" type="button" value="返回" onclick="se('findIndentByPage')">
<div class="dindxqxx">
<hr>
<div class="ekd">
<fieldset>
	<legend>订单信息</legend>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
			订单类型：${indent.type==0?'主订单':'附加订单'}
			</td>
			<td>
			订单状态：
			<%-- <c:choose>
				<c:when test="">审核中</c:when>
				<c:when test="">已通过</c:when>
				<c:when test="">已撤销</c:when>
				<c:when test="">未通过</c:when>
			</c:choose> --%>
			</td>
			<td>
			订单金额：
			</td>
			<td>
			代为制作：${indent.agency==0?'自行制作':'由索远科技代理制作' }
			</td>
			<td>注册编码：${indent.coding}</td>
		</tr>
		<tr>
			<td colspan="5">
			合同编号：${indent.formalid }
			</td>
			<!-- <td colspan="4">
				服务终止日期：
			</td>
			 -->
		</tr>
		<c:if test="">
		<tr>
			<td colspan="6">代发布三方市场：
				<c:forEach items="" var="market">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</td>
		</tr>
		</c:if>
	</table>
</fieldset>
<fieldset style="display: inline;">
	<legend>客户信息</legend>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td width="30%">企业名称：${indent.user.company.companyname } </td>
			<td>联系人：${indent.user.company.companyperson }</td>
			<td>联系电话：${indent.user.company.companyphone }</td>
			<td>邮箱：${indent.user.company.companyemail}</td>
		</tr>
		<tr>
			<td >后台账号：${indent.user.username }</td>
			<td >密码：${indent.user.userpass }</td>
			<td> 账号状态：${indent.user.userstuts==0?'未激活':'已激活' }</td>
			<td>主营业务：${indent.user.company.companybusiness }</td>
		</tr>
	</table>
</fieldset>
<fieldset style="display: inline;">
	<legend>APP信息</legend>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>APP名称：${indent.user.key.kw_name }  </td>
			<!-- 
			<td>关键词类型： </td>
			<td>关键词价格：</td> 
			<td>APP类型：</td>
			-->
		</tr>
		<tr>
			<td colspan="4">APP后台地址：<a href="javascript:void(0)" onclick="window.open('http://www.szsuoyuan.com/login.jsp')">http://www.szsuoyuan.com/login.jsp</a></td>
		</tr>
	</table>
</fieldset>
</div>

</div> 
</body>
<script type="text/javascript">
</script>
</html>