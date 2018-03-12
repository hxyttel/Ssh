<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="depart-toolbar">
				 <div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	 </div>
			  	 <div class="wu-toolbar-search">
					<label>部门编号：</label>
					<input class="wu-text" id="likeDnumber" name="department.dnumber" style="width:100px">
					<label>部门名称：</label>
					<input class="wu-text" id="likeDname" name="department.dname" style="width:100px">
					<label>部门地址：</label>
					<input class="wu-text" id="likeDaddress" name="department.daddress" style="width:100px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="depart-datagrid" toolbar="#depart-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="depaetDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="depart-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <s:form  method="post"  id="depart-form">
                	<input type="hidden" name="department.did" id="did" />
                	部门编号:<input type="text" name="department.dnumber" id="dnumber" style="width:140px;height:25px;" />
                	<br><br>
					部门名称:<input type="text" name="department.dname" id="dname" style="width:140px;height:25px" />
					<br><br>
					<!-- <font id="ctimes" style="display:none">创建时间:</font><input type="text" name="department.ddatetime" id="ddatetime"  style="width:140px;height:25px;display:none" />
					<br style="display:none" id="a"><br> -->
					创建时间:
					<input type="text" class="easyui-datetimebox" style="width:145px;height:30px;" name="department.ddatetime" id="ddatetime"/>
	   				<br><br>
					部门地址:<input type="text" name="department.daddress" id="daddress" style="width:140px;height:25px" />
					
				</s:form>  
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#depart-datagrid').datagrid({
			url:'/Enrollment/ListDepartment.action',
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
				{field:'dnumber',title:'部门编号',width:20,sortable:true},
				{ field:'dname',title:'部门名称',width:15,sortable:true},
				{ field:'ddatetime',title:'创建时间',width:60},
				{ field:'daddress',title:'部门地址',width:30},
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
			$('#depart-form').form('submit', {
				url:'/Enrollment/AddDepartment.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
						$('#depart-datagrid').datagrid('load');
						$('#depart-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#depart-datagrid').datagrid('load');
						$('#depart-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#depart-form').form('submit', {
			url:'/Enrollment/UpdateDepartment.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#depart-datagrid').datagrid('load');
					$('#depart-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#depart-datagrid').datagrid('load');
					$('#depart-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#depart-datagrid').datagrid('getSelected');
		var did =item.did;
		$('#depaetDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteDepartment.action',
							data:{'id':did},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#depart-datagrid').datagrid('load');
                                    $('#depaetDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');	
									$('#depart-datagrid').datagrid('load');
                                    $('#depaetDele').dialog('close');	
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#depaetDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#depart-form').form('clear');
			$('#depart-dialog').dialog({
				closed: false,
				modal:true,
	            title: "部门管理",
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
                    $('#depart-dialog').dialog('close');                    
                }
            }] 
	        });
		}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#depart-datagrid').datagrid('getSelected');
		/* $("#ddatetime").show();
		$("#ctimes").show();
		$("#a").show(); */
		 var did =item.did;
		 $.ajax({
			url:'/Enrollment/FindDepartment.action',
			data:{'id':did},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("did").value=data.did;
					document.getElementById("dnumber").value=data.dnumber;
					document.getElementById("dname").value=data.dname;
					document.getElementById("ddatetime").value=data.ddatetime;
					document.getElementById("daddress").value=data.daddress;
					$('#depart-form').form('load',data);
				}
				else{
					$('#depart-dialog').dialog('close');
				}
			}	
		}); 
		$('#depart-dialog').dialog({
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
                    $('#depart-dialog').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
		模糊查询
	*/
	function startSelect(){
		var number = $('#likeDnumber').val();
		var name = $('#likeDname').val();
		var address = $('#likeDaddress').val();
		$('#depart-datagrid').datagrid({
			url:'/Enrollment/likeDepartment.action?department.dnumber='+number+'&department.dname='+name+'&department.daddress='+address,
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
				{field:'dnumber',title:'部门编号',width:20,sortable:true},
				{ field:'dname',title:'部门名称',width:15,sortable:true},
				{ field:'ddatetime',title:'创建时间',width:60},
				{ field:'daddress',title:'部门地址',width:30},
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