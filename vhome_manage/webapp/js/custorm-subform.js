/**
 * 序列化表单提交
 */
(function($){
/**
 * 返回提示信息的请求
 * 提交表单请求，返回json提示信息
 */
sfState=true;//标示ajax是否执行完毕,防止重复点击
sf=function(form){
	if(sfState){
		sfState=false;
		$.ajax({
			url:form.attr('action'),
			dataType:"json",
			data:form.serializeArray(),
			type:form.method||"post",
			cache:false,
			success:function(data){
				if(data.state==1){
					if(data.forward!=null&&data.forward!="null"&&data.forward!=""){
//						加载中间区域内容
						se(data.forward);
					}
				}
//				提示信息
				tishi(data.state,data.message);
//				释放锁
				sfState=true;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				sfState=true;
				tishi(0,"查词失败,请稍后 重试！");
			}
		});
	}
};
/**
 * 更改首页模板区内容
 * @param url
 */
seState=true;
se=function(url){
	if(seState){
		seState=false;
		$.ajax({
			url:url,
			dataType:"html",
			type:"get",
			cache:false,
			success:function(data){
				$("#center").html(data);
				sd();
				seState=true;
			},error:function(){
				seState=true;
				tishi(0,"加载菜单失败！");
			}
		});
	}
};
/**
 * 分页组件
 */
sd=function(){
	if(typeof($(".pagination"))!=undefined){
		var count = parseInt($(".pagination").attr('totalCount'));/* 总记录数 */
		var num = parseInt($(".pagination").attr('numPerPage'));/* 每页显示条数 */
		var current = parseInt($(".pagination").attr('currentPage')); /*当前页 */
		var pageNum = parseInt($(".pagination").attr('pageNumShown'));/* 最多显示页码数量 */
		/* 计算出总页数 */
		var countPage = Math.ceil(count/num);
		var action = $("#lcForm").attr('action');/* 分页提交 */
		/**
		 * 生成分页元素
		 */
		var pagetou = "<a href='javascript:void(0)' value='1'>首页</a><a href='javascript:void(0)' value='-1'>&lt;&lt;上一页</a>";
		var pagecontent = "";
		var pagewei = "<a href='javascript:void(0)' value='-2'>下一页&gt;&gt;</a><a href='javascript:void(0)' value='"+countPage+"'>末页</a>";

		if(current<=1){
			pagetou ="<span class='disabled'>首页</span><span class='disabled'>&lt;&lt;上一页</span>";
		}
		if(current>=countPage){
			pagewei ="<span class='disabled'>末页</span><span class='disabled'>下一页&gt;&gt;</span>";
		}
		var dqy = parseInt(current%pageNum==0?current/pageNum:current/pageNum+1);//分页组件的当前页
		var zys = Math.ceil(countPage/pageNum);  //分页组件的总页数
		if(dqy<zys){
			pagewei ="..."+pagewei;
		}
		/**
		 * 对分页码进行分页
		 */
		if(current>(dqy-1)*pageNum){
			for(var i=(dqy-1)*pageNum;i<dqy*pageNum;i++){
				if(i+1<=countPage){
					if(1+i==current){
						pagecontent+="<span value='"+current+"' class='current'>"+current+"</span>";
					}else{
						pagecontent+="<a href='javascript:void(0)' value='"+(1+i)+"'>"+(1+i)+"</a>";
					}
				}
			}
		}
//		页码输出到页面
		$(".pagination").html(pagetou+pagecontent+pagewei);
//		提交分页信息
		$(".pagination a").die().live('click',function(){
			var value = $(this).attr('value');
			if(value==-1||value==-2){
				
				var a =parseInt($(".pagination span[class='current']").attr('value'));
				current = value==-1?a-1:a+1;
			}else{
				current=value;
			}
			$("#lcForm input[name='pageNum']").val(current);
			$("#lcForm input[name='numPerPage']").val(pageNum);
			sw($("#lcForm"));
		});
		/**
		 * 表格隔行变色
		 */
		$("#mytable tbody tr:odd").css("background-color","#EDEDED");
		$("#mytable tbody tr:even").css("background-color","#FFFFFF");
	}
};
/**
 * 初始化组件
 */
sq=function(){

//	  表格组件

	var chushicolor="#ffffff";
	var i =0;
	$("#mytable tbody tr").live('mousemove',function(){
		if(i==0){
			i++;
			chushicolor = $(this).css('background-color');
		}
		$(this).css('background-color',"#EEE8CD");
	});
	$("#mytable tbody tr").live('mouseout',function(){
		$(this).css('background-color',chushicolor);
		if(i==1){
			i--;
		}
	});
	/*提交按钮*/
	$(".sub").die().live('click',function(){
		var form = $(this).parents("form");
		/*$.getScript("/agt/javascript/custorm-subform.js");无效*/
		sf(form);
	});
	/*取消按钮*/
	$(".close").live('click',function(){
		location="index.jsp";
	});
	/*查询按钮*/
	$(".search_submit").die().live('click', function() {
		$("#lcForm input[name='pageNum']").val(1);
		sw($("#lcForm"));
	});
	$(".subform").die().live('click',function(){
		sw($(this).parents("form"));
	});
	/*弹出窗口上的确定按钮*/
	$(".formsub").die().live('click', function() {
		sf($(this).parents("form"));
		closezhep();
	});
	/*弹出窗口上的取消按钮*/
	$(".formclose").die().live('click', function() {
		closezhep();
	});
	/*提示框*/
	$("body").append("<div id='panel'><div class='conent'></div><input type='button' value='确定' onclick='yc()'></div>");
	/*查词框*/
	$("body").append("<div class='hide_div'><div class='sheet'><a class='guanbu' title='关闭' href='javascript:void(0);'></a></div></div>");
	cck();
	
	/*滚动*/
	$(document).bind('scroll',function(){
		$("#panel").css('top',$(document).scrollTop());
	});
	/*window.setInterval(function(){
		initjib();
	}, 30000);*/
};
/* 关闭屏障 */
closezhep = function(){
	$("#zhepcenter").remove();
	$("#zhep").remove();
};
/**
 * 返回页面的请求
 * 使用ajax提交表单，返回结果页面
 * @param form
 */
swState=true;
sw=function(form){
	if(swState){
		swState=false;
		$.ajax({
			url:form.attr('action'),
			data:form.serializeArray(),
			dataType:"html",
			type:"post",
			cache:false,
			success:function(data){
					$("#center").html(data);
					sd();
					swState=true;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				tishi(0,"表单提交失败！");
				swState=true;
			}
		});
	}
};

/**
 * 提示框
 */
tishi=function(state,message){
	if(state==0){
		$("#panel .conent").css('color',"red");
		$("#panel .conent").html("<br><img src='images/comment.png'><br>");
	}else{
		$("#panel .conent").css('color',"#006241");
		$("#panel .conent").html("<br><img src='images/comment.png'><br>");
	}
	$("#panel").css("display","block");
	$("#panel .conent").append(message);
	$("#panel").stop();
    $("#panel").css({"left":$(window).width()/2-($("#panel").width()/2),"opacity":"0.1","height":0})
    .animate({
         height: "+=100px",
         opacity:"100"
     }, 400,function(){
     	t = setTimeout(function(){
     		yc();
     	},3000);
     });
};
yc=function(){
	 $("#panel") .animate({
         height: "-=100px",
         opacity:"0"},800,function(){
        	 $("#panel").css("display","none");
         });
};
/*查词系统*/
cck=function(){
	$(".action").die().live('click',function(){
		var value=$("#search-field").val();
		if(value==""){
			tishi(0,"请输入关键词!");
			return ;
		}
		$.ajax({
			url:"findKeyWordNyName",
			type:"post",
			dataType:"json",
			data:{"keyword":value},
			success:function(data){
				$(".hide_div .guanbu").nextAll().remove();
				$(".hide_div").css({'height':"0px",'display':"none"});
				$(".hide_div").css('display','block');
				$.each(data.rows,function(index,param){
					var sta="";
					switch(param.kw_status){
					case 0:
						sta="未注册";
						break;
					case 1:
						sta="<font color='red'>已注册</font>";
						break;
					case 2:
						sta="<font color='red'>禁止注册</font>";
						break;
					}
					$(".hide_div .sheet").append("<fieldset><legend>关键词查询：</legend><hr><table><tr><td>查询关键词：</td><td id='keyci'>"+param.kw_name+"</td><td>APP价格：</td><td id='keyjiage'>"+param.tprice+"</td></tr>"+
					"<tr><td>关键词类型：</td><td id='keylx'>"+param.tname+"</td><td>注册状态：</td><td id='keyzhut'>"+sta+"</td></tr></table> </fieldset>");
				});
				$(".hide_div").stop(true).animate({
					'height' : '+=250px',
					'opacity' : '1'
				}, 400);
			},error:function(){
				tishi(0,"查词失败,请稍后 重试！");
			}
		});
	});
	$("div.hide_div .guanbu").die().live('click',function(){
		$(".hide_div").stop(true).animate({
			'height' : '-=250px',
			'opacity' : '0'
		}, 400,function(){
			$("div.hide_div").css('display','none');
		});
	});
};
})(jQuery);