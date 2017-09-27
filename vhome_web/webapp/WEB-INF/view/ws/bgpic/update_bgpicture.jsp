<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pageContent">
		<form method="post" action="ws/updatebgpic" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<input type="hidden" value="${bgpic.bgid }" name="id">
			<input type="hidden" value="${bgpic.bg_use }" name="bg_use">
		<div class="pageFormContent nowrap" layoutH="56" >
			<dl>
				<dt style="width: 20%">图片显示：</dt>
				<dd  style="width: 70%">
					<img alt="" src="ws/showBgpic?id=${bgpic.bgid }" width="30%">
				</dd>
			</dl>			
			<dl>
				<dt style="width: 20%">图片名称：</dt>
				<dd  style="width: 70%">
				<c:if test="${mark=='xg'}">
					<input type="text" name="bgpicname"  class="required"  size="30" value="${bgpic.bgname }"/>	
					<span class="info"></span>
				</c:if>
				<c:if test="${mark=='ck'}">
					${bgpic.bgname }
				</c:if>
				</dd>
			</dl>
			<dl>
				<dt style="width: 20%">图片描述：</dt>
				<dd  style="width: 70%">
				<c:if test="${mark=='xg'}">
					<textarea name="bgpicremark" cols="36" rows="6">${bgpic.bgremark }</textarea>
					<span class="info"></span>
				</c:if>
				<c:if test="${mark=='ck'}">
					${bgpic.bgremark }
				</c:if>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<c:if test="${mark=='xg'}">
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
