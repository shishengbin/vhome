<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<style>
 #infomation{color: red;}
</style>

<div class="pageContent">
	<form method="post" action="ws/addSecondType?flag=add" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">	
			<input type="hidden" name="partid" value="${partype.id }" />	
				<dl>
					<dt style="width:90px">二级分类：</dt>
					<dd  style="width:200px">
						<input name="tname" type="text" size="28"  class="required"  alt="请输入二级类别名"/>
					</dd>
					<dd style="width:25px"></dd>
					<dd style="width:200px"><span class="info" id="infomation">输入正确的分类名称</span></dd>
				</dl>
				<dl>
					<dt style="width:90px">所属一级分类：</dt>
					<dd  style="width:200px">
						<input name="partname" type="text" size="28"  readonly="readonly" value="${partype.tname }" />
					</dd>
					<dd style="width:25px"></dd>
				</dl>
				<dl>
					<dt style="width:90px">图片上传：</dt>
					<dd  style="width:200px">
						<input type="file" name="file" id="file" onchange="uploadingImgage(this)" width="100px">
					</dd>
					<dd style="width:25px"></dd>
					<dd style="width:200px"><span class="info" id="infomation">请上传150*150的图片，大小500KB以内</span></dd>
				</dl>
				<dl>
					<dt style="width: 90px">图片预览：</dt>
					<dd  style="width: 200px;height: 335px;">
						<img id="preview" name="picture" src="images/fm.png" style="width: 180px;height: 150px;">
					</dd>
					<dd style="width:25px"></dd>
					<dd style="width:200px"><span class="info" id="infomation">图片预览</span></dd>
				</dl>
		
		</div>
		<input type="hidden" id="filename" name="filename">
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
