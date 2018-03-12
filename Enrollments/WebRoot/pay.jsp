<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>海纳百川-缴费查询</title>
	<link rel="shortcut icon" href="img/icons/favicon1.ico" />
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrapValidator.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/bootstrap-table.min.css">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<%@include file="loginedHead.jsp" %>
	<div class="container" style="height:640px;margin-bottom: 10px;">
	<div class="row">
		<div class="col-xs-12">
			<div id="prints" class="col-xs-12" style="font-size: 14px;">
				<table id="noticeTable" data-toggle="table"
					   data-url="/Enrollment/listFiances?tid=${teachers.tid }"		
					   data-method="post"
					   data-contentType="application/x-www-form-urlencoded"
				       data-pagination="true"		<%-- 是否启用分页 --%>
				       data-striped="true"			<%--隔行变色 --%>
				       data-locale="zh-CN"			
				       <%--data-search="true"			显示表格搜索，不访问服务 --%>
				       data-side-pagination="client"		<%-- 客户端：client  服务端：server--%>
				       data="data"
				       data-queryParamsType=""		<%--  默认值为 'limit',参数为：offset,limit,sort
                                    					   设置为 '',参数为：pageSize,pageNumber  --%>
				      data-queryParams="getParams"  <%-- 前端调用服务时，会传递上面的参数 --%>
				       data-page-number="1"				<%-- --%>
				       
				       data-page-size="10"				
				       data-page-list="[10, 20, 30, 40, 50, 60]"
				       data-toolbar="#toolbar"		<%--工具按用什么容器 --%>
				       data-show-refresh="true"
				       data-show-toggle="true"  	<%--切换 --%>
				       data-show-columns="true"		<%--是否显示所有列 --%>
				       data-show-export="true" 
				       data-minimum-count-columns="2" 
				       data-id-field="f_id" 
				       data-show-footer="true"		<%-- 是否显示尾部 --%>
				       data-row-style="rowStyle">
				    <thead>
				    <tr>
				    	<th data-field="sname" data-align="left" data-footer-formatter="汇总" data-sortable="false" >学生姓名</th>
				    	<th data-field="feneeddmoney" data-align="left" data-sortable="false" >需缴金额</th>
				    	<th data-field="frpractical" data-align="left" data-sortable="false" >实缴金额</th>
				    	<th data-field="feway" data-align="left" data-sortable="false" >缴费方式</th>
				    	<th data-field="acontent" data-align="left" data-sortable="false" >专业</th>
				    	<th data-field="stype" data-align="left" data-footer-formatter="sumFormatter" data-sortable="false" >报名类型</th>
				    	<th data-field="festate" data-align="left" data-footer-formatter="sumFormatter" data-sortable="false" >缴费状态</th>
				    	<th data-field="tname" data-align="left" data-sortable="false" >班主任</th>
				    	<th data-field="fedate" data-align="center" data-formatter="dateFormatter" data-sortable="false" >缴费时间</th>
				    </tr>
				    </thead>
				</table>
				<!-- <span>总金额:2342</span> -->
				<button type="submit" class="btn btn-primary btn-sm" onclick="windowprint();">
				<span class="glyphicon glyphicon-print"></span>
				打印</button>
			</div>
		</div>
	</div>
	</div>
<!-- ******************************************************************* -->
	<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
	<script src="js/gzzs/jquery.min.js"></script>
	<script src="js/gzzs/bootstrap.min.js"></script>
	<script src="js/gzzs/bootstrap-table.min.js"></script>
	<script src="js/gzzs/bootstrap-table-zh-CN.min.js"></script>
	<script src="js/gzzs/bootstrapValidator.min.js"></script>
	<script src="js/gzzs/jquery.goup.min.js"></script>
	<script src="js/gzzs/sweetalert2.min.js"></script>
	<script src="js/gzzs/app.js"></script>
	
	<script type="text/javascript">
		function aa(){
			$("#ddd").jqprint();
		}
		$(document).ready(function() {
			 <c:if test="${empty sessionScope.teaindexes}" var="result">
				 $('.loginshow').hide();
				 $('.loginnone').show();
			 </c:if>
			  <c:if test="${not empty sessionScope.teaindexes}" var="result">
			 	 $('.loginshow').show();
				$('.loginnone').hide();
			 </c:if>
			});
		
		function sumFormatter(data) {
		    field = this.field;
		    return data.reduce(function(sum, row) { 
		        return sum + (+row[field]);
		    }, 0);
		}
		function dateFormatter(value) {
			if(value!=null){
				var date = value.split("-")[0]+"/"+value.split("-")[1]+"/"+(value.split("-")[2]).split(" ")[0];
			    return date
			}
		} 
		
		function windowprint() {
			var headstr = "<html><head><title></title></head><body>";  
			var footstr = "</body>";
			var printDatad = document.getElementById("prints").innerHTML; 
			var oldstr = document.body.innerHTML;  
			document.body.innerHTML = headstr+printDatad+footstr; 
			window.print();  
			document.body.innerHTML = oldstr; 
			return false;
			
			/* var f = document.getElementById('print').innerHTML;
			window.print();
			location.reload([true]); */
		}
	</script>
	<%@include file="foot.jsp" %>
	<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px; display: none;" class="goup-container" title="">
		<div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
	</div>
	<div style="position: fixed; width: 40px; height: 40px; background: rgb(0, 0, 0); cursor: pointer; bottom: 300px; right: 2px; border-radius: 10px; display: none;" class="goup-container" title="">
		<div class="goup-arrow" style="width: 0px; height: 0px; margin: 0px auto; padding-top: 13px; border-style: solid; border-width: 0px 10px 10px; border-color: transparent transparent rgb(255, 255, 255);"></div>
	</div>
</body>
</html>