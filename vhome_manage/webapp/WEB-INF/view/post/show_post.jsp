<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$(".theOne").bind('click',function(){
			var url = $(this).attr("url");
			se(url);
		});		
		$(".subb").die().live('click',function(){
			if(window.confirm("是否要继续删除操作?(无法恢复)")){
			var form = $(this).parents("form");
			/*$.getScript("/agt/javascript/custorm-subform.js");无效*/
			sf(form);
			}
		});
	});

</script>
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
	 <!-- <h1 style="font-size: 14px">账务管理》账务统计</h1> -->
	<img alt="" src="images/agent012.png" width="15%">
	<div class="centerPage">
    <form action="showPosts" id="lcForm">	
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage}" id="numPerPage"/><!-- 每页显示多少条 -->
	</form>
	<hr/>
	<table id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th width="15"></th>
				<th width="70%">公告栏</th>
				<th width="130">发布时间</th>
				<c:if test="${aid == 1}">
			        <th>操作</th>			
			    </c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="index" begin="0" end="9" step="1">
			<c:if test="${index.count%2!=0 }">
				<tr style="background-color: #FFFFFF;">
			</c:if>
			<c:if test="${index.count%2==0 }">
				<tr style="background-color: #F0F8FF;">
			</c:if>
				<td>${i+1 }</td>
				<td><a class="theOne" href="javascript:void(0)"  url="onePost?id=${postList[i].id}">${postList[i].postname}</a></td>
				<td><fmt:formatDate value="${postList[i].createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
			     <c:if test="${aid==1}">
			     <td>
			     </c:if>
			    <c:if test="${aid == 1&&postList[i].postname!=null}">
					   <form action="deletePost">
							<input type="hidden" value="${postList[i].id}" name="pid">
							<a href="javascript:void(0)" class="subb" ><img src="images/trash.png"  border="0" /></a>
						</form>
			   </c:if>
			  <c:if test="${aid==1}">
			   </td>		
			  </c:if>
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