<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
		<form method="post" action="ws/addNews" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
		<c:if test="${not empty news }">
			<input type="hidden" value="${news.id}" name="id">
			<input type="hidden" id="filename" name="filename" value="${newsRemark}">
		</c:if>
		<div class="pageFormContent nowrap" layoutH="56">
		    <dl>
				<dt>新闻标题：</dt>
				<dd>
					<input type="text" name="newsTitle"  class="required" value="${news.newsTitle }"/>
					<span class="info"></span>
				</dd>
			</dl>   
			<br/><br/>
			<div class="divider"></div> 
			<dl>
				<dt style="background-color: #ffffff">图片预览：</dt>
				<c:if test="${empty news }">
				<dd>
					<img id="preview" src="images/fm.png" alt="无" width="180" height="120">
				</dd>
				</c:if>
				<c:if test="${not empty news }">
				<dd>
					<img id="preview" src="${newsRemark}" alt="无" width="180" height="120">
				</dd>
				</c:if>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width: 80px">新闻图片：</dt>
				<dd style="width: 200px">
					<input type="file" name="file" id="file" onchange="uploadingImgage(this)"/>
					<span class="info"></span>
				</dd>
				<dd style="width:25px"></dd>
				<dd style="width:200px"><span class="info" style="color: red;">为了达到最佳显示效果，请上传480*320的图片，大小500KB以内</span></dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt>作者：</dt>
				<dd>
					<input type="text" name="newsAuthor" maxlength="10" class="required" value="${news.newsAuthor }"/>
					<span class="info"></span>
				</dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt>新闻分类：</dt>
				<dd>
					<c:if test="${not empty news }">
						<c:set var="name" value="ncu"></c:set>
					</c:if>
					<c:if test="${empty news }">
						<c:set var="name" value="nca"></c:set>
					</c:if>
					<select id="${name }" style="margin-left: 0px; width: 126px;" name="newsClass.id" zhi="${news.newsClass.id }">
					</select>
					<span class="info"></span>
				</dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="background-color: #FFF0F5">新闻内容：</dt>
				<dd>
					<textarea rows="15" cols="80" name="newsContent" upImgUrl="imgUpload" tools="Cut,Copy" upImgExt="jpg,jpeg,gif,png" class="editor">${news.newsContent }</textarea>
					<span class="info"></span>
				</dd>
			</dl>
		</div>
		<input type="hidden" id="filename" name="filename" value="${filename}">
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
<script type="text/javascript">
	function qc(th){
		/* 上传图片 */
		var thisid = $(th).attr("id");
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
						alert("预览失败");
					}
				});
	}
	$(function(){
		$("input[type='text']").css("width",120);
		LoadNewsList("${name}");
	});
</script>
