
function teacher_login() {
	document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";  
	document.querySelector('.cont_form_login').style.display = "block";
	document.querySelector('.cont_form_sign_up').style.opacity = "0";               
	
	setTimeout(function(){
		document.querySelector('.cont_form_sign_up').style.display = "none";
	},200); 
	setTimeout(function(){ 
		document.querySelector('.cont_form_login').style.opacity = "1"; 
	},400);  
}

function teachers_login() {
	document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";  
	document.querySelector('.cont_form_login').style.display = "block";
	document.querySelector('.cont_form_sign_up').style.opacity = "0";               
	
	setTimeout(function(){
		document.querySelector('.cont_form_sign_up').style.display = "none";
	},200); 
	setTimeout(function(){ 
		document.querySelector('.cont_form_login').style.opacity = "1"; 
		
		var tpassword = $("#tpassword").val();
		var tphone = $('#tphone').val();
		var kaptcha = $("#kaptcha").val();
		if(tpassword==""||tpassword.lenght==0||tphone==""||tphone.lenght==0||kaptcha==""||kaptcha.lenght==0){
			alert("请输入相应内容!");
			return;
		}else{
			if($("#t_rightPhone").is(":visible")){
				if($("#t_rightPsd").is(":visible")){
					if($("#t_rightCode").is(":visible")){
						location.href = "http://localhost:8080/Enrollment/index.action?tphone="+tphone; 
					}else{
						alert("验证码不正确!");
						return false;
					}
				}else{
					alert("密码不正确!");
					return false;
				}
			}else{
				alert("电话号码不正确!");
				return false;
			}
		}
	},400);  
}

function admins_login(at) {
	document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
	document.querySelector('.cont_form_sign_up').style.display = "block";
	document.querySelector('.cont_form_login').style.opacity = "0";
	  
	setTimeout(function(){  
		document.querySelector('.cont_form_login').style.display = "none";
	},400); 
	
	setTimeout(function(){
		document.querySelector('.cont_form_sign_up').style.opacity = "1";
		
		var tpassword = $("#apassword").val();
		var tphone = $('#aphone').val();
		var kaptcha = $("#akaptcha").val();
		if(tpassword==""||tpassword.lenght==0||tphone==""||tphone.lenght==0||kaptcha==""||kaptcha.lenght==0){
			alert("请输入相应内容!");
			return;
		}else{
			if($("#a_rightPhone").is(":visible")){
				if($("#a_rightPsd").is(":visible")){
					if($("#a_rightCode").is(":visible")){
						location.href = "adminLogin.action?tphone="+tphone;
					}else{
						alert("验证码不正确!");
						return false;
					}
				}else{
					alert("密码不正确!");
					return false;
				}
			}else{
				alert("电话号码不正确!");
				return false;
			}
		}
	},100);  

}    

function admin_login(at) {
	document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
	document.querySelector('.cont_form_sign_up').style.display = "block";
	document.querySelector('.cont_form_login').style.opacity = "0";
	  
	setTimeout(function(){
		document.querySelector('.cont_form_sign_up').style.opacity = "1";
	},100);  

	setTimeout(function(){  
		document.querySelector('.cont_form_login').style.display = "none";
	},400);  
}    


function ocultar_login_sign_up() {
	document.querySelector('.cont_forms').className = "cont_forms";  
	document.querySelector('.cont_form_sign_up').style.opacity = "0";               
	document.querySelector('.cont_form_login').style.opacity = "0"; 
	
	setTimeout(function(){
		document.querySelector('.cont_form_sign_up').style.display = "none";
		document.querySelector('.cont_form_login').style.display = "none";
	},500);  
  }