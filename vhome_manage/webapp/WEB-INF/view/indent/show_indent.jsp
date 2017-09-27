<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<img alt="" src="images/agent002.png" width="15%">
	<div class="centerPage">
	<form action="findIndentByPage" id="lcForm">
	<!-- 搜索框 -->
			<table class="biaogedaoh">
				<tr>
					<td>APP名称：<input style="width: 90px;" name="keyword" value="${keyword}" placeholder="名称匹配"></td>
					<td>下单时间：<input type="text" class="Wdate"
						onClick="WdatePicker()" style="width: 90px" name="beginDate" value="${beginDate }" placeholder="起始日期">至<input
						style="width: 90px;" type="text" class="Wdate"
						onClick="WdatePicker()" name="endDate" value="${endDate }" placeholder="结束日期"></td>
					<td><button type="button" class="search_submit">查询</button></td>
				</tr>
			</table>
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	</form>
	<hr>
	<table  id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th width="15"></th>
				<th width="75">订单类型</th>
				<th width="100">APP</th>
				<th>客户名称</th>
				<!-- <th width="35">年限</th> -->
				<!-- <th width="60">现价</th> -->
				<th width="75">创建时间</th>
				<th width="40">状态</th>
				<c:if test="${sessionScope.agtsessionkey.role.id==1 }">
				<th>代理公司</th>
				</c:if>
				<th width="65">下单人</th>
				<th width="35">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="index" begin="0" end="9" step="1">
				<tr>
				<td>${i+1 }</td>
				<td>
					<c:choose>
						<c:when test="${indents[i].type==0 }">主订单</c:when>
						<c:when test="${indents[i].type==2 }">附加订单</c:when>
					</c:choose>
				</td>
				<td>${indents[i].keyword }</td>
				<%-- <td>${indents[i] }</td> --%>
				<td>${indents[i].user.company.companyname }</td>
				<td><fmt:formatDate value="${indents[i].createTime }" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:choose>
						<c:when test="${indents[i].states ==0}">
							审核中
						</c:when>
						<c:when test="${indents[i].states ==1}">
							<font color="blue">激活</font>
						</c:when>
						<c:when test="${indents[i].states ==2}">
							已撤销
						</c:when>
						<c:when test="${indents[i].states ==3}">
							<font color="red">失效</font>
						</c:when>
					</c:choose>
				</td>
				<c:if test="${sessionScope.agtsessionkey.role.id==1 }">
				<td>${indents[i].firmName }</td>
				</c:if>
				<td>
					<c:out value="${indents[i].createName}"></c:out>
				</td>
				<td>
					<c:if test="${not empty indents[i]}">
						<form action="queryDetailsIndent">
							<input type="hidden" value="${indents[i].id }" name="indentId">
							<a href="javascript:void(0)" class="subform"><img src="images/user_edit.png" alt="订单详情"	title="" border="0" /></a>&nbsp;&nbsp;&nbsp;
						</form>					
					</c:if>
				</td>
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