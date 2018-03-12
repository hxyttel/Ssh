<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	 <div class="easyui-layout" data-options="fit:true">
		    <div data-options="region:'center',border:false">
			<!-- Begin of toolbar -->
			<table id="adult-datagrid" toolbar="#wu-toolbar-1"></table>
		</div>
   	</div>
		<!-- 表格内容 -->
			<div id="adult-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
					<form method="post"  enctype="multipart/form-data" id="adult-form" >
				 	<input type="hidden" name="atid" id="atid" > 
				 	成人教育:
				 	<input type="text"  name="atname" id="atname" />
				 	<br><br>
				 	 图标:
				 	<input type="file"  name="atpicture" id="atpicture" />
				 	<br><br>
				 	<s:hidden type="hidden" name="ataddress" id="ataddress"  />
				 	<!-- 跳转的jsp路径:
					<input type="text"  name="ataddress"  id="ataddress"/>
					<br><br> -->
				 	 介绍标题:
					<input type="text"  name="attitle"  id="attitle"/>
					<br><br>
					 代表图:
					 <input type="file"  name="atphoto1" id="atphoto1" />
					<br><br>
					 内容:
					<input type="text"  name="atcontent"  id="atcontent"/>
					<br><br>
					专业图:
					<input type="file"  name="atphoto2"  id="atphoto2"/>
					<br><br>
				</form>
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#adult-datagrid').datagrid({
			url:'/Enrollment/AdultList.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'atname',title:'成人教育',width:20,sortable:true},
				{ field:'atpicture',title:'图标',width:50,sortable:true},
				{ field:'ataddress',title:'跳转路径',width:40,sortable:true},
				{ field:'attitle',title:'标题',width:20,sortable:true},
				{ field:'atphoto1',title:'代表图',width:50,sortable:true},
				{ field:'atcontent',title:'内容',width:40,sortable:true},
				{ field:'atphoto2',title:'专业图',width:40,sortable:true},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />'
		}
	
	
	function openEdit(){
		$('#adult-form').form('clear');
		var item = $('#adult-datagrid').datagrid('getSelected');
		var atid =item.atid;
		$.ajax({
			url:'/Enrollment/findListAdult.action',
			data:{'atid':atid},
			dataType: 'json',
			success:function(data){
				if(data!="{}"){
					document.getElementById("atid").value=data.atid;
					document.getElementById("atname").value=data.atname;
					//document.getElementById("atpicture").value=data.atpicture;
					document.getElementById("ataddress").value=data.ataddress;
					document.getElementById("attitle").value=data.attitle;
					//document.getElementById("atphoto1").value=data.atphoto1;
					document.getElementById("atcontent").value=data.atcontent;
					//document.getElementById("atphoto2").value=data.atphoto2;		
					//绑定值
					$('#adult-form').form('load',data)
				}
				else{
					$('#adult-dialog').dialog('close');
				}
			}	
		});
		$('#adult-dialog').dialog({
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
                    $('#adult-dialog').dialog('close');                    
                }
            }]
        });
	}	
		function edit(){
		var tphone = document.getElementById("tphone").value;
		$('#adult-form').form('submit', {
			url:'/Enrollment/updateAdult.action',
			success:function(data){
				if(data!="{}"){
					$.messager.alert('信息提示','提交成功！','info');
					$('#adult-datagrid').datagrid('load');
					$('#adult-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#adult-datagrid').datagrid('load');
					$('#adult-dialog').dialog('close');
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