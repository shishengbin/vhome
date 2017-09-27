<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
		<c:if test="${not empty human }">
			<c:url value="ws/updateHuman" var="url"></c:url>
		</c:if>
		<c:if test="${empty human }">
			<c:url value="ws/addHuman" var="url"></c:url>
		</c:if>
		<form method="post" action="${url }"  class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<c:if test="${not empty human }">
			<input type="hidden" value="${human.id}" name="id">
		</c:if>
		<div class="pageFormContent nowrap" layoutH="56">
		    <dl>
					<dt style="width:80px">商家账号：</dt>
					<dd style="width:200px">
						<input type="text" name="human_account" size="28" class="required" value="${human.human_account }"/>
					</dd>
					<dd style="width:25px"></dd>
					<dd style="width:200px"><span class="info" id="infomation">输入正确的账号格式</span></dd>
		    </dl>          
			
		
			<dl>
				<dt style="width:80px">商家名称：</dt>
				<dd style="width:200px">
					<input type="text" name="human_question" size="28" class="required" value="${human.human_question }"/>
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			<dl>
				<dt style="width:80px">地址：</dt>
				<dd style="width:200px">
					<input type="text" name="human_address" size="28" class="required" value="${human.human_address}"/>
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"></dd>
			</dl>
			<dl>
				<dt style="width:80px">手机号：</dt>
				<dd style="width:200px">
					<input type="text" name="human_phone" size="28" class="required" value="${human.human_phone }"/>
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"></dd>
			</dl>
			<dl>
				<dt style="width:80px">联系人：</dt>
				<dd style="width:200px">
					<input type="text" name="human_name" size="28" class="required" value="${human.human_name }"/>
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"></dd>
			</dl>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
