<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<div class="easyui-layout" data-options="fit:true">
			    <div data-options="region:'center',border:false">
				<!-- Begin of toolbar -->
					<div id="role-toolbar">
						 <div class="wu-toolbar-button">
							<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
					  	 </div>
						<div class="wu-toolbar-search">
						<label>关键词：</label><input class="wu-text" style="width:100px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
						</div>
				</div>
				<!-- End of toolbar -->
				<table id="role-datagrid" toolbar="#role-toolbar"></table>
				<div id="roleDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
			</div>
	   	</div>
	   	<!-- 表格内容 -->
			<div id="role-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <form  method="post"  id="role-form">
                	<input type="hidden" name="roels.rid" id="rid" style="width:140px;height:25px"/>
	   				角色名称:
                	<input type="text" name="roels.rname" id="rname" style="width:140px;height:25px"/>
                	<br><br>
	   				角色描述:
	   				<input type="text" name="roels.rdes" id="rdes" style="width:140px;height:25px"/>
	   				<br><br>
	   				角色状态:
	   				<select name="roels.rstatus" id="rstatus" style="width:140px;height:25px">
						<option value="正常">正常</option>
						<option value="停用">停用</option>
					</select>
	   				<br><br>
	   				
				</form>  
			</div>
	<!-- 树 --> 
	<div id="rolegrant-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'"  style="width:600px;height:600px;padding:10px;">
		<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    		<div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
		        <div class="well well-small">
		            <form id="roleGrantForm" method="post">
		            	<input type="hidden" name="rolesrid" id="rolesrid" />
		                <ul id="resourceTree"></ul>
		                <input id="resourceIds" name="resourceIds" type="hidden" />
		            	<input id="notchecknodes" name="notchecknodes" type="hidden" />
		            </form>
		        </div>
    		</div>
	    <div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
	        <div>
	            <input type="button"  onclick="checkAll()" value="全选" />
	            <br /> <br />
	            <input type="button" onclick="checkInverse()" value="反选"/>
	            <br /> <br />
	            <input type="button" onclick="uncheckAll()" value="取消" />
	        </div>
	    </div>
</div>
	
	</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
	var resourceTree;
		/**
		* 弹出框内容
		*/
		$('#role-datagrid').datagrid({
			url:'/Enrollment/ListRole.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:1,           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'rname',title:'角色名称',width:10,sortable:true},
				{ field:'rdes',title:'角色描述',width:10},
				{ field:'rstatus',title:'状态',width:10},
				{ field:'rid',title:'操作',width:30,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			if(val==1){
				return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
			}else{
				return '<input type="button" onclick="openGrant()" value="授权" style="background-color:#ffffcc;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
			}
			
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#role-form').form('submit', {
				url:'/Enrollment/AddRole.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#role-datagrid').datagrid('load');
						$('#role-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#role-datagrid').datagrid('load');
						$('#role-dialog').dialog('close');
					}
				}
			});
		}
		
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#role-form').form('submit', {
			url:'/Enrollment/UpdateRole.action',
			success:function(data){
				if(data!={}){
					$.messager.alert('信息提示','提交成功！','info');
                    $('#role-datagrid').datagrid('load');
					$('#role-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#role-datagrid').datagrid('load');
					$('#role-dialog').dialog('close');
				}
			}
		});
	}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#role-datagrid').datagrid('getSelected');
		var rid =item.rid;
		$('#roleDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteRole.action',
							data:{'id':rid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#role-datagrid').datagrid('load');
                                    $('#roleDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#role-datagrid').datagrid('load');
                                    $('#roleDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#roleDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#role-form').form('clear');
			$('#role-dialog').dialog({
				closed: false,
				modal:true,
	            title: "用户表",
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
	                   $('#role-dialog').dialog('close');                    
	            	}
           		}] 
       		});
		}
		/**
		* Name 打开修改窗口
		*/
	function openEdit(){
		var item = $('#role-datagrid').datagrid('getSelected');
		var rid =item.rid;
		$.ajax({
			url:'/Enrollment/FindRole.action',
			data:{'id':rid},
			dataType:'json',
			success:function(data){
					if(data!="{}"){
					document.getElementById("rid").value=data.rid;
					document.getElementById("rname").value=data.rname;
					document.getElementById("rdes").value=data.rdes;
					document.getElementById("rstatus").value=data.rstatus;
				}
				else{
					$('#role-dialog').dialog('close');
				}
			}	
		}); 
		$('#role-dialog').dialog({
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
                    $('#role-dialog').dialog('close');                    
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
			//点击授权窗口
		function openGrant(){
			var item = $('#role-datagrid').datagrid('getSelected');
	 		var rid =item.rid;
	 		resourceTree = $('#resourceTree').tree({
	            url : '/Enrollment/AllTrees.action',
	            lines : true,
	            checkbox : true,
	            loadFilter: function(rows){	
						return convert(rows);				
				},
	            onLoadSuccess : function(node,data) {
	                $.ajax({
						url:'/Enrollment/findListByRoleId.action',
						data:{'id':rid},
						dataType:'json',
						success:function(data){
							var ids= data;
	                   	 if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                            if (resourceTree.tree('find', ids[i])) {
	                                resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
	                            	}
	                       		 }
	                    		}
								
							}	
						});
	            },
	            cascadeCheck : false 
	        });
			$('#rolegrant-dialog').dialog({
				closed: false,
				modal:true,
	            title: "系统资源",
	            buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
	                	var checknodes = resourceTree.tree('getChecked');
	                	 var notchecknodes = resourceTree.tree('getChecked', 'unchecked');
			             var ids = [];
			             var notids =[];
			             if (checknodes && checknodes.length > 0  ) {
			                 for ( var i = 0; i < checknodes.length; i++) {
			                     ids.push(checknodes[i].id);
			                 }
			             }
			             if(notchecknodes && notchecknodes.length > 0){
			             	 for ( var i = 0; i < notchecknodes.length; i++) {
			                     notids.push(notchecknodes[i].id);
			                 }
			             }else if(notchecknodes.length<=0){
			             	 notids=null;
			             }
			             else if(checknodes.length<=0){
			             	ids=null;
			             }
			             $('#rolesrid').val(rid);
			             $('#notchecknodes').val(notids);
			             $('#resourceIds').val(ids);
			             $('#roleGrantForm').form('submit',{
							url:'/Enrollment/AddGrant.action',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','提交成功！','info');
					                $('#role-datagrid').datagrid('load');
									$('#role-dialog').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','提交失败！','info');
								}
							}
						});
	                }
	            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#rolegrant-dialog').dialog('close');                    
	                }
	            }]
				});
				
			}
		function convert(rows){
			function exists(rows, jtsort){
				for(var i=0; i<rows.length; i++){
					if (rows[i].jtid == jtsort) return true;
				}
				return false;
			}
			
			var nodes = [];
			// get the top level nodes
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				if (!exists(rows, row.jtsort)){
					nodes.push({
						id:row.jtid,
						text:row.jtname
					});
				}
			}
			
			var toDo = [];
			for(var i=0; i<nodes.length; i++){
				toDo.push(nodes[i]);
			}
			while(toDo.length){
				var node = toDo.shift();	// the parent node
				// get the children nodes
				for(var i=0; i<rows.length; i++){
					var row = rows[i];
					if (row.jtsort == node.id ){
						var child = {id:row.jtid,text:row.jtname};
						if (node.children){
							node.children.push(child);
						} else {
							node.children = [child];
						}
						toDo.push(child);
					}
				}
			}
			return nodes;
		}
	  //全选
	 function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    //反选
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
    //取消
    function checkInverse() {
        var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
        var checknodes = resourceTree.tree('getChecked');
        if (unchecknodes && unchecknodes.length > 0) {
            for ( var i = 0; i < unchecknodes.length; i++) {
                resourceTree.tree('check', unchecknodes[i].target);
            }
        }
        if (checknodes && checknodes.length > 0) {
            for ( var i = 0; i < checknodes.length; i++) {
                resourceTree.tree('uncheck', checknodes[i].target);
            }
        }
    }
	</script>