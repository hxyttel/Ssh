<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>招生管理公告表</title>
    <link rel="shortcut icon" href="/Enrollment/img/icons/favicon1.ico">
    
    <link href="js/notice/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/notice/bootstrap-table.min.css" />
    <link href="js/notice/bootstrapValidator.min.css" rel="stylesheet">
    <link href="js/notice/css/style.css" rel="stylesheet">
     <script type="text/javascript">
	    function update(){
	    	swal({
	    	  title: '',
	    	  html:
	    	    '旧密码<input id="oldPwd" name="oldPwd" type="password" class="swal2-input" autofocus>' +
	    	    '新密码<input id="pwd" name="pwd" type="password" class="swal2-input">'+
	    	    '确认新密码<input id="rePwd" name="rePwd" type="password" class="swal2-input">',
	    	  preConfirm: function() {
	    		  var oldPwd = $('#oldPwd').val();
		          var pwd = $('#pwd').val();
		          var rePwd = $('#rePwd').val();
	    	      return new Promise(function(resolve) {
	    	   		if(pwd==rePwd){
	    	   			$.post("/user/editUserPwd1",{"oldPwd":oldPwd,"pwd":rePwd,"id":id},function(data) {
	    	   				data = $.parseJSON(data);
	    	   				if(data.success == true){
								sweetAlert(
									data.msg,
			   	   				  	'',
			   	   				  	'success'
			     	   			).then(function(isConfirm) {
			     	   				if(isConfirm) {
			     	   					window.location="/index/loginout";
			     	   				}
			     	   			})
							}else{
								sweetAlert(
									data.msg,
			   	   				  	'',
			   	   				  	'error'
			     	   			)
							}
						});
	      		  	}else{
	      		  		sweetAlert(
	   	   				  '新密码不一致',
	   	   				  '',
	   	   				  'error'
	     	   			)
	      		  	}
	    	    });
	    	  }
	    	})
	    }
    </script>
</head>
<body>
<%@ include file="strip.jsp" %>	
<div class="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
		              <%@include file="head.jsp" %>
		</nav>
	    <div><img src="img/happy.jpg" width="100%"></div>
	    <div class="container">
	        <div class="row clearfix" style="text-align: center;margin-top: 20px;">
	        	<div class="col-md-12">
	        		<div class="view">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>公告栏</h3>
						</div>
						<div class="panel-body">
						<h4><s:property value="%{notice.ncontent}"/></h4>
						</div>
						<div class="panel-footer">时间：<s:property value="%{notice.ndate}"/></div>
					</div>
					</div>
	        	</div>
	
	        </div>
	    </div>
	</div>
<%@ include file="foot.jsp" %>
<!-- ************************************************* -->
<script src="js/notice/jquery.min.js"></script>
<script src="js/notice/bootstrap.min.js"></script>
<script src="js/notice/bootstrap-table.min.js"></script>
<script src="js/notice/bootstrap-table-zh-CN.min.js"></script>
<script src="js/notice/bootstrapValidator.min.js"></script>
<script src="js/notice/jquery.goup.min.js"></script>
<script src="js/notice/js/app.js"></script>
</body>
</html>