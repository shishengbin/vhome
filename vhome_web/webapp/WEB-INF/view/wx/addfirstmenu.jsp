<%@ page language="java" import="java.util.*" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../ws/pageControl/jstlImport.jsp"%>
<div class="pageContent">
	<form method="post" action="wx/addParentMenu"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56">
			<br />
			<p>
				<label>主菜单名称：</label> <input type="text" name="parentmenuname"
					class="required" /> <span class="info">最多4个汉字</span>
			</p>
			 <div class="divider"></div>
			<p>
				<label>关键词或url：</label> <input type="text" name="keyorurl"
					class="required"/>
			</p>
			 <div class="divider"></div>
			<p>
				<label>备注信息：</label>
				<textarea name="userremark" cols="32" rows="6"></textarea>
			</p>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp"></c:import>
	</form>
</div>