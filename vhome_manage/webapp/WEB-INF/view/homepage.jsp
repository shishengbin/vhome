<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function(){
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
<body>
	<div class="home_right">
		<div  class="inner_mobile1" id="div1" style=" background:url(images/mobile_bg.png);">
			<div align="center" class="pce STYLE8" style="height:40px;background:url(images/search-container.png)" >产品案例</div>
			<div style="margin:45px 0 0 0; " >
				<table width="100%" height="231" border="0" align="center" cellpadding="0" padding="0">
					<tr>
						<td height="117" style="width:90px;height:90px;">
							<div align="center"><a href="http://apk.hiapk.com/html/2013/11/1953251.html?module=256&info=bFH4U%2BhsjFE%3D"  target="_blank"> <img src="images/al_01.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width *     1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </a></div>
						</td>
						<td   style="width:90px;height:90px;">
							<div align="center"><a href="http://apk.hiapk.com/html/2013/11/1952355.html?module=256&info=pE7LU1F%2F"  target="_blank"> <img src="images/al_02.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width * 1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </a></div>
						</td>
						<td   style="width:90px;height:90px;">
							<div align="center"><img src="images/al_03.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width * 1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </div>
						</td>
					</tr>
					<tr>
						<td  style="width:90px;height:90px;">
							<div align="center"><a href="http://www.anzhi.com/soft_1146599.html"  target="_blank"> <img src="images/al_04.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width * 1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </a></div>
						</td>
						<td  style="width:90px;height:90px;">
							<div align="center"><a href="http://www.anzhi.com/soft_1146601.html"  target="_blank"> <img src="images/al_05.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width * 1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </a></div>
						</td>
						<td  style="width:90px;height:90px;">
							<div align="center"><img src="images/al_06.png"  width="70" height="70" border="0" oldwidth="70" oldheight="70" onMouseOver="this.width = this.width * 1.1; this.height = this.height * 1.1;" onMouseOut="this.width = this.getAttribute('oldwidth'); this.height = this.getAttribute('oldheight');"> </div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="inner_mobile2" id="div2" style="background:url(images/mobile_bg02.jpg)">
			<div align="center" class="pce STYLE8" style="height:40px;background:url(images/search-container.png)" >平台指引</div>
			<div class="STYLE8" style="margin-left:10px;" >
				<p><a href="javascript:void(0)" url="html/guidcz.html" class="STYLE4">1.如何充值</a></p>
				<p><a href="javascript:void(0)" url="html/guidfp.html" class="STYLE4">2.发票事宜</a></p>
				<p><a href="javascript:void(0)" url="html/guidagent.html" class="STYLE4">3.新建客户</a></p>
				<p><a href="javascript:void(0)" url="html/guidapp.html" class="STYLE4">4.APP交付</a></p>
				<p><a href="javascript:void(0)" url="html/guidappfb.html" class="STYLE4">5.App发布</a></p>
			</div>
		</div>
			<script type="text/javascript">
				window.onload = function() {
					document.getElementById("div1").style.display = "block";
					document.getElementById("div2").style.display = "none";
				}
				function change_div(num) {
					document.getElementById("div" + (num - 1)).style.display = "none";
					if (num == 3) {
						num = 1;
					}
					document.getElementById("div" + num).style.display = "block";
					num++;
					setTimeout("change_div(" + num + ")", 3000);//1秒切换一次
				}
				change_div(3);
			</script>
		</div>
	<!--end of home_right-->
	<div class="home_left">
		<div class="gao" url="headPost">
			<h3 class="p_title"><span>平台公告</span><a href="javascript:content(316)"  url="showPosts" class="STYLE6" style="float:right">查看全部</a></h3>
			<div class="p_cont">
				<h2><a href="javascript:content(316)" id="oneName"></a></h2>
				<p id="oneContent"></p>
				<span id="oneTime"  style="width: 300px;"></span>
			</div>
			<div class="p_cont">
				<h2><a href="javascript:content(316)" id="twoName"></a></h2>
				<p id="twoContent"></p>
				<span id="twoTime"  style="width: 300px;"></span>
			</div>
			<div class="p_cont">
				<h2><a href="javascript:content(316)" id="threeName"></a></h2>
				<p id="threeContent"></p>
				<span id="threeTime"  style="width: 300px;"></span>
			</div>
		</div>
		<!--// gao end -->
		<div class="gao">
			<h3 class="p_title"><span>案例介绍</span><a href="javascript:content(316)"  url="showPosts" class="STYLE6" style="float:right"></a></h3>
			 <table width="100%" class="mytable">
					<tr>
						<td>vHome_标准版</td>
						<td>vHome_电商版</td>
						<td>vHome_多店版</td>
						<td>vHome_汽车板</td>
						<td>vHome_酒店版</td>
						<td>vHome_餐饮版</td>
					</tr>
					<tr>
						<td><img width="70px" height="70px" src="image/xuanchuan.png" /></td>
						<td><img width="70px" height="70px" src="image/jiaoyi.png" /></td>
						<td><img width="70px" height="70px" src="image/duodian.png" /></td>
						<td><img width="70px" height="70px" src="image/a-qc.png" /></td>
						<td><img width="70px" height="70px" src="image/a-jd.png" /></td>
						<td><img width="70px" height="70px" src="image/a-cy.png" /></td>
					</tr>
					<tr>
						<td>vHome_房产版</td>
						<td>vHome_行业版</td>
						<td>APP_定制版</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><img width="70px" height="70px" src="image/a-fc.png" /></td>
						<td><img width="70px" height="70px" src="image/hy.png" /></td>
						<td><img width="70px" height="70px" src="image/a-dz.png" /></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			<p>&nbsp;</p>
		</div>
		<!--product end -->
	</div>
	<!--home_left end  -->
	<div class="clear"></div>
</body>
</html>