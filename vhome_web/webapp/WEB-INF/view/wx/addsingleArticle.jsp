<%@page import="com.sy.modules.entity.sys.SysUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  %>
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
		alert(123);
		if (param == 1) {
		 $("#modulecontent").html('');
		 $("#modulecontent").append("<option value=1>aaa</option>");
		 	
		}
	}
</script>
<div class="pageContent">
	<form method="post" action="wx/addSingleArticle" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
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
			<br><br>
	        <div class="divider"></div>
			<div class="unit">
				<label>图文简介：</label>
				<textarea name="description" cols="80" rows="6"></textarea>
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>图文详情页显示封面：</label>
			      <input type="radio" name="flag" value="1" checked="checked" />是
		          <input type="radio" name="flag" value="0"/>否
			</div>
			<br><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>图文详情页内容：</label>
						<textarea class="editor" name="artdetail" rows="15" cols="95"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="upload.php" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="upload.php" upFlashExt="swf"
								upMediaUrl="upload.php" upMediaExt:"avi">
							</textarea>
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>图文外链类型：</label>
					<select class="combox" name="test" onchange="dochange(this.value)">
						<option value="0" selected="selected">选择类型</option>
						<option value="1">链接</option>
						<option value="2">vHome业务模块</option>
					</select>
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit" id="a" style="display: none;">
				   <label>访问路径：</label>
			       <input type="text" name="linkurl"    size="35" />
			        <span style="color: red">(必填,必须是正确的URL格式)</span>	
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit" id="b" style="display: none;">
				   <label>跳转类型：</label>
				   <select style="width: 150px" class="combox" name="moduletype" id="moduletype" onchange="toChange(this.value)">
						<option value="0" selected="selected">企业应用</option>
						<option value="1">新闻管理</option>
						<option value="2">产品管理</option>
						<option value="3">个性栏目</option>
						<option value="4">营销活动</option>
					</select>
					&nbsp;&nbsp;&nbsp;
				   <label>列表页链接：</label>
				   <select style="width: 150px"  name="modulecontent" id="modulecontent" >
				        <option value="" selected="selected">--请选择--</option>
						<option value="wap/wap_firminfo.html">公司简介</option>
						<option value="1">一键拨打</option>
						<option value="2">二维码</option>
				   </select>
			</div>
			<br><br>
			<div class="divider"></div>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
</div>
