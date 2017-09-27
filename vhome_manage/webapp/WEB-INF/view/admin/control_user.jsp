<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#chongzhi table{
	width: 100%;
	height: 100px;
}
#chongzhi b{
	text-align:left;
	display: block;
}
.jine{
	width: 80px;
}
</style>

</head>
<body>
	<c:if test="${xc=='x' }">
	<form action="updateAgentIsAdmin" method="post" id="buj">
		<input type="hidden" name="id" value="${id }"> <input
			type="hidden" name="coms.id" value="${comid}">
		<dl>
			<dt>企业名称：</dt>
			<dd>
				<input value="${name}" name="coms.companyname" >
			</dd>
			<dt>修改状态：</dt>
			<dd>
				<select name="u_status">
					<option value="1">启用</option>
					<option value="0">禁用</option>
				</select>
			</dd>
		</dl>
	</c:if>
	<c:if test="${xc=='c' }">
		<form action="agentPay" method="post" id="chongzhi">
			<input type="hidden" name="id" value="${id}">
			<b>最近一次充值记录</b>
			<table>
				<tr>
					<td>企业名称：</td>
					<td>${name }</td>
					<td style="padding-left: 50px">时间：</td>
					<td><fmt:formatDate value="${accounting.createTime }" pattern="yyyy年MM月dd日 "/></td>
				</tr>
				<tr>
					<td>充值金额：</td>
					<%-- <td>${accounting.sum }</td> --%>
					<td>${accounting.sum }</td>
					<td style="padding-left: 50px">余额：</td>
					<td>${accounting.balance }</td>
				</tr>
			</table>
			<hr>
			<p><p><b>充值金额：<!-- <input id="falseMoney" disabled="disabled" name="falseMoney" value="50000" type="text"/>
			<input id="money" name="money" value="50000" type="hidden"/> -->
				<select name="money">
					<option value="5000">5000</option>
					<option value="10000">10000</option>
					<option value="20000">20000</option>
					<option value="30000">30000</option>
					<option value="40000">40000</option>
					<option value="50000">50000</option>
					<option value="-1">输入...</option>
				</select><font>￥</font>
			</b>
			<p>
	</c:if>
	<button type="button" class="formsub">确定</button>
	<button type="button" class="formclose">取消</button>
	</form>
</body><script type="text/javascript">

	$("select[name='money']").bind('change',function(){
		if($(this).val()==-1){
			$(this).nextAll().remove();
			$(this).parent().append("<input name='money' class='jine'></input>￥");
			$(this).remove();
		}
	});
	$(".jine").live('change',function(){
		var emp =/(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
		if(!emp.test($(this).val())){
			$("#chongzhi .formsub").attr('disabled',true);
		}else{
			$("#chongzhi .formsub").attr('disabled',false);
		}
	});
</script>
</html>