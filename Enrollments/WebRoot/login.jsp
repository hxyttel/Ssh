<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>登录</title>
	<link rel="stylesheet" href="css/style.css">
	<script src="js/index.js"></script>
 	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/res.js"></script>
    <link rel="shortcut icon" href="img/icons/favicon1.ico">
    
    <script type="text/javascript">
    	$(function(){
	        $('#kaptchaImage').click(function () { 
	            $(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
	        });
   		});
    </script>
  </head>
  
  <body>
	<div class="cotn_principal">
	  <div class="cont_centrar">
	    <div class="cont_login">
	      <div class="cont_info_log_sign_up">
	        <div class="col_md_login">
	          <div class="cont_ba_opcitiy">
		          <h2>教师登录</h2>
		          <p>Teachers are engineers of human souls！</p>
		          <button class="btn_login" onClick="teacher_login()">登录</button>
	          </div>
	        </div>
	        <div class="col_md_sign_up">
	          <div class="cont_ba_opcitiy">
		          <h2>后台登录</h2>
		          <p>The student is the foundation of educating people！</p>
		          <button class="btn_sign_up" onClick="admin_login()">登录</button>
	          </div>
	        </div>
	      </div>
	      <div class="cont_back_info">
	        <div class="cont_img_back_grey"><img src="img/po.jpg" alt="" /> </div>
	      </div>
	      <div class="cont_forms" >
	        <div class="cont_img_back_"><img src="img/po.jpg" alt="" /></div>
	        <div class="cont_form_login"> 
	        	<a href="#" onClick="ocultar_login_sign_up()">
	        		<i class="material-icons">&#xE5C4;</i>
	        	</a>
		        <h2>教师登录</h2>
		        <input type="text" placeholder="手机号" id="tphone" name="tphone"/>
		        <span id="t_errorPhone" style="color:red;display: none"><b>X</b></span>
		        <span id="t_rightPhone" style="color:green;display: none"><b>√</b></span>
		        
		        <input type="password" placeholder="密码" id="tpassword" name="tpassword"/>
		        <span id="t_errorPsd" style="color: red;display: none"><b>X</b></span>
		        <span id="t_rightPsd" style="color: green;display:none"><b>√</b></span>
	            <br/>
	                
                <input type="text" name="kaptcha" id="kaptcha" placeholder="验证码" style="width:100px;height:20px"/>
                <span id="t_errorCode" style="color: red;display: none"><b>X</b></span>
		        <span id="t_rightCode" style="color: green;display: none"><b>√</b></span>
                <img src="kaptcha.jpg" id="kaptchaImage"  id="abc" style="width:135px"/>
		        <button class="btn_login" onClick="teachers_login()" id="checkTeacher">登录</button>
	        </div>
	        
	        <div class="cont_form_sign_up"> 
	        	<a href="#" onClick="ocultar_login_sign_up()">
	        		<i class="material-icons">&#xE5C4;</i>
	        	</a>
		        <h2>后台登录</h2>
		        <input type="text" placeholder="手机号" id="aphone" name="tphone"/>
		        <span id="a_errorPhone" style="color: red;display: none"><b>X</b></span>
		        <span id="a_rightPhone" style="color: green;display: none"><b>√</b></span>
		        
		        <input type="password" placeholder="密码" id="apassword" name="tpassword"/>
		        <span id="a_errorPsd" style="color: red;display: none"><b>X</b></span>
		        <span id="a_rightPsd" style="color: green;display: none"><b>√</b></span>
				
				<input type="text" name="kaptcha" id="akaptcha" placeholder="验证码" style="width:100px;height:20px"/>
                <span id="a_errorCode" style="color: red;display: none"><b>X</b></span>
		        <span id="a_rightCode" style="color: green;display: none"><b>√</b></span>
                <img src="kaptcha.jpg" id="kaptchaImage"  id="abc" style="width:135px"/>
		        <button class="btn_sign_up" onClick="admins_login()">登录</button>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
  </body>
</html>