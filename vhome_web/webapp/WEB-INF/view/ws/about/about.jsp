<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form action="ws/updateAbout" method="post"class="pageForm required-validate" enctype="multipart/form-data"
	      onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle">
		  <img alt="" src="images/geren.png">
		</p>
	    <div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			  <div layoutH="50" style="float: left; display: block; margin: 5px; overflow: auto; width: 836px; border: solid 0px #CCC; line-height: 21px; background: #FFF;">
		       <input type="hidden" name="id" value="${about.id}">
		         <textarea rows="15" cols="80" name="about" upImgUrl="imgUpload"
			       upImgExt="jpg,jpeg,gif,png" class="editor" tools="Cut,Copy,Paste,Pastetext,|,Source,Fullscreen,About">${about.about}
			     </textarea>
		       <div id="uploadList"></div>
	           </div>
		</div>
		<c:import url="../pageControl/submitButton.jsp" />
	</form>
</div>
