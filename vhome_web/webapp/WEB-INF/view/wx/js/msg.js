$(function() {
	$(".edit_bq").show();

	var reply_type = $("#replytype").val();
	if (reply_type == '0') {
		$(".edit_bq").show();
		$("#content").hide();
	} else {
		$(".edit_bq").hide();
		$("#content").show();
		$("#textContent").val("");
	}

});

function replyChange(){
	var id = $("#replytype").val();
	if(id==0){
		$(".edit_bq").show();
		$("#content").hide();
	}if(id==1){
		$("#content").show();
		$(".edit_bq").hide();	
		$.ajax({
			url:'../wx/findSinArticle',
			type:'post',
			data:{'kwd_id':-1},
			dataType:'json',
			success:function(data){
				$('#content').html('');
				$.each(data,function(i,article){
					$('#content').append('<option value="'+article.article_id+'">' + article.Title + '</option>');
				});
			},
			error:function(data){
				alert("加载单图文列表失败！");
			}
		});
	}
	if(id==2){
		$("#content").show();
		$(".edit_bq").hide();
		$.ajax({
			url:'../wx/findMutiArticle',
			dataType:'json',
			type:'post',
			data:{'kwd_id':-1},
			success:function(data){
				$('#content').html('');
				$.each(data,function(i,bigPic){
					if(bigPic.article_id==bigPic.mutiArticle_id){
						$('#content').append('<option value="'+bigPic.article_id+'">' + bigPic.Title + '</option>');
					}
				});
			}
		});
	}
}			