$(document).ready(function() {
// �ڼ��̰��²��ͷż��ύ����֤�ύ��
    $("#register").validate({
        rules: {
          mobile: {
            required: true,
            phone_value: ''
          },
          password: {
            required: true
          },
        },
        messages: {
          mobile: {
            required: "�ֻ����벻��Ϊ��",
          },
          password: {
            required: "���벻��Ϊ��"
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
        }
    });
});