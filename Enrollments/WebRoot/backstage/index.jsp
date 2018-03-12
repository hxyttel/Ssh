<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
	<title>后台管理-首页</title>

	<!-- 设置图标 -->
	 <link rel="shortcut icon" href="/Enrollment/img/icons/favicon1.ico">
	
	<script type="text/javascript" src="/Enrollment/js/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/Enrollment/backstage/easyui/1.3.4/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/Enrollment/backstage/css/wu.css" />
	<link rel="stylesheet" type="text/css" href="/Enrollment/backstage/css/icon.css" />
	
	<script type="text/javascript" src="/Enrollment/backstage/import/excel.js"></script>
	<script type="text/javascript" src="/Enrollment/backstage/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="/Enrollment/backstage/easyui/1.3.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/Enrollment/backstage/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	/**
		* Name 打开添加窗口
		*/
		function openEdit(){
			$('#pwd-form').form('clear');
			$('#pwd-dialog-0').dialog({
				closed: false,
				modal:true,
	            title: "修改密码",
	         	closed: false,
				modal:true,
           		 title: "添加信息",
            	 buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#pwd-dialog-0').dialog('close');                    
                }
            }] 
	        });
		}
		//Enrollment/fiance.action?tid=<s:property value="%{teacher.tid}"/>
		function openTrue(){
			  $('#pwd-dialog-2').dialog({
				closed: false,
				modal:true,
	            title: "判断框",
	         	closed: false,
            	 buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $('#pwd-dialog-2').dialog('close');                    
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#pwd-dialog-2').dialog('close');                    
                }
            }] 
			}); 
		}
		function openPsd(){
			  $('#pwd-dialog-1').dialog({
				closed: false,
				modal:true,
	            title: "判断框",
	         	closed: false,
            	 buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $('#pwd-dialog-1').dialog('close');                    
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#pwd-dialog-1').dialog('close');                    
                }
            }] 
			}); 
		}
		function openFalse(){
			  $('#pwd-dialog-3').dialog({
				closed: false,
				modal:true,
	            title: "判断框",
	         	closed: false,
            	 buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    $('#pwd-dialog-3').dialog('close');                    
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#pwd-dialog-3').dialog('close');                    
                }
            }] 
			}); 
		}
		function openTF(){
			  $('#pwd-dialog-4').dialog({
				closed: false,
				modal:true,
	            title: "判断框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function () {
	                    $('#pwd-dialog-4').dialog('close');                    
	                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#pwd-dialog-4').dialog('close');                    
                }
            }] 
			}); 
		}
		function add(){
			var tid = document.getElementById("tid").value;
			var newpassword = document.getElementById("newpassword").value;
			var tphone = document.getElementById("tphone").value;
			$.ajax({
				url:'/Enrollment/UpdatePassword.action',
				data:{'id':tid,'newpassword':newpassword},
				dataType:'json',
				type:'post',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','修改成功！','info');
						$('#pwd-dialog-0').dialog('close');
						location.href="/Enrollment/adminLogin?tphone="+tphone;
					}
					else
					{
						$.messager.alert('信息提示','修改失败！','info');
					}
				}
			});
		}
		
		
		$(document).ready(function(){
			var tid = document.getElementById("tid").value;
			if(tid==1){
				$("#Power").show();
				$("#contact").show();
			}
			else{
				$("#Power").hide();
			}
		//密码做判断
			$("#oldpassword").blur(function(){
				var oldpassword =$('#oldpassword').val(); 
				alert(oldpassword);
				var tpassword = document.getElementById("tpassword").value;
				alert(tpassword);
				if(oldpassword==""||oldpassword.length==0){
					openTrue();
					$("#t_errorPsd").show();
					$("#t_rightPsd").hide();
				}else if(oldpassword!=tpassword){
					openPsd();
					$("#t_errorPsd").show();
					$("#t_rightPsd").hide();
				}
				else{
					$("#t_errorPsd").hide();
					$("#t_rightPsd").show();
				}
			});
			$("#newpassword").blur(function(){
				var newpassword = $("#newpassword").val();
				if(newpassword==""||newpassword.length==0){
					openTrue();
					$("#t_errornewpassword").show();
					$("#t_rightnewpassword").hide();
				}
				else if(newpassword.length<6||newpassword.length>18){
					openFalse();
					$("#t_errornewpassword").show();
					$("#t_rightnewpassword").hide();
				}
				else{
					$("#t_errornewpassword").hide();
					$("#t_rightnewpassword").show();
				}
			});
			$("#repassword").blur(function(){
				var newpassword = $("#newpassword").val();
				var repassword=$("#repassword").val();
				var tid = document.getElementById("tid").value;
				if(repassword==""||repassword.length==0){
					openTrue();
					$("#t_errorrepassword").show();
					$("#t_rightrepassword").hide();
				}
				else if(repassword!=newpassword){
						openTF();
						$("#t_errorrepassword").show();
						$("#t_rightrepassword").hide();
				}
				else{
					$("#t_errorrepassword").hide();
					$("#t_rightrepassword").show();
				}
			});
			
		});
		function selebyid(){
			location.href="/Enrollment/seleByjid.action";
		}
		
</script>
</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
		<a href="#"><img src="/Enrollment/img/logo1.png" alt="image alt text here" style="width:600px;height:50px;"/></a>
        </div>
        <div class="wu-header-right">
        	<p><strong class="easyui-tooltip" title="2条未读消息"><s:property value="%{teacher.tname}" /></strong>，欢迎您！</p>
            <p><a href="#" id="editpass" onClick="openEdit()">修改密码</a> <a href="#" onClick="loginOut()">安全退出</a></span>
        	<input type="hidden" id="tid" name="tid" value='<s:property value="%{teacher.tid}"/>' />
			<input type="hidden" id="tpassword" name="tpassword" value='<s:property value="%{teacher.tpassword}"/>' />
        	<input type="hidden" id="tphone" name="tphone" value='<s:property value="%{teacher.tphone}"/>'>
        	<input type="hidden" id="tname" name="tname" value='<s:property value="%{teacher.tname}"/>'>
        	<input type="hidden" id="jid" name="jid" value='<s:property value="%{list}"/>'>
        </div>
    </div>
    	<!-- 安全退出弹出框 -->
    	<div id="teachersession" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">您确定要退出本次登录吗?</div>
    	<!-- 修改密码的弹出框 -->
    	<div id="pwd-dialog-0" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
    		<div id="pwd-dialog-1" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">输入错误!</div>
			<div id="pwd-dialog-2" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">密码不能为空!</div>
			<div id="pwd-dialog-3" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">密码必须是6-18位!</div>
			<div id="pwd-dialog-4" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">密码输入不一致!</div>	
				<s:form  id="pwd-form" method="post">
					  <div>
						旧密码:<input type="password"  id="oldpassword" name="oldpassword" />
						 <span id="t_errorPsd" style="color: red;display: none"><b>X</b></span>
				       	 <span id="t_rightPsd" style="color: green;display:none"><b>√</b></span>
			       	   </div>
			       	 	<br>
				       	 <div>
							新密码:<input type="password" id="newpassword" name="newpassword"/>
							 <span id="t_errornewpassword" style="color: red;display: none"><b>X</b></span>
					       	 <span id="t_rightnewpassword" style="color: green;display:none"><b>√</b></span>
						</div>
						<br>
						<div>
						   	 确认密码:<input type="password" id="repassword" name="repassword"/>
						   	 <span id="t_errorrepassword" style="color: red;display: none"><b>X</b></span>
					       	 <span id="t_rightrepassword" style="color: green;display:none"><b>√</b></span>
						</div>
				</s:form>				
			</div>
    
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
        	<s:iterator value="%{listPJuisd}" id="pj" >
        		<div title="<s:property value="#pj.jtname" />" data-options="iconCls:'icon-application-cascade'" style="padding:5px;" >
        			<ul class="easyui-tree wu-side-tree">
       				<s:if test="#pj.jtid==1" >
       					<s:iterator value="%{listCJuise1}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==2">
       					<s:iterator value="%{listCJuise2}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==3">
       					<s:iterator value="%{listCJuise3}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if> 
       				 <s:if test="#pj.jtid==4">
       					<s:iterator value="%{listCJuise4}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" /><s:property value="%{teacher.tid}"/>" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==5">
       					<s:iterator value="%{listCJuise5}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==6">
       					<s:iterator value="%{listCJuise6}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==7">
       					<s:iterator value="%{listCJuise7}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==8">
       					<s:iterator value="%{listCJuise8}" id="cj">
       								<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if>
       				<s:if test="#pj.jtid==9">
       					<s:iterator value="%{listCJuise9}" id="cj">
       							<li iconCls="icon-user-group" style="display: none" id="juese"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="<s:property value="#cj.jturl" />" iframe="0" ><s:property value="#cj.jtname" /></a></li>
       					</s:iterator>
       				</s:if> 
        			</ul>
        		</div>  	
        	</s:iterator>
		</div>
        </div>
    <!-- end of sidebar -->    
    <!-- begin of main -->
    <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">  
             <div title="首页" data-options="" class="easyui-layout">
           		<div data-options="region:'center',split:true" >
           			
           			<div id="cc" class="easyui-calendar" style="width:900px;height:528px;"></div> 
           		</div>
				<div data-options="region:'east',split:true" style="width:300px">
					<h1><font color="red">工作提示：</font></h1>
					<% int i = 1; %>
					<s:iterator value="%{listWork}" id="lw">
						<p>&nbsp;&nbsp;&nbsp;&nbsp;<font color="black" size="4"><%=i%>&nbsp;.&nbsp;</font><font size="3"><s:property value="#lw.wa_content" /></font></p>
						<% i++;%>
					</s:iterator>
				</div>
            </div>
        </div>
    </div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy; 2017 海纳百川招生系统
    </div>
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		/**
		* Name 载入树形菜单 
		*/
		$('#wu-side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#wu-datagrid').datagrid('reload');
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
		function loginOut(){
           $('#teachersession').dialog({
				closed: false,
				modal:true,
	            title: "安全退出",
	         	closed: false,
				modal:true,
	           	title: "安全退出",
	           	buttons: [{
	               text: '确定',
	               iconCls: 'icon-ok',
	               handler:function(){
	               	 location.href = '/Enrollment/RemoveTeacher.action';
	                }
	           	}, {
	               text: '取消',
	               iconCls: 'icon-cancel',
	               handler: function () {
	                   $('#teachersession-dialog').dialog('close');                    
	            	}
           		}] 
       		});
            }
	</script>
</body>
</html>
