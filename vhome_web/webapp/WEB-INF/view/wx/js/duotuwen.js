pubuliu();

function pubuliu() {
	var lieshu = parseInt(($("#contain04").width() + 12) / 316);
	$(".hezi").stop();
	$(".tuwen02").css("width", 304 * lieshu + 12 * (lieshu - 1)); //版心
	$(".hezi").each(function() {
		//计算top值
		var sum = 0;
		for ( var i = $(this).index() - lieshu; i >= 0; i = i - lieshu) {
			sum = sum + $(".hezi").eq(i).outerHeight() + 12;
		}
		$(this).animate({
			"top" : sum,
			"left" : ($(this).index() % lieshu) * 316
		}, 1000);
	});
}
$(window).resize(pubuliu);

