$(document).ready(function(e){
	var banner=$('.banner').unslider({
    	dots: true,
    	delay:2000
    });

	$("ul").siblings('.message1').show();
	$(".right-nav1 a").css("background-color","#fff");
	$("ul").siblings('.message2').hide();
	$("ul").siblings('.message3').hide();

	$('.right-nav1').hover(function(){
		$("ul").siblings('.message1').show();
		$(".right-nav1 a").css("background-color","#fff");
		$(".right-nav2 a").css("background-color","#fafafa");
		$(".right-nav3 a").css("background-color","#fafafa");
        $("ul").siblings('.message2').hide();
        $("ul").siblings('.message3').hide();
    });

	$('.right-nav2').hover(function(){
		$("ul").siblings('.message2').show();
		$(".right-nav2 a").css("background-color","#fff");
		$(".right-nav1 a").css("background-color","#fafafa");
		$(".right-nav3 a").css("background-color","#fafafa");
        $("ul").siblings('.message1').hide();
        $("ul").siblings('.message3').hide();
    });
    $('.right-nav3').hover(function(){
		$("ul").siblings('.message3').show();
		$(".right-nav3 a").css("background-color","#fff");
		$(".right-nav1 a").css("background-color","#fafafa");
		$(".right-nav2 a").css("background-color","#fafafa");
        $("ul").siblings('.message1').hide();
        $("ul").siblings('.message2').hide();
    }); 

    $('#error_type li').click(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }else{
            $(this).addClass("active");
        }
    });

    $('.goods-error').on('click', function(){
        var index=layer.open({
            type: 1,
            shade: [0.4, '#000'],
            shadeClose:true,
            btn:['提交','返回'],
            title: false, 
            area: ['680px', '430px'],
            content: $('.layer-error'),
            yes: function(index){
                layer.msg('感谢您的反馈', {icon: 6});
                // $('#layer_post').submit();
                // console.log("success");
                layer.close(index); 
            },
            cancel: function(index){
                $('#error_type li').removeClass("active");
                $("#layerform")[0].reset();
                layer.close(index);
            }           
        });     
    });
});