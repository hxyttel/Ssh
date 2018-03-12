<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="joinwork-toolbar">
				<div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	</div>
				<div class="wu-toolbar-search">
					<label>标题：</label>
					<input class="wu-text" id='likeTitle' name="jwtitle" style="width:100px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="joinwork-datagrid" toolbar="#joinwork-toolbar"></table>
			<!-- 删除信息 -->
			<div id="deleteJoinwork" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="joinwork-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				 <form method="post"  enctype="multipart/form-data" id="joinwork-form" >
				 	<input type="hidden" name="jwid" id="jwid" > 
				 	 标题:
					<input type="text"  name="jwtitle"  id="jwtitle"/>
					<br><br>
				 	 图片:
				 	<input type="file"  name="jwpicture" id="jwpicture" />
				 	<br><br>
					 学校介绍:
					<textarea name="jwcontent" id="jwcontent" ></textarea>
				 	<br><br>
				<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#joinwork-datagrid').datagrid({
			url:'/Enrollment/ListJoinwork.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
			    {field:'jwtitle',title:'标题',width:18,sortable:true},
			    {field:'jwpicture',title:'图片',width:28,sortable:true},
			    {field:'jwcontent',title:'内容',width:60},
			    {field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
			模糊查询
		*/		
		function startSelect(){
			var title = $('#likeTitle').val();
			$('#joinwork-datagrid').datagrid({
				url:'/Enrollment/likeJoinworks.action?jwtitle='+title,
				loadFilter:pagerFilter,		
				rownumbers:true,
				singleSelect:false,
				pageSize:1,           
				pagination:true,
				multiSort:true,
				fitColumns:true,
				fit:true,
				columns:[[
				    {field:'jwtitle',title:'标题',width:18,sortable:true},
				    {field:'jwpicture',title:'图片',width:28,sortable:true},
				    {field:'jwcontent',title:'内容',width:60},
				    {field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
				]]
			});
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#joinwork-form').form('submit', {
				url:'/Enrollment/Addjoinwork.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
						$('#joinwork-datagrid').datagrid('load');
						$('#joinwork-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#joinwork-datagrid').datagrid('load');
						$('#joinwork-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#joinwork-form').form('submit', {
			url:'/Enrollment/updateJoinwork.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#joinwork-datagrid').datagrid('load');
					$('#joinwork-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#joinwork-datagrid').datagrid('load');
					$('#joinwork-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#joinwork-datagrid').datagrid('getSelected');
		var jwid =item.jwid;
		alert(jwid);
		$('#deleteJoinwork').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteJoinwork.action',
							data:{'jwid':jwid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#joinwork-datagrid').datagrid('load');
									$('#deleteJoinwork').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#joinwork-datagrid').datagrid('load');
									$('#deleteJoinwork').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#deleteJoinwork').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#joinwork-form').form('clear');
			$('#joinwork-dialog').dialog({
				closed: false,
				modal:true,
	            title: "共享合作",
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
                    $('#joinwork-dialog').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#joinwork-datagrid').datagrid('getSelected');
		var jwid =item.jwid;
		 $.ajax({
			url:'/Enrollment/FindJoinwork.action',
			data:{'jwid':jwid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					$("#jwid").val(data.jwid);
					$("#jwtitle").val(data.jwtitle);
					$("#jwcontent").val(data.jwcontent);
					$('#joinwork-form').form('load',data);
				}
				else{
					$('#joinwork-dialog').dialog('close');
				}
			}	
		}); 
		$('#joinwork-dialog').dialog({
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
                    $('#joinwork-dialog').dialog('close');                    
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