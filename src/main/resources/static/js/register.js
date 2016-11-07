$(document).ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#register").validate({
       rules: {
          phone: {
            required: true,
            phone_value: '',
            remote: {
                url: "/checkmobile",     //后台处理程序
                type: "post",               //数据发送方式
                data: {                     //要传递的数据
                    mobile: function() {
                        return $("#phone").val();
                    }
                }
            }
          },
          password: {
            required: true,
            minlength:6,
            maxlength:16
          },
          code: {
            required: true,
            minlength:6,
            maxlength:6
          },
          agree:"required"
        },
        messages: {
          phone: {
            required: "手机号码不能为空",
            remote: '手机号已被注册，请直接<a href="/login">登录</a>'
          },          
          code: {
            required: "请输入验证码",
            minlength:"验证码输入错误",
            maxlength:"验证码输入错误"
          },
          password: {
            required: "请输入密码",
            minlength:"6-16个数字、字母或符号，字母区分大小写",
            maxlength:"6-16个数字、字母或符号，字母区分大小写"
          }
        },
        focusCleanup: true,
        
        onfocusin: function(element){
            $(element).addClass('active');
            $(element).parent().find('strong').hide(); 
            $(element).parent().find('.tip').css("color","#666").show();   
        },
        onfocusout: function(element) {
            $(element).parent().find('.tip').show();
            $(element).parent().find('strong').show();
            if($(element).valid()){
                $(element).parent().find('strong').removeClass('error');
                $(element).parent().find('strong').addClass('ok');
                $(element).parent().find('.tip').hide();
            }
            else{
                $(element).parent().find('strong').removeClass('ok');
                $(element).parent().find('strong').addClass('error');
                $(element).removeClass('active');
                $(element).addClass('error_box');
                $(element).parent().find('.tip').css("color","#ff464e").show();
            }
            
        },
        errorPlacement: function(error, element) {
            $(element).parent().find('.tip').html("");  
            $(element).parent().find('.tip').append(error).css("color","#ff464e").show();
            $(element).removeClass('active');
            $(element).addClass('error_box');
            $(element).parent().find('strong').show();
            $(element).parent().find('strong').removeClass('ok');
            $(element).parent().find('strong').addClass('error');
        },
        submitHandler: function(){
            $.ajax({
                url: "/register", // 进行二次验证
                type: "post",
                dataType: "json",
                data: {
                    mobile: $('#phone').val(),
                    code: $('#code').val(),
                    password: $('#password').val()
                },
                success: function (data) {
                    if(data.status == 'ok'){
                        window.location.href = '/';
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        }
    });

    $('#sendVerifySmsButton').click(function(e){
      var myreg = /^1[3|5|7|8][0-9]{1}\d{8}$/;
      if (!myreg.test($("#phone").val())) { 
        $('#phone').focus();
        return false;
      }

      $('.content-landing').hide();
      $('.geetest').fadeIn('fast');
      return false;
    });
});

var djs = 60;
function waitSend(){
    setTimeout(function(){
        if(djs > 0){
            $('#sendVerifySmsButton').attr('disabled','disabled');
            $('#sendVerifySmsButton').empty().text(djs + '秒后重新获取');
            djs--;
            waitSend();
        }else{
            $('#sendVerifySmsButton').removeProp('disabled');
            $('#sendVerifySmsButton').empty().text('获取验证码');
            djs = 60;
        }
    },1000);
}
var handlerEmbed = function (captchaObj) {
    // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
    captchaObj.appendTo("#embed-captcha");
    captchaObj.onReady(function () {
        $("#wait")[0].className = "hide";
    });
    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html

    captchaObj.onSuccess(function () {
        var validate = captchaObj.getValidate();
        if (!validate) {
            $("#notice")[0].className = "show";
            setTimeout(function () {
                $("#notice")[0].className = "hide";
            }, 2000);
            e.preventDefault();
        }

        $('.geetest').hide();
        $('.content-landing').fadeIn('fast');

        setTimeout(function(){
            captchaObj.refresh();
        }, 1000);
        
        var layerindex;
        //请求发送验证码
        $.ajax({
            url: "/sms?action=register", // 进行二次验证
            type: "post",
            dataType: "json",
            data: {
                type: "pc",
                mobile: $('#phone').val(),
                geetest_challenge: validate.geetest_challenge,
                geetest_validate: validate.geetest_validate,
                geetest_seccode: validate.geetest_seccode
            },
            beforeSend: function(){
                layerindex = layer.msg('正在发送验证码，请稍后...');
            },
            success: function (data) {
                layer.close(layerindex);
                if (data && (data.status === "ok")) {
                    waitSend();
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    });
};

$.ajax({
    // 获取id，challenge，success（是否启用failback）
    url: "/geetest?type=pc&t=" + (new Date()).getTime(), // 加随机数防止缓存
    type: "get",
    dataType: "json",
    success: function (data) {
        // 使用initGeetest接口
        // 参数1：配置参数
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
            offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
        }, handlerEmbed);
    }
});

