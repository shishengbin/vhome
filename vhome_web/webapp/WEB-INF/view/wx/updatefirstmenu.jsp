<%@ page language="java" import="java.util.*" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../ws/pageControl/jstlImport.jsp"%>
<div class="pageContent">
	<form method="post" action="wx/updateMenu?flag=fat" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="bt_id" value="${menu.bt_id }" /> <input
				type="hidden" name="type" value="${menu.type }" /> <br />
			<p>
				<label>主菜单名称：</label> <input type="text" name="parentmenuname"
					class="required" value="${menu.name }" />
			</p>
			 <div class="divider"></div>
			<p>
				<label>关键词或url：</label>
				<c:if test="${menu.type=='click'}">
					<input type="text" name="keyorurl" class="required"
						value="${menu.key }" />
				</c:if>
				<c:if test="${menu.type=='view'}">
					<input type="text" name="keyorurl" class="required"
						value="${menu.url}" />
				</c:if>
			</p>
			 <div class="divider"></div>
			<p>
				<label>备注信息：</label>
				<textarea name="remark" cols="32" rows="6">${menu.remark }</textarea>
			</p>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp"></c:import>
	</form>
</div>