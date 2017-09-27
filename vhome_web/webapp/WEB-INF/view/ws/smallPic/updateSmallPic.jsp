<%@ page language="java"  import="java.util.Random" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
	<%@ include file="../pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
@charset "utf-8";
/* CSS Document */

div,body,span,a,input,ol,ul,li,dl,dt,dd,img,option{
	margin:0;
	padding:0;
	list-style:none;
	border:none;
	}
body{
	font-size:12px; font-family:宋体; color:#000;
	}
a{text-decoration:none;}
a:hover{ text-decoration:underline;}


#content{
	width:1200px;
	height:450px;
	margin:0 auto;
	border-bottom:1px dashed #B6B6B6;
	}
.line{
	width:100%;
	height:3px;
	background:url(images/line.jpg) repeat-x;
	}
.teshu select {
margin-left:8px !important; 
margin-top:3px;
}
.zuoyou{
	width:100%;
	height:40px;
	margin-top:5px;
	margin-left: 15px;
   }
.zuoyou .zuo{
	height:40px;
	width:40px;
	border:1px #7C7C7C solid;
	display:inline-block;
	background-color:#E7E7E7;
	font-size:30px;
	color:#656668;
	text-align:center;
	line-height:40px;	
}
.zuoyou .you{
	height:40px;
	width:40px;
	border:1px #7C7C7C solid;
	display:inline-block;
	background-color:#E7E7E7;
	font-size:30px;
	color:#656668;
	text-align:center;
	line-height:40px;	
}
.tupian{
	margin-left:70px;
	margin-top:10px;
	width:100%;
	height:167px;
}
.tupian .tupian01{
	float:left;
	margin-right:396px;
	width:202px;
	height:167px;
}
.tupian ul.tupian02{
	float:left;
	width:202px;
	height:167px;
}
.tupian03{
	height:21px;
	line-height:21px;
	}
.tupian ul li{
	margin-bottom:7px;
}

.red{
	height:21px;
	line-height:21px;
	color:#933529;
	font-size:12px;
	display:inline-block;
	float:left;
}
.biaodan{
	margin-top:19px;
	width:1200px;
	height:270px;
	
}
.biaodan_l{
	width:600px;
	height:270px;
	float:left;
}
.biaodan_r{
	width:600px;
	height:270px;
	float:left;
}
.biaodan02{
	width:100%;
	height:20px;
}
.biaodan dt{
	float:left;
	height:20px;
	width:60px;
	text-align:left;
	font-weight:blod;
	color:#000;
	text-align:right;
	padding-right:10px;
}
.biaodan dd{
	float:left;
	width: 530px;
	
}
.biaodan dd .biaodan01{
	width:138px;
	height:18px;
	border:1px solid #8B8B8B;
	color:#676767;
	line-height: 18px;
}
.biaodan03{
	width:100%;
	margin-top:14px;
	height:134px;
}
select{
	width:88px;
	height:18px;
	margin-right:10px;
	overflow: hidden;

}
.biaodan03 li{
	margin-bottom:14px;
}

.images{
width:63px;
 height:21px; 
	cursor:pointer;
	display:inline-block;
	float:left;
	margin-right:10px;
	background: url(images/sahngchuan.jpg);
}
.red01{
	height:21px;
	line-height:21px;
	color:#933529;
	font-size:12px;
	display:inline-block;	
}


.file{  opacity: 0; width:63px;  height:21px; 
 filter:alpha(opacity:0);_visibility:hidden;
}
.spfl{
	width: 530px;
	height: 26px;
}
.teshu{
	display: inline-block; float: left;
	height: 26px;
	padding-left: 5px;
}
.teshu02{
	float: left;
	height: 26px;
	line-height: 26px;
	display: inline-block;

}
.bk{
    background:url("images/sahngchuan.jpg") no-repeat;
}
</style>
<SCRIPT LANGUAGE="JavaScript">
 /* ajax图片上传 */
	function uploadingImgage(th){
		/* 验证是否是有效图片格式 */

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
 </SCRIPT>
 <SCRIPT LANGUAGE="JavaScript">
 /* ajax图片上传 */
	function uploadingImgage2(th){
		/* 验证是否是有效图片格式 */

		/* 上传图片 */
		var thisid2 = $(th).attr("id");
		$.ajaxFileUpload({
					url : "ws/picupload?width=0&height=0", 
					secureuri : false,
					fileElementId : thisid2, //文件选择框的id属性（必须）
					dataType : 'text',
					data : {"updateP" : thisid2},
					success : function(data, status) {
						var data = eval("(" + data + ")");
						if(data.state==1)
							alert("图片格式不符，支持bmp、jgp、png、gif格式");
						else if(data.state==2)
							alert("图片大小超过1M");
						else if(data.state==3)
							alert("图片宽高不符合要求");
						else if(data.updateP==thisid2)
						{
							var imgsrc="images/"+data.fileName;
							$("#preview2").attr("src",imgsrc);
							$("#filename2").val(data.fileName);
						}
					},
					error:function(){
						alert("预览失败");
					}
				});
	}
 </SCRIPT>
<p class="contentTitle">小图广告位</p>
<form method="post" name="Myform" action="ws/updateSmallPic" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
	<div layoutH="98" style="float: left; display: block; margin: 10px; overflow: auto; width: 99%; border: solid 0px #CCC; line-height: 21px; background: #FFF;">
	    <c:if test="${not empty smallPic1}">
			<input type="hidden" name="id1" value="${smallPic1.smallPic_id}">
			<input type="hidden" name="id2" value="${smallPic2.smallPic_id}">
    	</c:if>
	<div id="content">
	<div class="line"></div>
    <div class="zuoyou">
    	<span style="width: 594px;height:40px;display:inline-block;"><span class="zuo">左</span></span>
        <span class="you">右</span>
    </div>
    <div class="tupian">
    <%
		Random r=new Random();
		session.setAttribute("nowT", r.nextLong());
    %>
    <ul class="tupian01">
    	<li>
          <c:if test="${not empty smallPic1}">
    		<img src="ws/showSmallPic?id=${smallPic1.smallPic_id}&t=${nowT}" width="202" height="132"  id="preview"/>
    	</c:if>
    	<c:if test="${empty smallPic1}">
    		 <img src="images/addPic.png" width="202" height="132"  id="preview"/>
    	</c:if>
        </li>
        <li class="tupian03" >
    
        <input type="file" name="fileField"  value="上传图片"  style="width:66px;float:left" id="fileField"   onchange="uploadingImgage(this)"/> 
     <!--     <span  class="images"><input type="file" name="fileField" class="file" id="fileField"  onchange="uploadingImgage(this)"/></span>
         -->
        <span class="red">
       		 <c:if test="${row%2==1}">
        		*请上传277*177图片
       		 </c:if>
          	 <c:if test="${row%2==0}">
        		*请上传192*177图片
       		 </c:if>
        </span>
        </li>
    </ul>
    <ul class="tupian02">
    	<li>
    	<c:if test="${not empty smallPic2}">
    		<img src="ws/showSmallPic?id=${smallPic2.smallPic_id}&t=${nowT}" width="202" height="132"  id="preview2"/>
    	</c:if>
    	<c:if test="${empty smallPic2}">
    		 <img src="images/addPic.png" width="202" height="132"  id="preview2"/>
    	</c:if>
        </li>
        <li class="tupian03" >
                <input type="file" name="fileField2"  value="上传图片"  style="width:66px;float:left" id="fileField2"   onchange="uploadingImgage2(this)"/> 
      <!--  <span class="images"> <input type="file" name="fileField2" class="file" id="fileField2"  onchange="uploadingImgage2(this)"/></span> -->
        <span class="red ">
        	  <c:if test="${row%2==0}">
        		*请上传277*177图片
        	  </c:if>
          	  <c:if test="${row%2==1}">
        		*请上传192*177图片
        	  </c:if>
        </span>
        </li>
    </ul>
    </div>
<div class="biaodan">   
    <div class="biaodan_l">
    	<dl class="biaodan02">
        	<dt>图片标题：</dt>
            <dd>
            <input type="text" value="${smallPic1.smallPic_name}" class="biaodan01 required textInput"  name="smallPic_name1" maxlength="8"/>
            <span class="red01">*请输入不超过8个汉字</span>
            </dd>
        </dl>
        <dl class="biaodan03">
        	<dt>链接到：</dt>
            <dd>
            	<ul>
                    <li class="spfl">
                        <div class="teshu02">
                    	<input type="radio" name="danxuan" checked="checked" value="0"/>
                        <span>商品分类:</span>
                    </div>
                    <div class="teshu">
						<select id="productClass"   zhi=""><option value="0">--请选择--</option></select>
						<select id="productTwoClass"  zhi=""><option value="0">--请选择--</option></select>
						<select id="productThreeClass"   zhi=""><option value="0">--请选择--</option></select>
						<select id="productFourClass"  name="productFour1" zhi=""><option value="0">--请选择--</option></select>
                    </div>
                    </li>
                	   <li class="spfl">
                        <div class="teshu02">
                        	<input type="radio" name="danxuan" value="1"/>
                            <span>文章分类:</span>
                        </div >
                        <div class="teshu">
                            <select name="newsClass1" id="newsClass1" zhi="$">
                            <option value="0">--请选择--</option>
                            </select>
                        </div>
                    </li>
                    <li class="spfl">
                        <div class="teshu02">
                        	<input type="radio" name="danxuan" value="2"/>
                            <span>自定义栏目分类:</span>
                        </div >
                        <div class="teshu">
                           <select name="moduleClass1" id="moduleClass1" zhi="">
                            <option value="0">--请选择--</option>
                            </select>
                        </div>
                    </li>
                </ul>
            </dd>
        </dl>
    </div>
    <div class="biaodan_r">
    	<dl class="biaodan02">
        	<dt>图片标题：</dt>
            <dd>
            <input type="text" value="${smallPic2.smallPic_name}" class="biaodan01 required textInput" name="smallPic_name2" maxlength="8"  />
            <span class="red01">*请输入不超过8个汉字</span>
            </dd>
        </dl>
        <dl class="biaodan03">
        	<dt>链接到：</dt>
            <dd>
            	<ul>
                    <li class="spfl">
                        <div class="teshu02">
                        <input type="radio" name="danxuan2" checked="checked" value="0"/>
                        <span>商品分类:</span>
                    </div>
                    <div class="teshu">
                        <select id="productClass1"   zhi=""><option value="0">--请选择--</option></select>
						<select id="productTwoClass1"  zhi=""><option value="0">--请选择--</option></select>
						<select id="productThreeClass1"   zhi=""><option value="0">--请选择--</option></select>
						<select id="productFourClass1"  name="productFour2" zhi=""><option value="0">--请选择--</option></select>
                    </div>
                    </li>
                	   <li class="spfl">
                        <div class="teshu02">
                            <input type="radio" name="danxuan2" value="1"/>
                            <span>文章分类:</span>
                        </div >
                        <div class="teshu">
                            <select name="newsClass2" id="newsClass2" zhi="">
                            <option value="0">--请选择--</option>
                            </select>
                        </div>
                    </li>
                    <li class="spfl">
                        <div class="teshu02">
                            <input type="radio" name="danxuan2" value="2"/>
                            <span>自定义栏目分类:</span>
                        </div >
                        <div class="teshu">
                            <select name="moduleClass2" id="moduleClass2" zhi="">
                            <option value="0">--请选择--</option>
                            </select>
                        </div>
                    </li>
                </ul>
            </dd>
        </dl>
    </div>
</div> 
<input type="hidden" id="filename" name="filename" value="old">
<input type="hidden" id="filename2" name="filename2" value="old">
</div>
</div>
<c:import url="../pageControl/submitButton.jsp"></c:import>
</form>
<script type="text/javascript">
$(function() {
	/* 初始化商品分类一级栏目 */
	LoadNewsList("newsClass1");
	LoadNewsList("newsClass2");
	LoadModuleList("moduleClass1");
	LoadModuleList("moduleClass2");
	$.ajax({
		url : "ws/initAddProduct",
		type : "get",
		data : {"levels" : "one"},
		dataType : "text",
		cache : false,
		success : function(data) {
			
			$.each($.parseJSON(data), function(index, person) {
				
				if ($("#productClass").attr('zhi') == person.id) {
					$("#productClass").append(
							"<option  selected='selected' value='"+person.id+"'>"+ person.remark + "</option>");
				} else {
					 $("#productClass").append("<option  value='"+person.id+"'>"+ person.remark + "</option>"); 
				}
				if ($("#productClass1").attr('zhi') == person.id) {
					$("#productClass1").append(
							"<option  selected='selected' value='"+person.id+"'>"+ person.remark + "</option>");
				} else {
					 $("#productClass1").append("<option  value='"+person.id+"'>"+ person.remark + "</option>"); 
				}
			});
			$("#productClass").trigger('change');
			$("#productClass1").trigger('change');
		},
		error : function() {
			alert("初始化失败！");
		}
	});
});
	/* 增加联动 */
	aa($("#productClass"), $("#productTwoClass"),"two");
	aa($("#productTwoClass"), $("#productThreeClass"),"three");
	aa($("#productThreeClass"), $("#productFourClass"),"four");
	
	aa($("#productClass1"), $("#productTwoClass1"),"two");
	aa($("#productTwoClass1"), $("#productThreeClass1"),"three");
	aa($("#productThreeClass1"), $("#productFourClass1"),"four");
	
/* 发送查询分类请求 */
function aa($fsz, $jsz, zhi) {
	$fsz.bind("change",function() {
		var a = $fsz.val();
		if(a==0){
			$jsz.html("<option value='0'>--请选择--</option>");
			$jsz.trigger('change');
		}else{
		$.ajax({
			url : "ws/initAddProduct",
			type : "get",
			dataType : "text",
			data : {"levels" : zhi,"id" : $fsz.val()},
			success : function(data) {
					$jsz.html("");
					
					$.each($.parseJSON(data),function(index,person) {
						if ($jsz.attr('zhi') == person.id) {
							$jsz.append("<option  selected='selected' class='combox' value='"+person.id+"'>"+person.remark+"</option>");
						} else {
							$jsz.append("<option class='combox' value='"+person.id+"'>"+person.remark+"</option>");
						}
					});
					$jsz.trigger('change');
			}
		});
		}
	});
};
</script>