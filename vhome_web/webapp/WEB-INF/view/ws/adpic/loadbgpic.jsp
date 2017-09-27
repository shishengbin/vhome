<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>
#infomation {
	color: red;
}
</style>
<div class="pageContent">
	<form method="post" action="ws/addWsAdpic"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt style="width: 80px">图片名称：</dt>
				<dd style="width: 200px">
					<input type="text" name="publicname" class="required" />
				</dd>
				<dd style="width: 25px"></dd>
				<dd style="width: 200px">
					<span class="info" id="infomation">输入正确的图片名称</span>
				</dd>
			</dl>
			<br>
			<br>
			<div class="divider"></div>
			<dl>
				<dt style="width: 80px">图片上传：</dt>
				<dd style="width: 200px">
					<input type="file" name="file" id="file"
						onchange="uploadingImgage(this)" width="100px">
				</dd>
				<dd style="width: 25px"></dd>
				<dd style="width: 200px">
					<span class="info" id="infomation">为了达到最佳显示效果，请上传480*250的图片，大小300KB以内</span>
				</dd>
			</dl>
			<br>
			<br>
			<div class="divider"></div>
			<dl>
				<dt style="width: 80px">图片预览：</dt>
				<dd style="width: 200px; height: 350px;">
					<img id="preview" name="picture" src="images/fm.png"
						style="width: 200px; height: 150px;">
				</dd>
				<dd style="width: 25px"></dd>
			</dl>
		</div>
		<input type="hidden" id="filename" name="filename" value="">
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>