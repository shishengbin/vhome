<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>子公司列表</title>
</head>
<body>
<img alt="" src="images/agent008.png" width="15%">
	<div class="centerPage">
	<form action="findAllUsersByPage" id="lcForm">
	<!-- 搜索框 -->
	<input name="username" value="${username }" placeholder="企业名称查询" >
	<button type="button" class="search_submit">查询</button>
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	</form>
	<hr>
	<table id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th width="5%"></th>
				<th width="15%">用户名</th>
				<th width="20%">企业名称</th>
				<th width="6%">客户数</th>
				<th width="15%">创建时间</th>
				<th width="15%">最后操作</th>
				<th width="5%">状态</th>
				<th width="5%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="index" begin="0" end="9" step="1">
				<tr>
				<td>${i+1 }</td>
				<td>${userList[i].u_name }</td>
				<td>${userList[i].coms.companyname }</td>
				<td>${userList[i].clientCount }</td>
				<td><fmt:formatDate value="${userList[i].createTime }" type="both"/></td>
				<td><fmt:formatDate value="${userList[i].updateTime }" type="both"/></td>
				<td>
					${userList[i]!=null?userList[i].u_status==0?'失效':'激活':''}
				</td>
				<td>
				<c:if test="${not empty userList[i]}">
				<a href="javascript:void(0)" onclick="update('${userList[i].id}','${userList[i].coms.id}','${userList[i].coms.companyname }','x')"><img src="images/user_edit.png" alt="修改账号信息"	title="" border="0" /></a>&nbsp;&nbsp;&nbsp;
				<%-- 
					<a href="#" class="ask" onclick="xiugai('${userList[i].id}','${userList[i].coms.id}','${userList[i].coms.companyname }','c')">充值</a>
					<a href="javascript:void(0)" class="ask" onclick="xiugai('${userList[i].id}','${userList[i].coms.id}','${userList[i].coms.companyname }','c')"><img src="images/rmb.png" alt="充值" title="" border="0" /></a>
				--%>
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
<script type="text/javascript">

	//update sub company info
	function update(id,comid,name,xc){
		openzhep("findAgentById",{"id":id,"comid":comid,"name":name,"xc":xc});
	}
</script>
</html>