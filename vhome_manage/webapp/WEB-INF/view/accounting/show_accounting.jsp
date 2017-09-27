<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
	
	1.id="lcForm" 必须，标示分页表单
	2.class="search_submit" 必须 ，标示搜索提交按钮
	3.<input type="hidden" name="pageNum" value="1" id="pageNum" /> 代表当前页 
	4.<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/> 每页显示多少条 
	5.id="mytable" 标示采用定义好的表格
	6.<div class="pagination" totalCount="${totalCount}" 	numPerPage="${numPerPage}" 	pageNumShown="10" currentPage="${curPage}">		
	</div> 分页必须的，接收从服务器端传过来的参数
	totalCount  总数
	numPerPage  每页数量
	pageNumShown 页脚显示分页码最多数量
	currentPage  当前页
	 -->
	<img alt="" src="images/agent011.png" width="15%">
	<div class="centerPage">
	<form action="findAccountingByPage" id="lcForm">
	<!-- 搜索框 -->
	<table class="biaogedaoh">
		<tr>
			<td>
				账务类型：
				<select name="accountingType">
					<option value="0">所有...</option>
					<option value="2" ${(accountingType==2)?'selected=selected':''}>订单出账</option>
					<option value="3" ${(accountingType==3)?'selected=selected':''}>订单返款</option>
					<option value="1" ${(accountingType==1)?'selected=selected':''}>银行充值</option>
				</select>
			</td>
			<c:if test="${sessionScope.agtsessionkey.role.id==1 }">
			<td>
				代理公司：
				<select name="agentName">
					<option value=''>所有...</option>
					<c:forEach items="${coms }" var="com">
						<option ${agentName==com.companyname?'selected=selected':'' } value="${com.companyname}">${com.companyname }</option>
					</c:forEach>
				</select> 
			</td>
			</c:if>
			<td>
				<button type="button" class="search_submit" >查询</button>
			</td>
		</tr>
	</table>
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	</form>
	<hr>
	<table id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th width="15"></th>
				<th width="68">账务类型</th>
				<th width="68">消费金额</th>
				<th width="68">账户余额</th>
				<th width="80">关键词</th>
				<th width="35">年限</th>
				<th width="68">返款金额</th>
				<th width="120">生成时间</th>
				<c:if test="${sessionScope.agtsessionkey.role.id==1 }">
				<th>公司</th>
				</c:if>
				<th width="60">操作人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="index" begin="0" end="9" step="1">
			<c:if test="${index.count%2!=0 }">
				<tr style="background-color: #FFFFFF;">
			</c:if>
			<c:if test="${index.count%2==0 }">
				<tr style="background-color: #EDEDED;">
			</c:if>
				<td>
				<c:if test="${not empty accountings[i] }">
				${i+1 }
				</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${accountings[i].type==1 }">银行充值</c:when>
						<c:when test="${accountings[i].type==2 }">订单出账</c:when>
						<c:when test="${accountings[i].type==3 }">订单返款</c:when>
					</c:choose>
				</td>
				<td>${accountings[i].sum }</td>
				<td>${accountings[i].balance }</td>
				<td>${accountings[i].antistop }</td>
				<td>${accountings[i].term }</td>
				<td>${accountings[i].rebate }</td>
				<td><fmt:formatDate value="${accountings[i].createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				<c:if test="${sessionScope.agtsessionkey.role.id==1 }">
				<td>${accountings[i].firmName}</td>
				</c:if>
				<td>${accountings[i].createName }</td>
			 </tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="display: inline; font-size: 12px;color:#0e4354;">总记录数：${totalCount }</div>
	<!--  分页-->
	<div class="pagination" totalCount="${totalCount}" 	numPerPage="${numPerPage}" 	pageNumShown="10" currentPage="${curPage}">		
	</div>
	</div>
</body>
</html>