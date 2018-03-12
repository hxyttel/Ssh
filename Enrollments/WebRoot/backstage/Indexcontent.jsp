<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<div id="wu-toolbar-1">
				 <div class="wu-toolbar-button">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
			  	 </div>
			</div>
			<!-- End of toolbar -->
			<table id="wu-datagrid-1" toolbar="#wu-toolbar-1"></table>
			<!-- 得到Teacher的电话号码 -->
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="wu-dialog-1" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				<s:form id="wu-form-1" method="post"  enctype="multipart/form-data">
					<s:hidden name="iid" id="iid"></s:hidden>				 	
				 	<s:file label="二维码图片"  name="myFile" id="icode" class="wu-file" style="width:150;height:20px" />
					<s:textfield name="iphone" id="iphone" class="wu-text" style="width:200;height:20px" label="联系电话"/>
					<s:textfield  name="iaddress" id="iaddress" style="width:200;height:20px" label="地址" />
				</s:form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#wu-datagrid-1').datagrid({
			url:'/Enrollment/ListIndexContent.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'icode',title:'二维码',width:20,sortable:true},
				{ field:'iaddress',title:'地址',width:50,sortable:true},
				{ field:'iphone',title:'联系电话',width:40,sortable:true},
				{ field:'_content',title:'操作',width:20,align:'center',formatter:formatContent},
			]]
		});
		function formatContent(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:30px" />';
		} 
		/**
		* Name 添加记录
		*/
		function add(){
			$('#wu-form-1').form('submit', {
				url:'/Enrollment/SaveContent.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
						$('#wu-datagrid-1').datagrid('load');
						$('#wu-dialog-1').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#wu-datagrid-1').datagrid('load');
						$('#wu-dialog-1').dialog('close');
					}
				}
			});
		}
		
		/**
	* Name 修改记录
	*/
	function edit(){
		$('#wu-form-1').form('submit', {
			url:'/Enrollment/UpdateContent.action',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#wu-datagrid-1').datagrid('load');
					$('#wu-dialog-1').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#wu-datagrid-1').datagrid('load');
					$('#wu-dialog-1').dialog('close');
				}
			}
		});
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#wu-form-1').form('clear');
		var item = $('#wu-datagrid-1').datagrid('getSelected');
		var iid =item.iid;
		$.ajax({
			url:'/Enrollment/FindContent.action',
			data:{'iid':iid},
			dataType: 'json',
			success:function(data){
				if(data){
					document.getElementById("iid").value=data.iid;
					document.getElementById("iphone").value=data.iphone;	
					document.getElementById("iaddress").value=data.iaddress;		
					//绑定值
					$('#wu-form-1').form('load',data)
				}
				else{
					$('#wu-dialog-1').dialog('close');
				}
			}	
		});
		$('#wu-dialog-1').dialog({
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
                    $('#wu-dialog-1').dialog('close');                    
                }
            }]
        });
	}	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#wu-form-1').form('clear');
			$('#wu-dialog-1').dialog({
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
                    $('#wu-dialog-1').dialog('close');                    
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