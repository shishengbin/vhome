<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//新增验证
	var checkName = false;
	var checkPwd = false;
	var check2Pwd = false;
	//验证用户名	
	function checkUsername() {
		var test = new String($("#username").val());
		if ($("#username").val() == '' | test.indexOf(" ") != -1) {
			$("#username_error").html(
					"<span style='color: red'>用户名不能为空或包含空格！</span>");
		} else {
			$.ajax({
				url : 'checkUsername',
				type : 'post',
				data : {
					'username' : $('#username').val()
				},
				dataType : 'json',
				success : function(data) {
					if (data.state == 1) {
						checkName = true;
						$("#username_error").html(
								"<img src='images/valid.png'/>");
					} else {
						$("#username_error").html(
								"<span style='color: red'>用户名不能重复！</span>");
					}
				},
				error : function() {
					alert('验证失败!');
				}
			});
		}
	}
	//验证密码
	function checkPassword() {
		if ($("#passwords").val() == '') {
			$("#passwords_error").html(
					"<span style='color: red'>密码不能为空！</span>");
		} else if ($("#passwords").val().length < 6) {
			$("#passwords_error").html(
					"<span style='color: red'>密码不能小于6位！</span>");
		} else {
			checkPwd = true;
			$("#passwords_error").html("<img src='images/valid.png'/>");
			checkPassword2();
		}
	}
	//验证二次密码
	function checkPassword2() {
		if ($("#passwords2").val() == '') {
			$("#passwords_error2").html("<span style='color: red'>请设置密码！</span>");
		} else if ($("#passwords2").val() != $("#passwords").val()) {
			$("#passwords_error2").html(
					"<span style='color: red'>两次密码输入不同！</span>");
		} else {
			check2Pwd = true;
			$("#passwords_error2").html("<img src='images/valid.png'/>");
		}
	}
	//提交表单
	function regist() {
		if (checkName == true && checkPwd == true && check2Pwd == true) {
			var form = $("#check").parents("form");
			sf(form);
		} else {
			alert("注册信息填写错误，请仔细检查更正后再提交");
		}
	}
</script>
</head>

<body>
	<div id="maincon">
		<div id="top">
			<img alt="" src="images/agent019.png" width="15%">
			<hr />
		</div>
		<div id="contain" style="margin: 20px 0 0 50px; padding: 0;">
			<form id="form1" name="form1" method="post" action="addEmp">
				<table>
					<tr>
						<td width="516">
							<p>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号： <input id="username"
									type="text" name="username"  style="width: 160px; height: 20px"
									onblur="checkUsername()" maxlength="15" /> <span
									id="username_error"></span>
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码： <input id="passwords"
									type="password" name="passwords"
									style="width: 160px; height: 20px" onblur="checkPassword()"
									maxlength="15" /> <span id="passwords_error"></span>
							</p>
							<p>
								确认密码： <input id="passwords2" type="password" 
									style="width: 160px; height: 20px" onblur="checkPassword2()"
									maxlength="15" /> <span id="passwords_error2"></span>
							</p>
							<p>
								账号状态：
										<input type="radio" name="state" value="1" checked="checked"/>启用
										<input type="radio" name="state" value="0"  />禁用
										<span style="font-size: 14px; color: #FF0000">*</span>
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色： 
								<select id="roleSearch" name="rid">
										<c:forEach items="${rolelist }" var="r" >
											<option value="${r.id }">${r.role_name}</option>
										</c:forEach>
								</select>
							</p>
							<p>
								<button type="button" class="check" id="check" onclick="regist()">提交</button>
								<button type="button" class="close">取消</button>
							</p>
						</td>
						<td width="368" align="left" valign="top">
							<p style="color: #E53333;"></p>
							<p style="color: #E53333;">权限说明：</p>
							<p style="color: #E53333;">管理员权限： 员工权限管理、账户管理、订单管理、客户管理</p>
							<p style="color: #E53333;">运营人员权限：订单管理&lt;新增订单、修改订单&gt;、查看公告</p>
							<p style="color: #E53333;">财务人员权限： 账户管理&lt;查看相关账务信息&gt;、查看公告</p>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>