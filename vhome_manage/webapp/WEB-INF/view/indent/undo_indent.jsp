<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
<!--
.STYLE4 {
	font-family: "宋体";
	font-size: 16px;
	color: #0033CC;
}

.STYLE5 {
	color: #FF0000;
	font-size: 16px;
}

.STYLE6 {
	font-family: "宋体";
	font-size: 24px;
}
-->
</style>
</head>

<body>
	<div class="centerPage">
	<h2>苏州正嘉广告传媒有限公司的撤单申请</h2>
	<hr/>
	<div style="border: 0px red solid;width: 400px;height: 300px;float: left;">
	关键词：
	<select id="key">
	</select>
	订单编号：
	<select id="uid"></select>
	<textarea rows="10" cols="50"></textarea>
	<button>提交</button>
	<button>取消</button>
	</div>
	<table style="border:1px; width: 400px;float: right;" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
		</tr>
		<tr>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
		</tr>
		<tr>
			<td>d</td>
			<td>d</td>
			<td>d</td>
			<td>d</td>
		</tr>
	</table>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		/* 加载有可撤单的关键词 */
		$.ajax({
			url:"queryUndoKey",
			dataType:"json",
			type:"get",
			success:function(data){
				$("#key").html("");
				$.each(data,function(index,param){
					$("#key").append("<option value='"+param.id+"'>"+param.kw_name+"</option>");
				});
				$("#key").bind('change',function(data){
					$("#uid").html("");
					alert($(this).val());
				});
			},
			error:function(){
				alert("加载可撤单关键词列表失败！");
			}
		})
	});
</script>
</html>