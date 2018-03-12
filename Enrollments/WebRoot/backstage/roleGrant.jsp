<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- 树 --> 
 	<div id="rolegrant-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:600px;height:600px;padding:10px;">
		<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    		<div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
		        <div class="well well-small">
		            <form id="roleGrantForm" method="post">
		            	<input type="hidden" name="rid" id="rid" value='<s:property value="%{roels.rid}"/>'/>
		                <ul id="resourceTree"></ul>
		                <input id="resourceIds" name="resourceIds" type="hidden" />
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
	<script type="text/javascript">
		var resourceTree;
			$(document).ready(function(){
					var resourceTree;
			//点击授权窗口
		
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
			/* location.href="/Enrollment/rolegrant.action?id="+rid; */
			$('#rolegrant-dialog').dialog({
				closed: false,
				modal:true,
	            title: "系统资源",
	            buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: grant
	            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#rolegrant-dialog').dialog('close');                    
	                }
	            }]
				});
			});
	
				
		//对授权窗口进行修改
		function grant(){
			 var checknodes = resourceTree.tree('getChecked');
             var ids = [];
             if (checknodes && checknodes.length > 0) {
                 for ( var i = 0; i < checknodes.length; i++) {
                     ids.push(checknodes[i].id);
                 }
             }
             else{
             	ids=null;
             }
             $('#resourceIds').val(ids);
             alert( $('#resourceIds').value);
             $('#roleGrantForm').form('submit',{
				url:'/Enrollment/AddGrant.action',
				success:function(data){
					alert(data);
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
	
	 function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
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