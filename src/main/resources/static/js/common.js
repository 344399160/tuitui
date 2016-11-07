$(function(){
    $("img.lazy").lazyload({
        effect:"fadeIn",
		placeholder:"statics/images/waiting.png"
    });

    $('.search_btn').click(function(e){
        if($('.search_text').val().length == 0){
            $('.search_text').focus();
            return false;
        }
    });

    $(window).scroll(function(){
        var sc=$(window).scrollTop();
        var rwidth=$(window).width()+$(document).scrollLeft();
        var rheight=$(window).height()+$(document).scrollTop();
        if(sc>0){
            $("#goTop").show();
        }else{
            $("#goTop").hide();
        }
    });
    $("#goTop").click(function(){
        $('body,html').animate({scrollTop:0},1000);
    }); 

    if(navigator.userAgent.match(/(iPhone|iPad|Android|ios)/i)){
        var theW=window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;
        $("body,html").css({"width": theW,"overflow-x":"hidden"});
    }
});