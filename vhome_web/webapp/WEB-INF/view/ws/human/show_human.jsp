<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="56">
		<dl>
			<dt style="width: 80px">商家账号：</dt>
			<dd style="width: 200px">${human.human_account }</dd>
		</dl>
		<dl>
			<dt style="width: 80px">会员密码：</dt>
			<dd style="width: 200px">******</dd>
		</dl>
		<dl>
			<dt style="width: 80px">饭店名称：</dt>
			<dd style="width: 200px">${human.human_question }</dd>
		</dl>
		<dl>
			<dt style="width: 80px">手机号码：</dt>
			<dd style="width: 200px">${human.human_phone }</dd>
		</dl>
		<dl>
			<dt style="width: 80px">联系人：</dt>
			<dd style="width: 200px">${human.human_name}</dd>
		</dl>
		<dl>
			<dt style="width: 80px">地址：</dt>
			<dd style="width: 200px">${human.human_address }</dd>
		</dl>
	</div>
</div>
