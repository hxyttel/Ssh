<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="log-toolbar">
					<div class="wu-toolbar-search">
						<label>用户名：</label>
						<input class="wu-text" id="likeName" name="logf.username" style="width:100px">
						<label>状态：</label>
						<input class="wu-text" id="likeMethod" name="logf.method" style="width:100px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
					</div>
			</div>
			<!-- End of toolbar -->
			<table id="log-datagrid" toolbar="#log-toolbar"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="logDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
  
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#log-datagrid').datagrid({
			url:'/Enrollment/GetLogfList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60,80],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'userid',title:'用户id',width:20,sortable:true},
				{ field:'username',title:'用户名字',width:50,sortable:true},
				{ field:'ip',title:'ip',width:50,sortable:true},
				{ field:'logtime',title:'登录时间',width:50,sortable:true},
				{ field:'method',title:'登录状态',width:50,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#log-datagrid').datagrid('getSelected');
		var lid =item.lid;
		$('#logDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteLogf.action',
							data:{'id':lid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#log-datagrid').datagrid('load');
									$('#logDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#log-datagrid').datagrid('load');
									$('#logDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#logDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	/**
	模糊查询
	*/
	function startSelect(){
		var name = $('#likeName').val();
		var method = $('#likeMethod').val();
		$('#log-datagrid').datagrid({
			url:'/Enrollment/likeLogf.action?logf.username='+name+'&logf.method='+method,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60,80],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'userid',title:'用户id',width:20,sortable:true},
				{ field:'username',title:'用户名字',width:50,sortable:true},
				{ field:'ip',title:'ip',width:50,sortable:true},
				{ field:'logtime',title:'登录时间',width:50,sortable:true},
				{ field:'method',title:'登录状态',width:50,sortable:true},
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