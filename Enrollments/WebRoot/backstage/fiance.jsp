<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<div class="easyui-layout" data-options="fit:true">
			    <div data-options="region:'center',border:false">
				<!-- Begin of toolbar -->
					<div id="fiance-toolbar">
						 <div class="wu-toolbar-button">
							<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
							<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="importFinance()" plain="true">上传缴费数据</a>
							<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="ExportFinance()" plain="true">导出当前数据</a>
					  	 </div>
						<div class="wu-toolbar-search">
							<!-- <label>学生姓名：</label>
							<input class="wu-text" id="likeSname" name="fiance.sname" style="width:100px">
							 -->
							<label>缴费方式：</label>
							<input class="wu-text" id="likeFeway" name="fiance.feway" style="width:100px">
							<label>缴费状态：</label>
							<input class="wu-text" id="likeSfestate" name="fiance.sfestate" style="width:100px">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="startSelect()">开始检索</a>
						</div>
				</div>
				<!-- End of toolbar -->
				<table id="fiance-datagrid" toolbar="#fiance-toolbar"></table>
				<!-- 得到Teacher的电话号码 -->
				<div id="fianceDele" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">确定你要删除的内容!</div>
			</div>
	   	</div>
	   	<!-- 表格内容 -->
			<div id="fiance-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <form  method="post"  id="fiance-form">
                	 <input type="hidden" name="fiance.feid" id="feid" />
					<font stype="display:none" id="textSname">学生姓名:</font>
					<select name="fiance.studentid" id="studentid" style="width:140px;height:25px;display:none">
						<s:iterator value="listStuname" >
							<option value="<s:property value="sid"/>"><s:property value="sname"/></option>
						</s:iterator>
					</select>
					<br><br>
					需缴金额：<input type="text" name="fiance.feneeddmoney" id="feneeddmoney"  style="width:140px;height:25px" />
					<br><br>
					实缴金额：<input  name="fiance.frpractical" id="frpractical" style="width:140px;height:25px" />
					<br><br>
					缴费时间：<input name="fiance.fedate" id="fedate" class="easyui-datetimebox" style="width:145px;height:30px;" />				
					<br><br>
					缴费方式：
					<select name="fiance.feway" id="feway" style="width:140px;height:25px;">
								<option value="支付宝">支付宝</option>
								<option value="现金">现金</option>
								<option value="微信">微信</option>
				    </select>
					<br>
					<input type="hidden" id="stuid" name="stuid">
					<br>
					缴费状态:
					<select name="fiance.sfestate" id="sfestate" style="width:140px;height:25px">
						<option value="已缴费">已缴费</option>
						<option value="未缴费">未缴费</option>
						<option value="未缴完">未缴完</option>
					</select>
				</form>  
			</div>
	<!-- 导入弹出框 -->
	<div id="into-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
                <form  method="post"  id="into-form" enctype="multipart/form-data">
                	上传文件:<input type="file" name="uploadFile" id="uploadFile" /> &nbsp;<input type="button" onclick="loadModel()" value="下载模板"/>
				</form>  
			</div>
	<!--弹出框方法和操作 -->
	<script type="text/javascript">
		$('#fiance-datagrid').datagrid({
			url:'/Enrollment/ListFiance.action',
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:10,
			pageList:[10,20,30],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'sname',title:'学生姓名',width:20,sortable:true},
				{ field:'feneeddmoney',title:'需缴金额',width:15,sortable:true},
				{ field:'frpractical',title:'实缴金额',width:15},
				{ field:'fedate',title:'缴费时间',width:30},
				{ field:'feway',title:'缴费方式',width:30},
				{ field:'sacontent',title:'专业',width:30},
				{ field:'sstype',title:'报名类型',width:30},
				{ field:'sfestate',title:'缴费状态',width:30},
				{ field:'tname',title:'班主任',width:30},
				{ field:'_operate',title:'操作',width:50,align:'center',formatter:formatOper},
			]]
		});
		function formatOper(val,row,index){
			return '<input type="button" onclick="openPrint()" value="打印" style="background-color:#ffffcc;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="openEdit()" value="编辑" style="background-color:#ccffff;border:none;width:50px;height:20px" />&nbsp;&nbsp<input type="button"  onclick="remove()" value="删除" style="background-color:#ffcccc;border:none;width:50px;height:20px" />'
		}
		/**
		* Name 添加记录
		*/
		function add(){
			$('#fiance-form').form('submit', {
				url:'/Enrollment/AddFiance.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#fiance-datagrid').datagrid('load');
						$('#fiance-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#fiance-datagrid').datagrid('load');
						$('#fiance-dialog').dialog('close');
					}
				}
			});
		}
		
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#fiance-form').form('submit', {
			url:'/Enrollment/UpdateFiance.action',
			success:function(data){
				if(data!={}){
					$.messager.alert('信息提示','提交成功！','info');
                    $('#fiance-datagrid').datagrid('load');
					$('#fiance-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
					$('#fiance-datagrid').datagrid('load');
					$('#fiance-dialog').dialog('close');
				}
			}
		});
	}
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#fiance-datagrid').datagrid('getSelected');
		var feid =item.feid;
		$('#fianceDele').dialog({
				closed: false,
				modal:true,
	            title: "删除框",
	         	closed: false,
            	 buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function(){
                		$.ajax({
							url:'/Enrollment/DeleteFiance.action',
							data:{'id':feid},
							dataType:'json',
							success:function(data){
								if(data!="{}"){
									$.messager.alert('信息提示','删除成功！','info');
									$('#fiance-datagrid').datagrid('load');
                                    $('#fianceDele').dialog('close');
								}
								else
								{
									$.messager.alert('信息提示','删除失败！','info');
									$('#fiance-datagrid').datagrid('load');
                                    $('#fianceDele').dialog('close');		
								}
							}	
						});
	                	}
		            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#fianceDele').dialog('close');                    
	                }
		            }] 
			}); 
		}
		
		function openPrint(){
			window.print();
		}
	
		/**
		* Name 打开添加窗口
		*/
		function openAdd(){
			$('#textSname').show();
			$('#studentid').show();
			$('#fiance-form').form('clear');
			$('#fiance-dialog').dialog({
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
	                   $('#fiance-dialog').dialog('close');                    
	            	}
           		}] 
       		});
		}
		/**
		* Name 打开修改窗口
		*/
	function openEdit(){
		$('#showTitle').show();
		$('#tcreatetime').show();
		var item = $('#fiance-datagrid').datagrid('getSelected');
		var feid =item.feid;
		$('#textSname').hide();
		$('#studentid').hide();
		$.ajax({
			url:'/Enrollment/FindFiance.action',
			data:{'id':feid},
			dataType:'json',
			success:function(data){
					if(data!="{}"){
						document.getElementById("feid").value=data.feid;
						document.getElementById("feneeddmoney").value=data.feneeddmoney;
						document.getElementById("frpractical").value=data.frpractical;
						document.getElementById("fedate").value=data.fedate;
						document.getElementById("feway").value=data.feway;
						document.getElementById("sfestate").value=data.sfestate;
						document.getElementById("stuid").value=data.studentid;
						$('#fiance-form').form('load',data);
				}
				else{
					$('#fiance-dialog').dialog('close');
				}
			}	
		}); 
		$('#fiance-dialog').dialog({
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
                    $('#fiance-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
		模糊查询
	*/
	function startSelect(){
		var sname = $('#likeSname').val();
		var feway = $('#likeFeway').val();
		var sfestate = $('#likeSfestate').val();
		$('#fiance-datagrid').datagrid({
			url:'/Enrollment/likeFiances.action?fiance.sname='+sname+'&fiance.feway='+feway+'&fiance.sfestate='+sfestate,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:10,
			pageList:[10,20,30],           
			pagination:true,
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'sname',title:'学生姓名',width:20,sortable:true},
				{ field:'feneeddmoney',title:'需缴金额',width:15,sortable:true},
				{ field:'frpractical',title:'实缴金额',width:15},
				{ field:'fedate',title:'缴费时间',width:30},
				{ field:'feway',title:'缴费方式',width:30},
				{ field:'sacontent',title:'专业',width:30},
				{ field:'sstype',title:'报名类型',width:30},
				{ field:'sfestate',title:'缴费状态',width:30},
				{ field:'tname',title:'班长任',width:30},
				{ field:'_operate',title:'操作',width:50,align:'center',formatter:formatOper},
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
		//导出
		function ExportFinance(){
			var data = JSON.stringify($('#fiance-datagrid').datagrid('getData').rows);
			if (data == '')
				return;
			JSONToCSVConvertor(data, "Download", true);
		}
		//导入
		function importFinance(){
			$('#into-dialog').dialog({
				closed: false,
				modal:true,
	            title: "上传缴费表",
	         	closed: false,
				modal:true,
	           	title: "添加信息",
	           	 buttons: [{
	               text: '导入',
	               iconCls: 'icon-ok',
	               handler: intofinance
	           	}, {
	               text: '取消',
	               iconCls: 'icon-cancel',
	               handler: function () {
	                   $('#into-dialog').dialog('close');                    
	            	}
           		}] 
       		});
		}
		
		//导入
		function intofinance(){
			$('#into-form').form('submit', {
				url:'/Enrollment/ImportFiance.action',
				success:function(data){
					if(data!="{}"){
						$.messager.alert('信息提示','提交成功！','info');
                        $('#fiance-datagrid').datagrid('load');
						$('#into-dialog').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
						$('#fiance-datagrid').datagrid('load');
						$('#into-dialog').dialog('close');
					}
				}
			});
		}
		//下载模板
		function loadModel(){
			location.href="/Enrollment/fianceUpload.action?fileName=Fiance.xls";
		}
	</script>