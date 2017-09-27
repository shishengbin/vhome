<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
	<form id="buj" action="updatePass" method="post">
		<dl>
			<dt>旧密码：</dt>
			<dd>
				<input name="pass" type="password" placeholder="请输入旧密码">
			</dd>
			<dt>新密码：</dt>
			<dd>
				<input name="pass2"  type="password" placeholder="请输入新密码">
			</dd>
			<dt>新密码：</dt>
			<dd>
				<input name="pass3" type="password" placeholder="请再次输入新密码"><span style="color:red;" id="tishi"></span>
			</dd>
		</dl>
		<button type="button" class="formsub" disabled="disabled">确定</button>
	<button type="button" class="formclose">取消</button>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$("input[name='pass3']").bind('blur',function(){
			if($(this).val()!=""&&$(this).val()==$("input[name='pass2']").val()){
				$("#tishi").html("");
				$(".formsub").removeAttr('disabled');
			}else{
				$("#tishi").html("两次密码不一致!");
			}
		}).bind('keyup',function(event){
			var $val = $(this).val();
			var $pass2 = $("input[name='pass2']").val()
			if($val==$pass2&&$("input[name='pass']").val()){
				$(".formsub").removeAttr('disabled');
			}else{
				$(".formsub").attr('disabled','disabled');
			}
		});
	});
</script>
</html>