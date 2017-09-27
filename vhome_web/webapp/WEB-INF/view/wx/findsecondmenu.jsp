<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../ws/pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		    <li><a class="edit" href="wx/findMenuByid?id={sid_user}&flag=update" title="修改子菜单" target="dialog" width="650" height="400"><span>修改子菜单</span></a></li>
			<li><a class="delete" href="wx/deleteSonMenu?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="91.5">
		<thead>
			<tr>
				<th width="30" align="center">菜单ID</th>
				<th width="110" align="center">子菜单名称</th>
				<th width="110" align="center">所属主菜单</th>
				<th width="150" align="center">触发关键词或链接地址</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${requestScope.commonbtn}" var="p">
			<tr target="sid_user" rel="${p.bt_id }" align="center">
				<td height="38px" align="center">${p.bt_id }</td>
				<td align="center">${p.name}</td>
				<td align="center">${fatmenu}</td>
                <c:if test="${p.type=='click' }">
				 <td align="center">${p.key }</td>
				</c:if>
				<c:if test="${p.type=='view' }">
				 <td align="center">${p.url}</td>
				</c:if>
			</tr>
		 </c:forEach>
		</tbody>
	</table>
</div>