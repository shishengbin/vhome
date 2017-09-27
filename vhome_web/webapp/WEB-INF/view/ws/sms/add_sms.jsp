<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageContent">
		<c:if test="${not empty sms }">
			<c:url value="ws/updatesms" var="url"></c:url>
		</c:if>
		<c:if test="${empty sms }">
			<c:url value="ws/addsms" var="url"></c:url>
		</c:if>
	<form method="post" action="${url}"  class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<c:if test="${not empty sms }">
			<input type="hidden" value="${sms.id}" name="id">
			<input type="hidden" value="${sms.sms_use}" name="sms_use">
		</c:if>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>短信标题：</dt>
				<dd>
				<input type="text" name="sms_title" class="required"  size="28" value="${sms.sms_title }">
				</dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt>短信分享内容：</dt>
				<dd>
				<textarea rows="10" cols="40" name="sms_content" class="required" >${sms.sms_content}</textarea>
				</dd>
			</dl>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
