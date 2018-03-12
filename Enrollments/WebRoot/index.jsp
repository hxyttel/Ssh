<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>招生系统-首页</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/font/fontello.css" type="text/css" media="screen"/>
	<link href="css/styles.css" rel="stylesheet" media="screen">
	<link href="css/media-queries.css" rel="stylesheet" media="screen">
	<link rel="shortcut icon" href="img/icons/favicon1.ico">	
	<link rel="apple-touch-icon" href="img/icons/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="img/icons/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="img/icons/apple-touch-icon-114x114.png">
	<link rel="shortcut icon" href="static/proscenium/logo/favicon2.ico" />
	<link href="css/rightindex/bootstrap.min.css" rel="stylesheet">
	<link href="css/rightindex/style.css" rel="stylesheet">
	<!-- 弹出提示框引用的css -->
	<link href="css/rightindex/sweetalert2.min.css" rel="stylesheet">
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/easing.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="js/waypoints.min.js"></script>
	<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
	<script src="js/responsiveslides.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/scroll/smooth-scroll.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="js/jquery-func.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	
	<script type="text/javascript">
		function get(){
			htmlobj=$.ajax({
				url:"/M.asp?mail="+$("#mail").val()+"&name="+$("#name").val()+"&nr="+$("#message").val(),
					async:false
				});
			if(htmlobj.responseText=="1"){
				alert("提交成功!");
			}
		}
		$(document).ready(function() {
			var upload = $('#upload').text();
			if(upload!=""){
				swal({  
		            text: "文件已上传成功！",  
		            type: "success",  
		            confirmButtonText: '确认',  
		            confirmButtonColor: '#4cd964',
		        }).then(function(isConfirm){
		        	$.ajax({
						url:'removeUpload.action',
						type:'post',
						data:{},
						dataType:'json',
						success:function(fail){
						}
					});
		        });
			}
			$("#assistant").click(function(){
				var tid = $('#teacherid').text();
				if(tid!=""){
					location.href = "http://localhost:8080/Enrollment/getWorkAssist.action?tid="+tid; 
				}else{
					swal({
				      text:'请先登录教师账号!',
				      type:'warning',
				      showCancelButton: true,  
			          confirmButtonColor: '#f8bb86',  
			          cancelButtonColor: 'gray',  
			          cancelButtonText: '取消',  
			          reverseButtons: true, //控制按钮反转  
			          confirmButtonText: '登录',
					}).then(function(isConfirm){
						if(isConfirm){
							location.href = "http://localhost:8080/Enrollment/login.jsp";
						}
					});
				}
			});
		});
	</script>
  </head>
  <body>
  	<span id="sign" style="display:none;">${sessionScope.signMsg}</span>
  	<span id="upload" style="display:none;">${sessionScope.uploadMsg }</span>
	<header id="top">
		<div class="container">
			<%@include file="head.jsp" %>
	  	</div>
	</header>
	<section id="slider">
	 <div class="container">
	    <div id="mainSlider" class="carousel slide">
	      <div class="carousel-inner">
	      	<s:iterator value="%{listPicture}" id="ip" begin="0" end="0">
	      		<div class="active item">
		          <div class="slide1 nBg">
		            <div class="row-fluid">
		              <div class="span6">
		                <h2 class="titleone"><s:property value="#ip.ptitle"></s:property></h2>
		                <p><s:property value="#ip.pcontent"></s:property></p>
		               </div>
		              <div class="span6"><img src="<s:property value="#ip.ppicture" />" alt="image alt text"/></div>
		            </div>
		          </div>
       		 </div>
	      	</s:iterator>
	      <s:iterator value="%{listPicture}" id="ip" begin="1" >
	      	<div class="item">
	          <div class="slide1 nBg">
	            <div class="row-fluid">
	              <div class="span6">
	                <h2 class="titleone"><s:property value="#ip.ptitle"></s:property></h2>
	                <p><s:property value="#ip.pcontent"></s:property></p>
	                </div>
	              <div class="span6"><img src="<s:property value="#ip.ppicture" />"  style="width:600px;height:300px" alt="image alt text"/></div>
	            </div>
	          </div>
	        </div>
	      </s:iterator>
	      </div>
	      <a class="carousel-control left" href="#mainSlider" data-slide="prev">&lsaquo;</a>
	      <a class="carousel-control right" href="#mainSlider" data-slide="next">&rsaquo;</a>
	      <ol class="carousel-indicators">
	        <li data-target="#mainSlider" data-slide-to="0" class="active"></li>
	        <li data-target="#mainSlider" data-slide-to="1"></li>
	        <li data-target="#mainSlider" data-slide-to="2"></li>
	        <li data-target="#mainSlider" data-slide-to="3"></li>
	      </ol>
	    </div>
	  </div>
	  <div class="slider-pag-bg"></div>
	</section>
	<section id="services" class="intro">
	  <div class="container">
	    <div class="row-fluid">
	      <div class="span12">
	        <h1>在您了解海纳百川之后，您会很惊叹</h1>
	        <p>欢迎您进入海纳百川学生报名网站，为您提供各种不同类型的报名路径！</p>
	      </div>
	    </div>
	  </div>
	</section>
	<div class="container">
        <div class="row clearfix" style="text-align: center;margin-top: 20px; color: #d58512;">
            <div class="col-xs-4" title="学生报名" style="width:25%;height:25%;">
                <a href="alltype.action">
                    <img alt="80x80" src="img/mainImg/apply.png" class="img-circle home-ico"/>
                    <br>
                    <span class="home_operation">学生报名</span>
                </a>
            </div>
            <div class="col-xs-4 column" title="学籍查询" style="width:25%;height:25%;">
                <a href="http://112.74.115.92/">
                    <img alt="60x60" src="img/mainImg/inquire.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">学籍查询</span>
                </a>
            </div>
            <div class="col-xs-4 column" title="上传文件" style="width:25%;height:25%;">
                <a href="upload.action">
                   <img alt="60x60" src="img/mainImg/arrow-up.png" class="img-circle home-ico" />
                   <br>
                   <span class="home_operation">上传文件</span>
		        </a>
            </div>
            <div class="col-xs-4 column" title="招生简章" style="width:25%;height:25%;">
                <a href="school.action">
                    <img alt="120x120" src="img/mainImg/clipboard.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">招生简章</span>
                </a>
            </div>
        </div>
        <div class="row clearfix" style="text-align: center;margin-top: 100px; color: #d58512;">
            <div class="col-xs-4 column" title="缴费查询" style="width:25%;height:25%;">
                <a href="getFiance?tid=${sessionScope.teacher.tid }">
                    <img alt="60x60" src="img/mainImg/pay.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">缴费查询</span>
                </a>
            </div>
            <div class="col-xs-4 column" title="联系我们" style="width:25%;height:25%;">
                <a href = "JavaScript:void(0)" id="contact">
                    <img alt="60x60" src="img/mainImg/phone.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">联系我们</span>
                </a>
            </div>
           	<div class="col-xs-4 column" title="共享合作" style="width:25%;height:25%;">
                <a href="Joinwork.action">
                    <img alt="60x60" src="img/mainImg/train.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">共享合作</span>
                </a>
            </div>
            <div class="col-xs-4 column" title="工作助手" style="width:25%;height:25%;">
                <a href="javascript:0;" id="assistant">
                	<span id="teacherid" style="display:none;">${sessionScope.teacher.tid }</span>
                    <img alt="80x80" src="img/mainImg/assistant.png" class="img-circle home-ico" />
                    <br>
                    <span class="home_operation">工作助手</span>
                </a>
            </div>
        </div>
	</div>
<div class="row" style="text-align: left;margin-top: 120px;margin-bottom: 20px; width:100%;">
     <div class="col-xs-12 column">
    	<div style="border-bottom: 1px solid #e0e0e0;">
            <h3>&nbsp;&nbsp;&nbsp;&nbsp;最新动态<span style="margin-left: 45%"><a href="/notice/selectMore">查看更多</a></span></h3>
        </div>
    	<table class="table" style="text-align: center; vertical-align: middle;">
			<tbody>
				<s:iterator value="%{listNotice}" id="ntice">
					<tr>
						<td>
							<a href="selectNoticeById?id=<s:property value="#ntice.nid" />">
								<s:property value="#ntice.ntitle"></s:property>
							</a>
						</td>
						<td>
							<s:property value="#ntice.ndate"/>
						</td>
					</tr>
				</s:iterator>
			</tbody>			    	
    	</table>
    </div>
 </div>
	<section id="about">
	  <div class="container">
	    <div class="row-fluid">
	      <div class="span12">
	        <h2 class="subpage-title white" align="center"><span>关于专业</span></h2>
	        <p>海纳百川具有更多,更好的学校，每个学校的专业都丰富多彩，海纳百川欢迎你的加入！</p>
	      </div>
	    </div>
	  </div>
	  <div class="about-arrow"></div>
	</section>
	
	<section id="blog">
		<div class="container">
			<h3 class="subpage-title" align="center"><span>热门专业</span></h3>
			<div class="row-fluid">
				<div class="span12">
					<ul class="blog-posts">
						<li>
						<s:iterator value="%{listHotmajor}" id="hot" begin="0" end="0">
							<div class="row-fluid">
								<div class="span6">
									<div class="post-image">
										<img src="<s:property value="#hot.hpicture" />" alt="image alt text here" style="width:100%;height:220px;">
									</div>
								</div>
								<div class="span6">
									<div class="blog-post">
										<h2 class="black"><s:property value="#hot.htitle" /></h2>
			            				<p><s:property value="#hot.hcontent"/></p>
									</div>
								</div>
							</div>
						</s:iterator>
						</li>
						<li>
						<s:iterator value="%{listHotmajor}" id="hot" begin="1" end="1">
							<div class="row-fluid">
								<div class="span6">
									<div class="blog-post">
										<h2 class="black"><s:property value="#hot.htitle" /></h2>
			            				<p><s:property value="#hot.hcontent"/></p>
									</div>
								</div>
								<div class="span6">
									<div class="post-image">
										<img src="<s:property value="#hot.hpicture" />" alt="image alt text here" style="width:100%;height:220px;">
									</div>
								</div>
							</div>
						</s:iterator>
						</li>
						<li>
						<s:iterator value="%{listHotmajor}" id="hot" begin="2" end="2">
							<div class="row-fluid">
								<div class="span6">
									<div class="post-image">
										<img src="<s:property value="#hot.hpicture" />" alt="image alt text here" style="width:100%;height:220px;">
									</div>
								</div>
								<div class="span6">
									<div class="blog-post">
										<h2 class="black"><s:property value="#hot.htitle" /></h2>
			            				<p><s:property value="#hot.hcontent"/></p>
									</div>
								</div>
							</div>
						</s:iterator>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	
	<section id="contact">
		 <%@include file="foot.jsp" %> 
	</section>
	<!-- 侧导航条 -->
	<%@include file="strip.jsp" %>
	<!-- <div id="fade" class="black_overlay"></div> -->
	<script src="js/rightindex/jquery.min.js"></script>
	<script src="js/rightindex/bootstrap.min.js"></script>
	<script src="js/rightindex/jquery.goup.min.js"></script>
	<!-- 弹出提示框引用的js -->
	<script src="js/rightindex/sweetalert2.min.js"></script>
	<script src="js/rightindex/app.js"></script>
	
  </body>
</html>


