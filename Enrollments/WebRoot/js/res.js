$(document).ready(function(){
	//验证手机号输入是否正确   教师登录
	$("#tphone").blur(function(){
		var tphone = $('#tphone').val();
		if(tphone==""||tphone.length==0){
			$("#t_errorPhone").show();
			$("#t_rightPhone").hide();
			return false;
		}else{
			$.ajax({
				url:"checkPhone.action",
				type:"post",
				data:{'tphone':tphone},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("手机号码不正确!");
						$("#t_errorPhone").show();
						$("#t_rightPhone").hide();
						return false;
					}else{
						$("#t_errorPhone").hide();
						$("#t_rightPhone").show();
						return true;
					}
				}
			});
		}
	})
	//验证密码输入是否正确   教师登录
	$("#tpassword").blur(function(){
		var tpassword = $("#tpassword").val();
		var tphone = $('#tphone').val();
		if(tpassword==""||tpassword.length==0){
			$("#t_errorPsd").show();
			$("#t_rightPsd").hide();
			return false;
		}else{
			$.ajax({
				url:"checkPwd.action",
				type:"post",
				data:{'tpassword':tpassword,'tphone':tphone},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("密码不正确！");
						$("#t_errorPsd").show();
						$("#t_rightPsd").hide();
						return false;
					}else{
						$("#t_errorPsd").hide();
						$("#t_rightPsd").show();
						return true;
					}
				}
			});
		}
	})
	//验证验证码输入是否正确   教师登录
	$("#kaptcha").blur(function(){
		var kaptcha = $("#kaptcha").val();
		if(kaptcha ==null || kaptcha==""){
			$("#t_errorCode").show();
			$("#t_rightCode").hide();
			return false;
		}else{
			$.ajax({
				url:"checkCode.action",
				type:"post",
				data:{'kaptcha':kaptcha},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("验证码错误！");
						$("#t_errorCode").show();
						$("#t_rightCode").hide();
						return false;
					}else{
						$("#t_errorCode").hide();
						$("#t_rightCode").show();
						return true;
					}
				}
			});
		}
	})
	
	//验证手机号输入是否正确   后台登录
	$("#aphone").blur(function(){
		var aphone = $('#aphone').val();
		if(aphone==""||aphone.length==0){
			$("#a_errorPhone").show();
			$("#a_rightPhone").hide();
			return false;
		}else{
			$.ajax({
				url:"checkPhone.action",
				type:"post",
				data:{'tphone':aphone},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("手机号码不正确!");
						$("#a_errorPhone").show();
						$("#a_rightPhone").hide();
						return false;
					}else{
						$("#a_errorPhone").hide();
						$("#a_rightPhone").show();
						return true;
					}
				}
			});
		}
	})
	//验证密码输入是否正确   后台登录
	$("#apassword").blur(function(){
		var apassword = $("#apassword").val();
		var aphone = $('#aphone').val();
		if(apassword==""||apassword.length==0){
			$("#a_errorPsd").show();
			$("#a_rightPsd").hide();
			return false;
		}else{
			$.ajax({
				url:"checkPwd.action",
				type:"post",
				data:{'tpassword':apassword,'tphone':aphone},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("密码不正确！");
						$("#a_errorPsd").show();
						$("#a_rightPsd").hide();
						return false;
					}else{
						$("#a_errorPsd").hide();
						$("#a_rightPsd").show();
						return true;
					}
				}
			});
		}
	})
	//验证验证码输入是否正确   后台登录
	$("#akaptcha").blur(function(){
		var akaptcha = $("#akaptcha").val();
		if(akaptcha ==null || akaptcha==""){
			$("#a_errorCode").show();
			$("#a_rightCode").hide();
			return false;
		}else{
			$.ajax({
				url:"checkCode.action",
				type:"post",
				data:{'kaptcha':akaptcha},
				dataType:"json",
				success:function(fail){
					if(fail!="{}"){
						alert("验证码错误！");
						$("#a_errorCode").show();
						$("#a_rightCode").hide();
						return false;
					}else{
						$("#a_errorCode").hide();
						$("#a_rightCode").show();
						return true;
					}
				}
			});
		}
	})
});
