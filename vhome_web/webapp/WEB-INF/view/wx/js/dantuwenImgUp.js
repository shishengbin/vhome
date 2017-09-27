/* ajax图片上传 */
function uploadingDTWImgage(th) {
	/* 验证是否是有效图片格式 */

	/* 上传图片 */
	var thisid = $(th).attr("name");
	var imgval = $("#sin_file").val();
	$.ajaxFileUpload({
		url : "/vhome/wx/picupload?width=0&height=0",
		secureuri : false,
		fileElementId : thisid, //文件选择框的id属性（必须）
		dataType : 'text',
		data : {
			"filePic" : thisid
		},
		success : function(data, status) {
			var data = eval("(" + data + ")");
			if (data.state == 1)
				alert("图片格式不符，支持bmp、jgp、png、gif格式");
			else if (data.state == 2)
				alert("图片大小超过1M");
			else if (data.state == 3)
				alert("图片宽高不符合要求");
			else if (data.filePic == thisid) {
				
				var imgsrc = "images/" + data.fileName;
				$("#single_img_target").attr("src", imgsrc);
				$("#filename").val(data.fileName);
			}
		},
		error : function() {
			alert("预览失败");
		}
	});
}