<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- 设置图标 -->
	 <link rel="shortcut icon" href="/Enrollment/img/icons/favicon1.ico">
    <link href="css/rightindex/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/rightindex/sweetalert2.min.css" rel="stylesheet"/>
    <link href="css/acc/bootstrapValidator.min.css" rel="stylesheet"/>
    <link href="css/rightindex/style.css" rel="stylesheet"/>
<title>招生管理公共平台-共享文件</title>
</head>
<body>
	<div class="container">
		<div class="row clearfix" style="text-align: center;margin-top: 20px;margin-bottom:10px; color: #d58512;">
	        <div class="col-xs-4">
				<p><font size="4"><s:property value="{joinwork.jwcontent}" /></font></p>
			</div>
	        
		</div>
	</div>
	

<%@include file="foot.jsp" %>
<%@include file="strip.jsp" %>

<!-- <div id="fade" class="black_overlay"></div> -->
<!-- **************************************** -->
<script src="js/rightindex/sweetalert2.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/notice/bootstrapValidator.min.js"></script>
<script src="js/notice/jquery.goup.min.js"></script>
<script src="js/notice/app.js"></script>
  </body>
</html>
