var newchild = 1; //新二级菜单

// 新增菜单栏目
function addMenu(part,id,type){
	var newparent = $("tr[name='npmenu']").length+1; //新一级菜单个数
	newchild = $("input[name='name']").length+2-newparent; //新二级菜单
	if(type==0){		//增加一级菜单
		$("#nonum").remove();
		var s = "menu_np_"+newparent;
		var str = '<tr id="menu_np_'+newparent+'" name="npmenu"><input type="hidden" value="0" id="mid_'+newparent+'" name="mid"/><td>'+
				  '<input type="text"  class="input_text66" value="0" name="sort" id="msort_'+newparent+'"/></td>'+
        		  '<td><div class="second_table"><table width="auto" border="0" cellspacing="0" cellpadding="0"><tr><td>'+
            	  '<input type="text"  class="input_text132" value="" name="name" id="mname_'+newparent+'"/></td>'+
        		  '<td><input type="button" class="btn_add" onclick="addMenu(\''+s+'\',0,1);" /></td></tr></table></div></td>'+
        		  '<td><input type="text" class="input_text180"  value="" name="content" id="mcontent_'+newparent+'"/></td>'+
        		  '<td><input type="checkbox" value="1" name="mstatus" id="mstatus_'+newparent+'" onclick="checkbox(this)"/><input type="hidden" name="status" id="cmstatus_'+newparent+'" value="0"></td><td><a href="javascript:delNewMenu(0,\'menu_np_'+newparent+'\')" class="del"></a></td></tr>';
		$("#addopt").append(str);
	}else if(type==1){			//新增加一级菜单的二级菜单
		var s = '<tr name="'+part+'_c" id="'+part+'_c_'+newchild+'"><input type="hidden" value="0" id="c_mid_'+newchild+'" name="mid"/><td>'+
				'<input type="text" class="input_text66" value="0" name="sort" id="c_msort_'+newchild+'"/></td>'+
        		'<td><div class="second_table"><table width="auto" border="0" cellspacing="0" cellpadding="0"><tr>'+
        		'<td width="40"><img src="images/pic_add.png" /></td><td><input type="text" class="input_text180"  value="" name="name" id="c_mname_'+newchild+'"/></td>'+
        		'</tr></table></div></td><td><input type="text" class="input_text180" value="" name="content" id="c_mcontent_'+newchild+'"/></td>'+
        		'<td><input type="checkbox" value="1" checked="checked" name="mstatus" id="c_mstatus_'+newchild+'" onclick="checkbox(this)"/><input type="hidden" name="status" id="cmstatus_'+newchild+'" value="1"></td>'+
        		'<td><a href="javascript:delNewMenu(1,\''+part+'_c_'+newchild+'\')" class="del"></a></td></tr>';
		$("#"+part).after(s);
	}else{				//原有一级菜单下增加二级菜单
		var myDate = new Date();
		var n = "n"+myDate.getTime(); //获取当前毫秒数(0-999)
		var s = '<tr id="'+part+'_c_'+n+'" name="'+part+'_c"><input type="hidden" value="0" name="mid" id="mid_'+id+'_'+n+'"/>'+
        		'<td><input type="text" class="input_text66" value="0" name="sort" id="msort_'+id+'_'+n+'"/></td>'+
        		'<td><div class="second_table"><table width="auto" border="0" cellspacing="0" cellpadding="0"><tr><td width="40"><img src="images/pic_add.png" /></td>'+
        		'<td><input type="text" class="input_text180"  value="" name="name" id="mname_'+id+'_'+n+'"/></td></tr></table></div></td>'+
        		'<td><input type="text" class="input_text180" value="" name="content" id="mcontnet_'+id+'_'+n+'"/></td>'+
        		'<td><input type="checkbox" value="1" checked="checked" name="mstatus" id="mstatus_'+id+'_'+n+'" onclick="checkbox(this)"/><input type="hidden" name="status" id="cmstatus_'+id+'_'+n+'" value="1"></td><td><a href="javascript:delNewMenu(1,\''+part+'_c_'+n+'\')" class="del"></a></td></tr>';
		$("#"+part).after(s);
	}
}


function checkbox(obj) {
	var id = $(obj).attr('id');
	if (obj.checked) {
		$('#c' + id).val('1');
	} else {
		$('#c' + id).val('0');
	}
}
function delNewMenu(type, id) {
	var mid = $('#' + id).children("input[name='mid']").val();
	if (confirm("确定删除该菜单?")) {
		if (type == 1) {
			if (mid != 0) {
				$.ajax({
					url : '../wx/delMenu',
					type : 'post',
					dataType : 'text',
					data : {
						bt_id : mid,
						type : 0// 0代表删除子菜单					
					},
					success : function(data) {
						$("#" + id).remove();
					},
					error : function(data) {
						alert("删除失败！");
					}
				});
			} else {
				$("#" + id).remove();
			}
		} else {
			if (mid != 0) {
				$.ajax({
					url : '../wx/delMenu',
					type : 'post',
					dataType : 'text',
					data : {
						bt_id : mid,
						type : 1// 1代表删除父菜单和下面的子菜单		
					},
					success : function(data) {
						$("#" + id).remove();
						$("tr[name='" + id + "_c']").remove();
					},
					error : function(data) {
						alert("删除失败！");
					}
				});
			} else {
				$("#" + id).remove();
				$("tr[name='" + id + "_c']").remove();
			}
		}
	}
}


//删除菜单
function delMenu(id){
	if(confirm("如删除一级菜单同时会删除该级菜单下的二级菜单,确定删除?")){
		$.post("/iwei/delMenu.do",{"id":id},function(status){
			alert("操作成功");
			window.location.href="/iwei/menuInfo.do";
		   });
	}
}

//生成菜单
function getMenu(){
	var oldmenu = "";
	var newmenu = "";
	var pmnum =0;
	var flag = true;

	$("tr[name='opmenu']").each(function(){
		var id = $(this).attr("id");			
		var num = id.substring(7,id.length);	
		var status = $("#mstatus_"+num).attr("checked");
		if(status=="checked") {
			pmnum = pmnum+1;
		}
	})
	
	$("tr[name='npmenu']").each(function(){					
		var id = $(this).attr("id");		
		var num = id.substring(8,id.length);	
		var status = $("#mstatus_"+num).attr("checked");
		if(status=="checked") {
			pmnum = pmnum+1;
		}
	});

	if(pmnum==0){
		//alert("一级菜单至少启用1个");
		//flag = false;
		$.post("/iwei/clearMenu.do",function(status){
			if(status=="success"){
				alert("操作成功");
			}else{
				alert("账户尚未绑定应用ID和应用密钥，或ID和密钥不可用，请先去授权设置中绑定信息")
			}
		});
		
		return false;
	}else if(pmnum>3){
		alert("一级菜单最多启用3个");
		flag = false;
		return false;
	}

	
	$("tr[name='opmenu']").each(function(){
		var s = "";					
		var id = $(this).attr("id");			
		var num = id.substring(7,id.length);

		var sort = $("#msort_"+num).val();
		var name = $("#mname_"+num).val();
		var content = $("#mcontent_"+num).val();
		var status = $("#mstatus_"+num).attr("checked");
		
		if(name==''){
			alert("请填写菜单标题");
			flag = false;
			return false;
		}else if(content==''){
			alert("请填写菜单触发关键词或链接");
			flag = false;
			return false;
		}else{
			if(status=="checked") {
				status=1;
				pmnum = pmnum+1;
			}else{
				status=0;
			} 
			s = s+num+","+sort+","+name+","+content+","+status+"#";
		}
		
		var cmnum = 0;  
		var x = "";			
		$("tr[name='"+id+"_c']").each(function(){			
			var m = "";		
			var sid = $(this).attr("id");			

			var cnum = 0;
			if(id.indexOf("np")!=-1){
				cnum = sid.substring(id.length+4,sid.length);  
			}else{
				cnum = sid.substring(id.length+3,sid.length);
			}
			
			sort = $("#msort_"+num+"_"+cnum).val();
			name = $("#mname_"+num+"_"+cnum).val();
			content = $("#mcontnet_"+num+"_"+cnum).val();
			var cid = $("#mid_"+num+"_"+cnum).val();
			var status2 = $("#mstatus_"+num+"_"+cnum).attr("checked");
			if(name==''){
				alert("请填写菜单标题");
				flag = false;
				return false;
			}else if(content==''){
				alert("请填写菜单触发关键词或链接");
				flag = false;
				return false;
			}else{
				if(status2=="checked") {
					status2=1;
					cmnum = cmnum+1;
				}else{
					status2=0;
				} 
				if(cnum.indexOf("n")!=-1){
					m = m+0+","+sort+","+name+","+content+","+status2+"!";
				}else{
					m = m+cnum+","+sort+","+name+","+content+","+status2+"!";
				}
				
			}
			x += m;
		})
		
		s = s+x+";;";
		oldmenu += s;

		if(cmnum>5){
			alert("二级菜单最多启用5个");
			flag = false;
			return false;
		}
		if(cmnum!=0 && status==0){
			alert("启用二级菜单必须启用一级菜单");
			flag = false;
			return false;
		}
	})
	
	
	$("tr[name='npmenu']").each(function(){
		var s = "0,";					
		var id = $(this).attr("id");		
		var num = id.substring(8,id.length);	
	
		var sort = $("#msort_"+num).val();
		var name = $("#mname_"+num).val();
		var content = $("#mcontent_"+num).val();
		var status = $("#mstatus_"+num).attr("checked");
		
		if(name==''){
			alert("请填写菜单标题");
			flag = false;
			return false;
		}else if(content==''){
			alert("请填写菜单触发关键词或链接");
			flag = false;
			return false;
		}else{
			if(status=="checked") {
				status=1;
				pmnum = pmnum+1;
			}else{
				status=0;
			} 
			s = s+sort+","+name+","+content+","+status+"#";
		}
		
		var cmnum = 0;  
		var x = "";			
		$("tr[name='"+id+"_c']").each(function(){
			var m = "0,";		
			id = $(this).attr("id");		
			num = id.substring(12,id.length);	
		
			sort = $("#c_msort_"+num).val();
			name = $("#c_mname_"+num).val();
			content = $("#c_mcontent_"+num).val();
			var status2 = $("#c_mstatus_"+num).attr("checked");
			if(name==''){
				alert("请填写菜单标题");
				flag = false;
				return false;
			}else if(content==''){
				alert("请填写菜单触发关键词或链接");
				flag = false;
				return false;
			}else{
				if(status2=="checked") {
					status2=1;
					cmnum = cmnum+1;
				}else{
					status2=0;
				} 

				m = m+sort+","+name+","+content+","+status2+"!";
			}
			x += m;
		})
		s = s+x+";;";
		newmenu += s;

		if(cmnum>5){
			alert("二级菜单最多启用5个");
			flag = false;
			return false;
		}
		if(cmnum!=0 && status==0){
			alert("启用二级菜单必须启用一级菜单");
			flag = false;
			return false;
		}
	})

	if(flag){
		$.post("/iwei/saveMenu.do",{"oldmenu":oldmenu,"newmenu":newmenu},function(status){
  	    });
		
		$.post("/iwei/getAppinfo.do",function(status){
			if(status=="success"){
				alert("操作成功");
			}else{
				alert("账户尚未绑定应用ID和应用密钥，或ID和密钥不可用，请先去授权设置中绑定信息")
			}
		});
	}
}

//保存
function save(){
	var oldmenu = "";
	var newmenu = "";
	var pmnum =0;
	var flag = true;
	$("tr[name='opmenu']").each(function(){
		var s = "";					//每一项父级menu
		var id = $(this).attr("id");			//eg:menu_p_1
		var num = id.substring(7,id.length);	//eg:获取menu_p_1中的1 也就是id

		var sort = $("#msort_"+num).val();
		var name = $("#mname_"+num).val();
		var content = $("#mcontent_"+num).val();
		var status = $("#mstatus_"+num).attr("checked");
		
		if(name==''){
			alert("请填写菜单标题");
			flag = false;
			return false;
		}else if(name.length >5){
			alert("菜单标题不能超过5个文字");
			flag = false;
			return false;	
		}else if(content==''){
			alert("请填写菜单触发关键词或链接");
			flag = false;
			return false;
		}else{
			if(status=="checked") {
				status=1;
				pmnum = pmnum+1;
			}else{
				status=0;
			} 
			s = s+num+","+sort+","+name+","+content+","+status+"#";
		}
		
		var cmnum = 0;  //子菜单启用个数
		var x = "";			//所有子menu
		$("tr[name='"+id+"_c']").each(function(){			//原有子菜单
			var m = "";		//单条子menu
			var sid = $(this).attr("id");			//eg:menu_p_3_c_5

			var cnum = 0;
			if(id.indexOf("np")!=-1){
				cnum = sid.substring(id.length+4,sid.length);  //eg:获取menu_p_1_c_1中的1 也就是id
			}else{
				cnum = sid.substring(id.length+3,sid.length);
			}
			
	//		alert(cnum+"###############")
			sort = $("#msort_"+num+"_"+cnum).val();
			name = $("#mname_"+num+"_"+cnum).val();
			content = $("#mcontnet_"+num+"_"+cnum).val();
			var cid = $("#mid_"+num+"_"+cnum).val();
			var status2 = $("#mstatus_"+num+"_"+cnum).attr("checked");
	//		alert(cid+"---"+sort+"--"+name+"----"+content+"---"+status2)
			if(name==''){
				alert("请填写菜单标题");
				flag = false;
				return false;
			}else if(content==''){
				alert("请填写菜单触发关键词或链接");
				flag = false;
				return false;
			}else{
				if(status2=="checked") {
					status2=1;
					cmnum = cmnum+1;
				}else{
					status2=0;
				} 
				if(cnum.indexOf("n")!=-1){
					m = m+0+","+sort+","+name+","+content+","+status2+"!";
				}else{
					m = m+cnum+","+sort+","+name+","+content+","+status2+"!";
				}
				
			}
			x += m;
		})
		
		s = s+x+";;";
		oldmenu += s;

//		if(cmnum>5){
//			alert("二级菜单最多启用5个");
//			flag = false;
//			return false;
//		}
//		if(cmnum!=0 && status==0){
//			alert("启用二级菜单必须启用一级菜单");
//			flag = false;
//			return false;
//		}
	})
	
//	alert(oldmenu)
	
	$("tr[name='npmenu']").each(function(){
		var s = "0,";					//每一项父级menu
		var id = $(this).attr("id");			//eg:menu_np_1
		var num = id.substring(8,id.length);	//eg:获取menu_up_1中的1 也就是id
	
		var sort = $("#msort_"+num).val();
		var name = $("#mname_"+num).val();
		var content = $("#mcontent_"+num).val();
		var status = $("#mstatus_"+num).attr("checked");
		
		if(name==''){
			alert("请填写菜单标题");
			flag = false;
			return false;
		}else if(content==''){
			alert("请填写菜单触发关键词或链接");
			flag = false;
			return false;
		}else{
			if(status=="checked") {
				status=1;
				pmnum = pmnum+1;
			}else{
				status=0;
			} 
			s = s+sort+","+name+","+content+","+status+"#";
		}
		
		var cmnum = 0;  //子菜单启用个数
		var x = "";			//所有子menu
		$("tr[name='"+id+"_c']").each(function(){
			var m = "0,";		//单条子menu
			id = $(this).attr("id");			//eg:menu_np_1_c_1
			num = id.substring(12,id.length);	//eg:获取menu_np_1_c_1中的1 也就是id
		
			sort = $("#c_msort_"+num).val();
			name = $("#c_mname_"+num).val();
			content = $("#c_mcontent_"+num).val();
			var status2 = $("#c_mstatus_"+num).attr("checked");
			if(name==''){
				alert("请填写菜单标题");
				flag = false;
				return false;
			}else if(content==''){
				alert("请填写菜单触发关键词或链接");
				flag = false;
				return false;
			}else{
				if(status2=="checked") {
					status2=1;
					cmnum = cmnum+1;
				}else{
					status2=0;
				} 
				
//				alert(sort+"--"+name+"--"+content+"--"+status2)
				m = m+sort+","+name+","+content+","+status2+"!";
			}
			x += m;
		})
		s = s+x+";;";
		newmenu += s;

//		if(cmnum>5){
//			alert("二级菜单最多启用5个");
//			flag = false;
//			return false;
//		}
////		alert(status+"################")
//		if(cmnum!=0 && status==0){
//			alert("启用二级菜单必须启用一级菜单");
//			flag = false;
//			return false;
//		}
	})
//	alert(newmenu)		//0,0,11,22,0#0,0,33,44,0!0,0,55,66,0!|0,0,77,88,1#0,0,99,00,1!|
//	if(pmnum>3){
//		alert("一级菜单最多启用3个");
//		flag = false;
//		return false;
//	}
	
	
//	alert(oldmenu+"---------"+newmenu)
	if(flag){
		$.post("/iwei/saveMenu.do",{"oldmenu":oldmenu,"newmenu":newmenu},function(status){
    		alert("保存成功");
    		window.location.href="/iwei/menuInfo.do";
  	    });
	}
	
}