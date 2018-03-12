package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Department;
import com.ssh.service.DepartmentService;

@SuppressWarnings("serial")
public class DepartmentAction extends ActionSupport {

	private DepartmentService departmentService;
	private String data;
	private Department department;
	private int id;
	Map<String, Object> map = new HashMap<String, Object>();

	// 添加部门表
	public String addDepartment() {
		Date date = new Date();
		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		department.setDdatetime(fmat.format(date));
		boolean isSave = departmentService.SaveDepartment(department);
		if (isSave) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}

	// 遍历部门表
	public void listDepartment() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = departmentService.getAllCount();
		List<Department> lis = departmentService.getDepartmentList();
		List<Department> li = new ArrayList<Department>();
		for(int i=0;i<lis.size();i++){
			department = lis.get(i);
			Department dep = new Department();
			dep.setDid(department.getDid());
			dep.setDnumber(department.getDnumber());
			dep.setDname(department.getDname());
			dep.setDdatetime(department.getDdatetime());
			dep.setDaddress(department.getDaddress());
			li.add(dep);
		}
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",count);
		m.put("rows",li);
		String mapjson = JSON.toJSONString(m);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	
	//查询部门对象
	public void findDepartment() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		Department department = departmentService.getDepartment(id);
		Department dep = new Department();
		dep.setDid(department.getDid());
		dep.setDnumber(department.getDnumber());
		dep.setDname(department.getDname());
		dep.setDdatetime(department.getDdatetime());
		dep.setDaddress(department.getDaddress());
		String mapJson = JSON.toJSONString(dep);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}

	
	//修改部门表
	public String updateDepartment(){
		boolean isUpdate = departmentService.UpdatDepartment(department);
		if (isUpdate) {
			map.put("msg", "true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//删除部门表
	public String deleteDepartment(){
		boolean isDelete = departmentService.DeleteDepartment(id);
		if (isDelete) {
			map.put("msg", "true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//根据输入条件模糊查询联系表
	public void getlikeDepartment() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp =ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		List<Department> lis = departmentService.listLikeDepartment(department,page,row);
		List<Department> li = new ArrayList<Department>();
		for(int i=0;i<lis.size();i++){
			department = lis.get(i);
			Department dep = new Department();
			dep.setDid(department.getDid());
			dep.setDnumber(department.getDnumber());
			dep.setDname(department.getDname());
			dep.setDdatetime(department.getDdatetime());
			dep.setDaddress(department.getDaddress());
			li.add(dep);
		}
		int count = lis.size();
		map.put("total",count);
		map.put("rows",li);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
