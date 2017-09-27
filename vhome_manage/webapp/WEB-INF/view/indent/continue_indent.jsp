<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
dl{
}
dl dt {float:left;}
dl dd{
display: block; margin: 0;
}
dl .me{
	height: 
}
</style>
</head>
<body>
	<img alt="" src="images/agent007.png" width="15%">
	<div class="centerPage">
		<dl>
			<dt>关键词：</dt>
			<dd>
				<select name="userlie" id="userlie">
					<option>你好啊</option>
					<option>你好啊</option>
					<option>你好啊</option>
					<option>你好啊</option>
				</select>
				<select name="keyword" id="keyword">
				
				</select>
			</dd>
			<dt>续购年限：</dt>
			<dd>
				<select name="term" id="buyterm">
					<option>一年</option>
					<option>二年</option>
					<option>三年</option>
					<option>四年</option>
					<option>无年</option>
					<option>六年</option>
					<option>七年</option>
					<option>八年</option>
					<option>九年</option>
					<option>十年</option>
				</select>
			</dd>
			<dt>带发布三方商城：</dt>
			<dd>
				<div id="markets" style="height: 100px;width: 500px; border: 0px red solid;"></div>
			</dd>
			<dt>是否代制作：</dt>
			<dd>
				<input type="radio" value="1" name="agency">是
				<input type="radio" value="0" name="agency" checked="checked">否
			</dd>
			<dt>价格：</dt>
			<dd>
				<input type="hidden" value="1" name="type">
				<div class="only">
				<input type="text" name="monetary" id="monetary" value="0.0" readonly="true" style="background-color: transparent;">
						￥
				</div>
			</dd>
		</dl>
		<button type="button" onclick="yanzh()" id="sub">提交</button>
		<button type="button" class="close">取消</button>
	</div>
</body>
<script type="text/javascript">
	var keylieb;//保存客户与订单信息
	var userid;//保存userid
	$(function(){
		/*请求客户与订单信息*/
		$.ajax({
			url : "initContinuePage",
			type : "get",
			dataType : "json",
			success : function(data) {
				$("#keyword").html("");
				$("#markets").html("");
				$("#userlie").html("");
				keylieb = data[0];
				/* 客户对应的关键词列表 */
				$.each(data[0],function(index,param){
					$("#userlie").append("<option value='"+index+"'>"+param.companyname+"</option>");
				});
				
				/* 三方商城列表 */
				$.each(data[1],function(index,param){
					$("#markets").append("<div class='td_mar'><input type='checkbox' name='mm' value='"+param.id+"' onchange='yanzh(this)'>"+ param.name+ "</input></div>");
				});
				
				$("#keyword").bind('change',function(){
					kk(this);
				});
				$("#userlie").bind('change',function(){
					ee(this);
				});
				$("#userlie").trigger('change');
				$("#keyword").trigger('change');
			},
			error : function() {
				alert("加载客户列表失败！");
				$("#sub").attr("disabled","disabled");
			}
		});
	});
	/* 客户关键词级联 */
	function ee(th){
		$("#keyword").html("");
		userid= $(th).val();//客户下拉索引
		$.each(keylieb[userid].indents,function(index,indent){
			$("#keyword").append("<option value='"+index+"'>"+indent.key.kw_name+"</option>");
			
		});
		
	}
	/* 关键词联动 */
	function kk(th){
		var $val = $(th).val();
		
		$("input[type='checkbox']").removeAttr("disabled");
		$("input[type='checkbox']").removeAttr("checked");
		$("input[name='agency']").removeAttr("checked");
		$("input[name='agency']").removeAttr("disabled");
		
		var indent = keylieb[userid].indents[$val];
		var $agency = $($("input[name='agency']"));
		if ($($agency[0]).val() ==indent.agency) {
			$($agency[0]).attr("checked", "checked");
		}else{
			$($agency[1]).attr("checked", "checked");
		}
		if (1 == indent.agency) {
			$agency.attr("disabled", "disabled");
		}
		
		var $mar = $("#markets .td_mar input[type='checkbox']");
		for (var i = 0; i < $mar.length; i++) {
			for (var j = 0; j < indent.market.length; j++) {
				if ($($mar.get(i)).val() == indent.market[j].id) {
					$($mar.get(i)).attr("checked", "checked");
					$($mar.get(i)).attr("disabled", "disabled");
				}
			}
		}
	}
	function yanzh(){
		
	}
</script>
</html>