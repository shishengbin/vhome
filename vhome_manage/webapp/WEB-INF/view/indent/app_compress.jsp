<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APP打包页面</title>
<style type="text/css">
.appdb{
	margin: 20px;
}
.appdb table {
	width: 500px;
	table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}

.appdb td {

	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}
.bush{
	margin: 20px;
}
.bush input{
	margin-left: 10px;
}
label{
	color:red;
}
</style>
</head>
<body>
	<form class="appdb">
		<table>
			<tr>
				<td width="100px;"><b>上&nbsp;传&nbsp;Icon：</b></td>
				<td><input type="file" name="appicon" id="appicon"></td>
				<td><label>请上传144*144的图片</label></td>
			</tr>
			<tr style="height: 200px;">
				<td colspan="3" align="left">
					<div style="width: 140px; height: 140px; overflow: hidden;" id="si">
					</div>
				</td>
			</tr>
			<tr>
					<td><b>选择APP风格：</b></td>
					<td colspan="2">
				<c:choose>
					<c:when test="${roleId==3 }">
						<!-- 电商版 -->
						<input type="radio" name="style" value="2" checked="checked">宫格展示
						<!-- <input type="radio" name="style">组图模式	 -->
					</c:when>
					<c:otherwise>
						<!-- 行业版，基础版 -->
						<input type="radio" name="style" value="1" checked="checked">列表展示
						<input type="radio" name="style" value="2">九宫格模式
						<input type="radio" name="style" value="3">十六宫格模式
					</c:otherwise>
				</c:choose>
					</td>
			</tr>
		</table>
		<input type="hidden" value="${userId }" name="userId"> <input
			type="hidden" value="${indentId }" name="indentId" id="indentId">
			<div class="bush"> 
			<input type="button" name="db" id="db" value="打&nbsp;&nbsp;包" class="fanh" style="border: 1px #CAD0D7 solid;border-radius:0px;">
		<input type="button" onclick="se('queryDetailsIndent?indentId=${indentId}')" value="取&nbsp;&nbsp;消" class="fanh" style="border: 1px #CAD0D7 solid;border-radius:0px;">		</div>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		$("#db").bind(
				'click',
				function() {
					if($("#appicon").val()==""){
						tishi(0, "请上传icon！");
						return false;
					}
					$("body").append("<div id='zhep'></div>");
					$("#zhep").css({
						"width" : $(document).width(),
						"height" : $(document).height()
					});
					$("#zhep").append("<div style='position: absolute;left:"+(document.body.clientWidth/2)+"px;top:"+(document.body.clientHeight/2)+"px;z-index:1000px;background-color:#FFf;color:#003366;font-weight: bold;'><img src='images/t01b005a13c8eef6852.gif' >正在打包中请稍后......</div>");
					$.ajaxFileUpload({
								//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
								url : 'shangchuan?'
										+ encodeURI($(this).parents("form")
												.serialize()),
								secureuri : false, //是否启用安全提交,默认为false
								fileElementId : 'appicon', //文件选择框的id属性
								dataType : 'json', //服务器返回的格式,可以是json或xml等
								type : "post",
								success : function(data, status) { //服务器响应成功时的处理函数
									$("#zhep").remove(); 
									if(data.status==200){
										tishi(1, data.message);
										se('queryDetailsIndent?indentId='+$("#indentId").val()+'');
									}else{
										tishi(0, data.message);
									}
								},
								error : function(data, status, e) { //服务器响应失败时的处理函数
									$("#zhep").remove(); 
									tishi(0, "打包失败！");
								}
							});
				});
		$("#appicon").bind('change', function(evt) {
			if(!$("#showicon").attr('id')){
				$("#si").append('<img src="" id="showicon" width="100%" />');
			}
			preview($("#showicon"), 1000000, evt);
		});
	});
</script>
</html>