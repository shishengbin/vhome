<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<form action="ws/addProduct" method="post" id="iform" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)" >
	<div class="pageFormContent" layoutH="98">
	
			<fieldset>
				<legend>基本信息</legend>
				<dl>
					<dt >产品名称:</dt>
					<dd>
						<input name="productName" value="" class="required"><span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt>成本 价：</dt>
					<dd>
						<input type="text" name="bazaarPrice" id="bazaarPrice"
							value="" class="number"><span
							class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt>提升率：</dt>
					<dd>
						<input type="text" name="productFavorable" id="productFavorable" value="" class="number" maxlength="4">
						<span class="info">输入1.2表示提升20%</span>
					</dd>
				</dl>
				<dl style="width: 500px;">
					<dt>产品售价：</dt>
					<dd style="width: 300px;">
						<input type="text" name="productPrice" id="productPrice"
							value="" class="number" readonly="readonly" style="color: #000"><span
							class="info">成本价*(乘)提升率=售价</span>
					</dd>
				</dl>
				<dl style="width:530px;">
					<dt>所属城市:</dt>
					<dd style="width: 330px;">
						<c:set value="${ fn:split(product.productCity,'-')}" var="city"></c:set>
						<div id="test">
							<select name="province" style="margin-left: 0px;" zhi="${not empty city[0] ? city[0] : '江苏' }"></select>
							<select name="city1" zhi="${not empty city[1] ? city[1] : '苏州' }"></select>
							<select name="city2" zhi="${not empty city[2] ? city[2] : '苏州市'}"></select>
						</div>
					</dd>
				</dl>
				<dl style="width: 660px;">
					<dt>商品分类:</dt>
					<dd style="width: 500px;">
						<select id="parentype1" style="margin-left: 0px;" onchange="dochange(this.value)">
						<option value="-1" selected="selected">--请选择--</option>
						</select>
						<select id="secondtype1"  style="display: none">
						  <option value="0">--请选择--</option>
						</select>
					</dd>
				</dl>
			</fieldset>
			<fieldset style="margin-top: 20px;">
				<legend>产品描述</legend>
				<div>
					<textarea rows="8" cols="75" name="productRemark" class="editor"
						tools="simple" upImgUrl="imgUpload"	upImgExt="jpg,jpeg,gif,png"></textarea>
				</div>
			</fieldset>

			<fieldset style="margin-top: 20px;" id="cptp">
				<legend>为了达到最佳显示效果，请上传400*400的图片,大小1M以内</legend>
				
				
				<span class="picspan">
				<!-- 上传图片 -->
				<input type="file" name="uploadPic1" id="uploadPic1"  onchange="uploadingImgage2(this)" style="width: 70px;height: 24px;">
				<!-- 删除图片 -->
				<input type="button" value="删除" style="width: 70px;margin-left: 20px;" onclick="removePic(this)">
				<!-- 这个span是个分割线 -->
				<span class="spiltline"></span>
					<!-- 这个span里面显示图片 -->
					<span class="picontent"></span>
				</span>
			
			</fieldset>
		</div>
		<div class="formBar">
			<ul style="float: left;margin-left: 5px;">
				<li>
					<div class="buttonActive" style="display: inline;">
					<div class="buttonContent"><button type="button" onclick="zengjiapic()">增加图片</button></div></div>
				</li>
			</ul>
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</body>
<script type="text/javascript">
function zengjiapic(){
	/* 获取已经增加多少上传图片组件*/
	var fileCount = $("#cptp input:file").length+1;
	/*增加图片组件*/
	$("#cptp").append(
			"<span style='width: 200px;height: 200px;border: 1px #C4C4C4 solid; display: block; float: left; margin: 5px;'>"+
				"<input type='file' name='uploadPic"+fileCount+"' id='uploadPic"+fileCount+"' style='width: 70px;height: 24px; margin-left: 20px;' onchange='uploadingImgage2(this)'>"+
				"<input type='button' value='删除' style='margin-left: 20px;width:70px;' onclick='removePic(this)'>"+
				"<span style='width: 90%;height: 1px;display: block;border-top:1px dotted #C7C7C7; margin:auto;'></span>"+
				"<span style='width:100%;height: 175px; display: block;'></span>"+
			"</span>");
	}
	
function uploadingImgage2(th){
	alert(1);
	/* 验证是否是有效图片格式 */
	/* 上传图片 */
	var thisid = $(th).attr("id");
	alert(thisid);
	$.ajaxFileUpload({
				url : "picupload?width=0&height=0", 
				secureuri : false,
				fileElementId : thisid, //文件选择框的id属性（必须）
				dataType : 'text',
				data : {"updateP" : thisid},
				success : function(data, status) {
					var data = eval("(" + data + ")");
					if(data.state==1)
						alert("图片格式不符，支持bmp、jgp、png、gif格式");
					else if(data.state==2)
						alert("图片大小超过100K");
					else if(data.state==3)
						alert("图片宽高不符合要求");
					else if(data.updateP==thisid)
					{
						/* 增加img标签将图片显示出来 */
						$("#"+thisid).next().next().next().html("<img src='"+data.fileName+"' width='200' height='175'>");
						/*真正保存图片的地方*/
						$("#"+thisid).after("<input type='hidden' id='filename' name='filename' value='"+data.fileName+"'>");
						/* 上传完后不允许修改 */
						$("#"+thisid).attr("disabled", true);
					}
				},
				error:function(){
					alert("预览失败！");
				}
			});
}

	/*移除图片*/
	function removePic(th){
		$(th).parent().remove();
	}
	
	//初始化城市，商品分类等信息
	$(function() {
		/* 初始化省市级联 */
		$("#test").ProvinceCity();
		/* 初始化商品分类一级栏目 */
		$.ajax({
			url : "ws/findOneAllByPageJson",
			type : "get",
			data : {"levels" : "one"},
			dataType : "text",
			cache : false,
			success : function(data) {
				$.each($.parseJSON(data), function(index, person) {
						$("#parentype1").append("<option value='"+person.id+"'>"+ person.tname + "</option>");
				});
				alert($("#parentype1").html());
				//$("#productClass").trigger('change');
			},
			error : function() {
				alert("初始化失败！");
			}
		});
		/* 增加联动 */
		//aa($("#productClass"), $("#productTwoClass"),"two");
		//aa($("#productTwoClass"), $("#productThreeClass"),"three");
		//aa($("#productThreeClass"), $("#productFourClass"),"four");
		
		/* 计算打折价 */
		$("#bazaarPrice,#productFavorable").bind('change',function(){
			var k = $("#bazaarPrice").val();
			var l = $("#productFavorable").val();
			if(isNaN(k)||isNaN(l)){
				alert("请输入数字!");
				$("#productPrice").val("");
				return ;
			}else if(k<k*l||k*l<0){
				$("#productPrice").val("");
				alert("输入不正确!");
				return;
			}
			
			if(l==""){
				l=1;
			}
			$("#productPrice").val(k*l);
		});
	});
	
	function dochange(param){
			$.ajax({
				url : "ws/findSecondAllByPageJson",
				type : "post",
				data : {"id" : param},
				dataType : "json",
				cache : false,
				success : function(data) {
					if(data.length>0){
						$.each(data, function(index, type) {
								$("#secondtype1").append("<option  value='"+type.id+"'>"+ type.tname + "</option>");
						});
						$("#secondtype1").css("display","block");
					}else{
						$("#secondtype1").html('');
						$("#secondtype1").append("<option  value='-1'>--请选择--</option>");
						$("#secondtype1").css("display","none");
					}
				},
				error : function() {
					alert("初始化失败！");
				}
		  });
	  }
</script>
