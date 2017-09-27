<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form method="post" action="sys/updateUserAlipay"  class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
	
		
		<div class="pageFormContent" layoutH="58">
		    
		    <div>
		      <img alt="" src="images/alipay_direct_icon.gif">
		    </div> 
		    <div align="center">
				<div class="unit">
					<label>支付宝ID：</label>
					<input type="text" name="alipayId" size="30"  class="required" />
				</div>
				<div class="unit">
					<label>支付宝Key：</label>
					<input type="text"  name="alipayKey" size="30" class="required"/>
				</div>
				<div class="unit">
					<label>支付宝账户：</label>
					<input type="text" name="alipayAccount" size="30"  class="required"/>
				</div>
			</div>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form>
</div>
