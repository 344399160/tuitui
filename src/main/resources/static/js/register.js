$(document).ready(function() {
// �ڼ��̰��²��ͷż��ύ����֤�ύ��
    $("#register").validate({
       rules: {
          phone: {
            required: true,
            phone_value: '',
            remote: {
                url: "/checkmobile",     //��̨�������
                type: "post",               //���ݷ��ͷ�ʽ
                data: {                     //Ҫ���ݵ�����
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
            required: "�ֻ����벻��Ϊ��",
            remote: '�ֻ����ѱ�ע�ᣬ��ֱ��<a href="/login">��¼</a>'
          },          
          code: {
            required: "��������֤��",
            minlength:"��֤���������",
            maxlength:"��֤���������"
          },
          password: {
            required: "����������",
            minlength:"6-16�����֡���ĸ����ţ���ĸ���ִ�Сд",
            maxlength:"6-16�����֡���ĸ����ţ���ĸ���ִ�Сд"
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
                url: "/register", // ���ж�����֤
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
            $('#sendVerifySmsButton').empty().text(djs + '������»�ȡ');
            djs--;
            waitSend();
        }else{
            $('#sendVerifySmsButton').removeProp('disabled');
            $('#sendVerifySmsButton').empty().text('��ȡ��֤��');
            djs = 60;
        }
    },1000);
}
var handlerEmbed = function (captchaObj) {
    // ����֤��ӵ�idΪcaptcha��Ԫ���ͬʱ��������input��ֵ��geetest_challenge, geetest_validate, geetest_seccode
    captchaObj.appendTo("#embed-captcha");
    captchaObj.onReady(function () {
        $("#wait")[0].className = "hide";
    });
    // ����ӿڲο���http://www.geetest.com/install/sections/idx-client-sdk.html

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
        //��������֤��
        $.ajax({
            url: "/sms?action=register", // ���ж�����֤
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
                layerindex = layer.msg('���ڷ�����֤�룬���Ժ�...');
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
    // ��ȡid��challenge��success���Ƿ�����failback��
    url: "/geetest?type=pc&t=" + (new Date()).getTime(), // ���������ֹ����
    type: "get",
    dataType: "json",
    success: function (data) {
        // ʹ��initGeetest�ӿ�
        // ����1�����ò���
        // ����2���ص����ص��ĵ�һ��������֤�����֮�����ʹ������appendTo֮����¼�
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            product: "embed", // ��Ʒ��ʽ��������float��embed��popup��ע��ֻ��PC����֤����Ч
            offline: !data.success // ��ʾ�û���̨��⼫��������Ƿ�崻���һ�㲻��Ҫ��ע
            // �������ò�����μ���http://www.geetest.com/install/sections/idx-client-sdk.html#config
        }, handlerEmbed);
    }
});

