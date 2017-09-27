<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>下单成功</title>
<style type="text/css">
.indent_title {
	height: 138px;
	margin: 0 auto;
	width: 100%;
}

.indent_img img {
	height: 60%;
	width: 100%;
}

.indent_line {
	padding-top: 30px;
}

.indent_line hr {
	border-style: dotted;
	color: #d61f1f;
	width: 90%;
}

.indent_btn {
	padding-top: 20px;
	text-align: center;
}

.indent_btn img {
	width: 40%;
}
</style>
</head>
<body>
	<div class="indent_title">
		<div class="indent_img">
			<img alt="" src="../../images/indent_success.jpg" />
		</div>
		<div class="indent_line">
			<hr />
		</div>
		<div class="indent_btn">
			<a
				href="../../ws/wxpages/index.html?accountid=${accountid}&humanId=${humanId}">
				<img alt="" src="../../images/button_goshopping.jpg">
			</a>
		</div>
	</div>
</body>
</html>