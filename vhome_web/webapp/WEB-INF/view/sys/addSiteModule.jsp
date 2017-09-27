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
    margin-top: 66px;
    width: 12px;
}
.ke-upload-area .ke-upload-file {
    border: 0 none;
    height: 25px;
    opacity: 0;
    padding: 0;
    right: 0;
    top: 0;
    margin-left:-67px;
    margin-bottom:-60px;
    z-index: 811212;
}
</style>
<script type="text/javascript">
	function dochange(param) {
		if (param == 0) {
			$("#a").css('display', 'none');
			$("#b").css('display', 'none');
		} else if (param == 1) {
			$("#a").css('display', 'block');
			$("#b").css('display', 'none');
		} else {
			$("#a").css('display', 'none');
			$("#b").css('display', 'block');
		}

	}

	function toChange(param) {
		if (param == 1) {
		 $("#modulecontent").html('');
		 $("#modulecontent").append("<option value=1>aaa</option>");
		 	
		}
	}
</script>
<div class="pageContent">
	<form method="post" action="sys/addSiteModule?flag=add" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle">
		   <img alt="" src="images/bjtw.jpg">
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
		    <input type="hidden" name="id" value="" />
		    <input type="hidden" id="filename" name="filename" value="">
			<div class="unit">
				<label>栏目名称：</label>
				<input type="text" name="modulename" size="30"  class="required" value="" />
			</div>
			<br/><br/><br/>
			<div class="unit">
				<label>栏目描述：</label>
				<textarea name="textarea1" cols="27" rows="3"></textarea>
			</div>
			<br/><br/><br/>
			<div class="divider"></div>
			<div class="unit">
			  <div class="ke-upload-area">
				<label>栏目封面：</label>
					<img alt="" src="images/fm.png" id="preview" name="picture"  width="140px" height="120px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="ke-button-common">
					  <input class="ke-button-common ke-button" type="button" style="width: 80px;">
					</span>
					  <input class="ke-upload-file" type="file" tabindex="-1" id="file" name="file" onchange="uploadingImgage(this)" style="width: 60px;" />
					  <span style="color: red">*必填上传一张图片,建议大小（360*200）</span>
			   </div>
			</div>
			<div class="divider"></div>
			<br/>
			<div class="unit">
				 <label>栏目类型：</label>
				 <label><input type="radio" name="moduletype" value="1" checked="checked" onclick="dochange(this.value)"/>自主栏目</label>
		         <label><input type="radio" name="moduletype" value="2" onclick="dochange(this.value)"/>系统栏目</label>
			</div>
			<br/><br/><br/><br/>
			<div class="unit" id="a" style="display: block;">
				   <label>访问路径：</label>
			       <input type="text" name="selfurl"   size="38" />
			        <span style="color: red">(必填,输入正确的URL格式)</span>	
			</div>
			<div class="unit" id="b" style="display: none;">
				   <label>跳转栏目：</label>
				   <select style="width: 120px"  name="modulehref" id="modulehref">
						<option value="wap/wap_firminfo.html" selected="selected">商家介绍</option>
						<option value="1">新闻管理</option>
						<option value="2">产品管理</option>
						<option value="3">个性栏目</option>
						<option value="4">营销活动</option>
					</select>
			</div>
			<br><br><br>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
</div>
