<%@ page language="java" contentType="text/html" import="java.text.DateFormat" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<html>
<body> 
<form action="ws/addProduct" method="post" id="iform" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
	<p class="contentTitle" style="font-size: 15px;color: #09c;font-weight: bold;">
			商品信息
		</p>
	<div class="pageFormContent" layoutH="98">
			<c:if test="${not empty product }">
				<input type="hidden" value="${product.id }" name="id" />
				<input type="hidden" value="${product.tid}" name="tid" />
			</c:if>
			<fieldset>
				<legend>基本信息</legend>
				<dl>
					<dt >产品名称:</dt>
					<dd>
						<input name="productName" value="${product.productName}"class="required"><span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt >单位:</dt>
					<dd>
						<select id="specname" name="specname" style="margin-left: 0px;width: 135px;">
						</select>
					</dd>
				</dl>
				<dl>
					<dt >保 质 期:</dt>
					<dd>
						<input name="expdate" value="${product.expdate}" class="required" >天<span class="info"></span>
					</dd>
				</dl>
				<dl>
				<dt>成 本 价：</dt>
					<dd>
						<input type="text" name="bazaarPrice" id="bazaarPrice"value="${product.bazaarPrice}" class="number">
						<span class="info"></span>
					</dd>
					
				</dl>
				<dl>
					<dt >生产日期:</dt>
					<dd>
						<input name="producedate" value="${product.producedate}" class="date" readonly="true" />
					</dd>
				</dl>
				
				<dl style="width: 500px;">
					<dt>提 升 率：</dt>
					<dd style="width: 300px;">
						<input type="text" name="productFavorable" id="productFavorable"value="${product.productFavorable}" class="number" maxlength="4">
						<span class="info">1.2代表升20%</span>
					</dd>
				</dl>
				<dl>
				<dt >商品产地:</dt>
					<dd>
						<input type="text" name="productaddress" value="${product.productaddress}"><span class="info"></span>
					</dd>
					
				</dl>
				<dl style="width: 500px;">
					<dt>产品售价：</dt>
					<dd style="width: 300px;">
						<input type="text" name="productPrice" id="productPrice" value="${product.productPrice}" class="number" readonly="readonly" style="color: #000">
						<span class="info">成本价*(乘)提升率=售价</span>
					</dd>
				</dl>
				<dl style="width:530px;">
					<dt>所属城市:</dt>
					<dd style="width: 330px;">
						<c:set value="${fn:split(product.productCity,'-')}" var="city"></c:set>
						<div id="test">
							<select name="province" style="margin-left: 0px;" zhi="${not empty city[0] ? city[0] : '江苏' }"></select>
							<select name="city1" zhi="${not empty city[1] ? city[1] : '苏州' }"></select>
							<select name="city2" zhi="${not empty city[2] ? city[2] : '苏州市'}"></select>
						</div>
					</dd>
				</dl>
				<dl style="width: 660px;">
					<dt>所属供应商:</dt>
					<dd style="width: 500px;">
						<select  id="supplier" name="supplier" style="margin-left: 0px;width: 150px;">
						  <option value="-1" selected="selected">------请选择------</option>
						</select>
					</dd>
				</dl>
				<dl style="width: 660px;">
					<dt>商品分类:</dt>
					<dd style="width: 500px;">
						<select id="fis_wptyeid" name="fis_wptyeid" style="margin-left: 0px;width: 150px;" onchange="dochange(this.value)">
						  <option value="-1" selected="selected">------请选择------</option>
						</select>
						<select id="sec_wptyeid" name="sec_wptyeid" style="display: none;width: 150px;">
						  <option value="-1">--请选择--</option>
						</select>
					</dd>
				</dl>
			</fieldset>
			<fieldset style="margin-top: 10px;" id="cptp">
				<legend>商品图片(<font color="#09c">建议上传500像素*500像素的图片,大小500kb以内</font>)</legend>
				<div class="image-container">
					<!-- 显示图片 -->
				</div>
			</fieldset>
			<fieldset style="margin-top: 10px;">
				<legend>商品描述</legend>
				<div>
					<textarea rows="12" cols="120" name="productRemark" class="editor" tools="Cut,Copy" upImgUrl="imgUpload"	upImgExt="jpg,jpeg,gif,png">
					   ${product.productRemark}
					</textarea>
				</div>
			</fieldset>
		</div>
		<div class="formBar">
			<ul style="float: left;margin-left: 5px;">
				<li>
				
					<div class="buttonActive" style="display: inline;">
				 	<div class="buttonContent"><button type="button" onclick="addpic()">增加图片</button></div></div>
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
	function addpic(){
		/* 获取已经增加多少上传图片组件*/
		var fileCount = $(".image-container input:file").length+1;
		var imageCount = $('.image-container .image-item').size();
		if(imageCount < 5){
		/*增加图片组件*/
			$(".image-container").append(
					"<div class='image-item'>"+
						"<span style='width: 200px;height: 200px;border: 1px #C4C4C4 solid; display: block; float: left; margin: 5px;'>"+
						"<input type='file' name='uploadPic' class='filePrew' id='uploadPic' fileFormat='GIF,JPG,JPEG,PNG,BMP' style='width: 65px;height: 27px; margin-left: 20px;'>"+
						"<input type='button' value='删除' style='margin-left: 20px;width:65px;' onclick='removePic(this)'>"+
						"<span style='width: 90%;height: 1px;display: block;border-top:1px dotted #C7C7C7; margin:auto;'></span>"+
						"<span style='width:100%;height: 175px; display: block;'><img src='images/fm.png' class='img-rounded' style='width: 100%;height: 97%'/></span>"+
						"</span>"+
					"</div>"
					);
		}else{
			 alert("最多添加五张图片");
		}
	}

	//验证上传图片的格式，大小及预览
	$('.image-container').on('change','.image-item  .filePrew',function(e) {
			if (this.files[0].size > 3145728) {
				alert("图片大小不能超过3M");
			} else {
				var target = this;
				var name = target.value;
				var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
				var fileFormat = $(this).attr("fileFormat");
				var fileFormatArr = fileFormat.split(",");
				var isAllowed = 0;
				for ( var i = 0; i < fileFormatArr.length; i++) {
					if (fileName == fileFormatArr[i].toLowerCase().trim()) {
						isAllowed++;
					}
				}
				if (isAllowed > 0) {
					console.log(this.files[0]);
					$(this).parents(".image-item").find(".img-rounded").attr("src",window.URL.createObjectURL(this.files[0]));
				} else {
					alert("图片格式仅支持(" + fileFormat + ")!");
					target.value = "";
				}
			}
		});

	function uploadingImgage2(th) {
		/* 验证是否是有效图片格式 */
		/* 上传图片 */
		var thisid = $(th).attr("id");
		$.ajaxFileUpload({
					url : "picupload?width=0&height=0",
					secureuri : false,
					fileElementId : thisid, //文件选择框的id属性（必须）
					dataType : 'text',
					data : {
						"updateP" : thisid
					},
					success : function(data, status) {
						var data = eval("(" + data + ")");
						if (data.state == 1)
							alert("图片格式不符，支持bmp、jgp、png、gif格式");
						else if (data.state == 2)
							alert("图片大小超过100K");
						else if (data.state == 3)
							alert("图片宽高不符合要求");
						else if (data.state == 4)
							alert("上传失败！");
						else if (data.updateP == thisid) {
							/* 增加img标签将图片显示出来 */
							$("#" + thisid).next().next().next().html("<img src='"+data.fileName+"' width='200' height='175'>");
							/*真正保存图片的地方*/
							$("#" + thisid).after("<input type='hidden' id='filename' name='filename' value='"+data.fileName+"'>");
							/* 上传完后不允许修改 */
							$("#" + thisid).attr("disabled", true);
						}
					},
					error : function() {
						alert("预览失败！");
					}
				});
	}
	/*移除图片*/
	function removePic(th) {
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
			data : {
				"levels" : "one"
			},
			dataType : "text",
			cache : false,
			success : function(data) {
				console.info(data);
				$.each($.parseJSON(data), function(index, parent) {
					$("#fis_wptyeid").append(
							"<option value='"+parent.id+"'>" + parent.tname
									+ "</option>");
				});
				$("#fis_wptyeid").val('${parentid}');
				//	alert('${product.tid}');
			},
			error : function() {
				alert("初始化失败！");
			}
		});
		/*商品规格*/
		$.ajax({
			url : "ws/findAllSpecByJson",
			type : "post",
			dataType : "json",
			success : function(data) {
				$.each(data.data, function(index, sp) {
					$("#specname").append(
							"<option value='"+sp.id+"'>" + sp.specname+ "</option>");
				});
				$("#specname").val('${product.specid}');
			},
			error : function() {
				alert("加载规格失败 !");
			}
		});

		/*供应商*/
		$.ajax({
			url : "ws/findAllSupplierByJson",
			type : "post",
			dataType : "json",
			success : function(data) {
				$.each(data.data, function(index, sp) {
					$("#supplier").append(
							"<option value='"+sp.id+"'>" + sp.suppcompany
									+ "</option>");
				});
				$("#supplier").val("${product.suppid}");
			},
			error : function() {
				alert("加载供应商失败 !");
			}
		});

		/* 计算打折价 */
		$("#bazaarPrice,#productFavorable").bind('change', function() {
			var k = $("#bazaarPrice").val();
			var l = $("#productFavorable").val();
			if (isNaN(k) || isNaN(l)) {
				alert("请输入数字!");
				$("#productPrice").val("");
				return;
			} else if (k * l < 0) {
				$("#productPrice").val("");
				alert("输入不正确!");
				return;
			}
			if (l == "") {
				l = 1;
			}
			//保留小数后两位
			$("#productPrice").val(Math.round(k * l * 100) / 100);
		});
	});

	function dochange(param) {
		$.ajax({
			url : "ws/findSecondAllByPageJson",
			type : "post",
			data : {
				"id" : param
			},
			dataType : "json",
			cache : false,
			success : function(data) {
				if (data.length > 0) {
					$("#sec_wptyeid").html('');
					$.each(data, function(index, type) {
						$("#sec_wptyeid").append(
								"<option  value='"+type.id+"'>" + type.tname
										+ "</option>");
					});
					$("#sec_wptyeid").css("display", "block");
				} else {
					$("#sec_wptyeid").html('');
					$("#sec_wptyeid").append(
							"<option  value='-1'>--请选择--</option>");
					$("#sec_wptyeid").css("display", "none");
				}
			},
			error : function() {
				alert("初始化失败！");
			}
		});
	}
</script>
</html>