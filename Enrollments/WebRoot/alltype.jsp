<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>学生报名</title>
	    <link rel="shortcut icon" href="img/icons/favicon1.ico">
		<link href="js/notice/bootstrap.min.css" rel="stylesheet">
    	<link href="js/notice/css/style.css" rel="stylesheet">
    	
    	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	</head>
<body>
<div class="wrapper">
	<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
		<%@include file="head.jsp" %>
	</nav>
    <div class="container">
        <div class="row clearfix" style="text-align: center;margin-top: 20px; color: #d58512;">
            <div class="col-xs-4 column">
                <a href="${sessionScope.adult.ataddress }">
                    <img alt="80x80" src="${sessionScope.adult.atpicture }" class="img-circle apply-ico" />
                    <br>
                    <span class="home_operation">${sessionScope.adult.atname }<br>报名</span>
                </a>
            </div>
            <div class="col-xs-4 column">
                <a href="${sessionScope.distance.deaddress }">
                    <img alt="80x80" src="${sessionScope.distance.depicture }" class="img-rounded apply-ico" />
                    <br>
                    <span>${sessionScope.distance.dename }<br>报名</span>
                </a>
            </div>
            <div class="col-xs-4 column">
                <a href="${sessionScope.country.cyaddress }">
                    <img alt="80x80" src="${sessionScope.country.cypicture }" class="img-circle apply-ico" />
                    <br>
                    <span class="home_operation">${sessionScope.country.cyname }<br>报名</span>
                </a>
            </div>
        </div>
        <div class="row clearfix" style="text-align: center;margin-top: 20px;">
           <div class="col-xs-4 column">
                <a href="${sessionScope.accounting.agaddress }">
                    <img alt="80x80" src="${sessionScope.accounting.agpicture }" class="img-circle apply-ico" />
                    <br>
                    <span class="home_operation">${sessionScope.accounting.agname}<br>报名</span>
                </a>
            </div>
            <div class="col-xs-4 column">
                <a href="${sessionScope.arts.asaddress }">
                    <img alt="80x80" src="${sessionScope.arts.aspicture }" class="img-circle apply-ico" />
                    <br>
                    <span class="home_operation">${sessionScope.arts.asname }<br>报名</span>
                </a>
            </div>
            <div class="col-xs-4 column">
                <a href="${sessionScope.vocational.vladdress }">
                    <img alt="140x140" src="${sessionScope.vocational.vlpicture }" class="img-circle apply-ico" />
                    <br>
                    <span class="home_operation">${sessionScope.vocational.vlname }<br>培训</span>
                </a>
            </div>
        </div>
        
        <div class="row" style="text-align: center;margin-bottom: 20px; margin-top: 20px;">
         <div class="col-xs-12 column">
             <div style="border-bottom: 2px solid #e0e0e0; margin-bottom: 10px;">
                 <h4 style="font-weight: bold;">海纳百川平台，助你平步青云！</h4>
             </div>
			<div class="row">
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="img/mainImg/study.jpg" />
                            <div class="caption">
                                <h4>学习</h4>
                                <p>“海纳百川平台”是让各种学生成功就业的学习平台，学历终身可查，保证学生的就业，月拿高薪不是梦！</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="img/mainImg/test.jpg" />
                            <div class="caption">
                                <h4>考试</h4>
                                <p>还在为自己拿不到高的学历而发愁？这里有专业的老师， 对你进行一对一的辅导。让你逢考必过，轻松愉悦高学历！</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="img/mainImg/certificate.jpg" />
                            <div class="caption">
                                <h4>拿证</h4>
                                <p>毕业证书国家承认，学信网可查，公司认可。让你职场上优先坐上好职位！</p>
                            </div>
                        </div>
                    </div>
               </div>
         </div>
     </div>
    </div>
<%@include file="foot.jsp" %>
</div>
<%@include file="strip.jsp" %>
<!-- <div id="fade" class="black_overlay"></div> -->
<!-- ****************************************** -->
<script src="js/notice/jquery.min.js"></script>
<script src="js/notice/bootstrap.min.js"></script>
<script src="js/notice/jquery.goup.min.js"></script>
<script src="js/notice/js/app.js"></script>
</body>
</html>