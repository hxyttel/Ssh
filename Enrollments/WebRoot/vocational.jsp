<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>职业培训</title>
		<link rel="shortcut icon" href="img/icons/favicon1.ico">
		
		<link href="css/acc/bootstrap.min.css" rel="stylesheet">
		<link href="css/acc/sweetalert2.min.css" rel="stylesheet"> 
		<link href="css/acc/bootstrapValidator.min.css" rel="stylesheet">
		<link href="css/acc/style.css" rel="stylesheet">
		
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
		
		//判断选择不同专业值
		function selected(){
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
		 }
		
    </script>
	</head>
<body>
	<span id="sign" style="display:none;">${sessionScope.signMsg}</span>
	<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
		<%@include file="head.jsp" %>
	</nav>
	<div>
		<img alt="" src="${sessionScope.vocational.vlphoto1 }" width="100%">
	</div>
	<div class="container">
	<div class="row" style="margin: 10px;font-size: 16px;">
		<div class="col-md-12">
			<div class="tabbable" id="tabs-train">
				<ul class="nav nav-tabs">
					<li class="active">
						 <a href="#panel-course" data-toggle="tab">${sessionScope.vocational.vltitle }</a>
					</li>
					<li>
						 <a href="#panel-sign" data-toggle="tab">我要报名</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-course" style="margin-top: 20px;">
						<div class="row" style="text-align: center;margin-bottom: 20px;">
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p1.png">
								<br>
								<span>中医师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p2.png">
								<br>
								<span>针灸师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p3.png">
								<br>
								<span>文饰师</span>
							</a>
							</div>
						</div>
						<div class="row" style="text-align: center;margin-bottom: 20px;">
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p4.png">
								<br>
								<span>健康调理师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p5.png">
								<br>
								<span>护理师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p6.png">
								<br>
								<span>康复理疗师</span>
							</a>
							</div>
						</div>
						<div class="row" style="text-align: center;margin-bottom: 20px;">
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p7.png">
								<br>
								<span>养生保健师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p8.png">
								<br>
								<span>中医美容师</span>
							</a>
							</div>
							<div class="col-xs-4">
							<a href="#">
								<img alt="" style="width: 100%;" src="img/acc_art_voc/p9.png">
								<br>
								<span>中医调理师</span>
							</a>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel-sign" style="margin-top: 20px;">
						<div class="row">
							<div class="col-md-12">
								<p style="text-indent: 20px;">
								职业资格证即职业资格证书，是表明劳动者具有从事某一职业所必备的学识和技能的证明。它是劳动者求职、任职、开业的资格凭证，是用人单位招聘、录用劳动者的主要依据，也是境外就业、对外劳务合作人员办理技能水平公证的有效证件。
								</p>
								<p style="text-indent: 20px;">
								很多职业都要求任职者具备职业资格证，这是对任职者专业基础掌握程度的检验和实践能力证明，确保企事业单位员工的办公能力和对专业知识的掌握层度。
								</p>
								<img alt="" src="${sessionScope.vocational.vlphoto2 }" width="90%">
								<p style="text-indent: 20px;">
									2016年12月16日，人力资源和社会保障部公示了国家职业资格目录清单，拟列入职业资格目录清单151项。除此之外，在总理力推下，从2014年至今，国务院部门设置的职业资格许可和认定事项已实现“七连消”，累计占到总数70%以上。
								</p>
								<form role="form" id="adultEnroll" action="register.action" method="post"> 
								    <div class="form-group"> 
								    	<label for="name">姓名</label> 
										<input type="text" class="form-control" id="sname" name="student.sname" style="width:50%;"/> 
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
							      		<div class="row">
											<div class="col-xs-6">
												<label for="sphone">手机号码</label> 
									        	<input type="text" class="form-control" id="sphone" name="student.sphone" />
									        	<span id="s_errorphone"></span>
												<span id="s_rightphone" style="color: #33ff00;display: none;"><b>√</b></span> 
											</div>
											<div class="col-xs-6">
												<label for="sGradations">报考职业</label> 
												<select id="acontent" name="student.acontent" onclick="selected();" class="form-control" style="width:170px;"> 
													<option value="">---请选择---</option>
													<option value="中医师">中医师</option>
													<option value="针灸师">针灸师</option>
													<option value="文饰师">文饰师</option>
													<option value="健康调理师">健康调理师</option>
													<option value="护理师">护理师</option>
													<option value="康复理疗师">康复理疗师</option>
													<option value="养生保健师">养生保健师</option>
													<option value="中医美容师">中医美容师</option>
													<option value="中医调理师">中医调理师</option>
												</select> 
												<span id="s_errorContent" style="color: red;display: none">*请选择专业</span>
		                    					<span id="s_rightContent" style="color: #33ff00;display: none"><b>√</b></span>
											</div>
										</div>
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
							      	<input type="hidden" id="stype" name="student.stype" value="职业资格报名" /> 
							      	<input type="hidden" id="teacherid" name="student.teacherid" value="${sessionScope.teacher.tid }"/> 
							      	<button type="submit" class="btn btn-primary btn-lg">提交</button>
									<span id="s_errorAll"></span> 
							    </form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<%@include file="foot.jsp" %>
	<%@include file="strip.jsp" %>
</body>
</html>