<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'center',border:false">
		<!-- Begin of toolbar -->
			<div id="wu-toolbar-3">
				<div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	</div>
				<div class="wu-toolbar-search">
					<label>QQ号：</label>
					<input class="wu-text" id="likeCqqnumber" name="contact.cqqnumber" style="width:100px">
					<label>电话号码：</label>
					<input class="wu-text" id="likeCphone" name="contact.cphone" style="width:100px">
					<label>联系老师：</label>
					<input class="wu-text" id="likeCpeople" name="contact.cpeople" style="width:100px">
					<label>联系类型：</label>
					<select name="contact.ctype" id="likeCtype" style="width:100px">
						<option value="">--请选择--</option>
						<s:iterator value="listType">
							<option value="<s:property value="ctype"/>"><s:property value="ctype"/></option>
						</s:iterator>
					</select>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
				</div>
			</div>
			<!-- End of toolbar -->
			<table id="wu-datagrid-3" toolbar="#wu-toolbar-3"></table>
			<!-- 得到Teacher的电话号码 -->
			<div id="contaceDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
		</div>
   	</div>
		<!-- 表格内容 -->
	<div id="wu-dialog-3" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
    	<s:form  method="post"  id="wu-form-3">
            <s:hidden name="contact.cid" id="cid" />
			<s:textfield name="contact.cqqnumber" id="cqqnumber" label="QQ号" style="width:140px;height:25px"></s:textfield>
			<s:textarea name="contact.cphone" id="cphone" label="电话号码" style="width:140px;height:25px"></s:textarea>
			<s:textarea name="contact.cpeople" id="cpeople" label="联系老师" style="width:140px;height:25px"></s:textarea>				
			联系类型:
			<select name="contact.ctype" id="ctype" style="width:140px;height:25px">
				<s:iterator value="listType">
					<option value="<s:property value="ctype"/>"><s:property value="ctype"/></option>
				</s:iterator>
			</select>
		</s:form>  
	</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#wu-datagrid-3').datagrid({
			url:'/Enrollment/ListContact.action',
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
				{field:'cqqnumber',title:'图片',width:20,sortable:true},
				{field:'cphone',title:'图片标题',width:15,sortable:true},
				{field:'cpeople',title:'图片内容',width:60},
				{field:'ctype',title:'联系类型',width:30},
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
			$('#wu-form-3').form('submit', {
				url:'/Enrollment/AddContact.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
				        $('#wu-datagrid-3').datagrid('load');
				        $('#wu-dialog-3').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#wu-datagrid-3').datagrid('load');
				        $('#wu-dialog-3').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#wu-form-3').form('submit', {
			url:'/Enrollment/UpdateContact.action',
			success:function(data){
				if(data!={}){
					$.messager.alert('信息提示','提交成功！','info');
					 $('#wu-dialog-3').dialog('close');
				     $('#wu-datagrid-3').datagrid('load');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#wu-dialog-3').dialog('close');
				    $('#wu-datagrid-3').datagrid('load');
				}
			}
		});
	}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#wu-datagrid-3').datagrid('getSelected');
		var cid =item.cid;
		$('#contaceDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteContact.action',
							data:{'id':cid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#wu-datagrid-3').datagrid('load');
									$('#contaceDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');	
									$('#wu-datagrid-3').datagrid('load');
									$('#contaceDele').dialog('close');	
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#contaceDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#wu-form-3').form('clear');
			$('#ac-datagrid').datagrid('load');
            $('#acneralDele').dialog('close');
			$('#wu-dialog-3').dialog({
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
                    $('#wu-dialog-3').dialog('close');                    
                }
            }] 
	        });
		}
		/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		var item = $('#wu-datagrid-3').datagrid('getSelected');
		 var cid =item.cid;
		 $.ajax({
			url:'/Enrollment/FindContact.action',
			data:{'id':cid},
			dataType:'json',
			success:function(data){
					if(data){
					document.getElementById("cid").value=data.cid;
					document.getElementById("cqqnumber").value=data.cqqnumber;
					document.getElementById("cphone").value=data.cphone;
					document.getElementById("cpeople").value=data.cpeople;
					document.getElementById("ctype").value=data.ctype;
					$('#wu-form-3').form('load',data);
				}
				else{
					$('#wu-dialog-3').dialog('close');
				}
			}	
		}); 
		$('#wu-dialog-3').dialog({
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
                    $('#wu-dialog-3').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
		模糊查询
	*/
	function startSelect(){
		var qqnumber = $('#likeCqqnumber').val();
		var phone = $('#likeCphone').val();
		var people = $('#likeCpeople').val();
		var type = $('#likeCtype').val();
		$('#wu-datagrid-3').datagrid({
			url:'/Enrollment/likeContact.action?contact.cqqnumber='+qqnumber+'&contact.cphone='+phone+'&contact.cpeople='+people+'&contact.ctype='+type,
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
				{field:'cqqnumber',title:'图片',width:20,sortable:true},
				{ field:'cphone',title:'图片标题',width:15,sortable:true},
				{ field:'cpeople',title:'图片内容',width:60},
				{ field:'ctype',title:'联系类型',width:30},
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