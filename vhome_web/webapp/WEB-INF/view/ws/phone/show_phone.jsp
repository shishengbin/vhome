<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>

<div class="pageContent" style="width: 100%">
	<div class="pageFormContent nowrap" layoutH="56">
		<dl>
			<dt style="background-color: #FFF0F5">名称：</dt>
			<dd>${phone.phone_Name}</dd>
		</dl>
		<dl>
			<dt style="background-color: #FFF0F5">号码：</dt>
			<dd>${phone.phone_Number}</dd>
		</dl>
		<dl>
			<dt style="background-color: #FFF0F5">创建人：</dt>
			<dd>${phone.createName}</dd>
		</dl>
		<dl>
			<dt style="background-color: #FFF0F5">创建时间：</dt>
			<dd>
				<fmt:formatDate value="${phone.createTime}"
					pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
			</dd>
		</dl>
	</div>
</div>
