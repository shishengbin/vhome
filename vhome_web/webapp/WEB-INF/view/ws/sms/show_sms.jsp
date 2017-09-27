<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent" style="width:100%">
		<div class="pageFormContent nowrap" layoutH="56">          
			<dl>
				<dt style="background-color: #FFF0F5">主题：</dt>
				<dd>
					${sms.sms_title}
				</dd>
			</dl>
			<dl>
				<dt style="background-color: #FFF0F5">内容：</dt>
				<dd>
					${sms.sms_content}
				</dd>
			</dl>
			<dl>
				<dt style="background-color: #FFF0F5">创建人：</dt>
				<dd>
					${sms.createName}
				</dd>
			</dl>
			<dl>
				<dt style="background-color: #FFF0F5">创建时间：</dt>
				<dd>
					<fmt:formatDate value="${sms.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
				</dd>
			</dl>
		</div>
</div>
