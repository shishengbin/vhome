<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<c:if test="${result=='simple'}">
		<div class="replyBorder">
			<div style="margin: 10px;">
			<img src="images/5215552a68ddc621.jpeg!200x200.jpg" alt="头像" width="30px" height="30px"><font style="margin-right: 30px; font-family: '仿宋_GB2312'">${messages.createName }</font><font style="font-weight: bold;">${messages.messageTitle }</font>&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: #BEBEBE;float: right;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
			</div>
			<div style="margin-left: 40px;margin-top: 10px;margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${messages.messageContent }" default="无"></c:out>
			</div>
			<div class="spiltline"></div>
			<c:forEach items="${messages.reply }" var="reply">
				<c:if test="${not empty reply.replyContent}">
				<div style="margin-left: 50px;margin-top: 8px;">
				&nbsp;&nbsp;&nbsp;&nbsp;<img alt="头像" src="images/5215743f6a721931.jpg!200x200.jpg" width="30" height="30">
				${reply.replyContent }
				<font style="color: #BEBEBE;float: right;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
				</div>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${result=='show'}">
		<h2 class="contentTitle">留言板</h2>
	<div class="pageContent">
	<form method="post" action="ws/addReply" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" value="${messages.id }" name="messageId">		
		<div layoutH="79">
		<div style="overflow: auto;width:90%;border: 0px dotted #635BA2; margin: auto;" layoutH="221">
			<div style="margin: 10px;">
			<img src="images/5215552a68ddc621.jpeg!200x200.jpg" alt="头像" width="30px" height="30px"><font style="margin-right: 30px; font-family: '仿宋_GB2312'">${messages.createName }</font><font style="font-weight: bold;">${messages.messageTitle }</font>&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: #BEBEBE;float: right;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
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
				<font style="color: #BEBEBE;float: right;">时间：<fmt:formatDate value="${messages.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
				</div>
				</c:if>
			</c:forEach>
		</div>
		
		<div style="width: 100%; border: none;border-top: dotted 1px #94AAD6;">
			<textarea rows="6" cols="145" name="replyContent" class="editor" tools="simple"></textarea>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">回复</button></div></div></li>
			</ul>
		</div>
		</form>
		</div>
	</c:if>
	<c:if test="${result=='hide'}">
		<c:out value="${result }" default="无"></c:out>
	</c:if>
</div>
<script type="text/javascript">
function navTabAjaxDone(json){
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok){
          if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
                navTab.reloadFlag(json.navTabId);
          } else { //重新载入当前navTab页面
        	  navTab.openTab("page3", "ws/showReply", { title:"留言板", fresh:false, data:{"messageId":json.ms,"result":"show"} });
          }
          if ("closeCurrent" == json.callbackType) {
                setTimeout(function(){navTab.closeCurrentTab();}, 100);
          } else if ("forward" == json.callbackType) {
                navTab.reload(json.forwardUrl);
          }
    }
}
</script>