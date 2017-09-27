<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 

<title>索远-微店宝平台管理中心</title>
<meta name="keywords" content="手机app,苏州app,索远" />
<meta name="description" content="苏州索远科技" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custorm-core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custorm-subform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custorm-core.css" />
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ie6.css"  media="screen, projection" />
<script type="text/javascript">
$(function() {
		$('a').die().live('click', function() {
			var url = $(this).attr('url');
			if (url) {
				$(this).attr("href", "javascript:void(0)");
				se(url);
			}
		});
		sq();
		initjib();
		$.ajax({
					type : 'post', //请求方式
					url : 'findMenuByRoleId?t=' + Math.random() + '', //请求地址
					cache : true, //是否清空缓存
					dataType : 'json', //服务器返回时接收的数据类型
					success : function(result) {
						/*alert(JSON.stringify(result));*/
						$.each(
								result.data,
								function(index, param) {
									if (param.fatherid == 1&& param.id != 1) {
											var caid = "<li id=''><a href='javascript:void(0)'><span style='color:coral '>"
														+ param.mname
														+ "</span></a>";
												var i = 0;
												$.each(
														result.data,
														function(index,ziji) {
																if (ziji.fatherid == param.id) {
																		i++;
																		if (i == 1) {
																			caid += "<ul>";
																		}
																		caid += "<li><a href='javascript:void(0)' url='"
																				+ ziji.muri
																				+ "' style='color:coral '>"
																				+ ziji.mname
																				+ "</a></li>";
																}
															});
												if (i > 0) {
													caid += "</ul>";
												}
												$("#nav").append(caid + "</li>");
											}
										});
					},
					error : function(result) {
						tishi(0, "加载导航菜单失败，请稍后重试！");
					}
				});

		$(".centerPage").die().live('keyup', function(event) {
			if (event.keyCode == 13) {
				$(".search_submit").trigger("click");
			}
		});
		$("#search-field").die().live('keyup', function(event) {
			if (event.keyCode == 13) {
				$(".action").trigger("click");
			}
		});
		
		$("#autoclick").click();
		/*///////////////zw///////////////// */
		var url = $(".gao").attr("url");
		$.ajax({
					type : "POST",
					url : url,
					dataType : "json",
					success : function(msg) {
						$("#oneName").html(msg.postList[0].postname);
						$("#oneName").attr("url","onePost?id=" + msg.postList[0].id);
						$("#oneTime").html((new Date(msg.postList[0].createTime)).toLocaleDateString());
						$("#oneContent").html(msg.postList[0].postcontent.substring(0, 58)+ ".....");
						$("#twoName").html(msg.postList[1].postname);
						$("#twoName").attr("url","onePost?id=" + msg.postList[1].id);
						$("#twoTime").html((new Date(msg.postList[1].createTime)).toLocaleDateString());
						$("#twoContent").html(msg.postList[1].postcontent.substring(0, 58)+ ".....");
						$("#threeName").html(msg.postList[2].postname);
						$("#threeName").attr("url","onePost?id=" + msg.postList[2].id);
						$("#threeTime").html((new Date(msg.postList[2].createTime)).toLocaleDateString());
						$("#threeContent").html(msg.postList[2].postcontent.substring(0, 58)+ ".....");
					}
				});
	});
</script>
</head>
<body id="home"  onkeyup ="kk(event)">
<div id="header">
	<div class="container">
		<h1> <a href="" id="logo" name="top"><span></span><img src="images/logo.png" border="0" width="182" height="51" /></a> </h1>
		<div id="Layer1">
			<table width="700" height="29" border="0" align="center" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td width="410" style="padding-left: 62px">
							<p class="headText">欢迎您：<a id="usertext"></a>（<a id="comstext" ></a>）</p>
						</td>
						<td>
							<p>
								<%--<span class="headText"> 账户余额 :<a id="moneytext" style="color:#F9F400;"></a></span> --%>
								<span class="STYLE4" style="float: right;">
									<a href="javascript:void(0)" style="color: coral" onclick="openzhep('preUpdatePass',{},400,250)">修改密码</a>
									<a style="color: coral" onclick="if(confirm('确认退出?'))location='invalidate'" href="javascript:void(0)">退出</a>
								</span> 
							</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="top-container">
			<ul id="nav">
				 <li id="nav-about"><a href="javascript:void(0)" id="autoclick" url="findHomePage"><span style="color:coral ">首页</span></a></li>
			</ul>
			<div id="search-container">
					<div id="search-field-container">
						<input type="text" class="text" name="q" id="search-field" placeholder="关键词查询"/>
					</div>
					<div id="search-btn-container">
						
						<input type="image" src="images/go.png" class="action" />   
						<input type="hidden" name="access" value="p" />
						<input type="hidden" name="as_dt" value="i" />
					</div>
			</div>
			<!-- // search-container -->
			<div id="page-tools-container">
				<ul id="page-tools">
					<!-- <li><a href="javascript:void(0)" url="connect.html" id="tools-contact">联系我们</a></li>  -->
				</ul>
				<div class="clear"></div>
			</div>
			<!-- // page-tools-container -->
		</div>
		<!-- // top-container -->
	</div>
	<!-- // container -->
</div>
<!-- center -->
<div id="center">

</div>
<!-- 底部 -->
<div class="foot">
<div id="Layer2">
	<div align="center"><span class="foot_text" >Copyright © 2013-2014 SUOYUAN Corporation, All Rights Reserved. 索远(苏州)软件科技有限公司 苏ICP备14012973号</a></span></div>
</div>
<!--end of foot-->
</div>
</body>
</html>
