<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APP管理平台</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link href="css/base.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="public/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	//登录方法
	function doLogin() {
		var uid = $('#username').val();
		var pwd = $('#userpass').val();
		//验证
		if (uid.length == 0 || uid == null) {
			alert("请输入用户名");
			$("username").focus();
			return false;
		} else if (pwd.length == 0 || pwd == null) {
			alert("请输入密码");
			$("userpass").focus();
			return false;
		}
		$("#myform").submit();
		return true;
	}
	//敲击键盘回车
	function kk(event) {
		if (event.keyCode == 13) {
			doLogin();
		}
	}
</script>

</head>
<body onkeyup="kk(event)">
<form id="myform" action="doLogin" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf" height="20px">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="images/login_06.gif">&nbsp;</td>
            <td width="183" background="images/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="21%" height="30"><div align="center"><span class="myaccount">账号</span></div></td>
                <td width="79%" height="30"><input type="text" name="username" id="username"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr>
                <td height="30"><div align="center"><span class="myaccount">密码</span></div></td>
                <td height="30"><input type="password" name="userpass" id="userpass"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30"><input type="button" onclick="doLogin()" value="登录" style="color: green;"/><input type="reset" value="重置" style="color: green;"/></td>
              </tr>
            </table></td>
            <td width="255" background="images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="edition">版本2016V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962" height="120px">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>