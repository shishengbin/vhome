function fsp_save(){
	var url=$('#fsp_Form').attr('action');
	var msg_type=$('#replytype').val();
	var content=$('#textContent').val();
	var msg_id=$('#fsp_msg_id').val();
	var article_id=$('#content').val();
	if(msg_type==0)
		article_id=0;
	
		$.ajax({
			url : url,
			dataType : 'text',
			type : 'post',
			data : {
				'msg_type' : msg_type,
				'content' : content,
				'msg_id' : msg_id,
				'article_id':article_id,
			},
			success : function(data) {
				if(data!="保存成功"){
					$('#fsp_msg_id').val(data);
					$("#fsp_Form").attr('action','../wx/updateFirstResp');
					alert("保存成功!");
				}else
					alert(data);
			},
			error : function(data) {
				alert(data);
			}
		});
	}
