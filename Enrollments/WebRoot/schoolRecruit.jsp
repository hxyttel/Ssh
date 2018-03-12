<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="img/icons/favicon1.ico">
    <link href="css/rightindex/bootstrap.min.css" rel="stylesheet">
    <link href="css/rightindex/style.css" rel="stylesheet">
<title>招生简章</title>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top navbar-top" role="navigation">
	<%@include file="head.jsp" %>
</nav>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="tabbable" id="tabs-680011">
				<ul class="nav nav-tabs">
					<li class="active">
						 <a href="" data-toggle="tab">合作院校</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-friends" style="margin-top: 20px;">
						<div class="row" style="text-align: center;">
							<div class="col-xs-12">
								<c:forEach items="${lists}" var="lists">
									<p style="float:left;width: 33%;">
										<a href="school_2?gid=${lists.gid} ">
											<img alt="" style="width: 90%" src="${lists.glogo}">
										</a>
									</p>
								</c:forEach>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="text-align: center;margin-bottom: 20px; margin-top: 20px;">
         <div class="col-xs-12 column">
             <div style="border-bottom: 2px solid #e0e0e0; margin-bottom: 10px;">
                 <h4 style="font-weight: bold;">招生管理公共平台，助你平步青云！</h4>
             </div>
			<div class="row">
                    <div class="col-md-4">
                        <div class="thumbnail">
                          
                            <div class="caption">
                                <h4>
          							学习
                                </h4>
                                <p>
                                	“招生管理公共平台”是让各种学生成功就业的学习平台，学历终身可查，
                                	保证学生的就业，月拿高薪不是梦！
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="thumbnail">
                          
                            <div class="caption">
                                <h4>
                                    考试
                                </h4>
                                <p>
                                   还在为自己拿不到高的学历而发愁？这里有专业的老师，
                                   对你进行一对一的辅导。让你逢考必过，轻松愉悦高学历！
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="thumbnail">
                        
                            <div class="caption">
                                <h4>
                                    拿证
                                </h4>
                                <p>
                                    毕业证书国家承认，学信网可查，公司认可。让你职场
                                    上优先坐上好职位！
                                </p>
                            </div>
                        </div>
                    </div>

                </div>
         </div>
     </div>
    </div>
<%@include file="foot.jsp" %>
<%@include file="strip.jsp" %>

<!-- <div id="fade" class="black_overlay"></div> -->
<!--	***********************************************  -->
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/rightindex/app.js"></script>
<script src="js/rightindex/jquery.min.js"></script>
<script src="js/rightindex/jquery.goup.min.js"></script>
</body>
</html>