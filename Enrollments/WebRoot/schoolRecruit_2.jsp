<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学院简介</title>
    <link rel="shortcut icon" href="img/icons/favicon1.ico" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
<%@include file="head.jsp" %>
    <div class="container">
	    <div class="row">
			<div class="col-md-12">
	    		<div>
					<div id="s1" style="margin-top: 20px;margin-bottom: 10px;">
						<img style="width: 100%;" alt="" src="${general.gpicture}">
	                </div>
	                <div style="margin-bottom: 40px;">
	                	<p style="text-indent: 20px;"><span>${general.gtitle}</span></p>
	                    <h3>学校简介</h3>
	                     <p style="text-indent: 20px;"><span>${general.gintroduce}</span></p>
	                </div>
	                <div>
	                      <img style="width: 100%;" alt="" src="${general.gmajor}">
	                </div>
	                <div style="margin-bottom: 20px;">
	                    <h4 style="text-align: center;color: red;"> 国家承认学历   教育部网上可查</h4>
	                    <h4>毕业证样本：</h4>
	                    <img style="width: 100%;" alt="" src="${general.gdiploma}">
	                </div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="foot.jsp" %>
<%@include file="strip.jsp" %>
<!--	*******************************************************  -->
<script src="js/rightindex/jquery.min.js"></script>
<script src="js/acc/bootstrap.min.js"></script>
<script src="js/acc/jquery.goup.min.js"></script>
<script src="js/acc/app.js"></script>

</body>

<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px;" class="goup-container" title=""><div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
</div>
<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px;" class="goup-container" title=""><div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
</div>
</body>
</html>