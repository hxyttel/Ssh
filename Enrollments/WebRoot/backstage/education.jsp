<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="edu-toolbar">
				<div class="wu-toolbar-search">
					<label>学号：</label>
					<input class="wu-text" id="likeStunos" name="student.stuno" style="width:50px">
					<label>学生：</label>
					<input class="wu-text" id="likeSnames" name="student.sname" style="width:50px">
					<label>电话：</label>
					<input class="wu-text" id="likeSphones" name="student.sphone" style="width:70px">
					<label>老师：</label>
					<input class="wu-text" id="likeTname" name="student.tname" style="width:50px">
					<label>报名类型：</label>
					<input class="wu-text" id="likeStype" name="student.stype" style="width:50px">
					<label>身份证：</label>
					<input class="wu-text" id="likeSnumber" name="student.snumber" style="width:100px">
					<label>学制：</label>
					<input class="wu-text" id="likeSsystem" name="student.ssystem" style="width:50px">
					<label>层次：</label>
					<input class="wu-text" id="likeSgradations" name="student.sgradations" style="width:50px">
					<label>院校：</label>
					<input class="wu-text" id="likeAname" name="student.aname" style="width:50px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="edu-datagrid" toolbar="#edu-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="educationDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
	<!-- 表格内容 -->
	<div id="edu-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
        <s:form method="post" id="edu-form">
        	<s:hidden name="student.sid" id="sid" />
        	<s:textfield name="student.stuno" id="stunos" label="学号" style="width:140px;height:25px"></s:textfield>
			<s:textfield name="student.sname" id="snames" label="学生姓名" style="width:140px;height:25px"></s:textfield>
			<s:textfield name="student.sphone" id="sphones" label="联系电话" style="width:140px;height:25px"></s:textfield>
        	<s:hidden name="student.festate" id="festate"></s:hidden>
			<s:hidden name="student.stype" id="stype" />
			<s:textfield name="student.snumber" id="snumber" label="身份证号" style="width:140px;height:25px"></s:textfield>
			<s:hidden name="student.ssystem" id="ssystem" />
			<s:textfield name="student.acontent" id="acontents" label="学习内容" style="width:140px;height:25px"></s:textfield>
<%-- 			<s:textfield name="student.sdate" id="sdates" label="报名日期" class="easyui-datetimebox" style="width:145px;height:30px;"></s:textfield>
 --%>		创建时间:
			<input type="text" class="easyui-datetimebox" style="width:145px;height:30px;" name="student.sdate" id="sdates"/>
 			<br><br>
			报考层次:
			<select id="sgradations" name="student.sgradations" style="width:140px;height:25px"> 
				<option value="专达本">专达本</option>
				<option value="高达专">高达专</option>
				<option value="高达本">高达本</option>
			</select>
			<br><br>
			老师:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="student.teacherid" id="teacherid" style="width:140px;height:25px">
				<s:iterator value="listTeacher">
					<option value="<s:property value="tid"/>"><s:property value="tname"/></option>
				</s:iterator>
			</select>
			<br><br>
			院校:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="student.academyid" id="academyid" style="width:140px;height:25px">
				<s:iterator value="listAcademy">
					<option value="<s:property value="aid"/>"><s:property value="aname"/></option>
				</s:iterator>
			</select>
			<br><br>
		</s:form> 
	</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#edu-datagrid').datagrid({
			url:'/Enrollment/educationList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'stuno',title:'学号',width:20,sortable:true},
				{field:'sname',title:'学生姓名',width:15,sortable:true},
				{field:'snumber',title:'身份证号',width:40,sortable:true},
				{field:'sphone',title:'联系电话',width:30,sortable:true},
				{field:'aname',title:'院校名称',width:20,sortable:true},
				{field:'tname',title:'老师姓名',width:20,sortable:true},
				{field:'sgradations',title:'报考层次',width:20,sortable:true},
				{field:'ssystem',title:'学制',width:20,sortable:true},
				{field:'stype',title:'报名类型',width:30,sortable:true},
				{field:'acontent',title:'学习内容',width:30,sortable:true},
				{field:'sdate',title:'报名日期',width:50,sortable:true},
				{field:'_operate',title:'操作',width:40,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#edu-datagrid').datagrid('getSelected');
		var sid =item.sid;
		$('#educationDele').dialog({
			closed: false,
			modal:true,
            title: "删除框",
         	closed: false,
           	buttons: [{
            	text: '确定',
                iconCls: 'icon-ok',
                handler: function(){
               		$.ajax({
						url:'/Enrollment/deleteStudent.action',
						data:{'student.sid':sid},
						dataType:'json',
						success:function(fail){
							if(fail!="{}"){
								$.messager.alert('信息提示','删除成功！','info');
								$('#edu-datagrid').datagrid('load');
								$('#educationDele').dialog('close');
                            }
							else
							{
								$.messager.alert('信息提示','删除失败！','info');	
								$('#edu-datagrid').datagrid('load');
								$('#educationDele').dialog('close');	
							}
						}	
					});
                }
	        }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#educationDele').dialog('close');                    
                }
	        }] 
		}); 
	}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#edu-datagrid').datagrid('getSelected');
		var sid =item.sid;
		$.ajax({
			url:'/Enrollment/findEducation.action',
			data:{'student.sid':sid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					$("#sid").val(data.sid);
					$("#teacherid").val(data.teacherid);
					$("#stunos").val(data.stuno);
					$("#snames").val(data.sname);
					$("#sphones").val(data.sphone);
					$("#snumber").val(data.snumber);
					$("#sgradations").val(data.sgradations);
					$("#academyid").val(data.academyid);
					$("#ssystem").val(data.ssystem);
					$("#stype").val(data.stype);
					$("#acontents").val(data.acontent);
					$("#sdates").val(data.sdate);
					$("#festate").val(data.festate);
					$('#edu-form').form('load',data);
				}else{
					$('#edu-dialog').dialog('close');
				}
			}	
		});
		$('#edu-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edu-dialog').dialog('close');                    
                }
            }]
        });
	}	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#edu-form').form('submit', {
			url:'/Enrollment/updateEducation.action',
			success:function(fail){
				if(fail!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#edu-datagrid').datagrid('load');
					$('#edu-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#edu-datagrid').datagrid('load');
					$('#edu-dialog').dialog('close');
				}
			}
		});
		
	}
	
	/**
		模糊查询
	*/
	function startSelect(){
		var stuno = $('#likeStunos').val();
		var sname = $('#likeSnames').val();
		var sphone = $('#likeSphones').val();
		var tname = $('#likeTname').val();
		var stype = $('#likeStype').val();
		var snumber = $('#likeSnumber').val();
		var ssytem = $('#likeSsystem').val();
		var sgradations = $('#likeSgradations').val();
		var aname = $('#likeAname').val();
		$('#edu-datagrid').datagrid({
			url:'/Enrollment/likeEducation.action?student.stuno='+stuno+'&student.sname='+sname+'&student.sphone='+sphone+'&student.tname='+tname+'&student.stype='+stype+'&student.snumber='+snumber+'&student.ssystem='+ssytem+'&student.sgradations='+sgradations+'&student.aname='+aname,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'stuno',title:'学号',width:20,sortable:true},
				{field:'sname',title:'学生姓名',width:20,sortable:true},
				{field:'snumber',title:'身份证号',width:40,sortable:true},
				{field:'sphone',title:'联系电话',width:30,sortable:true},
				{field:'aname',title:'院校名称',width:20,sortable:true},
				{field:'tname',title:'老师姓名',width:20,sortable:true},
				{field:'sgradations',title:'报考层次',width:20,sortable:true},
				{field:'ssystem',title:'学制',width:20,sortable:true},
				{field:'stype',title:'报名类型',width:30,sortable:true},
				{field:'acontent',title:'学习内容',width:50,sortable:true},
				{field:'sdate',title:'报名日期',width:50,sortable:true},
				{field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
	}
	
	
	//导出
	function btnExport(){
		var data = JSON.stringify($('#edu-datagrid').datagrid('getData').rows);
			alert(data);
			if (data == '')
				return;
			JSONToCSVConvertor(data, "Download", true);
		}
		
		/**
		* Name 分页过滤器
		*/
		function pagerFilter(data){            
			if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
				data = {                   
					total: data.length,                   
					rows: data               
				}            
			}        
			var dg = $(this);         
			var opts = dg.datagrid('options');          
			var pager = dg.datagrid('getPager');          
			pager.pagination({                
				onSelectPage:function(pageNum, pageSize){                 
					opts.pageNumber = pageNum;                   
					opts.pageSize = pageSize;                
					pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
					dg.datagrid('loadData',data);                
				}          
			});           
			if (!data.originalRows){               
				data.originalRows = (data.rows);       
			}         
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
			var end = start + parseInt(opts.pageSize);        
			data.rows = (data.originalRows.slice(start, end));         
			return data;       
		}
	</script>