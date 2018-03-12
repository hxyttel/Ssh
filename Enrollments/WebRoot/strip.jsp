<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
   <div class="float">
        <a href = "JavaScript:void(0)" id="contact2">
            <img src="img/index/phone.png" style="width: 100%;margin-bottom: 10px; border-radius: 10px;">
        </a>
        <a href = "JavaScript:void(0)" id="maps">
            <img src="img/index/maps.png" style="width: 100%; border-radius: 10px;">
        </a>
</div>
<div id="light" class="white_content">
    <div class="phone-head" style="text-align: right;background-color: burlywood;">
        <a href = "javascript:void(0)" id="closePhone">
            <img src="img/index/close2.png" style="width: 35px;margin: 5px;">
        </a>
    </div>
    <div class="phone-body" style="text-align: left;margin-top: 20px;">
    	<div class="container">
    	
    		<div class ="row">
    			<s:iterator value="%{listContact}" id="lc">
    				<div class="col-xs-6 column">
	    			<p><span><s:property value="#lc.ctype"/>：</span><span><s:property value="#lc.cpeople" /></span></p>
			        <p>
			        	<img src="img/index/phone1.png">
			            <a href="http://wpa.qq.com/msgrd?v=3&uin=2355888247&site=qq&menu=yes" target="_blank">
			            <img src="http://wpa.qq.com/pa?p=2:2355888247:51" title="点击这里给我发消息" style="height: 32px;width: 80px;"/>
						</a>
						<br>
			        	<span><s:property value="#lc.cphone"/></span>
			        </p>
	    		</div>
    			</s:iterator>
    		
	    	</div>   
    	</div>
   </div>
</div>
<div id="map" class="map_content">
    <div class="phone-head" style="text-align: right;background-color: burlywood;">
        <a href = "javascript:void(0)" id="closeMap">
            <img src="/Enrollment/img/index/close2.png" style="width: 35px;margin: 5px;">
        </a>
    </div>
    <div class="phone-body" style="text-align: left;margin-top: 2px;">
    	<div class="container"> 	
    		<div class ="row">
    		<div class="col-xs-6 column">
    		<h4 style="margin-left: 5px;">地址：江西省赣州市技师学院</h4>
			<iframe width="604" height="393" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://j.map.baidu.com/S7BeL"></iframe>
			</div>
			</div>    
    	</div>
   </div>
</div>
  </body>
</html>
