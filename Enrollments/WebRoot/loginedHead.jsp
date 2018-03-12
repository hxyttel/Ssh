<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
         <div class="navbar-header">
               <a class="navbar-brand" href="/index"  style="padding: 0 0;width:68%;">
      				<img src="img/logo1.png" alt="image alt text here" style="width:250px;height:60px"/>
           	   </a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-menu">
            <ul class="nav navbar-nav navbar-right">
                <li class="active">
                    <a href="index.action?tphone=${teachers.tphone }">
	                    <img alt="" src="img/home1.png" style="width: 30px;">
	                   	首页
                   	</a>
                </li>
           		<li>
           			<div style="padding: 10px 15px;">
            			<img alt="" src="img/user62.png" style="width: 30px;">
          				${teachers.tname}
            			</div>
          			</li>
          			<li>
          				<div style="padding: 10px 15px;">
           					<img alt="" src="img/user62.png" style="width: 30px;">
           					<a onclick="update();">修改密码</a>
           				</div>
          			</li>
              	<li>
                    <a href="login.action">
	                    <img alt="" src="img/logout2.png" style="width: 30px;">
	                   	退出
                   	</a>
               	</li>
            </ul>
            <span style="display: none;" id="tid">${teachers.tid}</span>
            <span style="display: none;" id="getOld">${teachers.tpassword}</span>
        </div>
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
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
</nav>