<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div>
	<%-- <form action="searchCompany" id="lcForm">
	
	<!-- 搜索框 -->
	<input  name="info" placeholder="输入公司名称....."/>
	<button type="button" class="search_submit">查询</button>
	<input name="meiyong" style="border: 0px;background-color: transparent;" />
	 
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<input type="hidden" name="clientList" value="true">
	</form> --%>
	<div style="float: right;top: 0px;right: 0px;cursor: pointer;color: blue;" onclick="closezhep()">关闭</div>
	<form action="searchCompany" style="text-align: left;padding-left: 20px;">
	<input placeholder="公司名称" id="abc" value="${info }"><input type="button" onclick="comchax()" value="查询">
	<input type="hidden" name="clientList" value="true">
	</form>
	<hr>
	<div style="height: 300px;overflow: scroll;">
	<table id="mytable" cellspacing="0"> 
		<thead>
			<tr>
				<th>公司名称</th>
				<th>主营业务</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="cl">
				<tr>
					<td>${cl.companyName }</td>
					<td>${cl.companyBusiness }</td>
					<td>${cl.companyPerson }</td>
					<td>${cl.companyPhone }</td>
				    <td style="padding: 0px 0px 0px 10px; text-align: center;">
						<img alt="" src="/vcd/agt/images/valid.png" width="40%" style="margin:0px 0px; cursor: pointer;border: 1px #C1D5E3 solid;display: block;" onclick="xuanzhong('${cl.companyName }',${cl.companyId })">
					</td>
			    </tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div style="display: inline; font-size: 12px;color:#0e4354;float: left;">总记录数：${totalCount }</div>
	</div>
</body>
<script type="text/javascript">
	function xuanzhong(aa,bb){
 		$("#companyname").val(aa);
		$("#companyid").val(bb);
		closezhep();
	}
	
	function comchax(){
		$("#zhepcenter").load("searchCompany",{clientList:"true",numPerPage:5,info:$("#abc").val()});
	};
</script>
</html>