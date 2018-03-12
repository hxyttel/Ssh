<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
				<div id="art-toolbar">
					 
			</div>
			<!-- End of toolbar -->
			<table id="art-datagrid" toolbar="#art-toolbar"></table>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="art-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				<form method="post"  enctype="multipart/form-data" id="art-form" >
				 	<input type="hidden" name="arid" id="arid" > 
				 	 艺考培训:
				 	<input type="text"  name="asname" id="asname" />
				 	<br><br>
				 	 图标:
				 	<input type="file"  name="aspicture" id="aspicture" />
				 	<br><br>
				 	<s:hidden type="hidden" name="asaddress" id="asaddress"  />
				 	<!-- 跳转的jsp路径:
					<input type="text"  name="asaddress"  id="asaddress"/>
					<br><br> -->
				 	 介绍标题:
					<input type="text"  name="astitle"  id="astitle"/>
					<br><br>
					 代表图:<input type="file"  name="asphoto1" id="asphoto1" />
					<br><br>
					专业图:<input type="file"  name="asphoto2"  id="asphoto2"/>
					<br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#art-datagrid').datagrid({
			url:'/Enrollment/ArtsList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'asname',title:'艺考培训',width:20,sortable:true},
				{ field:'asaddress',title:'跳转路径',width:40,sortable:true},
				{ field:'astitle',title:'标题',width:20,sortable:true},
				{ field:'aspicture',title:'图标',width:50,sortable:true},
				{ field:'asphoto1',title:'代表图',width:50,sortable:true},
				{ field:'asphoto2',title:'专业图',width:40,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />'
		}
	
	
	function openEdit(){
		$('#art-form').form('clear');
		var item = $('#art-datagrid').datagrid('getSelected');
		var asid =item.asid;
		$.ajax({
			url:'/Enrollment/findListArts.action',
			data:{'asid':asid},
			dataType: 'json',
			success:function(data){
				if(data!="{}"){
					$("#asid").val(data.arts[0].asid);
					$("#asname").val(data.arts[0].asname);
					//$("#aspicture").val(data.arts[0].aspicture);
					$("#asaddress").val(data.arts[0].asaddress);
					$("#astitle").val(data.arts[0].astitle);
					//$("#asphoto1").val(data.arts[0].asphoto1);
					//$("#asphoto2").val(data.arts[0].asphoto2);
					//绑定值
					$('#art-form').form('load',data)
				}
				else{
					$('#art-dialog').dialog('close');
				}
			}	
		});
		$('#art-dialog').dialog({
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
                    $('#art-dialog').dialog('close');                    
                }
            }]
        });
	}	
		function edit(){
		var tphone = document.getElementById("tphone").value;
		$('#art-form').form('submit', {
			url:'/Enrollment/updateArts.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#art-datagrid').datagrid('load');
					$('#art-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#art-datagrid').datagrid('load');
					$('#art-dialog').dialog('close');
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