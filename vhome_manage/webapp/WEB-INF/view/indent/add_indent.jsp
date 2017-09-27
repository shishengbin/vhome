<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
<!--
.fanh{margin-top:2px; width: 50px;height: 22px;margin-left: -2px;border: 1px #7F9DB9 solid;border-radius:0px;border-left:none;}
#companyname{color:#003399;margin-right: 0px;width: 150px;margin-top: 1px;border:1px #7F9DB9 solid;border-right: none;height: 18px;}
-->
</style>
<html>
<body>
<img alt="" src="images/agent010.png" width="15%">
	<div class="main">
		<form  action="addIndent" id="form1" method="post" class="niceform form">
		<input type="hidden" value="0" name="type"></input>
			<hr />
			<div style="height: 41px">
				<p>
					<strong>客户信息:</strong>
				</p>
			</div>
				<table>
					<tr>
						<td width="49%">选择客户：
							<!-- <select name="user.com.id" id="companyname" style="height: 25px;margin-left: 14px;" disabled="disabled">
							</select> -->
							<input name="user.company.id" id="companyid" type="hidden">
							<input id="companyname" disabled="disabled" style="">
							<input class="fanh" type="button"  id="sousuokeh" value="搜索">
							<span><font>*</font>请选择APP所属客户名称</span>
						</td>
						<td width="51%">所属合同号： <input type="text" name="formalid" />
							<span><font>*</font>请输入APP合同号</span>
						</td>
					</tr>
				</table>
			<br/>
			<div class="STYLE3" style="height: 30px">
				<p>
					<strong>APP名称：</strong>
				</p>
			</div>
				<table>
					<tr>
						<td width="49%">输入名称： <input type="text" name="keyword" />
						<span><font>*请输入中文或者英文的APP名称</font></span>
						</td>
					</tr>
				</table>
			<br/>
			<div style="height: 30px;">
				<strong>APP后台管理设置：</strong>
			</div>
				<table>
					<tr>
						<td width="49%">登录账号： <input type="text" name="user.username" id="username" />
						<span><font>*以字母开头的用户名(5-20位)</font></span>
						</td>
						<td width="51%">登录密码： <input type="password" name="user.userpass" id="password" />
						<span><font>*请输入6-20个字母、数字、下划线</font></span>
						</td>
					</tr>
				</table>
			<br />
			<div style="height: 30px;">
				<strong>发布需求：(<font color="red">制作费：200元</font>)</strong>
			</div>
				<table>
					<tr>
						<td><input type="radio" name="agency" value="1" checked="checked"/> 是 
							<input type="radio" name="agency" value="0" /> 否
							</td>
					</tr>
					
			</table>
			<div class="ft" align="center">
				<p>
					<button type="button"  id="sub" class="sub">确定</button>
					<button type="button" class="close">取消</button>
				</p>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function() {
	
		$("#sousuokeh,#companyname").bind('click',function(){
			openzhep("searchCompany",{clientList:"true",numPerPage:100},600,350);
		});
		//$("input[name='agency']").bind('change',function(){yanzh(this);});
		//$("input[name='keyword']").bind('change',function(){yanzh(this);});
		//$("#buyterm").bind('change',function(){yanzh(this);});
		$("#username").bind('change',function(){tj(this);});
		$("#password").bind('change',function(){tj(this);});
		$("#sub").die().bind('click',function(){
			var form = $(this).parents("form");
			sf(form);
		//	initjib();
		});
	});
	
	/*关键词验证  */
	/*
	var gjckeyong = false;
	var sf1=false,sf2=false;
	var pre =/^[a-zA-Z\u4e00-\u9fa5]+$/;
	function yanzh(th){
		if(pre.test($("input[name='keyword']").val())){
			var mek = new Array();
			$("input[name='mm']:checked").each(function(){
				mek.push($(this).val());
			});
			$.ajax({
				url:"verifyKeyword",
				data:{"key":$("input[name='keyword']").val(),
					"agency":$("input[name='agency']:checked").val(),
					"term":$("#buyterm").val(),
					"markets":JSON.stringify(mek)},
				type:"post",
				dataType:"json",
				success:function(data){
					var $span = $("input[name='keyword']").nextAll("span");
					if($span.html()!=null){
					$span.html(data.message);
					if(data.statue==0){
						$span.css('color',"#00CD00");
						$span.prepend("<img src='images/valid.png'/>");
						gjckeyong=true;
						if(sf1==true&&sf2==true&&gjckeyong&&$("#xyi").attr('checked')==true&&$("#companyname").val()!=null){
							$("#sub").attr("disabled",false);
						}
					}else{
						$span.css('color','red');
						$span.prepend("<img src='images/user_logout.png'/>");
						gjckeyong=false;
					}
					}
					$("#costprice").val(data.costPrice);
					$("#monetary").val(data.price);
				},
				error:function(){
					alert('验证失败!');
					gjckeyong=false;
				}
			});		
		}else{
			$("input[name='keyword']").nextAll("span").css({'color':'red','font-weight':'bold'}).html("<font>*</font>请输入汉字或字母词");
			gjckeyong=false;
		}
	};
	*/
	function tj(th){
		var a1 = $("#username");
		var a2 = $("#password");
		var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;/* 检验账户名 */
		var patrn2=/^([a-zA-Z0-9]){6,20}$/;/* 检验密码 */
		if($(th).attr('id')!="password"){
			if(!patrn.test(a1.val())){
				a1.nextAll("span").css({'color':'red','font-weight':'bold'});
				sf1=false;
			}else{
				a1.nextAll("span").css({'color':'#898989','font-weight':'normal'});
				sf1=true;
			}
		}else if($(th).attr('id')!="username"){
			if(!patrn2.test(a2.val())){
				a2.nextAll("span").css({'color':'red','font-weight':'bold'});
				sf2=false;
			}else{
				a2.nextAll("span").css({'color':'#898989','font-weight':'normal'});
				sf2=true;
			}
		}
		if($("#companyid").val()==""){
			$("#companyid").nextAll('span').css({'color':'red','font-weight':'bold'});
		}else{
			$("#companyid").nextAll('span').css({'color':'#898989','font-weight':'normal'});
		}
		if(sf1==true&&sf2==true&&$("#companyid").val()!=""){
			$("#sub").attr("disabled",false);
			return;
		} 
		$("#sub").attr("disabled",true);
	}

</script>
</html>