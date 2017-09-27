<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="pageContent">
      <c:if test="${not empty wsspec }">
			<c:url value="ws/addSpec?flag=upd" var="url"></c:url>
		</c:if>
		<c:if test="${empty wsspec }">
			<c:url value="ws/addSpec?flag=add" var="url"></c:url>
		</c:if>
	<form method="post" action="${url }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="id" type="hidden" value="${wsspec.id }">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>规格名称：</label>
				<input  type="text" name="specname" size="30" style="width:120px;" value="${wsspec.specname }" class="required" alt="请输入规格名称"/>
			</p>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
