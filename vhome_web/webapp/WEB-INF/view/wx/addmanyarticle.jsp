<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}

.imagesUpload {
    height: 34px;
    left: 0;
    opacity: 0;
    position: absolute;
    top: 0;
    width: 73px;
}
.ke-upload-area {
    margin: 0;
    overflow: hidden;
    padding: 0;
    position: relative;
}

.ke-button-common {
    cursor: pointer;
    display: inline-block;
    height: 23px;
    line-height: 23px;
    overflow: visible;
    vertical-align: top;
}
.ke-button {
    background-image:url('images/upbtn.png');
    background-position: right -25px;
    border: 0 none;
    color: #333;
    font-family: "sans serif",tahoma,verdana,helvetica;
    font-size: 12px;
    padding: 0 12px;
    text-decoration: none;
    margin-top: 50px;
    width: 12px;
}
.ke-upload-area .ke-upload-file {
    border: 0 none;
    height: 25px;
    opacity: 0;
    padding: 0;
    right: 0;
    top: 0;
    margin-left:-75px;margin-bottom:-10px;padding-top:38px;
    z-index: 811212;
}
</style>
<script type="text/javascript">
	function dochange(param) {
		if(param == 0){
			$("#a").css('display', 'none');
			$("#b").css('display', 'none');
		}else if (param == 1) {
			$("#a").css('display', 'block');
			$("#b").css('display', 'none');
		} else {
			$("#a").css('display', 'none');
			$("#b").css('display', 'block');
		}
	}
</script>
<div class="pageContent">
	<form method="post" action="wx/addManyarticle" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle">
		  <img alt="" src="images/bjtw.jpg">
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
		    <input type="hidden" name="id" value="" />
		    <input type="hidden" id="filename" name="filename" value="">
			<div class="unit">
				<label>图文标题：</label>
				<input type="text" name="artitle" size="28"  class="required" value="" />
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
			  <div class="ke-upload-area">
				<label>图片封面：</label>
					<img alt="" src="images/fm.png" id="preview" name="picture"  width="150px" height="100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="ke-button-common">
					  <input class="ke-button-common ke-button" type="button" style="width: 80px;">
					</span>
					  <input class="ke-upload-file" type="file" tabindex="-1" id="file" name="file" onchange="uploadingImgage(this)" style="width: 60px;" />
					  <span style="color: red">*必填上传一张图片,建议大小（360*200）</span>
			   </div>
			</div>
	        <div class="divider"></div>
			<div class="unit">
				<label>图文简介：</label>
				<textarea name="description" cols="80" rows="6"></textarea>
			</div>
			<br/><br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>图文详情页显示封面：</label>
			      <input type="radio" name="flag" value="1" checked="checked" />是
		          <input type="radio" name="flag" value="0"/>否
			</div>
			<br>
			<div class="unit">
				<label>图文详情页内容：</label>
						<textarea class="editor" name="artdetail" rows="15" cols="95"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="upload.php" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="upload.php" upFlashExt="swf"
								upMediaUrl="upload.php" upMediaExt:"avi">
							</textarea>
			</div>
			<div class="divider"></div>
			<div class="unit">
				<label>图文外链类型：</label>
					<select class="combox" name="test" onchange="dochange(this.value)">
						<option value="0" selected="selected">选择类型</option>
						<option value="1">链接</option>
						<option value="2">业务模块</option>
					</select>
			</div>
			<br/><br/><br/>
			<div class="unit" id="a" style="display: none;">
				   <label>登录帐号：</label>
			       <input type="text" name="username"   size="35" />
			        <span style="color: red">(必填,必须是正确的URL格式)</span>	
			</div>
			<div class="unit" id="b" style="display: none;">
				   <label>跳转类型：</label>
				   <select style="width: 150px" class="combox" name="test" ">
						<option value="0" selected="selected">文本</option>
						<option value="1">单图文</option>
						<option value="2">多文本</option>
						<option value="3">语音</option>
						<option value="4">视频</option>
					</select>
					&nbsp;&nbsp;&nbsp;
				   <label>列表页链接：</label>
				   <select style="width: 150px" class="combox" name="test" ">
						<option value="0" selected="selected">文本</option>
						<option value="1">单图文</option>
						<option value="2">多文本</option>
						<option value="3">语音</option>
						<option value="4">视频</option>
					</select>
			</div>
			<br/><br/>
			<dl class="nowrap">
				<dt>多图文：</dt>
				<dd>
					<input name="org3.id" value="" type="hidden">
					<input name="org3.orgName" type="text" size="80" class="required" readonly="readonly" />
					<a class="btnLook" href="wx/findSingleArticlesByPage" lookupGroup="org3">查找带回</a>
					<span class="info">(通过复选框选择多个单图文查找回带)</span>
				</dd>
		    </dl>
			<div class="divider"></div>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
</div>
