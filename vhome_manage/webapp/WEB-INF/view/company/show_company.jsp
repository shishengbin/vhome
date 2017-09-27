<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
 //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            </script>
 <style type="text/css">
 div.detail_info
{
    position: absolute;
    z-index: 1000;    
   min-width: 150px;
   min-height: 60px; 
    display: none;
    padding: 3px;
    overflow:visible;
    text-align: left;
     background-color: #fbf0db;
    border: 1px solid #ff9000;
    color: #000;
}
 </style>    
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
	<img alt="" src="images/agent013.png" width="15%">
	<div class="centerPage">
	<form action="searchCompany" id="lcForm">
	
	<!-- 搜索框 -->
	<input  name="info"/>
	<button type="button" class="search_submit" >查询</button>
	 <input name="meiyong" style="border: 0px;background-color: transparent;" />
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	</form>
	<hr>
	<table id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th width="15"></th>
				<th>公司名称</th>
				<th>主营业务</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>电子邮箱</th>
				<th>备注</th>
				<th>创建时间</th>
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="index" begin="0" end="9" step="1">
			<tr>
				<td>${i+1 }</td>
				<td>${companyList[i].companyName }</td>
				<td>${companyList[i].companyBusiness }</td>
				<td>${companyList[i].companyPerson }</td>
				<td>${companyList[i].companyPhone }</td>
				<td>${companyList[i].companyEmail }</td>
				<td><input value="${companyList[i].companyDescibe}" size="6" readonly="readonly" 
				style="border: 0px;background-color: transparent;" 
				onmouseover="showDetail(true,this);"
				onmouseout="showDetail(false,this);">
				<c:if test="${ companyList[i]!=null }">
				 <div class="detail_info">
		           ${companyList[i].companyDescibe}
		          </div>
		          </c:if>
		          </td>				
				 <td><fmt:formatDate value="${companyList[i].createTime }" pattern="yyyy-MM-dd"/></td> 
			    <td>
					<c:if test="${ companyList[i]!=null }">
					  <a class="theOne"  href="javascript:void(0)"  url="oneCompany?id=${companyList[i].companyId}"><img src="images/user_edit.png" alt=""	title="" border="0" style="float: left;"/></a>
					   <%-- <span class="del" href="javascript:void(0)"  urll="deleteCompany?id=${companyList[i].companyId}"><img src="images/trash.png" alt="" title="" border="0" /></span>  --%>
					   <form action="deleteCompany" style="float:right;">
							<input type="hidden" value="${companyList[i].companyId}" name="id">
							<a href="javascript:void(0)" class="subb"><img src="images/trash.png"  border="0" /></a>
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