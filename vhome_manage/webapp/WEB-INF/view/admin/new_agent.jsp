<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script type="text/javascript">
	//验证表单
	var u_name = false;
	var u_pwd = false;
	var u_pwd2 = false;
	var u_cname = false;
	var u_cperson = true;
	var u_cphone = true;
	var u_cemail = true;
	var u_business = true;
	//选填项
	$(".moreInfo").hide();
	$("#more").toggle(function() {
		$(".moreInfo").show();
		u_cperson = false;
		u_cphone = false;
		u_cemail = false;
		u_business = false;
	}, function() {
		$(".moreInfo").hide();
		document.getElementById('companyPerson').value = "";
		document.getElementById("companyBusiness").value = "";
		document.getElementById("companyPhone").value = "";
		document.getElementById('companyEmail').value = "";
		document.getElementById('companyDescibe').value = "";
		u_cperson = true;
		u_cphone = true;
		u_cemail = true;
		u_business = true;
	});
	//检查用户名       
	String.prototype.Trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	};
	function checkUsername() {
		var test = new String($("#username").val());
		if ($("#username").val() == '' | test.indexOf(" ") != -1) {
			u_name = false;
			$("#username_error").html(
					"<span style='color: red'>账号不能为空或包含空格！</span>");
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
						u_name = true;
						$("#username_error").html(
								"<img src='images/valid.png'/>");
					} else {
						u_name = false;
						$("#username_error").html(
								"<span style='color: red'>用户名不能重复！</span>");
					}
				},
				error : function() {
					u_name = false;
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
			u_pwd = false;
		} else if ($("#passwords").val().length < 6) {
			$("#passwords_error").html(
					"<span style='color: red'>密码不能小于6位！</span>");
			u_pwd = false;
		} else {
			u_pwd = true;
			$("#passwords_error").html("<img src='images/valid.png'/>");
			checkPassword2();
		}
	}
	//验证二次密码
	function checkPassword2() {
		if ($("#passwords2").val() == '') {
			$("#passwords_error2").html(
					"<span style='color: red'>请设置密码！</span>");
			u_pwd2 = false;
		} else if ($("#passwords2").val() != $("#passwords").val()) {
			$("#passwords_error2").html(
					"<span style='color: red'>两次密码输入不同！</span>");
			u_pwd2 = false;
		} else {
			u_pwd2 = true;
			$("#passwords_error2").html("<img src='images/valid.png'/>");
		}
	}
	$(function() {
		$("#companyName")
				.blur(
						function() {
							if ($('#companyName').val() == "") {
								u_cname = false;
								$("#companyName_error")
										.html(
												"<span style='color: red'>公司名称不能为空</span>");
							} else {
								$
										.ajax({
											url : 'checkCompany',
											type : 'post',
											data : {
												'companyName' : $(
														'#companyName').val()
											},
											dataType : 'json',
											success : function(data) {
												if (data.state == 1) {
													$("#companyName_error")
															.html(
																	"<img src='images/valid.png'/>");
													u_cname = true;
												} else {
													$("#companyName_error")
															.html(
																	"<span style='color: red'>公司名称不能重复</span>");
													u_cname = false;
												}
											},
											error : function() {
												u_cname = false;
												alert('验证失败!');
											}
										});
							}
						});
	});
	//检查联系人
	function check_companyPerson() {
		var username;
		username = document.getElementById('companyPerson').value;
		username = username.Trim();
		if (username == "") {
			u_cperson = false;
			document.getElementById('companyPerson_error').innerHTML = "<span style='color: red'>联系人不能为空</span>";
		} else {
			u_cperson = true;
			$("#companyPerson_error").html("<img src='images/valid.png'/>");
		}
	}
	//检查主营业务
	function check_companyBusiness() {
		var username;
		username = document.getElementById("companyBusiness").value;
		username = username.Trim();
		if (username == "") {
			u_business = false;
			document.getElementById('companyBusiness_error').innerHTML = "<span style='color: red'>主营业务不能为空</span>";
		} else {
			u_business = true;
			$("#companyBusiness_error").html("<img src='images/valid.png'/>");
		}
	}
	//检查联系电话
	function check_companyPhone() {
		var telephone = document.getElementById("companyPhone").value;
		telephone = telephone.Trim();
		if (telephone == "") {
			document.getElementById('companyPhone_error').innerHTML = "<span style='color: red'>请输入联系电话</span>";
			u_cphone = false;
		} else {
			var reg = /^[0-9]{11,}$/i;
			if (!reg.test(telephone)) {
				u_cphone = false;
				document.getElementById('companyPhone_error').innerHTML = "<span style='color: red'>请输入有效联系电话！例：13306191795</span>";
			} else {
				u_cphone = true;
				$("#companyPhone_error").html("<img src='images/valid.png'/>");
			}
		}
	}
	//检查Email
	function check_companyEmail() {
		var email;
		email = document.getElementById('companyEmail').value;
		email = email.Trim();
		if (email == "") {
			u_cemail = false;
			document.getElementById('companyEmail_error').innerHTML = "<span style='color: red'>请输入Email</span>";
		} else {
			if (/^[\w-]+[\.]*[\w-]+[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/
					.test(email)) {
				u_cemail = true;
				$("#companyEmail_error").html("<img src='images/valid.png'/>");
			} else {
				u_cemail = false;
				document.getElementById('companyEmail_error').innerHTML = "<span style='color: red'>请输入有效的Email地址</span>";
			}
		}
	}
	function regist() {
		if (u_name == true && u_pwd == true && u_pwd2 == true && u_cname == true) {
			var form = $("#check").parents("form");
			sf(form);
		} else {
			alert("注册信息填写错误，请仔细检查更正后再提交");
		}

	}
</script>

</head>
<body>
	<div style="margin-top: 30px;">
		<img alt="" src="images/agent014.png" width="15%">
		<hr align="center">
	</div>
	<div id="main">
		<div id="table1" align="center">
			<form id="form1" method="post" action="addAgent">
				<table>
					<tr style="line-height: 45px;">
						<td align="right" valign="middle" width="150">子公司账号：</td>
						<td width="200"><input type="text" name="username"
							id="username" class="_input" onblur="checkUsername()" /></td>
						<td id="username_error" class="after_input" align="left"
							width="250"><span style="color: #8A799F">请输入代理商账户</span></td>
					</tr>
					<tr style="line-height: 45px;">
						<td align="right" valign="middle" width="150">账户密码：</td>
						<td width="200"><input type="password" name="passwords"
							id="passwords" class="_input" onblur="checkPassword()"
							maxlength="15" /></td>
						<td id="passwords_error" class="after_input" align="left"
							width="250"><span style="color: #8A799F">请输入6-15位字符作为密码</span></td>
					</tr>
					<tr style="line-height: 45px;">
						<td align="right" valign="middle" width="150">确认密码：</td>
						<td width="200"><input type="password" name="passwords2"
							id="passwords2" class="_input" onblur="checkPassword2()"
							maxlength="15" /></td>
						<td id="passwords_error2" class="after_input" align="left"
							width="250"><span style="color: #8A799F">请再次输入密码</span></td>
					</tr>
					<tr style="line-height: 45px;">
						<td align="right" valign="middle" width="150">公司名称：</td>
						<td width="200"><input type="text" name="companyName"id="companyName" class="_input" onblur="check_companyName()" /></td>
						<td id="companyName_error" class="after_input" align="left" width="250"><span style="color: #8A799F">请输入公司名称</span></td>
					</tr>
					<tr id="more">
						<td colspan="1" align="center"><a
							href="javascript:content(316)" onclick="changeInfo()">详细信息(选填)</a></td>
					</tr>
					<tr class="moreInfo" style="line-height: 45px;">
						<td align="right" valign="middle" width="150">联系人：</td>
						<td width="200"><input type="text" name="companyPerson"
							id="companyPerson" class="_input" onblur="check_companyPerson()" /></td>
						<td id="companyPerson_error" class="after_input" width="250"></td>
					</tr>
					<tr class="moreInfo" style="line-height: 45px;">
						<td align="right" valign="middle" width="150">联系电话：</td>
						<td width="200"><input type="text" name="companyPhone"
							id="companyPhone" class="_input" onblur="check_companyPhone()" /></td>
						<td id="companyPhone_error" class="after_input" width="250"></td>
					</tr>
					<tr class="moreInfo" style="line-height: 45px;">
						<td align="right" valign="middle" width="150">电子邮箱：</td>
						<td width="200"><input type="text" name="companyEmail"
							id="companyEmail" class="_input" onblur="check_companyEmail()" /></td>
						<td id="companyEmail_error" class="after_input" width="250"></td>
					</tr>
					<tr class="moreInfo" style="line-height: 45px;">
						<td align="right" valign="middle" width="150">主营业务：</td>
						<td width="200"><input type="text" name="companyBusiness"
							id="companyBusiness" class="_input"
							onblur="check_companyBusiness()" /></td>
						<td id="companyBusiness_error" class="after_input" width="250"></td>
					</tr>
					<tr class="moreInfo" style="line-height: 45px;">
						<td align="right" valign="middle">备注：</td>
						<td><textarea name="companyDescibe" cols="20" rows="3"
								id="companyDescibe"></textarea></td>
					</tr>
					<tr align="right">
						<td height="61" colspan="2">
							<button type="button" class="check" id="check"
								onclick=" regist()">提交</button>
							<button type="button" class="close">取消</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>