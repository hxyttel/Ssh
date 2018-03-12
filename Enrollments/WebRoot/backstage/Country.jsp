<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<table id="country-datagrid" toolbar="#wu-toolbar-1"></table>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="country-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
				<form method="post"  enctype="multipart/form-data" id="country-form" >
				 	<input type="hidden" name="cyid" id="cyid" > 
				 	国家开发:
				 	<input type="text"  name="cyname" id="cyname" />
				 	<br><br>
				 	 图标:
				 	<input type="file"  name="cypicture" id="cypicture" />
				 	<br><br>
				 	<s:hidden type="hidden" name="cyaddress" id="cyaddress"  />
				 	<!-- 跳转的jsp路径:
					<input type="text"  name="cyaddress"  id="cyaddress"/>
					<br><br> -->
				 	 介绍标题:
					<input type="text"  name="cytitle"  id="cytitle"/>
					<br><br>
					 代表图:
					 <input type="file"  name="cyphoto1" id="cyphoto1" />
					<br><br>
					 内容:
					<input type="text"  name="cycontent"  id="cycontent"/>
					<br><br>
					专业图:
					<input type="file"  name="cyphoto2"  id="cyphoto2"/>
					<br><br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#country-datagrid').datagrid({
			url:'/Enrollment/CountryList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'cyname',title:'国家开放',width:20,sortable:true},
				{ field:'cypicture',title:'图标',width:50,sortable:true},
				{ field:'cyaddress',title:'跳转路径',width:40,sortable:true},
				{ field:'cytitle',title:'标题',width:20,sortable:true},
				{ field:'cyphoto1',title:'代表图',width:50,sortable:true},
				{ field:'cycontent',title:'内容',width:40,sortable:true},
				{ field:'cyphoto2',title:'专业图',width:40,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />'
		}
	
	
	function openEdit(){
		$('#country-form').form('clear');
		var item = $('#country-datagrid').datagrid('getSelected');
		var cyid =item.cyid;
		$.ajax({
			url:'/Enrollment/findListCountry.action',
			data:{'cyid':cyid},
			dataType: 'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("cyid").value=data.cyid;
					document.getElementById("cyname").value=data.cyname;
					//document.getElementById("cypicture").value=data.cypicture;
					document.getElementById("cyaddress").value=data.cyaddress;
					document.getElementById("cytitle").value=data.cytitle;
					//document.getElementById("cyphoto1").value=data.cyphoto1;
					document.getElementById("cycontent").value=data.cycontent;
					//document.getElementById("cyphoto2").value=data.cyphoto2;		
					//绑定值
					$('#country-form').form('load',data)
				}
				else{
					$('#country-dialog').dialog('close');
				}
			}	
		});
		$('#country-dialog').dialog({
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
                    $('#country-dialog').dialog('close');                    
                }
            }]
        });
	}	
		function edit(){
		var tphone = document.getElementById("tphone").value;
		$('#country-form').form('submit', {
			url:'/Enrollment/updateCountry.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#country-datagrid').datagrid('load');
					$('#country-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#country-datagrid').datagrid('load');
					$('#country-dialog').dialog('close');
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