<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- End of toolbar -->
			<table id="vocational-datagrid" toolbar="#wu-toolbar-1"></table>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="vocational-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				<form method="post"  enctype="multipart/form-data" id="vocational-form" >
				 	<input type="hidden" name="vlid" id="vlid" > 
				 	职业资格:
				 	<input type="text"  name="vlname" id="vlname" />
				 	<br><br>
				 	 图标:
				 	<input type="file"  name="vlpicture" id="vlpicture" />
				 	<br><br>
				 	<s:hidden type="hidden" name="vladdress" id="vladdress"  />
				 	<!-- 跳转的jsp路径:
					<input type="text"  name="vladdress"  id="vladdress"/>
					<br><br> -->
				 	 介绍标题:
					<input type="text"  name="vltitle"  id="vltitle"/>
					<br><br>
					 代表图:<input type="file"  name="vlphoto1" id="vlphoto1" />
					<br><br>
					专业图:<input type="file"  name="vlphoto2"  id="vlphoto2"/>
					<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#vocational-datagrid').datagrid({
			url:'/Enrollment/VocationalList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'vlname',title:'职业资格',width:20,sortable:true},
				{ field:'vlpicture',title:'图标',width:50,sortable:true},
				{ field:'vladdress',title:'跳转路径',width:40,sortable:true},
				{ field:'vltitle',title:'标题',width:20,sortable:true},
				{ field:'vlphoto1',title:'代表图',width:50,sortable:true},
				{ field:'vlphoto2',title:'专业图',width:40,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />'
		}
	
	
	function openEdit(){
		$('#vocational-form').form('clear');
		var item = $('#vocational-datagrid').datagrid('getSelected');
		var vlid =item.vlid;
		$.ajax({
			url:'/Enrollment/findListVocational.action',
			data:{'vlid':vlid},
			dataType: 'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("vlid").value=data.vlid;
					document.getElementById("vlname").value=data.vlname;
					//document.getElementById("vlpicture").value=data.vlpicture;
					document.getElementById("vladdress").value=data.vladdress;
					document.getElementById("vltitle").value=data.vltitle;
					//document.getElementById("vlphoto1").value=data.vlphoto1;
					//document.getElementById("vlphoto2").value=data.vlphoto2;		
					//绑定值
					$('#vocational-form').form('load',data)
				}
				else{
					$('#vocational-dialog').dialog('close');
				}
			}	
		});
		$('#vocational-dialog').dialog({
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
                    $('#vocational-dialog').dialog('close');                    
                }
            }]
        });
	}	
		function edit(){
		var tphone = document.getElementById("tphone").value;
		$('#vocational-form').form('submit', {
			url:'/Enrollment/updateVocational.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#vocational-datagrid').datagrid('load');
					$('#vocational-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#vocational-datagrid').datagrid('load');
					$('#vocational-dialog').dialog('close');
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