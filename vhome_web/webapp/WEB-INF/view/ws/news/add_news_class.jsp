<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="pageContent">
	<form method="post" action="ws/updateNewsClass" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<c:if test="${not empty nc }">
			<input name="id" type="hidden" value="${nc.id }">
		</c:if>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>类别名称：</label>
				<input name="remark" type="text" size="30" style="width:120px;" value="${nc.remark }" class="required" />
			</p>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
