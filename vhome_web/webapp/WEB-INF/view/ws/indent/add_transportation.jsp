<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form method="post" action="ws/indent/updateIndentPostById" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="97">          
			<dl>
				<dt style="background-color: #FFF0F5">物流公司：</dt>
				<dd>
					<select name="transportName" class="required" style="width:90px">		
					<option value="shentong">申通E物流 </option>
					<option value="yuantong">圆通快递</option>
					<option value="zhongtong">中通快递</option>
					<option value="yunda">韵达快运</option>
					<option value="huitongkuaidi">汇通快运 </option>
					<option value="shunfeng">顺丰快递 </option>
					<option value="ems">EMS</option>
				</select>
					<span class="info">*必填</span>
				</dd>
			</dl>
			<dl>
				<dt style="background-color: #FFF0F5">物流编号：</dt>
				<dd>
					<input type="text" name="transportNumber" size="50" class="required"/>
					<span class="info">*必填</span>
				</dd>
			</dl>
		</div>
		<input type="hidden" name="number" value="<%=request.getParameter("indentNumber")%>">
		<input type="hidden" name="status" value="2"/>
		<br><br><br><br>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
