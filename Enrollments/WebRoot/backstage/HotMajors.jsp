<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-1938" data-genuitec-path="/Enrollment/WebRoot/backstage/HotMajors.jsp">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="hotmajor-toolbar">
					 <div class="wu-toolbar-button">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				  	 </div>
					<div class="wu-toolbar-search">
						<label>标题：</label>
						<input class="wu-text" id="likeHtitle" name="htitle" style="width:100px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
					</div>
			</div>
			<!-- End of toolbar -->
			<table id="hotmajor-datagrid" toolbar="#hotmajor-toolbar"></table>
			<div id="hotmajorDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="hotmajor-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				 <form method="post"  enctype="multipart/form-data" id="hotmajor-form">
				 	 <input type="hidden" name="hid" id="hid" > 
					热门专业图片:<input type="file"  name="myFile" id="hpicture" />
					<br><br>
					热门专业标题:<input type="text"  name="htitle"   id="htitle"/>
					<br><br>
					热门专业内容:<textarea  name="hcontent" id="hcontent" ></textarea>
				<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#hotmajor-datagrid').datagrid({
			url:'/Enrollment/ListHotmajor.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'hpicture',title:'图片',width:20,sortable:true},
				{ field:'htitle',title:'图片标题',width:15,sortable:true},
				{ field:'hcontent',title:'图片内容',width:60},
				{ field:'_pictuer',title:'操作',width:20,align:'center',formatter:formatPicture},
			]]
		});
		function formatPicture(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#hotmajor-form').form('submit', {
				url:'/Enrollment/AddHotmajor.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#hotmajor-datagrid').datagrid('load');
						$('#hotmajor-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#hotmajor-datagrid').datagrid('load');
						$('#hotmajor-dialog').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#hotmajor-form').form('submit', {
			url:'/Enrollment/UpdateHotmajor.action',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#hotmajor-datagrid').datagrid('load');
					$('#hotmajor-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#hotmajor-datagrid').datagrid('load');
					$('#hotmajor-dialog').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#hotmajor-datagrid').datagrid('getSelected');
		var hid =item.hid;
		$('#hotmajorDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteHotmajor.action',
							data:{'id':hid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
                                    $('#hotmajor-datagrid').datagrid('load');
                                    $('#hotmajorDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');	
									$('#hotmajor-datagrid').datagrid('load');
                                    $('#hotmajorDele').dialog('close');	
								}
					}	
				});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#hotmajorDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#hotmajor-form').form('clear');
			$('#hotmajor-dialog').dialog({
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
                    $('#hotmajor-dialog').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#hotmajor-datagrid').datagrid('getSelected');
		 var hid =item.hid;
		 $.ajax({
			url:'/Enrollment/FindHotmajor.action',
			data:{'id':hid},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("hid").value=data.hid;
					document.getElementById("htitle").value=data.htitle;
					document.getElementById("hcontent").value=data.hcontent;
					document.getElementById("hpicture").value=data.hpicture;
					$('#hotmajor-form').form('load',data);
				}
				else{
					$('#hotmajor-dialog').dialog('close');
				}
			}	
		}); 
		$('#hotmajor-dialog').dialog({
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
                    $('#hotmajor-dialog').dialog('close');                    
                }
            }]
        });
	}	
	/**
	模糊查询
	*/
	function startSelect(){
		var htitle = $('#likeHtitle').val();
		$('#hotmajor-datagrid').datagrid({
			url:'/Enrollment/likeHotMahors.action?htitle='+htitle,
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
				{field:'hpicture',title:'图片',width:20,sortable:true},
				{ field:'htitle',title:'图片标题',width:15,sortable:true},
				{ field:'hcontent',title:'图片内容',width:60},
				{ field:'_pictuer',title:'操作',width:20,align:'center',formatter:formatPicture},
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