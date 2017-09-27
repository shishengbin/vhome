<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<script type="text/javascript">
$(function(){
	/* 初始化商品分类一级栏目 */
	$.ajax({
			url : "ws/findOneAllByPageJson",
			type : "get",
			data : {"levels" : "one"},
			dataType : "text",
			cache : false,
			success : function(data) {
				$.each($.parseJSON(data), function(index, p) {
						$("#parentype").append("<option value='"+p.id+"'>"+ p.tname + "</option>");
				});
				//$("#productClass").trigger('change');
			},
			error : function() {
				alert("初始化失败！");
			}
		});
  });

function dochange(param){
	  $.ajax({
			url : "ws/findSecondAllByPageJson",
			type : "post",
			data : {"id" : param},
			dataType : "json",
			cache : false,
			success : function(data) {
				if(data.length>0){
					$("#secondtype").html('');
					$.each(data, function(index, type) {
							$("#secondtype").append("<option value='"+type.id+"'>"+ type.tname + "</option>");
					});
					$("#secondtype").css("display","block");
				}else{
					$("#secondtype").html('');
					$("#secondtype").append("<option  value='-1'>--请选择--</option>");
					$("#secondtype").css("display","none");
				}
			},
			error : function() {
				alert("初始化失败！");
			}
	  });
  }
</script>

<div class="pageHeader">
	<form id="pagerForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="ws/findAllProductsByPage" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${numPerPage }" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<ul class="searchContent" style="display:inline;">
			<li style="width: 300px;">
				<label>产品名称：</label>
				<input type="text" name="usertext" value="${usertext }"/>
			</li>
			<li style="width: 200px;">
				<label>所属分类：</label>
				<select id="parentype" name="parentype" style="margin-left: 0px;" onchange="dochange(this.value)">
					<option value="0" selected="selected">--请选择--</option>
				</select>
			</li>
			<li style="width:200px;">
			    <select id="secondtype" name="secondtype" style="display: none">
					<option value="0">--请选择--</option>
			    </select>
			</li>
		</ul>
		<c:import url="../pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/preAddProduct" target="navTab" title="添加商品"><span>添加</span></a></li>
			<li><a class="edit" href="ws/findById?id={sid_user}" target="navTab" title="修改商品"><span>修改</span></a></li>
			<li><a class="delete" href="ws/deleteProduct?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" style="width: 100%" layoutH="112">
		<thead>
			<tr>
			 <!--
				<th width="3%" ><input type="checkbox" group="ids" class="checkboxCtrl" ></th>
		     --> 
				<th width="8%">商品名称</th>
				<th width="5%">成本价格</th>
				<th width="5%">销售价格</th>
				<th width="3%">规格</th>
				<th width="6%">所属分类</th>
				<th width="3%">上架</th>
				<th width="8%">所属城市</th>
				<th width="8%">所属供应商</th>
				<th width="4%">商品状态</th>
				<th width="5%">创建人</th>
				<th width="10%">创建时间</th>
				<th width="10%">修改时间</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${productlist}" var="product" varStatus="index">
				<tr target="sid_user" rel="${product.id }" >
			<!-- <td width="10"><input name="ids" value="${product.id}" type="checkbox"></td> -->
				<td align="center">${product.productName }</td>
				<td>${product.bazaarPrice }</td>
				<td>${product.productPrice }</td>
				<td style="color: #FF0000;">${product.specname }</td>
				<td style="color: blue;"><c:out value="${product.tname}" default="其他"></c:out></td>
				<td>
				 <c:if test="${product.deleteStatus==1}"><a href="ws/downProduct?id=${product.id }" target="ajaxTodo" title="确定要下架吗?"><img src="images/shangjia.png"/></a></c:if>
				 <c:if test="${product.deleteStatus==0}"><a href="ws/upProduct?id=${product.id }" target="ajaxTodo" title="确定要上架吗?"><img src="images/xiajia.png"/></a></c:if> 
				</td>
				<td>${product.productCity }</td>
				<td style="color: #FF0000;">${product.suppcomany }</td>
				<td>
				 <c:if test="${product.salesStatus==1}"><a href="ws/doSales?id=${product.id }" target="ajaxTodo" title="确定加入促销吗?"><img src="images/normal.png"/></a></c:if>
				 <c:if test="${product.salesStatus==0}"><a href="ws/doNormal?id=${product.id }" target="ajaxTodo" title="确定恢复正常吗?"><img src="images/sales.png"/></a></c:if> 
				</td>
				<td>${product.createName }</td>
				<td>
					<fmt:formatDate value="${product.createTime }" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td ><fmt:formatDate value="${product.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp" />
</div>

