<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>海纳百川-工作助手</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="img/icons/favicon1.ico" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/bootstrap-table.min.css">
	<link href="css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="css/sweetalert2.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="loginedHead.jsp" %>
<div class="container">
	<h3 style="text-align: center;font-weight: bold;">工作助手</h3>
	<div class="row" style="margin-top: 40px;">
		<div class="col-xs-12">
			<table id="scheduleTable" data-toggle="table"
			       data-url="/Enrollment/selectAssist?tid=${teachers.tid}"
			       data-pagination="true"
			       data-pagination-h-align="right"
			       data-locale="zh-CN"
			       data-side-pagination="client"		<%-- 客户端：client  服务端：server --%>
				   data="data"
			       data-toolbar="#toolbar"
			       data-search="true"
			       data-striped="true" 
			       data-show-refresh="true"
			       data-show-toggle="true"
			       data-show-columns="true"
			       data-show-export="true"
			       data-minimum-count-columns="2" 
			       data-id-field="wa_id" 
			       data-show-footer="false"
			       data-row-style="rowStyle">
			    <thead>
			    <tr>
			    	<th data-field="wa_title" data-align="center" data-sortable="false" >计划标题</th>
			    	<th data-field="wa_content" data-align="center" data-sortable="false" >计划内容</th>
			    	<th data-field="wa_createtime" data-align="center"  data-sortable="false" >创建时间</th>
			    	<th data-field="wa_state" data-formatter="stateFormatter" data-align="center"  data-sortable="false" >是否完成</th>
			    	<th data-align="center" data-formatter="actionFormatteradd" data-events="actionadd" data-sortable="false">操作</th>
			    	<th data-field="wa_id" data-align="center" data-formatter="actionFormatteredit" data-events="actionEvents" data-sortable="false">操作</th>
			    </tr>
			    </thead>
			</table>
		</div>
	</div>
</div>
<%@include file="foot.jsp" %>
<!--	***********************************************************  -->
<script src="js/gzzs/jquery.min.js"></script>
<script src="js/gzzs/bootstrap.min.js"></script>
<script src="js/gzzs/bootstrap-table.min.js"></script>
<script src="js/gzzs/bootstrap-table-zh-CN.min.js"></script>
<script src="js/gzzs/bootstrapValidator.min.js"></script>
<script src="js/gzzs/jquery.goup.min.js"></script>
<script src="js/gzzs/sweetalert2.min.js"></script>
<script src="js/gzzs/app.js"></script>

<script type="text/javascript">
	//登录前后页面显示不同
	function actionFormatteradd(value, row, index) {
	    return [
	        '<a class="add ml10" href="javascript:void(0)" title="添加">',
	        '<i class="glyphicon glyphicon-save"></i>',
	        '</a>'
	    ].join('');
	}
	function actionFormatteredit(value, row, index) {
		 return [
		        '<a class="remove ml10" href="javascript:void(0)" title="删除">',
		        '<i class="glyphicon glyphicon-remove"></i>',
		        '</a>'
		    ].join('');
	}
	function actionFormatter(value, row, index) {
	    return [
	        '<a class="edit ml10" href="javascript:void(0)" title="修改">',
	        '<i class="glyphicon glyphicon-edit"></i>',
	        '</a>'
	    ].join('');
	}
	function dateFormatter(value) {
		if(value != null){
			var date = value.split("-")[0]+"/"+value.split("-")[1]+"/"+(value.split("-")[2]).split(" ")[0];
		    return date
		}
	}
	function stateFormatter(value) {
		if(value=="0"){
		
			value="未完成";
		}else{
			value="完成";
		}
		
		return value;
	}

	window.actionadd = {
	'click .add': function (e, value, row, index) {
		swal({
	    	  title: '',
	    	  html:
	      	    '标题<input id="wa_title" name="wa_title" type="text" class="swal2-input">' +
	      	    '内容 <textarea id="wa_content" name ="wa_content" class="form-control" rows="3"></textarea>',
	      	  preConfirm: function() {
	      		  var wa_title = $('#wa_title').val();
	  	          var wa_content = $('#wa_content').val();
	  	          var id = ${teacher.tid};
	      	      return new Promise(function(resolve) {
	     	   			$.post("/Enrollment/addAssist",{
	     	   					"workAssist.wa_title":wa_title,
	     	   					"workAssist.wa_content":wa_content,
	     	   					"tid":id
	     	   					},function(result) {
	   	   				if(result.msg=='增加成功!'){
							sweetAlert(
								result.msg,
		   	   				  	'',
		   	   				  	'success'
		     	   			)
		     	   			$('#scheduleTable').bootstrapTable('refresh');
						}else{
							sweetAlert(
							result.msg,
		   	   				  	'',
		   	   				  	'error'
		     	   			)
						}
					});
	    	    });
	    	  }
	    	})
	    }
	}

	window.actionEvents = {
		'click .remove': function (e, value, row, index) {
		swal({
			  title: '提示',
			  text: '你确定要删除吗?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: '确认',
			  cancelButtonText: '取消',
			}).then(function(isConfirm) {
			  if (isConfirm === true) {
			    $.post('/Enrollment/DeleteAssist', {
	                id : value
	            }, function(result) {
	                if (result.msg=='删除成功!') {
	                	
	                	sweetAlert(
								result.msg,
		   	   				  	'',
		   	   				  	'success'
		     	   			)
	                    $('#scheduleTable').bootstrapTable('refresh');
	                }
	            }, 'JSON');
			  } else if (isConfirm === false) {
			    /* swal(
			      'Cancelled',
			      'Your imaginary file is safe :)',
			      'error'
			    ); */
			  } else {
			    // Esc, close button or outside click
			    // isConfirm is undefined
			  }
			});
	    }
	}
</script>
	<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px; display: none;" class="goup-container" title="">
		<div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
	</div>
	<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px; display: none;" class="goup-container" title="">
		<div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
	</div>
</body>
</html>