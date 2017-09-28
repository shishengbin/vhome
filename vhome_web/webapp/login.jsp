<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SYCMS管理系统</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_login.css" />
<script type="text/javascript">
				//登录方法
				function doLogin() {
					var uid = $("#username").val();
					var pwd = $("#userpass").val();
					//验证
					if (uid == "") {
						alert("请输入账号");
						$("#username").focus();
						return false;
					} else if (pwd == "") {
						alert("请输入密码");
						$("#userpass").focus();
						return false;
					}
					$("#myform").submit();
					return true;
				}
				function kk(event) {
					if (event.keyCode == 13) {
						doLogin();
					}
				}
</script>
</head>
<body onkeyup="kk(event)" class="bg_body ">
	<div class="login">
		<div class="home_in">
			<a href="http://www.szsuoyuan.com" target="_blank"> <img
				src="images/home_in.png" />
			</a>
		</div>
		<div class="login_logo">
			<a href="#"></a>
		</div>
		<img height="450" src="images/login.png" />
			<div class="login_form">
				<form action="doLogin" id="myform" method="post">
					<!-- push  ipush 需要传的参数 -->
					<input type="hidden" name="action" value="login" />
					<div class="loginInfo" style="display: inline;">
						<p class="login_user">
							<input type="text" name="username" id="username" placeholder="请输入账号"></input>
						</p>
						<p class="login_pwd">
							<input type="password" name="userpass" id="userpass" placeholder="请输入密码"></input>
						</p>
						<p class="btnlogin">
							<img src="images/btn_login.png" width="75" height="38"
								onclick="doLogin()" />
						</p>
					</div>
				</form>
			</div>
	</div>
</body>
</html>
