<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<p class="contentTitle" style="font-size: 15px;color: #09c;font-weight: bold;">新闻资讯</p>
<div class="pageContent">
	<div class=".show_news" layoutH="98">
		<div style="text-align: center;	">
			<div style="font-size: 14px;font-weight: bold; margin: auto;">${news.newsTitle }</div>
			<div style="color: #CDBA96">作者：<c:out value="${news.newsAuthor }"></c:out> <font style="margin-left:50%">时间<fmt:formatDate value="${news.updateTime }" pattern="yyyy-MM-dd HH:mm"/> </font> </div>
		</div>
		<div class="spiltline"></div>
		<br/><br/>
		<p>
			&nbsp;&nbsp;&nbsp;&nbsp;${news.newsContent }
		</p>
	</div>
</div>
