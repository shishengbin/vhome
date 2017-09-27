<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script type="text/javascript">
	function clearTitle() {
		if ($("#postname").val() == "此处填写公告标题") {
			$("#postname").val('');
		}
	}
	function recovery() {
		var $str = $.trim($("#postname").val());
		if ($str == '') {
			$("#postname").val('此处填写公告标题');
		}
	}
</script>

</head>
<body>
	<img alt="" src="images/agent015.png" width="15%">
	<div align="left" id="table1"
		style="margin-left: 100px; margin-right: 100px">
		<form action="addPost" id="form1" method="post" class="niceform">
			<br />
			<br />
			<br />
			<div style="margin-left: 80px; margin-right: 80px">
				<input id="postname" name="postname" type="text" size="40"
					style="height: 20px; width: 100%" value="此处填写公告标题"
					onblur="recovery()" onclick="clearTitle()" />
			</div>
			<hr>
			<div style="margin-left: 80px; margin-right: 80px">
				<textarea name="postcontent" cols="100" rows="25"
					style="overflow: auto; width: 100%;"></textarea>
			</div>
			<div align="center"
				style="margin-left: 40px; margin-right: 80px; clear: right;">
				<button type="button" class="sub" id="sub">提交</button>
				<button type="button" class="close">取消</button>
			</div>
		</form>
	</div>
</body>
</html>