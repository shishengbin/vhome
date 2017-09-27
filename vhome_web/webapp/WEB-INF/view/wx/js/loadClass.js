function loadProductOneClass(type,co){
$.ajax({
		url:'../ws/appwx/getOneAllJsonForwx',
		dataType:'json',
		type:'post',
		data:{
			id:0,
		},
		success:function(data){
			if(type==0){//单图文
				$.each(data,function(i,data){
					$("#ChildmodelName").append('<option value='+data.id+'>'+data.remark+'</option>');
				});
			}else{//多图文
				$.each(data,function(i,data){
					$("#muti_ChildmodelName_"+co).append('<option value='+data.id+'>'+data.remark+'</option>');
				});
			}
	
		}
	});
}

function loadNewsOneClass(type,co){
	$.ajax({
			url:'../ws/appwx/selectAll',
			dataType:'json',
			type:'post',
			success:function(data){
				if(type==0){
					$.each(data,function(i,data){	
						$("#ChildmodelName").append('<option value='+data.id+'>'+data.remark+'</option>');
					});	
				}else{
					$.each(data,function(i,data){	
						$("#muti_ChildmodelName_"+co).append('<option value='+data.id+'>'+data.remark+'</option>');
					});	
				}

			}
		});
	}

function loadSelfModel(type,co){
	$.ajax({
			url:'../ws/appwx/getSelfModuleGson',
			dataType:'json',
			type:'post',
			success:function(data){
				if(type==0){
					$.each(data,function(i,data){	
						$("#ChildmodelName").append('<option value='+data.id+'>'+data.module_name+'</option>');
					});
				}else{
					$.each(data,function(i,data){	
						$("#muti_ChildmodelName_"+co).append('<option value='+data.id+'>'+data.module_name+'</option>');
					});
				}
			}
		});
	}