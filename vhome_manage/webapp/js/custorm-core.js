function initjib(){
//	加载首页金额等信息
	$.ajax({
		url:'initIndex?t='+Math.random()+'',	
		type:"get",
		dataType:"json",
		cache:false,   //是否清空缓存
		success:function(data){
			if(data!=null){
				$("#usertext").html(data.u_name+"<a style='font-weight: bold; color:coral'>"+data.role.role_name+"</a>");
				if(data.role.id==2||data.role.id==4||data.role.id==1){
					$("#moneytext").html(data.money_box);
				}else{
					$("#moneytext").html("--");
				}
				$("#comstext").html(data.coms.companyname);
			}else{
				tishi(0,"加载代理商信息失败！");
			}
		},
		error:function(){
			tishi(0,"加载代理商信息失败！");
			location="login.html";
		}
	});
}
//弹出的窗口通用方法,url为窗口内容地址,data附带参数
function openzhep(url,data,width,height){
//	添加遮屏
	$("#home").append("<div id='zhep'></div>");
//	添加遮屏上的小窗口
	$("#zhep").css({"width":$(document).width(),"height":$(document).height()}).after("<div class='zhepcenter' id='zhepcenter'></div>");
//	加载窗口里面内容
	$("#zhepcenter").css({"width":width,"height":height}).load(url,data);
}

/*本地图片预览
 * 此为html5技术实现
 * */
function preview(show,astrict,evt){
	if (!window.FileReader){
		tishi(0,"此浏览器版本过低无法预览图片！");
		return;	
	}  
    var files = evt.target.files;  
    for (var i = 0, f; f = files[i]; i++) {  
        if (!f.type.match('image.*')) {
        	$(this).val("");
        	tishi(0,"你上传的是非图片文件，请重新选择图片");
            continue;  
        }else if(f.size>astrict){
        	$(this).val("");
        	tishi(0,'你上传的图片不能大于'+astrict/1000+'KB');
            continue;  
        }
        var reader = new FileReader();  
        reader.onload = (function(theFile) {  
            return function(e) {  
                // img 元素  
            	show.attr('src',e.target.result);
            };  
        })(f);  
        reader.readAsDataURL(f);  
    }
}
