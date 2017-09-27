<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form method="post" action="sys/toUpdPass" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<p class="contentTitle">
			<img alt="" src="images/updpss.png">
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			<div class="unit">
				<label>原始密码：</label>
				<input type="password" name="oldPassword" size="28" minlength="4" maxlength="18" class="required" />
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>新的密码：</label>
				<input type="password" id="cp_newPassword" name="newPassword" size="28" minlength="6" maxlength="18" class="required alphanumeric"/>
			    <span class="info">&nbsp;&nbsp;提示：长度为6—18位字符</span>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>确认密码：</label>
				<input type="password" name="rnewPassword" size="28" equalTo="#cp_newPassword" class="required alphanumeric"/>
			</div>
			<br><br>
			<div class="divider"></div>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
</div>
