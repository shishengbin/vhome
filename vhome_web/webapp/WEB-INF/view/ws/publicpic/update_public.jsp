<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pageContent">
		<form method="post" action="ws/updatepublic" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<input type="hidden" value="${publicpic.publicid }" name="id">
		<div class="pageFormContent nowrap" layoutH="56" >
			<dl>
				<dt style="width: 20%">图片显示：</dt>
				<dd  style="width: 70%">
					<img alt="" src="ws/showPublic?id=${publicpic.publicid }" width="30%">
				</dd>
			</dl>			
			<dl>
				<dt style="width: 20%">图片名称：</dt>
				<dd  style="width: 70%">
				<c:if test="${mark=='xg'}">
					<input type="text" name="publicname"  class="required"  size="30" value="${publicpic.publicname }"/>	
					<span class="info"></span>
				</c:if>
				<c:if test="${mark=='ck'}">
					${publicpic.publicname }
				</c:if>
				</dd>
			</dl>
			<dl>
				<dt style="width: 20%">图片描述：</dt>
				<dd  style="width: 70%">
				<c:if test="${mark=='xg'}">
					<textarea name="publicremark" cols="32" rows="6">${publicpic.publicremark }</textarea>
					<span class="info"></span>
				</c:if>
				<c:if test="${mark=='ck'}">
					${publicpic.publicremark }
				</c:if>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<c:if test="${mark=='xg'}">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
