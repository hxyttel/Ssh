<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="train-toolbar">
				<div class="wu-toolbar-search">
					<label>学号：</label>
					<input class="wu-text" id="likeStuno" name="student.stuno" style="width:100px">
					<label>学生姓名：</label>
					<input class="wu-text" id="likeSname" name="student.sname" style="width:100px">
					<label>联系电话：</label>
					<input class="wu-text" id="likeSphone" name="student.sphone" style="width:100px">
					<label>老师姓名：</label>
					<input class="wu-text" id="likeTname" name="student.tname" style="width:100px">
					<label>报名类型：</label>
					<input class="wu-text" id="likeStype" name="student.stype" style="width:100px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="train-datagrid" toolbar="#train-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="trainDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
	<!-- 表格内容 -->
	<div id="train-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
        <s:form method="post" id="train-form">
        	<s:hidden name="student.sid" id="sid" />
        	<s:hidden name="student.festate" id="festate" />
        	<s:textfield name="student.stuno" id="stuno" label="学号" style="width:140px;height:25px"></s:textfield>
			<s:textfield name="student.sname" id="sname" label="学生姓名" style="width:140px;height:25px"></s:textfield>
			<s:textfield name="student.sphone" id="sphone" label="联系电话" style="width:140px;height:25px"></s:textfield>
			<%-- <s:textfield name="student.stype" id="stype" label="报名类型"></s:textfield> --%>
			<s:hidden name="student.stype" id="stype" />
			<s:textfield name="student.acontent" id="acontent" label="学习内容" style="width:140px;height:25px"></s:textfield>
<%-- 			<s:textfield name="student.sdate" id="sdate" label="报名日期" class="easyui-datetimebox" style="width:145px;height:30px;"></s:textfield>
 --%>		创建时间:
			<input type="text" class="easyui-datetimebox" style="width:145px;height:30px;" name="student.sdate" id="sdate"/>
 			<br><br>
			老师:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="student.teacherid" id="teacherid" style="width:140px;height:25px">
				<s:iterator value="listTeacher">
					<option value="<s:property value="tid"/>"><s:property value="tname"/></option>
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
		$('#train-datagrid').datagrid({
			url:'/Enrollment/listTrain.action',
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
				{field:'tname',title:'老师姓名',width:20,sortable:true},
				{field:'sphone',title:'联系电话',width:20,sortable:true},
				{field:'stype',title:'报名类型',width:20,sortable:true},
				{field:'acontent',title:'学习内容',width:40,sortable:true},
				{field:'sdate',title:'报名日期',width:50,sortable:true},
				{field:'_operate',title:'操作',width:30,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#train-datagrid').datagrid('getSelected');
		var sid =item.sid;
		$('#trainDele').dialog({
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
                                $('#train-datagrid').datagrid('load');
                                $('#trainDele').dialog('close');
							}
							else
							{
								$.messager.alert('信息提示','删除失败！','info');
								$('#train-datagrid').datagrid('load');
                                $('#trainDele').dialog('close');		
							}
						}	
					});
                }
	        }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#trainDele').dialog('close');                    
                }
	        }] 
		}); 
	}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#train-datagrid').datagrid('getSelected');
		var sid =item.sid;
		$.ajax({
			url:'/Enrollment/findArtStudent.action',
			data:{'student.sid':sid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					$("#sid").val(data.sid);
					$("#teacherid").val(data.teacherid);
					$("#stuno").val(data.stuno);
					$("#sname").val(data.sname);
					$("#sphone").val(data.sphone);
					$("#stype").val(data.stype);
					$("#acontent").val(data.acontent);
					$("#sdate").val(data.sdate);
					$("#festate").val(data.festate);
					$('#train-form').form('load',data);
				}else{
					$('#train-dialog').dialog('close');
				}
			}	
		});
		$('#train-dialog').dialog({
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
                    $('#train-dialog').dialog('close');                    
                }
            }]
        });
	}	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#train-form').form('submit', {
			url:'/Enrollment/updateArt.action',
			success:function(fail){
				if(fail!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#train-datagrid').datagrid('load');
					$('#train-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#train-datagrid').datagrid('load');
					$('#train-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
		模糊查询
	*/
	function startSelect(){
		var stuno = $('#likeStuno').val();
		var sname = $('#likeSname').val();
		var sphone = $('#likeSphone').val();
		var tname = $('#likeTname').val();
		var stype = $('#likeStype').val();
		$('#train-datagrid').datagrid({
			url:'/Enrollment/likeTrain.action?student.stuno='+stuno+'&student.sname='+sname+'&student.sphone='+sphone+'&student.tname='+tname+'&student.stype='+stype,
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
				{field:'tname',title:'老师姓名',width:20,sortable:true},
				{field:'sphone',title:'联系电话',width:20,sortable:true},
				{field:'stype',title:'报名类型',width:20,sortable:true},
				{field:'acontent',title:'学习内容',width:50,sortable:true},
				{field:'sdate',title:'报名日期',width:50,sortable:true},
				{field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
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