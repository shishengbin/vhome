<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
 #infomation{
color: red;
}
</style>
<div class="pageContent">
	<form method="post" action="ws/updateSelfModuleInfo" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<%-- 	<div class="pageFormContent" layoutH="56">
			<br/>
			<input type="hidden" name="moduleId" value="${selfmodule.id}">
			<p>
				<label>栏目名称：</label>
				<input type="text" name="modulename"  size="30"  class="required" value="${selfmodule.module_name }"/>
			</p>
			<p>
				<label>栏目描述：</label>
				<textarea name="moduleremark" cols="32" rows="6">${selfmodule.module_remark }</textarea>
			</p>
		</div> --%>
		
		<div class="pageFormContent nowrap" layoutH="56" >    
			<input type="hidden" name="moduleId" value="${selfmodule.id}">      
			<dl>
				<dt style="width:80px">栏目名称：</dt>
				<dd  style="width:200px">
					<input type="text" name="modulename"  class="required"  size="30" value="${selfmodule.module_name}"/>
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">栏目名称</span></dd>
			</dl>
			<dl>
				<dt style="width:80px">图片上传：</dt>
				<dd  style="width:200px">
					<input type="file" name="file" id="file" onchange="uploadingImgage(this)" width="100px">
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">为了达到最佳显示效果，请上传150*150的图片，大小1M以内</span></dd>
			</dl>
			<dl>
				<dt style="width: 80px">图片预览：</dt>
				<dd  style="width: 200px;height: 335px;">
					<img id="preview" name="picture" src="${picurl }" style="width: 150px;height:150px;">
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">图片预览</span></dd>
			</dl>
		</div>
		<input type="hidden" id="filename" name="filename" value="old">
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>