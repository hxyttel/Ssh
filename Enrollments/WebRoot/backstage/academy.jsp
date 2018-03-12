<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="ac-toolbar">
					 <div class="wu-toolbar-button">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				  	 </div>
					<div class="wu-toolbar-search">
						<label>院校名称：</label>
						<input class="wu-text" id="likeAname" name="academy.aname" style="width:100px">
						<label>隶属类型：</label>
						<input class="wu-text" id="likeAmajor" name="academy.amajor" style="width:100px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
					</div>
				</div>
			<!-- End of toolbar -->
			<table id="ac-datagrid" toolbar="#ac-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="academyDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
	<!-- 表格内容 -->
	<div id="ac-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
        <s:form method="post" id="ac-form">
        	<s:hidden name="academy.aid" id="aid" />
			<s:textfield name="academy.aname" id="aname" label="院校名称"></s:textfield> 		
 			隶属种类:&nbsp;
 			<select name="academy.amajor" id="amajor" style="width:140px;height:25px">
				<option value="成人教育">成人教育</option>
				<option value="国家开放大学">国家开放大学</option>
			</select>
 		</s:form> 
	</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#ac-datagrid').datagrid({
			url:'/Enrollment/listAcademy.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:4,
			pageList:[4,8,12],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'aname',title:'院校名称',width:20,sortable:true},
				{ field:'amajor',title:'隶属种类',width:50,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){	
			$('#ac-form').form('submit', {
				url:'/Enrollment/saveAcademy.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
						$('#ac-datagrid').datagrid('load');
						$('#ac-dialog').dialog('close');
					}else{
						$.messager.alert('信息提示','提交失败！','info');
						$('#ac-datagrid').datagrid('load');
						$('#ac-dialog').dialog('close');
					}
				}
			});
		}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#ac-datagrid').datagrid('getSelected');
		var aid =item.aid;
		$('#academyDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/deleteAcademy.action',
							data:{'id':aid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#ac-datagrid').datagrid('load');
									$('#ac-dialog').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');	
									$('#ac-datagrid').datagrid('load');
									$('#ac-dialog').dialog('close');	
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#academyDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#ac-form').form('clear');
			$('#ac-dialog').dialog({
				closed: false,
				modal:true,
	            title: "院校管理",
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
                    $('#ac-dialog').dialog('close');                    
                }
            }]
	        });
		}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#ac-datagrid').datagrid('getSelected');
		var aid =item.aid;
		$.ajax({
			url:'/Enrollment/findAcademy.action',
			data:{'id':aid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("aid").value=data.aid;
					document.getElementById("amajor").value=data.amajor;
					document.getElementById("aname").value=data.aname;
					$('#ac-form').form('load',data);
				}else{
					$('#ac-dialog').dialog('close');
				}
			}	
		});
		$('#ac-dialog').dialog({
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
                    $('#ac-dialog').dialog('close');                    
                }
            }]
        });
	}	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#ac-form').form('submit', {
			url:'/Enrollment/updateAcademy.action',
			success:function(data){
				if(data!={}){
					$.messager.alert('信息提示','提交成功！','info');
					$('#ac-datagrid').datagrid('load');
					$('#ac-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#ac-datagrid').datagrid('load');
					$('#ac-dialog').dialog('close');
				}
			}
		});
	}
	/**
		模糊查询
	*/
	function startSelect(){
		var aname = $('#likeAname').val();
		var amajor = $('#likeAmajor').val();
		$('#ac-datagrid').datagrid({
			url:'/Enrollment/likeAcademy.action?academy.aname='+aname+'&academy.amajor='+amajor,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:4,
			pageList:[4,8,12],            
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'aname',title:'院校名称',width:20,sortable:true},
				{ field:'amajor',title:'隶属种类',width:50,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
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