<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="ws/showMessages" method="post">
		<div class="searchBar">
			<table class="searchContent" style="float: left;">
			<tr>
				<td>
					起始日期：<input type="text" class="date" readonly="true" name="qidate"/>
				</td>
				<td>
					终止日期：<input type="text" class="date" readonly="true" name="zhongdate"/>
				</td>
			</tr>
		</table>
			<c:import url="../pageControl/retrieval.jsp"></c:import>
		</div>
	</form>
</div>
<div class="pageContent">
	<div  layoutH="65.5" style="overflow: auto;">
	<c:forEach items="${messages}" var="messages" varStatus="index">
		<c:if test="${index.count%2==0}">
			<div style="background-color: #FFFAF0;" class="messageBg">
		</c:if>
		<c:if test="${index.count%2!=0}">
			<div style="background-color: #FFFAFA;" class="messageBg">
		</c:if>
		
			<div style="margin: 10px;">
			<img src="images/5215552a68ddc621.jpeg!200x200.jpg" alt="头像" width="30px" height="30px"><font style="margin-right: 30px; font-family: '仿宋_GB2312'">${messages.createName }</font><font style="font-weight: bold;">${messages.messageTitle }</font>&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: #BEBEBE; margin-left:200px;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
			</div>
			<div style="margin-left: 40px;margin-top: 10px;margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${messages.messageContent }" default="无"></c:out>
			</div>
			<div style="border: none;border-bottom: 1px dotted #BFCAE6; width: 60%;display: block;margin-left: 50px;"></div>
			<c:forEach items="${messages.reply }" var="reply">
				<c:if test="${not empty reply.replyContent}">
				<div style="margin-left: 50px;margin-top: 8px;">
				&nbsp;&nbsp;&nbsp;&nbsp;<img alt="头像" src="images/5215743f6a721931.jpg!200x200.jpg" width="30" height="30">
				${reply.replyContent }
				<font style="color: #BEBEBE; margin-left:200px;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
				</div>
				</c:if>
			</c:forEach>
			</div>
			</c:forEach>
		</div>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="ws/showMessages"></c:param>
	</c:import>
</div>
