<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<%@page import="com.sy.web.commons.Constants"%>
<%@page import="com.sy.modules.entity.sys.SysUser"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SysUser user = (SysUser) session.getAttribute(Constants.USER_LOGIN_SESSION_KEY);
%>

<div class="pageHeader">
	<div class="searchBar">	
		<b>友情提醒：</b><br/><br/>
		<p>1.主菜单至少开启1个，最多只能开启3个；2级子菜单最多开启5个，可不开启;</p><br/>
		<p>2.如果尚未在授权设置中填写在公众平台申请自定义菜单使用的“AppId”和“AppSecret”，只能保存自定义菜单设置，不能生成微信菜单！
		      注：自定义菜单使用的“AppId”和“AppSecret”<br/>&nbsp;&nbsp;&nbsp;在微信公众平台-功能-高级功能-开发模式页面获取;</p><br/>
		<p>3.生成自定义菜单,必须在已经保存的基础上进行,第一步必须先保存好菜单！第二步点击生成!</p><br/>
		<p>4.当您为自定义菜单填写链接地址时必须以"http://"开头;</p> <br/>
		<p>5.当您为自定义菜单填写关键字时只能填写一个关键字;</p><br/>
	</div>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		    <li><a class="add" href="wx/PreAddParentMenu" title="添加主菜单" target="dialog"  width="650" height="400"><span>添加主菜单</span></a></li>
			<li><a class="edit" href="wx/findMenuById?id={sid_user}" title="修改主菜单" target="dialog" width="650" height="400"><span>修改主菜单</span></a></li>
			<li><a class="delete" href="wx/deletParentMenu?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除主菜单</span></a></li>
			<li><a class="edit" href="wx/findSonMenusByFatId?id={sid_user}" target="navTab" rel="page100" ><span>查看子菜单</span></a></li>
			<li><a class="edit" href="wx/createMenu" title="确定要生成微信菜单吗?" target="ajaxTodo"><span>生成微信菜单</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="10%" align="center">菜单ID</th>
				<th width="20%" align="center">主菜单名称</th>
				<th width="80" align="center">触发关键词或链接地址</th>
				<th width="60">操作</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${requestScope.parentlist }" var="p">
			<tr target="sid_user" rel="${p.bt_id }" align="center">
				<td height="38px" align="center">${p.bt_id }</td>
				<td align="center">${p.name }</td>
				<c:if test="${p.type=='click' }">
				 <td align="center">${p.key }</td>
				</c:if>
				<c:if test="${p.type=='view' }">
				 <td align="center">${p.url}</td>
				</c:if>
				<td align="center">
					<a title="添加子菜单" target="dialog" href="wx/findMenuById?id=${p.bt_id}&flag=add" width="650" height="400">添加子菜单</a>
				  <!-- 	<a title="查看子菜单" target="navTab" rel="page10" href="wx/findSonMenusByFatId?id={sid_user}" width="650" height="400">查看子菜单</a>  -->
				</td>
			</tr>
		 </c:forEach>
		</tbody>
	</table>
</div>

