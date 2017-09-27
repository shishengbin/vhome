<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="payAccounting" method="post">
	<table>
		<tr>
			<td>选择代理商</td>
			<td>
				<select name="user.id" id="com">
				</select>
			</td>
		</tr>
		<tr>
			<td>充值金额：</td>
			<td>
				<select name="money">
					<option value="1000">1000</option>
					<option value="1500">1500</option>
					<option value="2000">2000</option>
					<option value="2500">2500</option>
					<option value="3000">3000</option>
					<option value="3500">3500</option>
					<option value="4000">4000</option>
					<option value="4500">4500</option>
					<option value="5000">5000</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" class="sub" id="sub">提交</button>
				<button type="button" class="close">取消</button>
			</td>
		</tr>
	</table>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"queryAgent",
			dataType:"json",
			success:function(data){
				$("#com").html("");
				$.each(data,function(index,com){
					$("#com").append("<option value="+com.id+">"+com.companyname+"</option>");
				});
			},
			error:function(){
				alert("失败！");
			}
		});
	});
</script>
</html>