<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<table id="accounting-datagrid" toolbar="#wu-toolbar-1"></table>
			</div>
   	</div>
		<!-- 表格内容 -->
			<div id="accounting-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				<form method="post"  enctype="multipart/form-data" id="accounting-form" >
				 	<input type="hidden" name="agid" id="agid" > 
				 	 会计培训:
				 	<input type="text"  name="agname" id="agname" />
				 	<br><br>
				 	 图标:
				 	<input type="file"  name="agpicture" id="agpicture" />
				 	<br><br>
				 	<s:hidden type="hidden" name="agaddress" id="agaddress"  />
				 	<!-- 跳转的jsp路径:
					<input type="text"  name="agaddress"  id="agaddress"/>
					<br><br> -->
				 	 介绍标题:
					<input type="text"  name="agtitle"  id="agtitle"/>
					<br><br>
					 代表图:<input type="file"  name="agphoto1" id="agphoto1" />
					<br><br>
					专业图:<input type="file"  name="agphoto2"  id="agphoto2"/>
					<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#accounting-datagrid').datagrid({
			url:'/Enrollment/AccountingList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'agname',title:'会计培训',width:20,sortable:true},
				{ field:'agpicture',title:'图标',width:50,sortable:true},
				{ field:'agaddress',title:'跳转路径',width:40,sortable:true},
				{ field:'agtitle',title:'标题',width:20,sortable:true},
				{ field:'agphoto1',title:'代表图',width:50,sortable:true},
				{ field:'agphoto2',title:'专业图',width:40,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />'
		}
	
	
	function openEdit(){
		$('#accounting-form').form('clear');
		var item = $('#accounting-datagrid').datagrid('getSelected');
		var agid =item.agid;
		$.ajax({
			url:'/Enrollment/findListAccounting.action',
			data:{'agid':agid},
			dataType: 'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("agid").value=data.agid;
					document.getElementById("agname").value=data.agname;
					//document.getElementById("agpicture").value=data.agpicture;
					document.getElementById("agaddress").value=data.agaddress;
					document.getElementById("agtitle").value=data.agtitle;
					//document.getElementById("agphoto1").value=data.agphoto1;
					//document.getElementById("agphoto2").value=data.agphoto2;		
					//绑定值
					$('#accounting-form').form('load',data)
				}
				else{
					$('#accounting-dialog').dialog('close');
				}
			}	
		});
		$('#accounting-dialog').dialog({
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
					$('#accounting-dialog').dialog('close');                 
                }
            }]
        });
	}	
		function edit(){
		var tphone = document.getElementById("tphone").value;
		$('#accounting-form').form('submit', {
			url:'/Enrollment/updateAccounting.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#accounting-datagrid').datagrid('load');
					$('#accounting-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#accounting-datagrid').datagrid('load');
					$('#accounting-dialog').dialog('close');
				}
			}
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