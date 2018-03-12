//验证短信验证码	
var sends = {
	checked:1,
	send:function(){
		var numbers = /^1\d{10}$/;
		var val = $('#sphone').val().replace(/\s+/g,""); //获取输入手机号码
		if($('.div-sphone').find('span').length == 0 && $('.div-phone a').attr('class') == 'send1'){
			if(!numbers.test(val) || val.length ==0){
				$('.div-phone').append('<span class="error">手机格式错误</span>');
				return false;
			}
		}
		if(numbers.test(val)){
			var time = 30;
			$('.div-phone span').remove();
			function timeCountDown(){
				if(time==0){
					clearInterval(timer);
					$('.div-sphone a').addClass('send1').removeClass('send0').html("发送验证码");
					sends.checked = 1;
					return true;
				}
				$('.div-sphone a').html(time+"S后重发");
				time--;
				return false;
				sends.checked = 0;
			}
			$('.div-sphone a').addClass('send0').removeClass('send1');
			timeCountDown();
			var ph = $("#sphone").val();
			$.ajax({url:'getranks.action',
				type:'post',
				data:{'phone':ph},
				dataType:'json',
				success:function(data){
					alert(data);
					$("#ranksvalue").val(data);
				}
			});
			var timer = setInterval(timeCountDown,1000);
		}
	}
}