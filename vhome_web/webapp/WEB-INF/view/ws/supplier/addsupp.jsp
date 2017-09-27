<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
        <c:if test="${not empty wssupp }">
			<c:url value="ws/addSupplier?flag=upd" var="url"></c:url>
		</c:if>
		<c:if test="${empty wssupp }">
			<c:url value="ws/addSupplier?flag=add" var="url"></c:url>
		</c:if>
	<form method="post" action="${url}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${wssupp.id }" />
			<div class="unit">
				<label>供应商名称：</label>
				<input type="text" id="suppcompany" name="suppcompany" size="28"  class="required" value="${wssupp.suppcompany }"/>
				 <span class="info">&nbsp;&nbsp;提示：输入公司或企业名称</span>
			</div>
			<br><br>
			<div class="unit">
				<label>负责人：</label>
				<input type="text" id="suppname" name="suppname" size="28"  class="required" value="${wssupp.suppname }"/>
			    <span class="info">&nbsp;&nbsp;提示：输入正确的负责人姓名</span>
			</div>
			<br><br>
			<div class="unit">
				<label>联系电话：</label>
				<input type="text" id="supptel" name="supptel" class="required"  size="28" value="${wssupp.supptel}" />
				 <span class="info">&nbsp;&nbsp;提示：输入正确的联系方式</span>
			</div>
			<br><br>
			<div class="unit">
				<label>备注：</label>
				<textarea name="suppremark" cols="40" rows="6">${wssupp.remark }</textarea>
		    </div>
	    </div>
		<c:import url="../pageControl/submitButton.jsp" />
	</form>
</div>
