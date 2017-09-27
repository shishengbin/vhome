<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
<div class="pageContent">
	<c:choose>
		<c:when test="${not empty payment.id }">
			<c:set var="action" value="ws/indent/updatePayment"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="action" value="ws/indent/createPayment"></c:set>
		</c:otherwise>
	</c:choose>
	<form method="post" action="${action }" id="abc"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="56">
			<c:if test="${not empty payment.id }">
				<input type="hidden" value="${payment.id }" name="id">
			</c:if>
			<p>
				<label>支付方式：</label> <input name="name" type="text" size="30"
					value="${payment.name }" class="required" />
			</p>
			<table class="list nowrap itemDetail" addButton="添加参数" width="100%">
				<thead>
						<tr>
							<th type="text" name="mark[#index#].key" size="30"
								fieldClass="required">key</th>
							<th type="text" name="mark[#index#].value" size="30"
								fieldClass="required">value</th>
							<th type="del" width="60">操作</th>
						</tr>					
				</thead>
				<tbody>
					<c:forEach items="${payment.mark }" var="mark" varStatus="index">
						<tr class="unitBox">
							<td>
							<input name="mark[${index.index }].id" value="${mark.id }" type="hidden">
							<input name="mark[${index.index }].key" value="${mark.key }" size="30"
								class="required textInput" type="text"></td>
							<td><input name="mark[${index.index }].value" value="${mark.value }" size="30"
								class="required textInput" type="text"></td>
							<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
