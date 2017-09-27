<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<form id="pagerForm" method="post" action="ws/showSms">
	<input type="hidden" name="username" value="${requestScope.username}" /> 
	<input type="hidden" name="pageNum"  value="1" />
	<input type="hidden" name="numPerPage" value="${requestScope.numPerPage }" /> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="ws/showSms" method="post" >
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					内容：<input type="text" name="content" value="${content}"/>
				</td>
			</tr>
		</table>
		<c:import url="../pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ws/preAddSms" target="dialog" title="添加短息" width="720" height="400"><span>添加</span></a></li>
			<li><a class="edit" href="ws/smsDetails?id={sid_user}&result=xg" target="dialog" width="720" height="400" title="修改短息"><span>修改</span></a></li>
			<li><a class="delete" href="ws/deleteSms?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		<%--<li><a class="edit" href="ws/smsDetails?id={sid_user}&result=ck" target="dialog" width="720" height="400" title="短信内容"><span>查看</span></a></li>--%>
			<li><a class="edit" href="ws/updateSmsStatus?id={sid_user}" target="ajaxTodo" title="确定要设为默认吗?"><span>设为默认</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="5%">默认</th>
				<th width="19%">短信主题</th>
				<th width="19%">短信内容</th>
				<th width="10%">创建人</th>
				<th width="19%">创建时间</th>
				<th width="19%">最后修改时间</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${list}" var="sms">
				<tr target="sid_user" rel="${sms.id }">
					<td>
						<c:if test="${sms.sms_use>0}">
							<label><input type="radio" name="sms_use" value="${sms.id}"  checked="checked"/></label>
						</c:if>
						<c:if test="${sms.sms_use==0}">
							<label><input type="radio" name="sms_use" value="${sms.id}" /></label>
						</c:if>
					</td>
					<td>${sms.sms_title}</td>
					<td>
						<c:choose>
    						<c:when test="${fn:length(sms.sms_content) > 12}">
     							<c:out value="${fn:substring(sms.sms_content, 0, 13)}......" />
   							</c:when>
    						<c:otherwise>
     							<c:out value="${sms.sms_content}" />
    						</c:otherwise>
   						</c:choose>
					</td>
					<td>${sms.createName}</td>
					<td><fmt:formatDate value="${sms.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
					<td><fmt:formatDate value="${sms.updateTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp"></c:import>
</div>