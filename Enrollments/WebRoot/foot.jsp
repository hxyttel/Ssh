<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'foot.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/rightindex/bootstrap.min.css" rel="stylesheet">
	<link href="css/rightindex/style.css" rel="stylesheet">
	<link href="css/rightindex/sweetalert2.min.css" rel="stylesheet">
	<script src="js/rightindex/jquery.min.js"></script>
	<script src="js/rightindex/bootstrap.min.js"></script>
	<script src="js/rightindex/jquery.goup.min.js"></script>
	<script src="js/rightindex/sweetalert2.min.js"></script>
	<script src="js/rightindex/app.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<s:iterator value="%{listContent}" id="ip" begin="0" end="0">
  		<div  style="background-color:#33cc99;">
	<div class="container">
		<div class="row" style="margin-top: 20px;text-align: center">
			<div class="col-xs-6 column">
				<p>
					<img alt="140x140" src="<s:property value="#ip.icode"></s:property>" style="width:80%;height:70%;margin-bottom: 10px;" />
				</p>
			</div>
			<div class="col-xs-6 column">
				<br>
				<br>
				<br>
				<h4>
				<img src="img/index/phone.png" style="width: 20%">
				&nbsp;&nbsp;
				<span><font size="7" color="black"><s:property value="#ip.iphone"></s:property></font></span>
				<br>
				</h4>
				<br>
				<div></div>
				<h4><font size="6" >地址：</font><span><font size="6"><s:property value="#ip.iaddress"></s:property></font></span></h4>
	    		<div class="col-xs-12 column">
	    		<br>
				
			</div>
			</div>
			
    	</div>
	</div>
</div>
</div>
  	</s:iterator>
    
  </body>
</html>
