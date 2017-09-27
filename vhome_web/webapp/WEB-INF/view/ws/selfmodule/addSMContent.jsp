<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<c:if test="${identify!='ck' }">
	<h2 class="contentTitle">
		<c:if test="${not empty smc }">
			修改栏目内容
		</c:if>
		<c:if test="${empty smc }">
			添加栏目内容
		</c:if>
	</h2>
	</c:if>
<div class="pageContent">
	<form method="post" action="ws/updateSmContent" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
		<c:if test="${lanmuId!=null }">
			<input type="hidden" name="lanmuId" value="${lanmuId }">
		</c:if>
		<c:if test="${smc!=null }">
			<input type="hidden" name="id" value="${smc.id }">
		</c:if>
		<div class="pageFormContent nowrap" layoutH="96">   
			<dl>
				<dt style="background-color: #FFF0F5;width: 80px">图片预览：</dt>
				<dd>
					<img id="preview" src="${picurl}" alt="无" width="240" height="160">
				</dd>
			</dl> 
			<dl>
				<dt style="background-color: #FFF0F5;width: 80px">标题：</dt>
				<dd>
					<c:if test="${identify=='ck'}">
						${smc.scm_title}
					</c:if>
					<c:if test="${identify!='ck'}">
						<input type="text" name="title"  size="30"  class="required" value="${smc.scm_title }"/>
					</c:if>
				</dd>
			</dl>
			<c:if test="${identify!='ck' }">
				<dl>
					<dt style="background-color: #FFF0F5;width: 80px">图片：</dt>
					<dd style="width: 200px">
						<input type="file" name="file" id="file" onchange="uploadingImgage(this)">
					</dd>
					<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" style="color: red;">为了达到最佳显示效果，请上传480*320的图片，大小1M以内</span></dd>
				</dl>
			</c:if>	
			<dl>
				<dt style="background-color: #FFF0F5;width: 80px">内容：</dt>
				<dd>
				<c:if test="${identify=='ck'}">
						${smc.smc_content }
					</c:if>
					<c:if test="${identify!='ck'}">
						<textarea name="content" cols="70" rows="15" name="newsContent" upImgUrl="imgUpload" upImgExt="jpg,jpeg,gif,png" class="editor">${smc.smc_content }</textarea>
					</c:if>
				</dd>
			</dl>
		</div>
		<input type="hidden" id="filename" name="filename" value="${filename}">
		<div class="formBar">
			<ul>
				<c:if test="${identify!='ck'}">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>