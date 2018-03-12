<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<div class="easyui-layout" data-options="fit:true">
			    <div data-options="region:'center',border:false">
				<!-- Begin of toolbar -->
				<div id="teacher-toolbar">
					 <div class="wu-toolbar-button">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				  	 </div>
					<div class="wu-toolbar-search">
						<label>姓名：</label>
						<input class="wu-text" id="likeTname" name="teacher.tname" style="width:50px">
						<label>性别:</label>
						<select  class="wu-text" name="teacher.tsex" id="likeTsex" style="width:100px;height:25px;">
							<option value="">--请选择--</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
						<label>年龄:</label>
	   					<input  class="wu-text" type="text" name="teacher.tage" id="likeTage" style="width:50px"/>
						<label>电话：</label>
						<input class="wu-text" id="likeTphone" name="teacher.tphone" style="width:70px">
						<label>类型:</label>
						<select class="wu-text" name="teacher.roleid" id="likeTtype" style="width:100px;height:25px;">
							<option value="">--请选择--</option>
							<s:iterator value="listRoels">
								<option value="<s:property value="rid"/>"><s:property value="rname"/></option>
							</s:iterator>
						</select>
						<label>状态:</label>
		   				<select  class="wu-text" name="teacher.tstatus" id="likeTstatus" style="width:100px;height:25px">
							<option value="">--请选择--</option>
							<option value="正常">正常</option>
							<option value="停用">停用</option>
						</select>
						<label>部门:</label>
		   				<select  class="wu-text" name="teacher.departmentid" id="likeDname" style="width:100px;height:25px">
		   					<option value="">--请选择--</option>
		   					<s:iterator value="listDep">
								<option value="<s:property value="did"/>"><s:property value="dname"/></option>
							</s:iterator>
		   				</select>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
					</div>
				</div>
				<!-- End of toolbar -->
				<table id="teacher-datagrid" toolbar="#teacher-toolbar"></table>
				<!-- 得到Teacher的电话号码 -->
				<div id="teacherDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
			</div>
	   	</div>
	   	<!-- 表格内容 -->
			<div id="teacher-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <form  method="post"  id="teacher-form">
                	<s:hidden type="hidden" name="teacher.tid" id="tids" />
                	<s:hidden type="hidden" name="teacher.tpassword" id="tpassword"  />
                	用户姓名:
                	<input type="text" name="teacher.tname" id="tnames" style="width:140px;height:25px"/>
                	<br><br>
					用户性别:
					<select name="teacher.tsex" id="tsex" style="width:140px;height:25px;">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
	   				<br><br>
	   				用户年龄:
	   				<input type="text" name="teacher.tage" id="tage" style="width:140px;height:25px"/>
	   				<br><br>
	   				用户电话:
	   				<input type="text" name="teacher.tphone" id="ttphone" style="width:140px;height:25px"/>
	   				<br><br>
   					创建时间:
					<input type="text" class="easyui-datetimebox" style="width:145px;height:30px;" name="teacher.tcreatetime" id="tcreatetimes"/>
	   				<br><br>
	   				用户类型:
	   				<select name="teacher.roleid" id="roleid" style="width:140px;height:25px;">
						<s:iterator value="listRoels">
							<option value="<s:property value="rid"/>"><s:property value="rname"/></option>
						</s:iterator>
					</select>
	   				<br><br>
	   				用户状态:
	   				<select name="teacher.tstatus" id="tstatus" style="width:140px;height:25px">
						<option value="正常">正常</option>
						<option value="停用">停用</option>
					</select>
	   				<br><br>
	   				用户部门:
	   				<select name="teacher.departmentid" id="departmentid" style="width:140px;height:25px">
	   					<s:iterator value="listDep">
							<option value="<s:property value="did"/>"><s:property value="dname"/></option>
						</s:iterator>
	   				</select>
				</form>  
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		/**
		* 弹出框内容
		*/
		$('#teacher-datagrid').datagrid({
			url:'/Enrollment/ListTeacher.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'tname',title:'用户姓名',width:20,sortable:true},
				{ field:'tsex',title:'用户性别',width:15,sortable:true},
				{ field:'tage',title:'用户年龄',width:15},
				{ field:'tphone',title:'用户电话',width:20},
				{ field:'ttype',title:'用户类型',width:20},
				{ field:'dname',title:'用户部门',width:20},
				{ field:'tstatus',title:'用户状态',width:20},
				{ field:'tcreatetime',title:'创建时间',width:30},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
	/**
	模糊查询
	*/
	function startSelect(){
		var name = $('#likeTname').val();
		var sex = $('#likeTsex').val();
		var age = $('#likeTage').val();
		var phone = $('#likeTphone').val();
		var type = $('#likeTtype').val();
		var status = $('#likeTstatus').val();
		var did = $('#likeDname').val();
		$('#teacher-datagrid').datagrid({
			url:'/Enrollment/likeTeachers.action?teacher.tname='+name+'&teacher.tsex='+sex+'&teacher.tage='+age+'&teacher.tphone='+phone+'&teacher.roleid='+type+'&teacher.tstatus='+status+'&teacher.departmentid='+did,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,
			pageList:[20,40,60],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'tname',title:'用户姓名',width:20,sortable:true},
				{ field:'tsex',title:'用户性别',width:15,sortable:true},
				{ field:'tage',title:'用户年龄',width:15},
				{ field:'tphone',title:'用户电话',width:20},
				{ field:'ttype',title:'用户类型',width:20},
				{ field:'dname',title:'用户部门',width:20},
				{ field:'tstatus',title:'用户状态',width:20},
				{ field:'tcreatetime',title:'创建时间',width:30},
				{ field:'_operate',title:'操作',width:20,align:'center',formatter:formatOper},
			]]
		});
	}
		function formatOper(val,row,index){
			return '<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#teacher-form').form('submit', {
				url:'/Enrollment/AddTeacher.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#teacher-datagrid').datagrid('load');
						$('#teacher-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#teacher-datagrid').datagrid('load');
						$('#teacher-dialog').dialog('close');
					}
				}
			});
		}
		
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#teacher-form').form('submit', {
			url:'/Enrollment/UpdateTeacher.action',
			success:function(data){
				if(data!='"{}"'){
					$.messager.alert('信息提示','提交成功！','info');
                    $('#teacher-datagrid').datagrid('load');
					$('#teacher-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#teacher-datagrid').datagrid('load');
					$('#teacher-dialog').dialog('close');
				}
			}
		});
	}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#teacher-datagrid').datagrid('getSelected');
		var tid =item.tid;
		$('#teacherDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteTeacher.action',
							data:{'id':tid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#teacher-datagrid').datagrid('load');
                                    $('#teacherDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#teacher-datagrid').datagrid('load');
                                    $('#teacherDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#teacherDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#teacher-form').form('clear');
			$('#teacher-dialog').dialog({
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
	                   $('#teacher-dialog').dialog('close');                    
	            	}
           		}] 
       		});
		}
		/**
		* Name 打开修改窗口
		*/
	function openEdit(){
		var item = $('#teacher-datagrid').datagrid('getSelected');
		var tid =item.tid;
		$.ajax({
			url:'/Enrollment/FindTeacher.action',
			data:{'id':tid},
			dataType:'json',
			success:function(data){
					if(data!="{}"){
					$("#tids").attr("value",data.tid);
					$('#tnames').val(data.tname);
					$('#tage').val(data.tage);
					$('#tpassword').val(data.tpassword);
					$('#tsex').val(data.tsex);
					$('#ttphone').val(data.tphone);
					$("#tcreatetimes").attr("value",data.tcreatetime);
					$('#roleid').val(data.roleid);
					$('#tstatus').val(data.tstatus);
					$('#departmentid').val(data.departmentid);
					$('#teacher-form').form('load',data);
				}
				else{
					$('#teacher-dialog').dialog('close');
				}
			}	
		}); 
		$('#teacher-dialog').dialog({
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
                    $('#teacher-dialog').dialog('close');                    
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