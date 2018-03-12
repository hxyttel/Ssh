<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="ma-toolbar">
				 <div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	 </div>
			</div>
			<!-- End of toolbar -->
			<table id="ma-datagrid" toolbar="#ma-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="majorDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
	<!-- 表格内容 -->
	<div id="ma-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
        <s:form method="post" id="ma-form">
        	<s:hidden name="major.mrid" id="mrid" />
        	<s:textfield name="major.mrname" id="mrname" label="专业名称"></s:textfield>
			院校名称:&nbsp;
			<select name="major.academyid" id="academyid" style="width:140px;height:25px">
				<s:iterator value="list">
					<option value="<s:property value="aid"/>"><s:property value="aname"/></option>
				</s:iterator>
			</select>
		</s:form> 
	</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#ma-datagrid').datagrid({
			url:'/Enrollment/listMajor.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'mrname',title:'专业名称',width:20,sortable:true},
				{field:'aname',title:'院校名称',width:20,sortable:true},
				{field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){	
			$('#ma-form').form('submit', {
				url:'/Enrollment/saveMajor.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#ma-datagrid').datagrid('load');
						$('#ma-dialog').dialog('close');
					}else{
						$.messager.alert('信息提示','提交失败！','info');
						$('#ma-datagrid').datagrid('load');
						$('#ma-dialog').dialog('close');
					}
				}
			});
		}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#ma-datagrid').datagrid('getSelected');
		var mrid =item.mrid;
		$('#majorDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/deleteMajor.action',
							data:{'major.mrid':mrid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#ma-datagrid').datagrid('load');
                                    $('#majorDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#ma-datagrid').datagrid('load');
                                    $('#majorDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#majorDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#ma-form').form('clear');
			$('#ma-dialog').dialog({
				closed: false,
				modal:true,
	            title: "专业管理",
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
                    $('#ma-dialog').dialog('close');                    
                }
            }]
	        });
		}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#ma-datagrid').datagrid('getSelected');
		var mrid =item.mrid;
		$.ajax({
			url:'/Enrollment/findMajor.action',
			data:{'major.mrid':mrid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("mrid").value=data.mrid;
					document.getElementById("mrname").value=data.mrname;
					document.getElementById("academyid").value=data.academyid;
					$('#ma-form').form('load',data);
				}else{
					$('#ma-dialog').dialog('close');
				}
			}	
		});
		$('#ma-dialog').dialog({
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
                    $('#ma-dialog').dialog('close');                    
                }
            }]
        });
	}	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#ma-form').form('submit', {
			url:'/Enrollment/updateMajor.action',
			success:function(data){
				if(data!={}){
					$.messager.alert('信息提示','提交成功！','info');
                    $('#ma-datagrid').datagrid('load');
					$('#ma-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#ma-datagrid').datagrid('load');
					$('#ma-dialog').dialog('close');
				}
			}
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