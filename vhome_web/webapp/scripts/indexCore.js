$(function(){
		
	$("input[type='text']").css('width',"95px");
		/*初始化dwz*/
	DWZ.init("public/dwz.frag.xml", {
		//loginUrl : "login.html",
		//loginTitle : "登录", // 弹出登录对话框
				loginUrl:"login.jsp",	// 跳到登录页面
		statusCode : {
			ok : 200,
			error : 300,
			timeout : 301
		}, //【可选】
		pageInfo : {
			pageNum : "pageNum",
			numPerPage : "numPerPage",
			orderField : "orderField",
			orderDirection : "orderDirection"
		}, //【可选】
		debug : false, // 调试模式 【true|false】
		callback : function() {
			initEnv();
			$("#themeList").theme({
				themeBase : "public/themes"
			}); // themeBase 相对于index页面的主题base路径
		}
	});
	
		/*格式化时间  */
		Date.prototype.format =function(format)
		{
		var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
		};
		if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		(this.getFullYear()+"").substr(4- RegExp.$1.length));
		for(var k in o)if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,
		RegExp.$1.length==1? o[k] :
		("00"+ o[k]).substr((""+ o[k]).length));
		return format;
		};
		/*首页消息显示*/
		/* 新闻信息*/
		$.ajax({
			url:"ws/showNewsIndex",
			dataType:"json",
			data:{"numPerPage":10},
			type:"get",
			success:function(data){
				/*if(data.length>0){
					$("#newsResult").html("");
				}*/
				$.each(data,function(index,result){
					 var title = result.newsTitle.replace(/<[^>]+>/g,"");//去掉所有的html标记
					 if(title.length > 15) {
						 title = title.substring(0,15)+"...";
					 }
					 var a= new Date(result.updateTime);
					 var str =a.format('yyyy-MM-dd');
					//$("#newsResult").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: left;'><a href='ws/newsDetails?id="+result.id+"&result=ck' target='navTab' rel='page35'>"+title+"</a></td><td style='color:#ABABAB'>"+str+"</td></tr>");
					 $("#news"+(index+1)).attr('href','ws/newsDetails?id='+result.id+'&result=ck');
					 $("#news"+(index+1)).text(title);
					 $("#t"+(index+1)).text(str);
					 $('#newsResult').append();
					 $("#newsResult a").mouseover(function(){
						$(this).css("color","#103667","text-decoration","none");
					});
					$("#newsResult a").mouseout(function(){
						$(this).css("color","#000000");
					});
				});
			},error:function(){
				alert("加载新闻出错了");
			}
		});
		/* 公司简介 */
		$.ajax({
			url:"ws/showAboutIndex",
			dataType:"json",
			type:"get",
			success:function(data){
					 var result = data.data.about.replace(/<[^>]+>/g,"");//去掉所有的html标记
					 if(result.length > 180) {
						 result = result.substring(0,180);
						 result+="...";
					 }
					 
					$("#aboutResult").html("<ul><li style='line-height:2;text-indent: 20px;font-family:新宋体;font-size: 12px; color: #000000;letter-spacing: 1px;'>"+result+"</li></ul>");
			},error:function(){
				alert("加载公司简介出错了");
			}
		});
		/*初始化首页账户信息*/
		$.ajax({
			url:"sys/showCompany",
			dataType:"json",
			type:"get",
			success:function(data){
				$("#mylogo").append(data.objValue.key.kw_name);
				$("#account").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: center;width:100px;'>企业名称：</td><td style='color:#2F4F4F;text-align:left;'>"+data.objValue.company.companyname+"</td></tr>");
				$("#account").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: center;width:100px;'>企业联系人：</td><td style='color:#2F4F4F;text-align:left;'>"+data.objValue.company.companyperson+"</td></tr>");
				$("#account").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: center;width:100px;'>APP名称：</td><td style='color:#167AEB;text-align:left;'>"+data.objValue.key.kw_name+"</td></tr>");
				$("#account").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: center;width:100px;'>企业邮箱：</td><td style='color:#2F4F4F;text-align:left;'>"+data.objValue.company.companyemail+"</td></tr>");
				$("#account").append("<tr><td style='color: #5BBD2B; text-align: left; width: 4px;'>&gt;&gt;</td><td style='text-align: center;width:100px;'>联系方式：</td><td style='color:#2F4F4F;text-align:left;'>"+data.objValue.company.companyphone+"</td></tr>");
			},error:function(){
				alert("初始化账户信息!");
			}
		});
		
	});
/*
function navTabAjaxDone($json){
	 去掉多余字符 
	DWZ.ajaxDone($json);
   if ($json.statusCode == DWZ.statusCode.ok){
          if ($json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
                navTab.reloadFlag($json.navTabId);
          } else { //重新载入当前navTab页面
                navTabPageBreak();
          }
          if("refreshCurrent" ==$json.callbackType){
        	  navTabPageBreak();
          }else if ("closeCurrent" == $json.callbackType) {
                setTimeout(function(){navTab.closeCurrentTab();}, 100);
          } else if ("forward" == $json.callbackType) {
                navTab.reload($json.forwardUrl);
          }
    }
}*/
/*产生新闻下拉列表*/
function LoadNewsList(th){
	$.ajax({
		url:"ws/selectAll",
		dataType:"text",
		type:"post",
		success:function(data){
			if(data!="null")
			{
				$.each($.parseJSON(data),function(index,person){
					if(person.id==$("#"+th).attr('zhi')){
						$("#"+th).append("<option selected='selected' value='"+person.id+"'>"+person.remark+"</option>");
					}else{
						$("#"+th).append("<option value='"+person.id+"'>"+person.remark+"</option>");
					}
				});
			}
		},
		error:function(){
			alert("加载新闻类型出错！");
		}
	});
}
/*产生自主栏目下拉列表*/
function LoadModuleList(th){
	$.ajax({
		url:"ws/getSelfModuleGson",
		dataType:"text",
		type:"post",
		success:function(data){
			if(data!="null")
			{
				$.each($.parseJSON(data),function(index,person){
					if(person.id==$("#"+th).attr('zhi')){
						$("#"+th).append("<option selected='selected' value='"+person.id+"'>"+person.module_name+"</option>");
					}else{
						$("#"+th).append("<option value='"+person.id+"'>"+person.module_name+"</option>");
					}
				});
			}
		},
		error:function(){
			alert("加载自主栏目出错！");
		}
	});
}

/* ajax图片上传 */
function uploadingImgage(th){
	/* 验证是否是有效图片格式 */
	/* 上传图片 */
	var thisid = $(th).attr("id");
	var imgval=$("#file").val();
	$.ajaxFileUpload({
				url : "picupload?width=0&height=0", 
				secureuri : false,
				fileElementId : thisid, //文件选择框的id属性（必须）
				dataType : 'text',
				data : {"updateP" : thisid},
				success : function(data, status) {
					var data = eval("(" + data + ")");
					if(data.state==1)
						alert("图片格式不符，支持bmp、jgp、png、gif格式");
					else if(data.state==2)
						alert("图片大小超过1M");
					else if(data.state==3)
						alert("图片宽高不符合要求");
					else if(data.state==4)
						alert("上传失败！");
					else if(data.updateP==thisid)
					{
						var imgsrc=data.fileName;
						$("#preview").attr("src",imgsrc);
						$("#filename").val(data.fileName);
					}
				},
				error:function(){
					alert("预览失败");
				}
			});
}

