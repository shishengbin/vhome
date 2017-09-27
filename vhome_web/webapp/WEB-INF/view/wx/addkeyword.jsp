<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<div class="pageContent">
<script type="text/javascript">
	function dochange(param) {
		if (param == 0) {
			$(".b").css('display', 'none');
			$(".a").css('display', 'block');
		}if(param == 1) {
			
			//单图文
			$(".a").css('display', 'none');
			$(".b").css('display', 'block');
			$("#article_id").empty();
			$.ajax({
				url:"wx/findAllArticlesByPageJson",
				dataType:"json",
				type:"post",
				success:function(data){
					$.each(data,function(i,data){
						$("#article_id").append("<option value="+data.id+">"+data.Title+"</option>");
					});
				},
				error:function(data){
				  	alert("加载失败！");
				}
			});
		}if(param == 2) {
			$(".a").css('display', 'none');
			$(".b").css('display', 'block');
			$("#article_id").empty();
			$.ajax({
				url:"wx/findManyArticleByPageJson",
				dataType:"json",
				type:"post",
				success:function(data){
					$.each(data,function(i,data){
						$("#article_id").append("<option value="+data.id+">"+data.Title+"</option>");
					});
				},
				error:function(data){
				  	alert("加载失败！");
				}
			});
		
		}
		
	}
</script>

	<form method="post" action="wx/addKeyword" class="pageForm required-validate"  onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle">
		 <img alt="" src="images/geren.png">
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
		     <input type="hidden" name="id" value="" />
		     <div class="unit">
				<label>关键词：</label>
			    <input type="text" name="keyname"  class="required"  size="28" />	
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>匹配类型：</label>
			      <input type="radio" name="r1" checked="checked"/>
			        &nbsp;完全匹配，用户输入的和此关键词一样才会触发，相同关键词只能使用一次！	
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>回复类型：</label>
					<select class="combox" name="article_type" onchange="dochange(this.value)">
						<option value="0" selected="selected">文本</option>
						<option value="1">单图文</option>
						<option value="2">多图文</option>
					<!-- 
						<option value="3">语音</option>
						<option value="4">视频</option>
				    -->
					</select>
					&nbsp;&nbsp;
					<span class="info" style="color: red">*回复图文、语音及视频资源时，请先进入素材管理进行资源添加</span>	
			</div>
			<br/><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>回复类容：</label>
				<div class="a" >
				  <textarea name="replycontent" cols="50" rows="10">${art.content }</textarea>
				</div>
				<div class="b"  style="display: none;">
				  <select style="margin-left: 2px;width:240px" id='article_id' name='article_id'>
				  
				  </select>
				</div>
			</div>
			<br/><br/>
			<div class="divider"></div>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
</div>
