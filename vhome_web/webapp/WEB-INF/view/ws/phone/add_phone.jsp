<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageContent">
		<c:if test="${not empty phone }">
			<c:url value="ws/updatephone" var="url"></c:url>
		</c:if>
		<c:if test="${empty phone }">
			<c:url value="ws/addphone" var="url"></c:url>
		</c:if>
	<form method="post" action="${url}"  class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<c:if test="${not empty phone}">
			<input type="hidden" value="${phone.id}" name="id">
		</c:if>
		<div class="pageFormContent nowrap" layoutH="56">  
			<p>
				<label>名称：</label>
				<input type="text" name="phone_Name" maxlength="20" class="required" value="${phone.phone_Name }"/>
			</p>
			<br/><br/>
			<div class="divider"></div>
			<p>
				<label>手机号码：</label>
				 <input type="text" name="phone_Number"  maxlength="20" class="required phone" value="${phone.phone_Number}"/>
			    <span class="info"></span>	
			</p>
			<br/><br/>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>

