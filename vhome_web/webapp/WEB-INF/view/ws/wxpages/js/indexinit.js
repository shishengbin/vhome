/**
 * 账号id，从菜单绑定路径获取，表示公众号在平台上的id
 */
var accountid=request("accountid");
/**
 * 会员id，从cookie中获取
 * 登陆后，账号信息存入cookie中
 */
var humanId=getCookie("humanId");
/**
 * 获取和赋值
 */
$("#humanId").val(humanId);
/**
 * 手机端展示图片在服务器上的路径，发布后将localhost修改成相应的域名+端口
 */
var fix_url="http://www.fdmaster.com:8080/appimages/";

//loadAdpic();//获取广告图
loadOneAll();//获取一级分类
seturl();//设置菜单路径

/**
 * 获取广告图
 */
/*
function loadAdpic(){
$.ajax({
	url:'/vhome_r/ws/appwx/queryAdpicJsonForwx',
	dataType:'json',
	type:'post',
	data:{
		id:accountid,
	},
	success:function(data){
	  //console.info(data.data);
		$.each(data.data,function(i,adpic){
			$('#adpic'+i+'_img').attr("src",fix_url+adpic.publicpic);
			//图片标题
			//$('#desc'+i).html(adpic.publicname);
		});
	},
	error:function(data){
		   alert("加载图片失败！");
	   }
  });
}
*/
//获取一级分类
function loadOneAll(){
	$.ajax({
		url:'/vhome_r/ws/appwx/findAllPtypesForWx',
		dataType:'json',
		type:'post',
		data:{
			id:accountid,
		},
		success:function(data){
			$.each(data,function(i,data){
				if(i==0||i==1){
					$("#fi2").show();	
					setkind(i,data.tname,data.id,accountid,data.picurl);
				}
				if(i==2||i==3){
					$("#fi3").show();
					setkind(i,data.tname,data.id,accountid,data.picurl);
				}	
				if(i==4||i==5){
					$("#fi4").show();
					setkind(i,data.tname,data.id,accountid,data.picurl);
				}			
			});	
		}
	});
}


function checklogin(){
	 var accountid=request("accountid");
	 var humanId=getCookie("humanId");
	 if(humanId==''||humanId==null){
			alert("您还没有登录，请先登录！");
			location.href="vlogin.html?accountid="+accountid;
		}else{
			location.href="/vhome_r/ws/indent/appCart?accountid="+accountid+"&id="+humanId;
		} 
}	


function setkind(i,remark,id,accountid,picurl){
	$("#istc"+i).show();
	$('#kind_'+i).html(remark);
	$('#kind_img_'+i).attr("src",fix_url+picurl);
	$('#pcone_'+i).val(id);
	//设置点击一级分类图片跳二级分类 页面
	$('#kind_href_'+i).attr("href","list_1.html?class1id="+id+"&humanId="+$("#humanId").val()+"&accountid="+accountid);
	//设置点击一级分类名称跳二级分类 页面
	$('#kind_href2_'+i).attr("href","list_1.html?class1id="+id+"&humanId="+$("#humanId").val()+"&accountid="+accountid);				
	loadClassTwo(i,id);
}

//获取二级分类id
function loadClassTwo(i,classid){
	$.ajax({
		url:'/vhome_r/ws/appwx/findAllStypesForWx',
		type:'post',
		dataType:'json',
		data:{
			id:accountid,
			fatherId:classid
		},
		success:function(data){
				$.each(data,function(j,classTwo){	
					if(j<6)
						$("#kind_2_"+i).append('<a href="product_list.html?class2id='+classTwo.id+'&class1id='+classid+'&humanId='+$("#humanId").val()+'&accountid='+accountid+'" class="tab">'+classTwo.tname+'</a>');
				});
		}
	});
}

/*
//获取自定义模块
$.ajax({
	url:'../appwx/getSelfModuleGson',
	dataType:'json',
	type:'post',
	data:{
		userid:accountid,id:'-1'
	},
	success:function(data){
		$.each(data,function(i,data){
			if(i%2==0){
				$("#selfModel").append('<div class="col-xs-12" style="margin-bottom:1em"><div class="col-xs-6" style="padding-right:0.40625em"><a href="../getModuleContent?id='+data.id+'&page=1"><img src="'+data.module_remark+'" style="width:100%;"></a></div></div>');
			}else{
				$("#selfModel").children("div").last().append('<div class="col-xs-6" style="padding-left:0.40625em"><a href="../getModuleContent?id='+data.id+'&page=1"><img src="'+data.module_remark+'" style="width:100%;"></a></div>');
			}
		});						
	},
	error:function(){
		
	}
});
*/


//为头尾部连接赋值
function seturl(){
	var humanId=$("#humanId").val();
	$("#mine").attr("href","mine.html?humanId="+humanId+"&accountid="+accountid);
	$("#allClass").attr("href","search.html?accountid="+accountid+"&humanId="+humanId);
	$("#foot_index").attr("href","index.html?accountid="+accountid+"&humanId="+humanId);
	$("#foot_human").attr("href","mine.html?accountid="+accountid+"&humanId="+humanId);
	$("#foot_company").attr("href","/vhome_r/ws/wxpages/brandinfo.html?accountid="+accountid);
	$("#sales_product").attr("href","sales_product_list.html?accountid="+accountid+"&humanId="+humanId);
}

//获取url参数
function request(paras) {
	var url = location.href;
	url = decodeURI(url);
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {};
	for ( var i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j
				.indexOf("=") + 1, j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if (typeof (returnValue) == "undefined") {
		return "";
	} else {
		return returnValue;
	}
}




