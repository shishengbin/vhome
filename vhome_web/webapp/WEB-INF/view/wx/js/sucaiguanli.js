  $(".image_text ul li").click(function(){
	  
      $(this).addClass("press01").siblings().removeClass("press01").parent().parent().siblings().children().eq($(this).index()).show().siblings().hide();
       var text = $(this).text();
        if(text.indexOf("单图文消息")>0){
        dongdong();
        $(".pic0001").eq(0).addClass("pic001").removeClass("pic01");
        $(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
        $(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
        $(".pic0001").eq(3).addClass("pic04").removeClass("pic004");        
      }
      if(text.indexOf("多图文消息")>0){
        pubuliu();      
        $(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
        $(".pic0001").eq(1).addClass("pic002").removeClass("pic02");
        $(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
        $(".pic0001").eq(3).addClass("pic04").removeClass("pic004");  
      }
      if(text.indexOf("语音消息")>0){
        $(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
        $(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
        $(".pic0001").eq(2).addClass("pic003").removeClass("pic03");
        $(".pic0001").eq(3).addClass("pic04").removeClass("pic004");  
      }
      if(text.indexOf("视频消息")>0){
        $(".pic0001").eq(0).addClass("pic01").removeClass("pic001");
        $(".pic0001").eq(1).addClass("pic02").removeClass("pic002");
        $(".pic0001").eq(2).addClass("pic03").removeClass("pic003");
        $(".pic0001").eq(3).addClass("pic004").removeClass("pic04");
      }   


    });


    function dongdong(){
      var lieshu =  parseInt(($("#contain04").width()+12 ) / 316);      
      $(".gezi").stop();
      $(".tuwen01").css("width",304*lieshu + 12*(lieshu -1 )); //版心
      $(".gezi").each(
        function(){
          //计算top值
          var sum = 0;
          for(var i = $(this).index() - lieshu ; i >= 0 ; i = i - lieshu){
            sum = sum + $(".gezi").eq(i).outerHeight() + 12;
          }
          $(this).animate(
            {
              "top":sum,
              "left":($(this).index() % lieshu) * 316
            },1000
          );
        }
      );
    }
    $(window).resize(dongdong);



    function pubuliu(){
      var lieshu =  parseInt(($("#contain04").width() +12) / 316);      
      $(".hezi").stop();
      $(".tuwen02").css("width",304*lieshu + 12 * (lieshu -1 )); //版心
      $(".hezi").each(
        function(){
          //计算top值
          var sum = 0;
          for(var i = $(this).index() - lieshu ; i >= 0 ; i = i - lieshu){
            sum = sum + $(".hezi").eq(i).outerHeight() + 12;
          }
          $(this).animate(
            {
              "top":sum,
              "left":($(this).index() % lieshu) * 316
            },1000
          );
        }
      );
    }
    $(window).resize(pubuliu);



