package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ssh.pojo.Department;
import com.ssh.pojo.Jurisdication;
import com.ssh.pojo.Logf;
import com.ssh.pojo.Roels;
import com.ssh.pojo.Rolejur;
import com.ssh.pojo.Teacher;
import com.ssh.pojo.WorkAssist;
import com.ssh.service.DepartmentService;
import com.ssh.service.LogfService;
import com.ssh.service.RoleService;
import com.ssh.service.TeacherService;
import com.ssh.service.WorkAssistService;

/**
 * 有关Teacher的Action
 * */
@SuppressWarnings("serial")
/*@ParentPackage("json-default")
@Namespace("/")*/
public class TeacherAction extends ActionSupport{
	public TeacherService teacherService;
	public DepartmentService departmentService;
	public	LogfService logfService;
	public  RoleService roleService;
	public  WorkAssistService workAssistService;
	private String tphone;
	private String tpassword;
	private String fail;
	private Teacher teacher;
	private String data;
	private int id;
	private String newpassword;
	private Department department;
	List<Department> listDep;
	private Logf logf;
	private List<Roels> listRoels;
	
	private List<WorkAssist> listWork;
	private List<Jurisdication> listPJuisd;
	private List<Jurisdication> listCJuise1;
	private List<Jurisdication> listCJuise2;
	private List<Jurisdication> listCJuise3;
	private List<Jurisdication> listCJuise4;
	private List<Jurisdication> listCJuise5;
	private List<Jurisdication> listCJuise6;
	private List<Jurisdication> listCJuise7;
	private List<Jurisdication> listCJuise8;
	private List<Jurisdication> listCJuise9;
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	//日志
	/*private static Logger logger = LoggerFactory.getLogger(TeacherAction.class);*/
	
	//得到ip
	public String getIpAddr(HttpServletRequest request) {     
	      String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP");     
	     }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("WL-Proxy-Client-IP");     
	      }     
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	     }     
	     return ip;     
	}

	//判断电话号码输入是否正确
	public String surePhone(){
		Map<String,String> map = new HashMap<>();
		boolean isSure = teacherService.getPhone(tphone);
		if(isSure){
			fail = JSON.toJSONString(map);
		}else{
			map.put("msg", "手机号码不正确!");
			fail = JSON.toJSONString(map);
		}
		return "error";
	}
	 
	//判断密码输入是否正确
	public String surePwd(){
		boolean isSure = teacherService.getPassword(tpassword,tphone);
		Map<String,String> map = new HashMap<>();
		if(isSure){
			fail = JSON.toJSONString(map);
		}else{
			map.put("msg", "密码不正确！");
			fail = JSON.toJSONString(map);
		}
		return "error";
	}
	
	//判断验证码输入是否正确
	public String sureCode(){
		Map<String,String> map = new HashMap<>();
		HttpServletRequest req = ServletActionContext.getRequest();
		String kaptchaExpected = (String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String kaptchaReceived = req.getParameter("kaptcha"); //获取填写的验证码内容
		if(kaptchaReceived == null || !kaptchaReceived.toUpperCase().equalsIgnoreCase(kaptchaExpected.toUpperCase())) {  
			map.put("msg", "验证码错误！");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return "error";
	}
	//后台登录
	public String  adminLogin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		teacher = teacherService.getTeacher(tphone);
		Logf logf = new Logf();
		int tid =teacher.getTid();
		String userid = Integer.toString(teacher.getTid());
		logf.setUserid(userid);
		logf.setUsername(teacher.getTname());
		logf.setIp(this.getIpAddr(request));
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logf.setLogtime(f.format(d));
		logf.setMethod("登录");
		logfService.addJournal(logf);
		listWork = workAssistService.selectWorkAssist(tid);
		
		int rid =teacher.getRoleid();
	    List<Integer> list =roleService.getRolejurList(rid);
		listPJuisd = roleService.seleJurisByJid(list);
		for(int i=0;i<listPJuisd.size();i++){
			Jurisdication jurisdication = listPJuisd.get(i);
			int jid = jurisdication.getJtid();
			if(jid==1){
				listCJuise1 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==2){
				listCJuise2 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==3){
				listCJuise3 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==4){
				listCJuise4 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==5){
				listCJuise5 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==6){
				listCJuise6 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==7){
				listCJuise7 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==8){
				listCJuise8 =roleService.seleJuiisByidChird(jid);
			}
			if(jid==9){
				listCJuise9 =roleService.seleJuiisByidChird(jid);
			}
			
			
		}
		return SUCCESS;
	}
	 
	//修改Teacher密码
	public String updatePassword(){
		boolean isSure = teacherService.updatePasswordById(newpassword,id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(isSure){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//添加Teacher
	public String addTeacher(){
		Date date = new Date();
		/*SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");*/
		teacher.setTpassword("123456");
		/*teacher.setTcreatetime(fmat.format(date));*/
		boolean isSave = teacherService.SaveTeacher(teacher);
		if (isSave) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//遍历教师表
	public  void listTeacher() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = teacherService.getAllCount();
		List<Teacher> lis = teacherService.getTeacherList();
		Department department = new Department();
		Roels roels = new Roels();
		List<Teacher> li = new ArrayList<Teacher>();
		for(int i=0;i<lis.size();i++){
			teacher = lis.get(i);
			Teacher t = new Teacher();
			t.setTid(teacher.getTid());
			t.setTname(teacher.getTname());
			t.setTpassword(teacher.getTpassword());
			t.setTage(teacher.getTage());
			t.setTsex(teacher.getTsex());
			t.setTcreatetime(teacher.getTcreatetime());
			t.setTphone(teacher.getTphone());
			int rid =(teacher.getRoleid());
			roels = roleService.getRoels(rid);
			t.setTtype(roels.getRname());
			t.setTstatus(teacher.getTstatus());
			int did = teacher.getDepartmentid();
			department =departmentService.getDepartment(did);
			t.setDname(department.getDname());
			li.add(t);
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
	
	//遍历教师表(模糊查询)
	//@Action("likeTeachers")
	public  void likeTeacher() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		List<Teacher> lis = teacherService.listLikeTeacher(teacher,page,row);
		Department department = new Department();
		List<Teacher> li = new ArrayList<Teacher>();
		Roels roels = new Roels();
		for(int i=0;i<lis.size();i++){
			teacher = lis.get(i);
			Teacher t = new Teacher();
			t.setTid(teacher.getTid());
			t.setTname(teacher.getTname());
			t.setTpassword(teacher.getTpassword());
			t.setTage(teacher.getTage());
			t.setTsex(teacher.getTsex());
			t.setTcreatetime(teacher.getTcreatetime());
			t.setTphone(teacher.getTphone());
			int rid =(teacher.getRoleid());
			roels = roleService.getRoels(rid);
			t.setTtype(roels.getRname());
			t.setTstatus(teacher.getTstatus());
			int did = teacher.getDepartmentid();
			department =departmentService.getDepartment(did);
			t.setDname(department.getDname());
			li.add(t);
		}
		int count = li.size();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",count);
		m.put("rows",li);
		String mapjson = JSON.toJSONString(m);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}

	//Teacher查找部门表
	//@Action(value="FindListDepartment",results={@Result(type="json",params={"root","data"})})
	public String  findListDepartment(){
		listDep = departmentService.getDepartmentList();
		listRoels = roleService.getRoelsList();
		return SUCCESS;
	}

	//查询Teacher对象
	public void findTeacher() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		teacher = teacherService.getTeacher(id);
		Teacher t = new Teacher();
		t.setTid(teacher.getTid());
		t.setTname(teacher.getTname());
		t.setTpassword(teacher.getTpassword());
		t.setTage(teacher.getTage());
		t.setTsex(teacher.getTsex());
		t.setTphone(teacher.getTphone());
		t.setRoleid(teacher.getRoleid());
		t.setTcreatetime(teacher.getTcreatetime());
		t.setDepartmentid(teacher.getDepartmentid());
		t.setTstatus(teacher.getTstatus());
		String mapjson = JSON.toJSONString(t);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	
	//修改Teacher
	public String updateTeacher(){
		boolean isUpdate = teacherService.UpdateTeacher(teacher);
		if (isUpdate) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//删除Teacher
	public String deleteTeacher(){
		boolean isDelete = teacherService.DeleteTeacher(id);
		if (isDelete) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public String getTpassword() {
		return tpassword;
	}
	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getListDep() {
		return listDep;
	}

	public void setListDep(List<Department> listDep) {
		this.listDep = listDep;
	}

	public Logf getLogf() {
		return logf;
	}

	public void setLogf(Logf logf) {
		this.logf = logf;
	}

	public LogfService getLogfService() {
		return logfService;
	}

	public void setLogfService(LogfService logfService) {
		this.logfService = logfService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Roels> getListRoels() {
		return listRoels;
	}

	public void setListRoels(List<Roels> listRoels) {
		this.listRoels = listRoels;
	}

	public WorkAssistService getWorkAssistService() {
		return workAssistService;
	}

	public void setWorkAssistService(WorkAssistService workAssistService) {
		this.workAssistService = workAssistService;
	}

	public List<WorkAssist> getListWork() {
		return listWork;
	}

	public void setListWork(List<WorkAssist> listWork) {
		this.listWork = listWork;
	}

	public List<Jurisdication> getListPJuisd() {
		return listPJuisd;
	}

	public void setListPJuisd(List<Jurisdication> listPJuisd) {
		this.listPJuisd = listPJuisd;
	}

	public List<Jurisdication> getListCJuise1() {
		return listCJuise1;
	}

	public void setListCJuise1(List<Jurisdication> listCJuise1) {
		this.listCJuise1 = listCJuise1;
	}

	public List<Jurisdication> getListCJuise2() {
		return listCJuise2;
	}

	public void setListCJuise2(List<Jurisdication> listCJuise2) {
		this.listCJuise2 = listCJuise2;
	}

	public List<Jurisdication> getListCJuise3() {
		return listCJuise3;
	}

	public void setListCJuise3(List<Jurisdication> listCJuise3) {
		this.listCJuise3 = listCJuise3;
	}

	public List<Jurisdication> getListCJuise4() {
		return listCJuise4;
	}

	public void setListCJuise4(List<Jurisdication> listCJuise4) {
		this.listCJuise4 = listCJuise4;
	}

	public List<Jurisdication> getListCJuise5() {
		return listCJuise5;
	}

	public void setListCJuise5(List<Jurisdication> listCJuise5) {
		this.listCJuise5 = listCJuise5;
	}

	public List<Jurisdication> getListCJuise6() {
		return listCJuise6;
	}

	public void setListCJuise6(List<Jurisdication> listCJuise6) {
		this.listCJuise6 = listCJuise6;
	}

	public List<Jurisdication> getListCJuise7() {
		return listCJuise7;
	}

	public void setListCJuise7(List<Jurisdication> listCJuise7) {
		this.listCJuise7 = listCJuise7;
	}

	public List<Jurisdication> getListCJuise8() {
		return listCJuise8;
	}

	public void setListCJuise8(List<Jurisdication> listCJuise8) {
		this.listCJuise8 = listCJuise8;
	}

	public List<Jurisdication> getListCJuise9() {
		return listCJuise9;
	}

	public void setListCJuise9(List<Jurisdication> listCJuise9) {
		this.listCJuise9 = listCJuise9;
	}
	
	
	
	

	
}
