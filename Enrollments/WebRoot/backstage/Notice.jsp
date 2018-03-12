<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="wu-toolbar-5">
					 <div class="wu-toolbar-button">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				  	 </div>
					<div class="wu-toolbar-search">
					<label>主题：</label>
					<input id="likeNtitle" name="notice.ntitle" class="wu-text" style="width:100px">
					<label>公告类型：</label>
					<select name="notice.nflag" id="likeNflag" style="width:100px">
						<option value="">--请选择--</option>
						<option value="公告">公告</option>
						<option value="简章">简章</option>
					</select>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="wu-datagrid-5" toolbar="#wu-toolbar-5"></table>
			<!-- 得到Teacher的电话号码 -->
			<!-- 删除框 -->
			<div id="noticeDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="wu-dialog-5" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <s:form  method="post"  id="wu-form-5">
                <s:hidden name="notice.nid" id="nid" />
				<s:textfield name="notice.ntitle" id="ntitle" label="公告主题"></s:textfield>
				<s:textarea name="notice.ncontent" id="ncontent" label="公告内容"></s:textarea>
				<s:hidden name="notice.ndate" id="ndate"></s:hidden>
				<%-- 				<s:radio list="#{'a':'公告','b':'简章'}" name="notice.nflag" id="nflag"  label="公告类型"></s:radio>
	 --%>
				公告类型:
					<select name="notice.nflag" id="nflag" style="width:140px;height:25px;">
						<option value="公告">公告</option>
						<option value="简章">简章</option>
					</select>
				</s:form>  
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#wu-datagrid-5').datagrid({
			url:'/Enrollment/ListNotice.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'ntitle',title:'公告标题',width:30,sortable:true},
				{ field:'ncontent',title:'公告内容',width:85,sortable:true},
				{ field:'ndate',title:'发布时间',width:15},
				{ field:'nflag',title:'公告类型',width:20},
				{ field:'_notice',title:'操作',width:20,align:'center',formatter:formatnotice},
			]]
		});
		function formatnotice(val,row,index){
			return '<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  plain="true" onclick="openEdit()" ">修改</a>&nbsp;&nbsp<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()"  plain="true">删除</a>';
		}
		/* function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		} */
		/**
		模糊查询
	*/
	function startSelect(){
		var title = $('#likeNtitle').val();
		var nflag = $('#likeNflag').val();
		$('#wu-datagrid-5').datagrid({
			url:'/Enrollment/likeNotices.action?notice.ntitle='+title+'&notice.nflag='+nflag,
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
				{field:'ntitle',title:'公告标题',width:30,sortable:true},
				{ field:'ncontent',title:'公告内容',width:85,sortable:true},
				{ field:'ndate',title:'发布时间',width:15},
				{ field:'nflag',title:'公告类型',width:20},
				{ field:'_notice',title:'操作',width:20,align:'center',formatter:formatnotice},
			]]
		});
	}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#wu-form-5').form('submit', {
				url:'/Enrollment/AddNotice.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#wu-datagrid-5').datagrid('load');
						$('#wu-dialog-5').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#wu-datagrid-5').datagrid('load');
						$('#wu-dialog-5').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#wu-form-5').form('submit', {
			url:'/Enrollment/UpdateNotice.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#wu-datagrid-5').datagrid('load');
					$('#wu-dialog-5').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#wu-datagrid-5').datagrid('load');
					$('#wu-dialog-5').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#wu-datagrid-5').datagrid('getSelected');
		var nid =item.nid;
		$('#noticeDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteNotice.action',
							data:{'id':nid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#wu-datagrid-5').datagrid('load');
                                    $('#noticeDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#wu-datagrid-5').datagrid('load');
                                    $('#noticeDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#noticeDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#wu-form-5').form('clear');
			$('#wu-dialog-5').dialog({
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
                    $('#wu-dialog-5').dialog('close');                    
                }
            }] 
	        });
		}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#wu-datagrid-5').datagrid('getSelected');
		 var nid =item.nid;
		 $.ajax({
			url:'/Enrollment/FindNotice.action',
			data:{'id':nid},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("nid").value=data.nid;
					document.getElementById("ntitle").value=data.ntitle;
					document.getElementById("ncontent").value=data.ncontent;
					document.getElementById("ndate").value=data.ndate;
					document.getElementById("nflag").value=data.nflag;
					
					$('#wu-form-5').form('load',data);
				}
				else{
					$('#wu-dialog-5').dialog('close');
				}
			}	
		}); 
		$('#wu-dialog-5').dialog({
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
                    $('#wu-dialog-5').dialog('close');                    
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