$(function(){
		$("#selectAll").click(function(){
			var check=$(this).is(":checked");
			$('input:checkbox[name=ck_gjz]').each(function(){
	            this.checked = check;
	        });	
	});

		$("#selectAll01").click(function(){
			var check = $(this).is(":checked");
			$('input:checkbox[name=video01]').each(function(){
				this.checked = check;
			});
		});

		$("#selectAll02").click(function(){
			var check = $(this).is(":checked");
			$('input:checkbox[name=voice01]').each(function(){
				this.checked = check;
			});
		});
});

function save_keyword(){
	var article_type=$('#select_resptype').val();
	var kw_name=$('#focusedInput').val();
	var kw_trg_type=$('#kw_trg_type').val();//未使用，关键词触发方式
	var content=$('#kw_resp_content').val();
	var article_id=$('#select_article_list').val();
	if(article_type==0){
		article_id=0;
	}
	
	$.ajax({
		url:'../wx/saveKeyword',
		type:'post',
		dataType:'text',
				data : {
					Kwd_name : kw_name,
					article_type : article_type,
					kw_trg_type : kw_trg_type,
					content : content,
					article_id:article_id
				},
		success:function(data){				
			$('#kw_tbody').append('<tr><td><input type="hidden" name="Kwd_id" value='+data+'><input name="ck_gjz" type="checkbox"></td><td>'+kw_name+'</td>'+
					'<td>'+kwtype(article_type)+'</td><td>完全</td><td>'+new Date().format('yyyy-MM-dd hh:mm:ss')+'</td><td><span class="add_s" onclick="findOneKw('+data+')" data-toggle="modal" data-target="#myModal02"></span><span class="del_s" onclick="delete_kw('+data+')"></span></td></tr>');
			$('#focusedInput').val('');
			$('#kw_resp_content').val('');
		},
		error:function(data){
			alert("保存失败！");
		}
	});
}

function update_keyword(){
	var kwd_id=$('#kwd_id_s').val();
	var article_type=$('#select_resptype_s').val();
	var kwd_name=$('#text_keyword_s').val();
	var kw_trg_type=$('#check_kwtype_s').val();
	var content=$('#text_respcontent_s').val();	
	var article_id=$('#select_article_list_s').val();
	$.ajax({
		url:'../wx/updateKw',
		type:'post',
		dataType:'json',
		data : {
			kwd_id : kwd_id,
			kwd_name : kwd_name,
			article_type : article_type,
			kw_trg_type : kw_trg_type,
			content : content,
			article_id:article_id
		},
		success:function(data){
			loadkw(data);
		},
		error:function(data){
			alert(data);
		}
	});
}
