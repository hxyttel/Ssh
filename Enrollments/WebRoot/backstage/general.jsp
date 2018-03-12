<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="ge-toolbar">
					 <div class="wu-toolbar-button">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				  	 </div>
					<div class="wu-toolbar-search">
						<label>标题：</label>
						<input class="wu-text" id="likeGtitle" name="gtitle" style="width:100px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
					</div>
			</div>
			<!-- End of toolbar -->
			<table id="ge-datagrid" toolbar="#ge-toolbar"></table>
			<!-- 删除信息 -->
			<div id="generalDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="ge-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				 <form method="post"  enctype="multipart/form-data" id="ge-form" action="">
				 	<input type="hidden" name="gid" id="gid" > 
				 	 图片logo:
				 	<input type="file"  name="glogo" id="glogo" />
				 	<br><br>
				 	 介绍标题:
					<input type="text"  name="gtitle"  id="gtitle"/>
					<br><br>
					 学校介绍:
					<textarea name="gintroduce" id="gintroduce" ></textarea>
				 	<br><br>
					 院校图片:<input type="file"  name="gpicture" id="gpicture" />
					<br><br>
					专业:<input type="file"  name="gmajor"  id="gmajor"/>
					<br><br>
					毕业证书:<input type="file"  name="gdiploma"  id="gdiploma"/>
				<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#ge-datagrid').datagrid({
			url:'/Enrollment/ListGeneral.action',
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
			    {field:'glogo',title:'学校logo',width:18,sortable:true},
			    {field:'gintroduce',title:'学校介绍',width:60},
			    {field:'gpicture',title:'院校图片',width:28,sortable:true},
			    {field:'gtitle',title:'学校标题',width:15,sortable:true},
			    {field:'gmajor',title:'专业',width:20,sortable:true},
				{field:'gdiploma',title:'毕业证书',width:20,sortable:true},
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
			$('#ge-form').form('submit', {
				url:'/Enrollment/Addgeneral.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
						$('#ge-datagrid').datagrid('load');
						$('#ge-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#ge-datagrid').datagrid('load');
						$('#ge-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#ge-form').form('submit', {
			url:'/Enrollment/updateGeneral.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#ge-datagrid').datagrid('load');
					$('#ge-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#ge-datagrid').datagrid('load');
					$('#ge-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#ge-datagrid').datagrid('getSelected');
		var gid =item.gid;
		$('#generalDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteGeneral.action',
							data:{'gid':gid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#ge-datagrid').datagrid('load');
									$('#generalDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#ge-datagrid').datagrid('load');
									$('#generalDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#generalDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#ge-form').form('clear');
			$('#ge-dialog').dialog({
				closed: false,
				modal:true,
	            title: "招生简章",
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
                    $('#ge-dialog').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#ge-datagrid').datagrid('getSelected');
		var gid =item.gid;
		 $.ajax({
			url:'/Enrollment/FindGeneral.action',
			data:{'gid':gid},
			dataType:'json',
			success:function(data){
				if(data!="{}"){
					$("#gid").val(data.gid);
					$("#gintroduce").val(data.gintroduce);
					$("#gtitle").val(data.gtitle);
					$("#glogo").val(data.glogo);
					$("#gpicture").val(data.gpicture);
					$("#gmajor").val(data.gmajor);
					$("#gdiploma").val(data.gdiploma);
					$('#ge-form').form('load',data);
				}
				else{
					$('#ge-dialog').dialog('close');
				}
			}	
		}); 
		$('#ge-dialog').dialog({
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
                    $('#ge-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	模糊查询
	*/
	function startSelect(){
		var gtitle = $('#likeGtitle').val();
		$('#ge-datagrid').datagrid({
			url:'/Enrollment/likeGenerals.action?gtitle='+gtitle,
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
			    {field:'glogo',title:'学校logo',width:18,sortable:true},
			    {field:'gintroduce',title:'学校介绍',width:60},
			    {field:'gpicture',title:'院校图片',width:28,sortable:true},
			    {field:'gtitle',title:'学校标题',width:15,sortable:true},
			    {field:'gmajor',title:'毕业证',width:20,sortable:true},
				{field:'gdiploma',title:'毕业证书',width:20,sortable:true},
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