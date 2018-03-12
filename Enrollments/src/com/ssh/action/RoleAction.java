package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Jurisdication;
import com.ssh.pojo.Roels;
import com.ssh.pojo.Rolejur;
import com.ssh.service.DepartmentService;
import com.ssh.service.RoleService;
import com.ssh.service.TeacherService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/")

public class RoleAction extends ActionSupport{
	
	private RoleService roleService;
	private TeacherService teacherService;
	private DepartmentService departmentService;
	
	private Roels roels;
	private String data;
	private int id;
	private int rid;
	private Jurisdication jurisdication;
	private Rolejur rolejur;
	private List<Integer> obj;
	Map<String,Object> map = new HashMap<String,Object>();
	
	//遍历角色表
	@Action(value="ListRole")
	public void listRole() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = roleService.getAllCount();
		List<Roels> lis = roleService.getRoelsList();
		List<Roels> li = new ArrayList<Roels>();
		for(int i=0;i<lis.size();i++){
			roels = lis.get(i);
			Roels r = new Roels();
			r.setRid(roels.getRid());
			r.setRname(roels.getRname());
			r.setRdes(roels.getRdes());
			r.setRstatus(roels.getRstatus());
			li.add(r);
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
	//添加角色表
	@Action(value="AddRole",results={@Result(name="success",type="json",params={"root","data"})})
	public String addRole(){
		boolean isSave = roleService.SaveRoels(roels);
		if (isSave) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//得到角色表对象
	@Action(value="FindRole")
	public void findRole() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		roels = roleService.getRoels(id);
		Roels r = new Roels();
		r.setRid(roels.getRid());
		r.setRname(roels.getRname());
		r.setRdes(roels.getRdes());
		r.setRstatus(roels.getRstatus());
		String mapjson = JSON.toJSONString(r);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	//修改角色表
	@Action(value="UpdateRole",results={@Result(name="success",type="json",params={"root","data"})})
	public String updateRole(){
		boolean isUpdate = roleService.UpdateRoels(roels);
		if (isUpdate) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//删除角色表
	@Action(value="DeleteRole",results={@Result(name="success",type="json",params={"root","data"})})
	public String deleteRole(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		boolean isDelete = roleService.DeleteRoels(id);
		if (isDelete) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//跳转权限页面
	@Action(value="rolegrant",results={@Result(name="success",location="/backstage/roleGrant.jsp")})
	public String rolegrant(){
		roels = roleService.getRoels(id);
		return SUCCESS;
	}
	
	//遍历权限表
	@Action(value="AllTrees")
	public void allTrees() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Jurisdication> list = roleService.getJurisdicationList();
		List<Jurisdication> li = new ArrayList<Jurisdication>();
		for(int i=0;i<list.size();i++){
			jurisdication = list.get(i);
			Jurisdication j = new Jurisdication();
			j.setJtid(jurisdication.getJtid());
			j.setJtname(jurisdication.getJtname());
			j.setJtsort(jurisdication.getJtsort());
			j.setJtstatus(jurisdication.getJtstatus());
			li.add(j);
		}
		String mapjson = JSON.toJSONString(li);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	//根据角色id查找权限id
	@Action(value="findListByRoleId")
	public void findResourceIdListByRoleId() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Integer> list = roleService.getRolejurList(id);
		String mapjson = JSON.toJSONString(list);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	//修改权限表
	@Action(value="AddGrant",results={@Result(name="success",type="json",params={"root","data"})})
	public String addGrant(){
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		Integer rid =Integer.parseInt(req.getParameter("rolesrid"));
		boolean isSave =false;
		String jid = req.getParameter("resourceIds");
		String notjid = req.getParameter("notchecknodes");
		if(jid==""&&notjid!=""){
			String[] notstrarr = notjid.split(",");
			Integer[] notids = new Integer[notstrarr.length];
				for(int i=0;i<notstrarr.length;i++){
					notids[i]=Integer.parseInt(notstrarr[i]);
				}	
			 isSave = roleService.updateNotRolejur(rid,notids);
		}else if(jid!=""&&notjid==""){
			String[] strarr = jid.split(",");
			Integer[] ids = new Integer[strarr.length];
				for(int i=0;i<strarr.length;i++){
					ids[i]=Integer.parseInt(strarr[i]);
				}	
			 isSave = roleService.saveRolejur(rid,ids);
		}else if(jid!=""&& notjid!=""){
			String[] notstrarr = notjid.split(",");
			String[] strarr = jid.split(",");
			Integer[] ids = new Integer[strarr.length];
				for(int i=0;i<strarr.length;i++){
					ids[i]=Integer.parseInt(strarr[i]);
				}
			Integer[] notids = new Integer[notstrarr.length];
			for(int i=0;i<notstrarr.length;i++){
				notids[i]=Integer.parseInt(notstrarr[i]);
			}
			isSave = roleService.updateRolejur(rid, ids, notids);
		}
		if (isSave) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
		
	}
	public Roels getRoels() {
		return roels;
	}
	public void setRoels(Roels roels) {
		this.roels = roels;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Jurisdication getJurisdication() {
		return jurisdication;
	}

	public void setJurisdication(Jurisdication jurisdication) {
		this.jurisdication = jurisdication;
	}

	public Rolejur getRolejur() {
		return rolejur;
	}

	public void setRolejur(Rolejur rolejur) {
		this.rolejur = rolejur;
	}

	public List<Integer> getObj() {
		return obj;
	}
	public void setObj(List<Integer> obj) {
		this.obj = obj;
	}
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	
	
	

	

}
