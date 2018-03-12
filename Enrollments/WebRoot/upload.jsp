<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="img/icons/favicon1.ico">	
    <link href="css/acc/bootstrap.min.css" rel="stylesheet">
    <link href="css/acc/bootstrapValidator.min.css" rel="stylesheet">
    <link href="css/acc/style.css" rel="stylesheet">
    <link href="css/acc/sweetalert2.min.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
   	<script type="text/javascript">
   		$(document).ready(function(){
			var sign = $('#sign').text();
			var upload = $('#upload').text();
			if(sign!=""){
				swal({  
		            text: "学生信息已提交成功！",  
		            type: "success",  
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
			if(upload!=""){
				swal({  
		            text: "对不起信息上传失败！",  
		            type: "error",  
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
		});
   	</script>
<title>海纳百川-文件上传</title>
</head>
<body>
	<span id="sign" style="display:none;">${sessionScope.signMsg}</span>
	<span id="upload" style="display:none;">${sessionScope.uploadMsg }</span>
	<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
       <%@include file="head.jsp" %>
	</nav>
	<div>
		<img alt="" src="img/index/timg1.jpg" style="width: 100%;">
	</div>
	<div class="container" style="margin-bottom: 40px;">
	
	<div class="row" style="margin: 20px;">
		<div class="col-md-12">
			<h3>文件上传</h3>
			<form role="form" id="uploadForm" method="post" enctype="multipart/form-data" action="AddUpload.action">
				<div class="form-group">
					 <label for="photo">相片</label>
					 <input type="file" name="sfphoto" id="sfphoto"   onchange="fileType2(this,'t1')"/>
					 <span id="tishi1" style="color:red;font-weight:bold"></span>
				</div>
				<div class="form-group">
					 <label for="voucher">缴费凭证</label>
					 <input type="file" name="sfvoucher" id="sfvoucher"  onchange="fileType2(this,'t2')"/>
					 <span id="tishi2" style="color:red;font-weight:bold"></span>
				</div>
				<div class="form-group">
					 <label for="paper">论文</label>
					 <input type="file" name="sfpaper" id="sfpaper"  onchange="fileType1(this)"/>
					 <span id="tishi3" style="color:red;font-weight:bold"></span>
				</div>
				  <input type="hidden" name="sid" id="sid" value="${sessionScope.student.sid}">
				 <%--  <input type="hidden" name="sname" id="sname" value="${sessionScope.student.sname}"> --%>
				  <input type="submit" class="btn btn-info btn-lg"  value="上传" />
			</form>
			
		</div>
	</div>
	</div>
	
<%@include file="foot.jsp" %>
<%@include file="strip.jsp" %>
<!-- <div id="fade" class="black_overlay"></div> -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrapValidator.min.js"></script>
	<script src="js/jquery.goup.min.js"></script>
	<script src="js/app.js"></script>
	<script src="js/ajaxfileupload.js"></script>
	<script src="js/sweetalert2.min.js"></script>
	<!-- *************************** -->
	<script type="text/javascript">
	function upload(){
			var sfphoto = document.getElementById("sfphoto").value;
			var sfvoucher = document.getElementById("sfvoucher").value;
			var sfpaper = document.getElementById("sfpaper").value;
			var sid = document.getElementById("sid").value;
			var sname = document.getElementById("sname").value;
		 $.ajax({  
	          url: 'AddUpload.action' ,  
	          type: 'post',  
	          data: {'ssfphoto':sfphoto,'sfvoucher':sfvoucher,'sfpape':sfpaper,'sid':sid,'sname':sname},
	          dataType:'json',  
	          success: function (data) {  
	        	 if(data!={}){
	        			alert("上传文件成功!")
	        		  	window.location="upload.action";
		          }else { 
						alert("上传文件失败,没有登录")
					   }
					}
	     	});
	}
	function fileType2(obj,t){
		var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
		if(t=='t1') {
			if(fileType == '.jpeg' | fileType == '.png' | fileType == '.jpg'){
				$("#tishi1").html('');
		    }else {
		    	$("#tishi1").html("<b>*请选择jpeg、jpg或png格式的文件</b>");
		    	$("#photo").val('');
		    	document.getElementById("photo").files[0] = '请选择jpeg、jpg或png格式的文件';
			}
		}else {
			if(fileType == '.jpeg' | fileType == '.png' | fileType == '.jpg'){
				$("#tishi2").html('');
		    }else {
		    	$("#tishi2").html("<b>*请选择jpeg、jpg或png格式的文件</b>");
		    	$("#voucher").val('');
		    	document.getElementById("voucher").files[0] = '请选择jpeg、jpg或png格式的文件';
			}
		}
	}
	
</script>

</body>
</html>