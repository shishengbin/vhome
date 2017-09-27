function save_menu(){
	var faNum=$("tr[name='npmenu']").length;//父菜单个数	
	var arrayObj = new Array([faNum]);
	for(var i=1;i<faNum+1;i++){//计算各级菜单下子菜单的个数
		var j=$("tr[name='menu_np_"+i+"_c']").length;
		arrayObj[i-1]=j;
	}
	$('#menuNum').append('<input type="hidden" name="faNum" value='+faNum+'>');
	for(var i=0;i<faNum;i++){
		$('#menuNum').append('<input type="hidden" name="sonNum" value='+arrayObj[i]+'>');
	}
	$.ajax({
		url:'../wx/saveMenu',
		type:'post',
		data:$('#save_menu_form').serialize(),
		dataType:'json',
		success:function(data){
			$('#menuNum').html("");
			$('#addopt').html("");
			var newchild = 1; //新二级菜单
			var newparent = 1; //新一级菜单个数
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
					}else
						$('#c_mcontent_'+newchild).val(menu.url);
					if(menu.status==0){
						$('#c_mstatus_'+newchild).attr("checked",false);
					}else
						$('#c_mstatus_'+newchild).attr("checked",true);
					newchild = newchild+1;
				}
				
			});
			alert("保存成功！");
		},
		error:function(data){
			alert("保存失败！");
		}
	});
}

function create_menu(){
	$.ajax({
		url:'../wx/createMenu',
		type:'post',
		dataType:'text',
		success:function(data){
			alert(data);
		},
		error:function(data){
			alert(data);
		}
	});
}


