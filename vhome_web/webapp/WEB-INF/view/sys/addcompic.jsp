<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
#infomation{
  color: red;
}
</style>
<div class="pageContent">
		<form method="post" action="sys/addComPic?flag=add" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		 <input type="hidden" name="id" value="" />
		 <input type="hidden" id="filename" name="filename" value="">
		<div class="pageFormContent nowrap" layoutH="56" >   
			<dl>
				<dt style="width:80px">图片名称：</dt>
				<dd  style="width:200px">
					<input type="text" name="picname"  class="required"  size="30" />
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">请输入有效的图片名称</span></dd>
			</dl>
			<dl>
				<dt style="width:80px">图片上传：</dt>
				<dd  style="width:200px">
					<input type="file" name="file" id="file" onchange="uploadingImgage(this)" width="100px">
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">为了达到最佳显示效果，请上传200*200的图片，大小1M以内</span></dd>
			</dl>
			
				<dl>
				<dt style="width: 80px">图片预览：</dt>
				<dd  style="width: 200px;height: 335px;">
					<img id="preview" name="picture" src="" style="width: 198px;height: 200px;">
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" id="infomation">图片预览</span></dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>