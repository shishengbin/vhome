 $(document).ready(function(){
  dongdong();

    function dongdong(){
      var lieshu =  parseInt(($("#contain04").width() +12) / 316);      
      $(".gezi").stop();
      $(".tuwen01").css("width",304*lieshu + 12 * (lieshu -1 )); //版心
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


    $(".a_img").click(function(){
        $("#dantuwen01").hide();
        $("#dantuwen02").show();
        
    });
    $(".a_img_duo").click(function(){
        $("#duotuwen01").hide();
        $("#duotuwen02").show();
        
    });
 });
  
  $('#single_title').bind('input propertychange', function() {
			       $('#single_title_target').html($(this).val());
			});    
  



   