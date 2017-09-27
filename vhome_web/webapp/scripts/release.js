$(function () {
	init();
});

function init(){
    $('.right-menu').click( function (e) {
        $(this).addClass("active").siblings().removeClass("active");
        selectItems($(this).attr("id"));
    });

    //添加上传图片模块
    $('#add-image-button').click(function() {
        var imageCount = $('.image-container .image-item').size();
        if(imageCount < 5){
            var html = "";
            html += '<div class="image-item">';
                html += '<div class="image-uploader"><img src="../images/released/pic.png" class="img-rounded"><input type="file" class="filePrew" fileFormat="GIF,JPG,JPEG,PNG,BMP"/></div>';
                html += '<div class="pad8">';
                    if(imageCount == 0){
                        html += '<input type="radio" name="imgradio" checked/>设为主图';
                    } else {
                        html += '<input type="radio" name="imgradio"/>设为主图';
                    }
                    html += '<div class="del-image-buttom">';
                    html += '<span>删除</span>';
                    html += '<div class="delimg"></div>';
                    html += '</div>';
                html += '</div>';
            html += '</div>';
            $('.image-container').append(html);
        } else {
            alert("最多添加五张图片");
        }
    });

    //删除上传图片模块
    $('.image-container').on('click', '.image-item .del-image-buttom', function(e){
        var delButton = $(e.currentTarget);
        delButton.parents(".image-item").remove();
    });

    //验证上传图片的格式，大小及预览
    $('.image-container').on('change', '.image-item .filePrew', function(e){
        if(this.files[0].size > 3145728){
            alert("图片大小不能超过3M");
        } else {
            var target = this;
            var name = target.value;
            var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();

            var fileFormat = $(this).attr("fileFormat");
            var fileFormatArr = fileFormat.split(",");

            var isAllowed = 0;
            for (var i = 0; i < fileFormatArr.length; i++) {
                if(fileName == fileFormatArr[i].toLowerCase().trim()){
                    isAllowed++;
                }
            }

            if(isAllowed > 0){
                console.log(this.files[0]);
                $(this).parents(".image-item").find(".img-rounded").attr("src", window.URL.createObjectURL(this.files[0]));
            } else {
                alert("图片格式仅支持("+fileFormat+")！");
                target.value="";
            }
        }
    });
}

function  controlNum(obj,maxNum){
    var currentNum = $(obj).val().length;
    var lastNum = maxNum - currentNum;
    if(lastNum < 0){
        lastNum = 0;
    }
    $(obj).siblings("div").children("i").text(lastNum);

}
function selectItems(divId){
    $("#"+ divId + "_item").find('.fa-chevron-up').removeClass("fa-chevron-down").addClass("fa-chevron-up");
    $("#"+ divId + "_item").find('.panel-body').show();
    $("#"+ divId +"_item").siblings("div").find('.fa-chevron-up').addClass("fa-chevron-down").removeClass("fa-chevron-up");
    $("#"+ divId +"_item").siblings("div").find('.panel-body').hide();
}
