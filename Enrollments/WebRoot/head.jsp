<%@page import="com.ssh.pojo.Teacher"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="static/proscenium/logo/favicon2.ico" /> 
    <link href="js/notice/bootstrap-table.min.css" rel="stylesheet" />
    <link href="js/notice/bootstrapValidator.min.css" rel="stylesheet">
    <link href="js/notice/bootstrap.min.css" rel="stylesheet">
    <link href="js/notice/style.css" rel="stylesheet">
  </head>
  
  <body>
     	
        <div class="navbar-header">
               <a class="navbar-brand" href="#"  style="padding: 0 0;width:68%;">
      				<img src="img/logo1.png" alt="image alt text here" style="width:250px;height:60px"/>
           	   </a>
        </div>
       	 <div class="collapse navbar-collapse" id="navbar-menu">
       	   <s:if test="#session.teacher.tphone==null">
       	   		<ul class="nav navbar-nav navbar-right">
                <li class="active">
                    <a href="index.action">
	                    <img alt="" src="img/home1.png" style="width: 30px;">
	                   	首页
                   	</a>
                </li>
           		<li class="active">
       				<a href="login.jsp">
  						<img alt="" src="img/user62.png" style="width: 30px;">
   					 	合作招生
   		 			</a>
 		 		</li>
 		 		<li class="active">
       		 		<a href="login.jsp">
        				<img alt="" src="img/user62.png" style="width: 30px;">
         				 后台登录
       		 		</a>
       		 	</li>	 
            </ul>
       	   </s:if>
       	   <s:if test="#session.teacher.tphone!=null">
       	   		<ul class="nav navbar-nav navbar-right">
                <li class="active">
                    <a href="index.action?tphone=${sessionScope.teacher.tphone }">
	                    <img alt="" src="img/home1.png" style="width: 30px;">
	                   	首页
                   	</a>
                </li>
           		<li>
           			<a href="#">
            			<img alt="" src="img/user62.png" style="width: 30px;">
          				${sessionScope.teacher.tname}
          			</a>
          		</li>
        		<li>
      				<a onclick="update();">
      					<img alt="" src="img/user62.png" style="width: 30px;">
      					修改密码
      				</a>
        		</li>
              	<li>
                    <a href="login.action">
	                    <img alt="" src="img/logout2.png" style="width: 30px;">
	                   	退出
                   	</a>
               	</li>
            </ul>
            <span style="display: none;" id="tid">${sessionScope.teacher.tid}</span>
            <span style="display: none;" id="getOld">${sessionScope.teacher.tpassword}</span>
       	   </s:if>
       	</div>
  </body>
		<script src="js/notice/jquery.min.js"></script>
		<script src="js/notice/bootstrap.min.js"></script>
		<script src="js/notice/bootstrap-table.min.js"></script>
		<script src="js/notice/bootstrap-table-zh-CN.min.js"></script>
		<script src="js/notice/bootstrapValidator.min.js"></script>
		<script src="js/notice/jquery.goup.min.js"></script>
		<script src="js/notice/app.js"></script>
		<script type="text/javascript">
			function actionFormatter(value, row, index) {
			    return [
			        '<a class="open ml10" href="javascript:void(0)" title="查看">',
			        '<i class="glyphicon glyphicon-eye-open"></i>',
			        '</a>'
			    ].join('');
			}
			function dateFormatter(value) {
				var date = value.split("-")[0]+"/"+value.split("-")[1]+"/"+(value.split("-")[2]).split(" ")[0];
			    return date
			}
			window.actionEvents = {
				'click .open': function (e, value, row, index) {
					//window.location="/notice/selectById?id="+value;
			    }
			}
			function update(){
	    	swal({
	    	  title: '修改密码',
	    	  html:
	    	    '旧密码<input id="oldPwd" name="oldPwd" type="password" class="swal2-input" autofocus>' +
	    	    '新密码<input id="pwd" name="pwd" type="password" class="swal2-input">'+
	    	    '确认新密码<input id="rePwd" name="rePwd" type="password" class="swal2-input">',
              showCancelButton: true,
              confirmButtonColor: '#4cd964',
              cancelButtonColor: 'gray',  
              cancelButtonText: '取消',  
              reverseButtons: true, //控制按钮反转
              confirmButtonText: '确定',
              preConfirm: function() {
		          var oldPwd = $('#oldPwd').val();
		          var pwd = $('#pwd').val();
		          var rePwd = $('#rePwd').val();
		          var id = $('#tid').text();
		          var getOld = $('#getOld').text();
		          return new Promise(function(resolve) {
		          if(oldPwd==getOld){
		          	if(pwd==rePwd){
	    	   			$.post("UpdatePassword.action",{"newpassword":rePwd,"id":id},function(data) {
	    	   				data = $.parseJSON(data);
	    	   				if(data.msg){
								sweetAlert(
									"修改成功",
			   	   				  	'',
			   	   				  	'success'
			     	   			).then(function(isConfirm) {
			     	   				if(isConfirm) {
			     	   					window.location="http://localhost:8080/Enrollment/login.jsp";
			     	   				}
			     	   			})
							}else{
								sweetAlert(
									"修改密码失败",
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
		          }else{
	          		sweetAlert(
   	   				  '旧密码不正确',
   	   				  '',
   	   				  'error'
     	   			).then(function(isConfirm) {
     	   				if(isConfirm) {
     	   					update();
     	   				}
     	   			})
		          }
		        }) 
		      }
           });
	    }
	</script>
</html>
