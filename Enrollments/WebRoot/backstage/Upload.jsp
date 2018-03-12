<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

 <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<div id="stufile-toolbar">
				<div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	</div>
				<div class="wu-toolbar-search">
					<label>学生姓名：</label>
					<input class="wu-text" id='likeSname' name="sname" style="width:100px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="stufile-datagrid" toolbar="#stufile-toolbar"></table>
			<!-- 删除框 -->
			<div id="stufileDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="stufile-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
              	<form  id="stufile-form" method="post" enctype="multipart/form-data" >
               		 <input type="hidden" name="sid" id="sid" />
               		 <input type="hidden" name="sfid" id="sfid" />
					上传相片:<input type="file" name="sfphoto" id="sfphoto" />
				<br><br>
					缴费凭证: <input type="file" name="sfvoucher" id="sfvoucher"  />
				<br><br>
					上传时间: <input type="text" name="sfdate" id="sfdate" class="easyui-datetimebox" style="width:145px;height:30px;" />
				<br><br>
					 上传论文:<input type="file" name="sfpaper" id="sfpaper" />
				 <br><br>
			</form>

			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#stufile-datagrid').datagrid({
			url:'/Enrollment/ListUpload.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:10,
			pageList:[10,20,30],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'sname',title:'学生姓名',width:15},
				{ field:'sfphoto',title:'上传相片',width:35,sortable:true},
				{ field:'sfvoucher',title:'缴费凭证',width:35,sortable:true},
				{ field:'sfpaper',title:'上传论文',width:35},
				{ field:'sfdate',title:'上传时间',width:20},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		//模糊查询
		function startSelect(){
			var sname = $('#likeSname').val();
			$('#stufile-datagrid').datagrid({
				url:'/Enrollment/likeUpload.action?sname='+sname,
				loadFilter:pagerFilter,		
				rownumbers:true,
				singleSelect:false,
				pageSize:10,
				pageList:[10,20,30],           
				pagination:true,
				multiSort:true,
				fitColumns:true,
				fit:true,
				columns:[[
					{ field:'sname',title:'学生姓名',width:15},
					{ field:'sfphoto',title:'上传相片',width:35,sortable:true},
					{ field:'sfvoucher',title:'缴费凭证',width:35,sortable:true},
					{ field:'sfpaper',title:'上传论文',width:35},
					{ field:'sfdate',title:'上传时间',width:20},
					{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
				]]
			});
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#stufile-form').form('submit', {
				url:'/Enrollment/AddUpload.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#stufile-datagrid').datagrid('load');
						$('#stufile-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#stufile-datagrid').datagrid('load');
						$('#stufile-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#stufile-form').form('submit', {
			url:'/Enrollment/UpdateUpload.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
                    $('#stufile-datagrid').datagrid('load');
					$('#stufile-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#stufile-datagrid').datagrid('load');
					$('#stufile-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#stufile-datagrid').datagrid('getSelected');
		var sfid =item.sfid;
		$('#stufileDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteUpload.action',
							data:{'id':sfid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
                                    $('#stufile-datagrid').datagrid('load');
                                    $('#stufileDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#stufileDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#stufile-form').form('clear');
			$('#stufile-dialog').dialog({
				closed: false,
				modal:true,
	            title: "上传文件",
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
                    $('#stufile-dialog').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#stufile-datagrid').datagrid('getSelected');
		var sfid =item.sfid;
		 $.ajax({
			url:'/Enrollment/FindUpload.action',
			data:{'id':sfid},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("sfid").value=data.sfid;
					document.getElementById("sid").value=data.sid;
					document.getElementById("sfpaper").value=data.sfpaper;
					document.getElementById("sfvoucher").value=data.sfvoucher;
					document.getElementById("sfphoto").value=data.sfphoto;
					document.getElementById("sfdate").value=data.sfdate;
					$('#stufile-form').form('load',data);
				}
				else{
					$('#stufile-dialog').dialog('close');
				}
			}	
		}); 
		$('#stufile-dialog').dialog({
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
                    $('#stufile-dialog').dialog('close');                    
                }
            }]
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