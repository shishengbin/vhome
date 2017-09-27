<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<style type="text/css" media="screen">
.my-uploadify-button {background:none;border: none;text-shadow: none;border-radius:0;}
.uploadify:hover .my-uploadify-button {background:none;border: none;}
.fileQueue {width: 400px;height: 150px;overflow: auto;border: 1px solid #E5E5E5;margin-bottom: 10px;}
.imagesUpload {height: 34px;left: 0; opacity: 0;position: absolute;top: 0;width: 73px;}
.ke-upload-area {margin: 0;overflow: hidden;padding: 0;position: relative;}
.ke-button-common {cursor: pointer;display: inline-block;height: 23px;line-height: 23px;overflow: visible;vertical-align: top;}
.ke-button {background-image:url('images/upbtn.png');background-position: right -25px;border: 0 none;color: #333;font-family: "sans serif",tahoma,verdana,helvetica;font-size: 12px;padding: 0 12px;text-decoration: none;margin-top: 50px;width: 12px;}
.ke-upload-area .ke-upload-file
 {border: 0 none;height: 25px;opacity: 0;padding: 0;right: 0;top: 0;margin-left:-75px;margin-bottom:-10px;padding-top:38px;z-index: 811212;}
</style>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/wx/updateAccount" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle"><img alt="" src="images/gzh.png"></p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			<input type="hidden" name="id" value="${account.id }" />
			<input type="hidden" id="filename" name="filename" value="${picurl}">
			<div class="unit">
				<label>公众号名称：</label>
				<input type="text" name="ac_name" size="28"  class="required" value="${account.ac_name }" />
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>公众号原始ID：</label>
				<input type="text" name="ac_original_id" size="28" class="required" value="${account.ac_original_id }" />
				<span class="info">&nbsp;&nbsp;请认真填写</span>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<div class="ke-upload-area">
				<label>头像：</label>
				<c:if test="${empty picurl}">
					<img alt="" src="images/fm.png" id="preview" width="110px" height="110px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty picurl}">
					<img alt="" src="${picurl}" id="preview" width="110px" height="110px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
					<span class="ke-button-common">
						<input class="ke-button-common ke-button" type="button" style="width: 80px;">
					</span>
					<input class="ke-upload-file" type="file" tabindex="-1" id="file" name="file" onchange="uploadingImgage(this)" style="width: 60px;">
			   </div>
			</div>
			<div class="divider"></div>
			<br><br>
			<div class="unit">
				<label>微信号：</label>
				<input type="text" name="ac_wx_number" size="28" class="required"  value="${account.ac_wx_number }"/>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>接口地址：</label>
				<span class="vxaddress">http://www.vcodo.com/api?t=68a9c0eb4599d88cad74f84037923270==M</span>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>TOKEN：</label>
				<span class="vxtoken">sy_000</span>
			</div>
			<br><br/>
			<div class="divider"></div>
			<div class="unit">
				<label>地区：</label>
					<c:set value="${fn:split(account.ac_city,'-')}" var="city"></c:set>
					<div id="test">
							<select name="province" style="margin-left: 0px;" zhi="${not empty city[0] ? city[0] : '江苏' }"></select>
							<select name="city1" zhi="${not empty city[1] ? city[1] : '苏州' }"></select>
							<select name="city2" zhi="${not empty city[2] ? city[2] : '苏州市'}"></select>
					</div>
			</div>
			<br>
			<div class="divider"></div>
			<br><br>
			<div class="unit">
				<label>公众号邮箱：</label>
				<input type="text" name="ac_email" size="28" value="${account.ac_email }"/>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>粉丝数：</label>
				<input type="text" name="ac_funs" size="28" value="0" readonly="readonly"/>
			</div>
			<br><br>
			<div class="divider"></div>
			<div class="unit">
				<label>类型：</label>
				<select class="combox" id="actype" name="actype">
					<option value="0">选择类型</option>
					<option value="1">文学</option>
					<option value="2">娱乐</option>
					<option value="3">数码</option>
					<option value="4">IT</option>
					<option value="5">体育</option>
					<option value="6">购物</option>
					<option value="7">服务</option>
				</select>
			</div>
			<br/><br/>
			<div class="divider"></div>
		</div>
		<c:import url="../ws/pageControl/submitButton.jsp" />
	</form>
	<script type="text/javascript">
	$(function() {
		/* 初始化省市级联 */
		$("#test").ProvinceCity();
	    $(".combox").val("${account.ac_type}");
	});
	
	</script>
</div>
