<!DOCTYPE html>
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>艺考培训报名</title>
    <link rel="shortcut icon" href="img/icons/favicon1.ico">
    
    <link href="css/acc/sweetalert2.min.css" rel="stylesheet">
    <link href="css/acc/bootstrap.min.css" rel="stylesheet">
    <link href="css/acc/bootstrapValidator.min.css" rel="stylesheet">
    <link href="css/acc/style.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="css/acc/colorA_87eab02.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/bootstrap_6229293.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/brand_3f5ddbf.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/iconfont_0acaebe.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/daymode_ae958b1.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/image_c703bce.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/text_1909920.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/form_0601e68.css" /> 
	<link rel="stylesheet" type="text/css" href="css/acc/default_06e7803.css" /> 
    
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
    	var sign = $('#sign').text();
		if(sign!=""){
			swal({  
	            text: "对不起信息上传失败！",  
	            type: "error",  
	            confirmButtonText: '确认',  
	            confirmButtonColor: '#4cd964',
	        }).then(function(isConfirm){
	        	$.ajax({
					url:'removeSign.action',
					type:'post',
					data:{},
					dataType:'json',
					success:function(fail){
					}
				});
	        });
		}
    	$(function(){
	        $('#kaptchaImage').click(function () { 
	            $(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
	        });
   		});
	
		$(document).ready(function(){
			//验证名字不为空
			$("#sname").blur(function(){
				var sname = $('#sname').val();
				if(sname==""||sname==null){
					$('#s_errorName').html("<font color='red'>*姓名不能为空</font>");
					$("#s_errorName").show();
					$("#s_rightName").hide();
					return false;
				}else{
					$("#s_errorName").hide();
					$("#s_rightName").show();
					return true;
				}
			});
			//验证kaptcha输入是否正确   学生登录
			$("#kaptcha").blur(function(){
				var kaptcha = $('#kaptcha').val();
				if(kaptcha==""||kaptcha==null){
					$('#s_errorCode').html("<font color='red'>*验证码不能为空</font>");
					$("#s_errorCode").show();
					$("#s_rightCode").hide();
					return false;
				}else{
					$.ajax({
						url:'checkCode.action',
						type:'post',
						data:{'kaptcha':kaptcha},
						dataType:'json',
						success:function(fail){
							if(fail!="{}"){
								$('#s_errorCode').html("<font color='red'>*验证码输入错误</font>");
								$("#s_errorCode").show();
								$("#s_rightCode").hide();
								return false;
							}else{
								$("#s_errorCode").hide();
								$("#s_rightCode").show();
								return true;
							}
						}
					});
				}
			});
			//验证手机号输入格式以及判断是否已存在
			$("#sphone").blur(function(){
				var sphone = $('#sphone').val();
				var reg = /^1[0-9]{10}$/g;
				if(sphone==""||sphone==null){
					$('#s_errorphone').html("<font color='red'>*手机号不能为空</font>");
					$("#s_errorphone").show();
					$("#s_rightphone").hide();
					return false;
				}else{
					if(!reg.test(sphone)){
						$('#s_errorphone').html("<font color='red'>*手机号格式输入错误</font>");
						$("#s_errorphone").show();
						$("#s_rightphone").hide();
						return false;
					}else{
						$.ajax({
							url:'checkSphone.action',
							type:'post',
							data:{'student.sphone':sphone},
							dataType:"json",
							success:function(fail){
								if(fail!="{}"){
									$('#s_errorphone').html("<font color='red'>*手机号已存在</font>");
									$("#s_errorphone").show();
									$("#t_rightPhone").hide();
									return false;
								}else{
									$("#s_errorphone").hide();
									$("#s_rightphone").show();
									return true;
								}
							}
						});
					}
				}
			});
			//内容不能为空
			$("#acontent").blur(function(){
				var acontent = $('#acontent').val();
				if(acontent==""||acontent==null){
					$("#s_errorContent").show();
					$("#s_rightContent").hide();
					return false;
				}else{
					$("#s_errorContent").hide();
					$("#s_rightContent").show();
					return true;
				}
			});
			//判断输入的短信验证码输入是否正确
			$("#scode").blur(function(){
				var scode = $('#scode').val();
				var code = $('#code').val();
				if(scode==""||scode==null){
					$('#s_errormsg').html("<font color='red'>*短信验证不能为空</font>");
					$('#s_errormsg').show();
					$("#s_rightmsg").hide();
					return false;
				}else{
					if(scode!=code){
						$('#s_errormsg').html("<font color='red'>*短信验证码不正确</font>");
						$('#s_errormsg').show();
						$("#s_rightmsg").hide();
						return false;
					}else{
						$('#s_errormsg').hide();
						$("#s_rightmsg").show();
						return true;
					}
				}
			});
			//判断提交入数据库成功跳转
			$("form").bind("submit",function(){
				if($("#s_rightName").is(":hidden")||$("#s_rightCode").is(":hidden")||$("#s_rightphone").is(":hidden")||$('#s_rightmsg').is(":hidden")||$('#s_rightContent').is(":hidden")){
					$('#s_errorAll').html("<font color='red'>*请将相应内容填写完整</font>");
					return false; 
				}else{
					return true;
				} 
			});
		});
		var InterValObj; //timer变量，控制时间
		var count = 60; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
		var code = ""; //验证码
		var codeLength = 6;//验证码长度
		
		function sendMessage() {
			var phone = $("#sphone").val();
			var yz = /^1[0-9]{10}$/g.test(phone);
			if(phone != null && phone!=''){
				if(yz) {
					curCount = count;
					//产生验证码
					for (var i = 0; i < codeLength; i++) {
						code += parseInt(Math.random() * 9).toString();
					}
					$("#code").val(code);
					//设置button效果，开始计时
					$("#btnSendCode").attr("disabled", "true");
					$("#btnSendCode").val( + curCount + "秒再获取");
					InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
					//向后台发送处理数据
					$.ajax({
						url:'getranks.action', //目标地址
						type:'post', //用POST方式传输
						data:{'sphone':phone,'code':code},
						dataType:'json', //数据格式:JSON
						success:function(msg){
						}
					});
				}else {
					swal(
				      '请输入正确的手机号码!',
				      '',
				      'error'
					)
				}
			}else{
				swal(
			      '手机号码不能为空!',
			      '',
			      'error'
				)
			}
		}
		//timer处理函数
		function SetRemainTime() {
			if (curCount == 0) {                
				window.clearInterval(InterValObj);//停止计时器
				$("#btnSendCode").removeAttr("disabled");//启用按钮
				$("#btnSendCode").val("重新发送验证码");
				code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
			}else {
				curCount--;
				$("#btnSendCode").val( + curCount + "秒再获取");
			}
		}
    </script>
</head>
<body>
	<span id="sign" style="display:none;">${sessionScope.signMsg}</span>
<div class="wrapper">
	<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
		<%@include file="head.jsp" %>
	</nav>
    <div class="container"> 
		<div class="row clearfix"> 
			<div class="col-md-12"> 
				<div class="piece image-con" style="padding-left: 0px; padding-right: 0px; padding-top: 0px; padding-bottom: 0px;" data-idx="0"> 
					<div class="image-box"> 
						<a target="_blank" class="image-link" href="javascript:void(0);"> 
							<img class="image-item" src="${sessionScope.arts.asphoto1 }" /> 
						</a> 
						<span class="image-text"></span> 
					</div> 
				</div> 
			    <div class="piece" data-idx="2"> 
			    	<div class="floor-space" style="height: 10px; background-color: rgba(255,255,255,0);"></div> 
			    </div> 
			    <div class="piece text-piece" data-idx="3"> 
				    <div class="rich-text" style="padding: 10px 15px 10px 15px;"> 
					    <div class="inner"> 
						    <div style="text-align: center;"> 
						    	<span style="line-height: 1.5; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; display: inline !important;">
						    		<b><font size="4" color="#ff0000">${sessionScope.arts.astitle }</font></b>&nbsp;
						    	</span> 
						    </div> 
						    <div style="text-align: center;"> 
						    	<font color="#000000">梦，开始的地方</font> 
						    </div> 
					    </div> 
				    </div> 
			   	</div> 
			    <div class="piece image-con" style="padding-left: 0px; padding-right: 0px; padding-top: 0px; padding-bottom: 0px;" data-idx="10"> 
				    <div class="image-box"> 
					    <a target="_blank" class="image-link" href="javascript:void(0);"> 
					    	<img class="image-item" src="img/acc_art_voc/art2_0.png" /> 
					    </a> 
					    <span class="image-text"></span> 
				    </div> 
			    </div> 
			    <div class="piece image-con" style="padding-left: 0px; padding-right: 0px; padding-top: 0px; padding-bottom: 0px;" data-idx="10"> 
				    <div class="image-box"> 
				    	<a target="_blank" class="image-link" href="javascript:void(0);"> 
				    		<img class="image-item" src="${sessionScope.arts.asphoto2 }" /> 
				    	</a> 
				    	<span class="image-text"></span> 
				    </div> 
			    </div> 
			    <div class="piece text-piece" data-idx="17"> 
				    <div class="rich-text" style="padding: 10px 15px 10px 15px;"> 
					    <div class="inner"> 
						    <div style="text-align: center;"> 
						    	<font color="#ff0000" face="Helvetica Neue, Helvetica, Arial, sans-serif" size="4">
									<span style="line-height: 27px;"><b>过了这一次，又要等半年 </b></span>
							    </font>
						    </div> 
						    <div style="text-align: center;"> 
							    <font face="Helvetica Neue, Helvetica, Arial, sans-serif" color="#000000" size="3">
							    	<span style="line-height: 27px;">填写资料，火速报名呦！</span>
							    </font> 
						    </div> 
					    </div> 
				    </div> 
			    </div> 
			    <div class="piece form-piece" data-idx="18"> 
			    	<div class="form-count" data-node="topCount"> 
					    <span class="split-count"></span> 
					    <span class="count-text" data-node="countTextPre"></span> 
					    <span class="count-text-num" data-node="countTextNum"></span> 
					    <span class="count-text" data-node="countTextPos"></span> 
					    <span class="split-count"></span> 
			    	</div> 
			      	<form role="form" id="adultEnroll" action="register.action" method="post"> 
				       <div class="form-group"> 
							<label for="name">姓名</label> 
							<input type="text" class="form-control" id="sname" name="student.sname" style="width:70%;height:32px"/> 
							<span id="s_errorName"></span>
	                        <span id="s_rightName" style="color: #33ff00;display: none"><b>√</b></span>
						</div> 
			           	<div class="form-group">
		      		  		<div class="row"> 
				           		<div class="col-xs-6">
				           		 	<label for="kaptcha">验证码</label> 
				           			<input class="form-control" type="text" id="kaptcha" name="kaptcha"/>
				           			<span id="s_errorCode"></span>
		                            <span id="s_rightCode" style="color: #33ff00;display: none"><b>√</b></span>
				           		</div>
				           		<div class="col-xs-6">
				           			<img  style="margin-top:27px;" id="kaptchaImage" id="abc" alt="验证码" src="kaptcha.jpg"  style="vertical-align:middle;border-radius:4px;width:100%;height:50px;cursor:pointer;">
				           		</div>
		           			</div>
		                </div>
				       	<div class="form-group"> 
				        	<label for="sphone">手机号码</label> 
				        	<input type="text" class="form-control" id="sphone" name="student.sphone" style="width:70%;height:32px"/>
				        	<span id="s_errorphone"></span>
							<span id="s_rightphone" style="color: #33ff00;display: none;"><b>√</b></span>
				       	</div>
					   	<div class="form-group">
							<label for="sGradations">短信验证码</label>
							<div class="row">
								<div class="col-xs-6">
									<input type="hidden" id="code" name="code">
									<input type="text" id="scode" name="scode" class="form-control"/>
									<span id="s_errormsg"></span>
									<span id="s_rightmsg" style="color: #33ff00;display: none;"><b>√</b></span>
								</div>
								<div class="col-xs-6">
									<input id="btnSendCode" type="button" value="获取验证码" onclick="sendMessage();" class="btn btn-primary">
								</div>
							</div>
					   	</div>
				       	<div class="form-group"> 
		      				<label for="content">学习内容</label> 
		       				<textarea class="form-control" rows="3"  id="acontent" name="student.acontent"  style="width:70%;"></textarea> 
		       				<span id="s_errorContent" style="color: red;display: none">*学习内容不能为空</span>
		                    <span id="s_rightContent" style="color: #33ff00;display: none"><b>√</b></span>
	      				</div>  
				       	<input type="hidden" id="stype" name="student.stype" value="艺考培训报名" /> 
						<input type="hidden" id="teacherid" name="student.teacherid" value="${sessionScope.teacher.tid }"/> 
						<button type="submit" class="btn btn-primary btn-lg">提交</button>
						<span id="s_errorAll"></span>  
			      	</form> 
				    <div class="form-count" data-node="bottomCount"> 
				    	<span class="split-count"></span> 
				    	<span class="count-text" data-node="countTextPre"></span> 
				    	<span class="count-text-num" data-node="countTextNum"></span> 
				    	<span class="count-text" data-node="countTextPos"></span> 
				    	<span class="split-count"></span> 
				    </div> 
			     </div> 
			     <div class="piece image-con" style="padding-left: 0px; padding-right: 0px; padding-top: 0px; padding-bottom: 0px;" data-idx="19"> 
				     <div class="image-box"> 
				     	<a target="_blank" class="image-link" href="javascript:void(0);"> 
				     		<img class="image-item" src="img/acc_art_voc/art4_0.png" /> 
				     	</a> 
				     	<span class="image-text"></span> 
				     </div> 
			     </div> 
			</div> 
		</div> 
	</div>
	<%@include file="foot.jsp" %>
</div>
	<%@include file="strip.jsp" %>
</body>
</html>