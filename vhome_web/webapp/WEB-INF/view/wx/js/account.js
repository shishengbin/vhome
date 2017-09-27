$(function() {
	$.ajax({
		url : '../wx/getAccountInfo',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$('#head_name').html(data.ac_name+"——管理平台");
			$('#head_userName').html(data.ac_name);
			$('#head_img').attr("src",data.ac_pic);
			$('#t_name').html(data.ac_name);
			$('#t_img').attr("src",data.ac_pic);
			$('#preview').attr("src",data.ac_pic);
			$('#interface').html(data.ac_interface);
			$('#token').html(data.ac_token);			
			$('#ac_name').val(data.ac_name);
			$('#ac_original_id').val(data.ac_original_id);
			$('#ac_wx_number').val(data.ac_wx_number);
			$('#ac_interface').val(data.ac_interface);
			$('#ac_token').val(data.ac_token);
			$('#ac_email').val(data.ac_email);
			$('#userid').val(data.account_id);
		},
		error : function() {
			alert("获取用户基本信息失败！请重新登录！");
			window.location.href="login.html";
		}
	});
});

function save_uinfo(){
		 var pic = $('#preview').attr("src");
		 var ac_email=$('#ac_email').val();
		 $.ajax({
			url : '../wx/saveAccountInfo',
			type : 'post',
			dataType : 'text',
			data:{"ac_pic":pic,"ac_email":ac_email},
			success : function(data) {
				alert(data);
			},
			error : function(data) {
				alert(data);
			}
		});
}

