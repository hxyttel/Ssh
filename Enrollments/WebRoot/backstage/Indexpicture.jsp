<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="wu-toolbar">
				 <div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	 </div>
			</div>
			<!-- End of toolbar -->
			<table id="wu-datagrid" toolbar="#wu-toolbar"></table>
			<div id="pictureDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="wu-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				 <form method="post" action="/Enrollment/AddIndexpicture.action" enctype="multipart/form-data" id="wu-form">
				 	 <input type="hidden" name="pid" id="pid" > 
					图片路径:<input type="file"  name="myFile" id="ppicture" />
					<br><br>
					图片标题:<input type="text"  name="ptitle"   id="ptitle"/>
					<br><br>
					图片内容:<textarea  name="pcontent" id="pcontent" ></textarea>
				<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#wu-datagrid').datagrid({
			url:'/Enrollment/ListIndexpicture.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'ppicture',title:'图片',width:20,sortable:true},
				{ field:'ptitle',title:'图片标题',width:15,sortable:true},
				{ field:'pcontent',title:'图片内容',width:60},
				{ field:'_pictuer',title:'操作',width:20,align:'center',formatter:formatPicture},
			]]
		});
		function formatPicture(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#wu-form').form('submit', {
				url:'/Enrollment/AddIndexpicture.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#wu-datagrid').datagrid('load');
						$('#wu-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#wu-datagrid').datagrid('load');
						$('#wu-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#wu-form').form('submit', {
			url:'/Enrollment/UpdatePicture.action',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#wu-datagrid').datagrid('load');
					$('#wu-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#wu-datagrid').datagrid('load');
					$('#wu-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#wu-datagrid').datagrid('getSelected');
		var pid =item.pid;
		$('#pictureDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeletePicture.action',
							data:{'id':pid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
                                    $('#wu-datagrid').datagrid('load');
                                    $('#pictureDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#wu-datagrid').datagrid('load');
                                    $('#pictureDele').dialog('close');		
								}
					}	
				});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#pictureDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#wu-form').form('clear');
			$('#wu-dialog').dialog({
				closed: false,
				modal:true,
	            title: "轮播图",
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
                    $('#wu-dialog').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#wu-datagrid').datagrid('getSelected');
		 var pid =item.pid;
		 $.ajax({
			url:'/Enrollment/FindPicture.action',
			data:{'id':pid},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("pid").value=data.pid;
					document.getElementById("ptitle").value=data.ptitle;
					document.getElementById("pcontent").value=data.pcontent;
					document.getElementById("ppicture").value=data.ppicture;
					$('#wu-form').form('load',data);
				}
				else{
					$('#wu-dialog').dialog('close');
				}
			}	
		}); 
		$('#wu-dialog').dialog({
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
                    $('#wu-dialog').dialog('close');                    
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