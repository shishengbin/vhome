<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="wx/updateMenu?flag=son" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56">
		    <br/>
		    <input type="hidden" name="fatid" value="${fatid}" />
			<input type="hidden" name="bt_id" value="${commonmenu.bt_id }" />
			<p>
				<label>子单名称：</label>
			    <input type="text" name="sonmenuname"  class="required"  size="28" value="${commonmenu.name }" />	
			</p>
			<p>
				<label>所属主菜单：</label>
			    <input type="text" name="parentmenuname"  size="28"  readonly="readonly" value="${complexMenuName }" />	
			</p>
			<p>
				<label>关键词或url：</label>
				<c:if test="${commonmenu.type=='click'}">
			    <input type="text" name="keyorurl"  class="required"  size="28" value="${commonmenu.key }"  />	
			    </c:if>
			    <c:if test="${commonmenu.type=='view'}">
			    <input type="text" name="keyorurl"  class="required"  size="28" value="${commonmenu.url}"  />	
			    </c:if>
			</p>
			<p>
				<label>备注信息：</label>
				<textarea name="menuremark" cols="32" rows="6"></textarea>
			</p>
		</div>
		<c:import url="/ws/pageControl/submitButton.jsp"></c:import>
	</form>
</div>