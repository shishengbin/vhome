/*导航栏开始*/
$("#img1").attr("src", "images/z_1.png");
var $div_li = $(".title ul li");
$div_li.click(function() {
	$(this).addClass("selected").siblings().removeClass("selected").parent()
			.parent().siblings(".lab").children().eq($(this).index()).show().siblings().hide();
	var text = $(this).text();

	if (text.indexOf("公众帐号管理") > 0) {
		$("#img1").attr("src", "images/z_1.png");
		$("#img2").attr("src", "images/s_2.png");
		$("#img3").attr("src", "images/h_2.png");
		$("#img4").attr("src", "images/sc_2.png");
		$("#img5").attr("src", "images/dy_2.png");
	}
	if (text.indexOf("首次关注回复") > 0) {
		$("#img2").attr("src", "images/s_1.png");
		$("#img1").attr("src", "images/z_2.png");
		$("#img3").attr("src", "images/h_2.png");
		$("#img4").attr("src", "images/sc_2.png");
		$("#img5").attr("src", "images/dy_2.png");
		$(function(){
			var reg=new RegExp("@#2","g");
			$.ajax({
				url:'../wx/getFirstResp',
				type:'post',
				dataType:'json',
				success:function(data){				
					if(data.state==0){
						alert("请添加关注回复信息");
						$("#fsp_Form").attr('action','../wx/saveFirstResp');
					}else{													
						$('#textContent').val(data.content.replace(reg,"\n"));
						$('#fsp_msg_id').val(data.msg_id);
						$("#fsp_Form").attr('action','../wx/updateFirstResp');
					}
				},
				error:function(){
					alert("获取关注回复信息失败！");
				}
			});
		});
		
	}

	if (text.indexOf("关键字回复") > 0) {
		$("#img3").attr("src", "images/h_1.png");
		$("#img1").attr("src", "images/z_2.png");
		$("#img2").attr("src", "images/s_2.png");
		$("#img4").attr("src", "images/sc_2.png");
		$("#img5").attr("src", "images/dy_2.png");
		
		$.ajax({
			url:'../wx/findKwResp',
			type:'post',
			dataType:'json',
			success:function(data){
				loadkw(data);
			},
			error:function(data){
				alert("加载关键词回复列表失败！请刷新重新加载！");
			}
		});
	}
	if (text.indexOf("素材管理") > 0) {
		article();
		$("#img4").attr("src", "images/sc_1.png");
		$("#img1").attr("src", "images/z_2.png");
		$("#img2").attr("src", "images/s_2.png");
		$("#img3").attr("src", "images/h_2.png");
		$("#img5").attr("src", "images/dy_2.png");
		
	}
	if (text.indexOf("自定义菜单") > 0) {
		$("#img5").attr("src", "images/dy_1.png");
		$("#img4").attr("src", "images/sc_2.png");
		$("#img1").attr("src", "images/z_2.png");
		$("#img2").attr("src", "images/s_2.png");
		$("#img3").attr("src", "images/h_2.png");	
		$(function(){
			$.ajax({
				url:'../wx/findMenu',
				type:'post',
				dataType:'json',
				success:function(data){
					var newparent = 1; //新一级菜单个数
					var newchild = 1; //新二级菜单
					$("#nonum").remove();
					$('#addopt').html("");
					var s="";
					$.each(data,function(i,menu){
						if(menu.fat_bt_id==0){
							s = "menu_np_"+newparent;
							var str = '<tr id="menu_np_'+newparent+'" name="npmenu"><input type="hidden" value="'+menu.bt_id+'" id="mid_'+newparent+'" name="mid"/><td>'+
									  '<input type="text"  class="input_text66" value="0" name="sort" id="msort_'+newparent+'"/></td>'+
					        		  '<td><div class="second_table"><table width="auto" border="0" cellspacing="0" cellpadding="0"><tr><td>'+
					            	  '<input type="text"  class="input_text132" value="'+menu.name+'" name="name" id="mname_'+newparent+'"/></td>'+
					        		  '<td><input type="button" class="btn_add" onclick="addMenu(\''+s+'\',0,1);" /></td></tr></table></div></td>'+
					        		  '<td><input type="text" class="input_text180"  value="" name="content" id="mcontent_'+newparent+'"/></td>'+
					        		  '<td><input type="checkbox" value="1" name="mstatus" id="mstatus_'+newparent+'" onclick="checkbox(this)"/><input type="hidden" name="status" id="cmstatus_'+newparent+'" value="0"></td><td><a href="javascript:delNewMenu(0,\'menu_np_'+newparent+'\')" class="del"></a></td></tr>';
							newparent = newparent+1;
							$("#addopt").append(str);
						}
						else{
							addMenu(s,0,1);
							$('#c_mid_'+newchild).val(menu.bt_id);
							$('#c_mname_'+newchild).val(menu.name);
							if(menu.type=="click"){
								$('#c_mcontent_'+newchild).val(menu.key);
							}else{
								var beginIndex=menu.url.indexOf("redirect_uri");
								if(beginIndex>0){//判断是否是取用户openid的url
									var endIndex=menu.url.indexOf("accountid");
									var temp_url=decodeURIComponent(menu.url.substring(beginIndex+13,endIndex));
									$('#c_mcontent_'+newchild).val(temp_url.substring(0,temp_url.length-1));//截取去掉最后的”？“
								}else
									$('#c_mcontent_'+newchild).val(menu.url);
							}
								
							if(menu.status==0){
								$('#c_mstatus_'+newchild).attr("checked",false);
							}else
								$('#c_mstatus_'+newchild).attr("checked",true);
							newchild = newchild+1;
						}
					});
				},
				error:function(data){
					newchild=1;
					alert("菜单信息加载失败");
					
				}
			});
		});
	}
});
/*导航栏结束*/

/*素材管理开始*/
$(".image_text ul li").click(
		function() {

			$(this).addClass("press01").siblings().removeClass("press01")
					.parent().parent().siblings().children()
					.eq($(this).index()).show().siblings().hide();
			var text = $(this).text();
			if (text.indexOf("单图文消息") > 0) {
				dongdong();
				$(".pic0001").eq(0).addClass("pic001").removeClass("pic01");
				$(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
				$(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
				$(".pic0001").eq(3).addClass("pic04").removeClass("pic004");
			}
			if (text.indexOf("多图文消息") > 0) {
				findMutiArticle();
				$(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
				$(".pic0001").eq(1).addClass("pic002").removeClass("pic02");
				$(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
				$(".pic0001").eq(3).addClass("pic04").removeClass("pic004");
			}
			if (text.indexOf("语音消息") > 0) {
				$(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
				$(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
				$(".pic0001").eq(2).addClass("pic003").removeClass("pic03");
				$(".pic0001").eq(3).addClass("pic04").removeClass("pic004");
			}
			if (text.indexOf("视频消息") > 0) {
				$(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
				$(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
				$(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
				$(".pic0001").eq(3).addClass("pic004").removeClass("pic04");
			}
		});
/*素材管理结束*/

/*单图文开始*/

function dongdong() {
	var lieshu = parseInt(($("#contain04").width() + 12) / 316);
	$(".gezi").stop();
	$(".tuwen01").css("width", 304 * lieshu + 12 * (lieshu - 1)); //版心
	$(".gezi").each(function() {
		//计算top值
		var sum = 0;
		for ( var i = $(this).index() - lieshu; i >= 0; i = i - lieshu) {
			sum = sum + $(".gezi").eq(i).outerHeight() + 12;
		}
		$(this).animate({
			"top" : sum,
			"left" : ($(this).index() % lieshu) * 316
		}, 1000);
	});
}
/*单图文结束*/

/*多图文开始*/
function pubuliu() {
	var lieshu = parseInt(($("#contain04").width() + 12) / 316);
	$(".hezi").stop();
	$(".tuwen02").css("width", 304 * lieshu + 12 * (lieshu - 1)); //版心
	$(".hezi").each(function() {
		//计算top值
		var sum = 0;
		for ( var i = $(this).index() - lieshu; i >= 0; i = i - lieshu) {
			sum = sum + $(".hezi").eq(i).outerHeight() + 12;
		}
		$(this).animate({
			"top" : sum,
			"left" : ($(this).index() % lieshu) * 316
		}, 1000);
	});
}
$(window).resize(pubuliu);
/*多图文结束*/
function article(){
	$.ajax({
		url:'../wx/findSinArticle',
		type:'post',
		data:{kwd_id:"-1"},//标志获取全部单图文
		dataType:'json',
		success:function(data){
			$('.gezi').remove();
			var bt='<div class="gezi" style="border:none;"><button type="button" class="btn btn-primary add01" id="add01" onclick="addSinArt()">添加素材</button></div>';
			$('#dantuwen01').append(bt);
			$.each(data,function(i,article){
				var s='<div class="gezi"><div class="gezi_title"><h4>'+article.Title+'</h4><p style="margin:0; font-size:14px; color:#7a7a7a"></p></div>'+
				'<div class="gezi_pic"><img src="'+article.PicUrl+'" style="border:0;width:290px;height:170px"/></div><div class="gezi_con"><p>'+article.Description+'</p></div>'+
				'<div class="gezi_last"><span class="add09_a_img"></span><span class="del09_d_img"><input type="hidden" name="article_id" value='+article.article_id+'><input type="hidden" name="article_url" value='+article.Url+'></span></div></div></div>';
				$('.gezi').last().after(s);
			});			
			$(".del09_d_img").click(function(){
				var article_id=$(this).children("input[name='article_id']").val();
				if (confirm("确认要删除么？")) {
					 deleteSinArticle(article_id);
					 $(this).parent().parent().remove();
				 }else{
					 return false;
				 }								
			});
			$(".add09_a_img").click(function(){
				$("#dantuwen01").hide();
				$("#dantuwen02").show();
				$('#single_title').val($(this).parent().parent().children('div').children('h4').html());
				$('#single_img_target').attr('src',$(this).parent().parent().children('div').next().children('img').attr('src'));
				$('#sin_abstract').val($(this).parent().parent().children('div').next().next().children('p').html());		
				$("input[name='url']").val($(this).next().children("input[name='article_url']").val());	
				$("input[name='art_id']").val($(this).next().children("input[name='article_id']").val());	
			});
			dongdong();
		},
		error:function(data){
			alert("加载图文失败！");
		}
	});
}

function loadkw(data){
	$('#kw_tbody').html('');
	$.each(data,function(i,Kword){		
		$('#kw_tbody').append('<tr><td><input type="hidden" name="Kwd_id" value='+Kword.Kwd_id+'><input name="ck_gjz" type="checkbox"></td><td>'+Kword.Kwd_name+'</td>'+
				'<td>'+kwtype(Kword.article_type)+'</td><td>完全</td><td>'+Kword.update_time+'</td><td><span class="add_s" data-toggle="modal" data-target="#myModal02" onclick="findOneKw('+Kword.Kwd_id+')"></span><span class="del_s" onclick="delete_kw('+Kword.Kwd_id+')"></span></td></tr>');
	});	
	
}

function kwtype(value){
	if(value=="0")
		return "文本";
	if(value=="1")
		return "单图文";
	if(value=="2")
		return "多图文";
	if(value=="3")
		return "语音";
	if(value=="4")
		return "视频";
}

function findOneKw(id){
	$.ajax({
		url:'../wx/findOneKw',
		type:'post',
		dataType:'json',
		data:{
			kwd_id:id
		},
		success:function(data){
			$('#kwd_id_s').val(data.Kwd_id);
			$("#text_keyword_s").val(data.Kwd_name);
			$('#check_kwtype_s').attr('checked','checked');			
			$('#select_resptype_s').val(data.article_type);
			$('#text_respcontent_s').val(data.content);
			$('#select_article_list_s option[value=-1]').text(data.title);
			if(data.article_type==0){
				$('#trss').show();
			 	$('#sals').hide();
			 	$('#select_article_list_s').val(-1);
			}if(data.article_type==1){
				$('#trss').hide();
		 		$('#sals').show();
			}if(data.article_type==2){
				$('#trss').hide();
		 		$('#sals').show();
			}	
		},
		error:function(data){
			alert("加载关键词信息失败！请重新操作！");
		}
	});
}

function clean_kw(){
	$('#kwd_id_s').val('');
	$("#text_keyword_s").val('');
	$('#check_kwtype_s').attr('checked','checked');			
	$('#select_resptype_s option[value=0]').attr("selected",false);
	$('#select_resptype_s option[value=1]').attr("selected",false);
	$('#select_resptype_s option[value=2]').attr("selected",false);
	$('#select_resptype_s option[value=3]').attr("selected",false);
	$('#select_resptype_s option[value=4]').attr("selected",false);
	$('#text_respcontent_s').val('');
}

function deleteSinArticle(id) {
	$.ajax({
		url : '../wx/deleteSinArticle',
		type : 'post',
		dataType : 'text',
		data : {
			article_id : id,
		},
		success : function(data) {
			dongdong();
		},
		error : function(data) {
			alert(data);
		}
	});
}

function delete_kw(id){
	if (confirm("确认要删除么？")) {
		$.ajax({
			url : '../wx/deleteKw',
			type : 'post',
			dataType : 'json',
			data : {
				kwd_id : id
			},
			success : function(data) {
				loadkw(data);
			},
			error : function(data) {
				alert("删除失败！请重新操作！");
			}
		});
	}else
		return false;
}
function deleteMutiArticle(mutiID){
	if (confirm("确认要删除么？")) {
		$.ajax({
			url:'../wx/deleteMutiArticle',
			dataType:'text',
			data:{article_id:mutiID,
				picType:'bigPic'
			},
			success:function(data){
				findMutiArticle();
			},
			error:function(){
				
			}
		});		
	 }else{
		 return false;
	 }	

}
function findMutiArticle(){
	$(".hezi").not($(".hezi")[0]).remove();
	$.ajax({
		url:'../wx/findMutiArticle',
		dataType:'json',
		type:'post',
		data:{
			kwd_id:"-1",//标志获取全部单图文
		},
		success:function(data){
			$.each(data,function(i,bigPic){
				if(bigPic.article_id==bigPic.mutiArticle_id){
					$(".hezi").last().after('<div class="hezi"><input type="hidden" name="article_id" value='+bigPic.article_id+'><div class="gezi_pic"><img src='+bigPic.PicUrl+' width="280px" height="160px"><div class="zhezhao">'+bigPic.Title+'</div></div>');
				}
				$.each(data,function(j,smallPic){
					if(i<j&&smallPic.mutiArticle_id==bigPic.article_id)
						$(".hezi").last().append('<dl><dt>'+smallPic.Title+'</dt><dd><img src='+smallPic.PicUrl+' width="44px" height="44px" style="border:1px solid #b5b2b3"/><input type="hidden" name="article_id" value='+smallPic.article_id+'></dd></dl></div>');									
				});
				if(bigPic.article_id==bigPic.mutiArticle_id){						
					$(".hezi").last().append('<dl><div class="gezi_last"><span class="add09_a_img" > </span><span class="del09_d_img" onclick="deleteMutiArticle('+bigPic.article_id+')"></span></div></dl>');
				}
			});
			pubuliu();		
		},
		error:function(){
			
		}
	});
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1,RegExp.$1.length == 1 ? o[k]: ("00" + o[k]).substr(("" + o[k]).length));
			return format;
};