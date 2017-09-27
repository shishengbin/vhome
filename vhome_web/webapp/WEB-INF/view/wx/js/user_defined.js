
  $(".user_defined ul li").click(function(){

 
      $(this).addClass("press01").siblings().removeClass("press01").parent().parent().siblings().children().eq($(this).index()).show().siblings().hide();
        var text = $(this).text();
      if(text.indexOf("授权设置")>0){
        $(".pic0002").eq(0).addClass("pic0010").removeClass("pic001");
        $(".pic0002").eq(1).addClass("pic002").removeClass("pic0020");      
      }
      if(text.indexOf("菜单设置")>0){
        $(".pic0002").eq(0).addClass("pic001").removeClass("pic0010");
        $(".pic0002").eq(1).addClass("pic0020").removeClass("pic002");
      }
     

    });


    


