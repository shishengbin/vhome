<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
	<%@ include file="../pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<SCRIPT LANGUAGE="JavaScript">
 /* ajax图片上传 */
	function uploadingImgage(th){
		/* 验证是否是有效图片格式 */

		/* 上传图片 */
		var thisid = $(th).attr("id");
		var imgval=$("#file").val();
		$.ajaxFileUpload({
					url : "ws/picupload?width=0&height=0", 
					secureuri : false,
					fileElementId : thisid, //文件选择框的id属性（必须）
					dataType : 'text',
					data : {"updateP" : thisid},
					success : function(data, status) {
						var data = eval("(" + data + ")");
						if(data.state==1)
							alert("图片格式不符，支持bmp、jgp、png、gif格式");
						else if(data.state==2)
							alert("图片大小超过1M");
						else if(data.state==3)
							alert("图片宽高不符合要求");
						else if(data.updateP==thisid)
						{
							var imgsrc="images/"+data.fileName;
							$("#preview").attr("src",imgsrc);
							$("#filename").val(data.fileName);
						}
					},
					error:function(){
						
					}
				});
	}
 </SCRIPT>
 <font color="blue">
	<h2 class="contentTitle">
		在这里，您可以维护APP二维码！
	</h2>
	</font>
<div class="pageContent">
	
		<form method="post" name="Myform" action="ws/updateUserPic" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">

			<input type="hidden" value="${about.id}" name="id">
			
		<div class="pageFormContent nowrap" layoutH="97">  
			<div>
				将软件在应用市场的下载地址转为二维码后，将二维码图片上传，以便更多人扫描下载。
			</div>  
			<dl>
				<dt style="background-color: #FFF0F5">二维码：</dt>
				<dd>
					<input type="file" name="file" id="file" onchange="uploadingImgage(this)"/>
					<span class="info"></span>
				</dd>
			</dl>
			<img  id="preview" alt="" name="picture" src="ws/showUserPicture?id=${about.id}" name="picture" width="300px" height="300px">
		</div>
		<input type="hidden" id="filename" name="filename" value="${filename}">
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
