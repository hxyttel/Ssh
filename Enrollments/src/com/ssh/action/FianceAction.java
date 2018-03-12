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

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Finance;
import com.ssh.pojo.Student;
import com.ssh.pojo.Teacher;
import com.ssh.service.FianceService;
import com.ssh.service.StudentService;
import com.ssh.service.TeacherService;

@SuppressWarnings("serial")
public class FianceAction extends ActionSupport{
	private FianceService fianceService;
	private StudentService studentService;
	private TeacherService teacherService;
	private int tid;
	private List<Student> listStuname;
	private Finance fiance;
	private String data;
	private int id;
	private int stuid;
	Map<String,Object> map = new HashMap<String, Object>();
	public Student student;

	//根据老师id查询学生
	public String TeaLookStu(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String tid =request.getParameter("tid");
		int id = Integer.parseInt(tid);
		if(id==1){
			listStuname = studentService.getStuStype();
		}else{
			listStuname = studentService.getTeacherList(Integer.parseInt(tid));
		}
		return SUCCESS;
		
	}
	
	//遍历缴费
	public void listFiance() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = fianceService.getAllCount();
		List<Finance> lis = fianceService.getFinanceList();
		List<Finance> li = new ArrayList<Finance>();
		for(int i=0;i<lis.size();i++){
			fiance = lis.get(i);
			Finance f = new Finance();
			f.setFedate(fiance.getFedate());
			f.setFeid(fiance.getFeid());
			f.setFeneeddmoney(fiance.getFeneeddmoney());
			f.setFrpractical(fiance.getFrpractical());
			f.setFeway(fiance.getFeway());
			int sid =fiance.getStudentid();
			Student student = studentService.getStudentById(sid);
			String festate=student.getFestate();
			String acontent=student.getAcontent();
			String stype=student.getStype();
			f.setSfestate(festate);
			f.setSacontent(acontent);
			f.setSstype(stype);
			f.setSname(student.getSname());
		
			int tid=student.getTeacherid();
			Teacher teacher = teacherService.getTeacher(tid);
			f.setTname(teacher.getTname());
			li.add(f);
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
	//遍历缴费(模糊查询)
	public void likeFiance() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		String sname = fiance.getSname();
		int tids = studentService.getIdByName(sname);
		fiance.setStudentid(tids);
		List<Finance> lis = fianceService.listLikeFinance(fiance,page,row);
		List<Finance> li = new ArrayList<Finance>();
		for(int i=0;i<lis.size();i++){
			fiance = lis.get(i);
			Finance f = new Finance();
			f.setFedate(fiance.getFedate());
			f.setFeid(fiance.getFeid());
			f.setFeneeddmoney(fiance.getFeneeddmoney());
			f.setFrpractical(fiance.getFrpractical());
			f.setFeway(fiance.getFeway());
			int sid =fiance.getStudentid();
			Student student = studentService.getStudentById(sid);
			String festate=student.getFestate();
			String acontent=student.getAcontent();
			String stype=student.getStype();
			f.setSfestate(festate);
			f.setSacontent(acontent);
			f.setSstype(stype);
			f.setSname(student.getSname());
		
			int tid=student.getTeacherid();
			Teacher teacher = teacherService.getTeacher(tid);
			f.setTname(teacher.getTname());
			li.add(f);
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
	//添加缴费
	public String addFiance(){
		boolean isSave = fianceService.SaveFinance(fiance);
		if (isSave) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
			int sid =fiance.getStudentid();
			Student stu =studentService.getStudentById(sid);
			stu.setFestate(fiance.getSfestate());
			studentService.update(stu);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//查找缴费对象
	public void findFiance() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		fiance = fianceService.getFinance(id);
		Finance f = new Finance();
		f.setFedate(fiance.getFedate());
		f.setFeid(fiance.getFeid());
		f.setFeneeddmoney(fiance.getFeneeddmoney());
		f.setFrpractical(fiance.getFrpractical());
		f.setFeway(fiance.getFeway());
		f.setSfestate(fiance.getSfestate());
		f.setStudentid(fiance.getStudentid());
		String mapjson = JSON.toJSONString(f);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	
	//修改缴费
	public String updateFiance(){
		fiance.setStudentid(stuid);
		boolean isUpdate = fianceService.UpdateFinance(fiance);
		if (isUpdate) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
			int sid =fiance.getStudentid();
			Student stu =studentService.getStudentById(sid);
			stu.setFestate(fiance.getSfestate());
			studentService.update(stu);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//删除缴费
	public String deleteFiance(){
		boolean isDelete = fianceService.DeleteFinance(id);
		if (isDelete) {
			map.put("msg","true");
			data = JSON.toJSONString(map);
		} else {
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//遍历缴费(在前台首页获取信息)
	public void listFiancePage() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Student> slist = studentService.getStudentList(tid);
		List<Student> li = new ArrayList<Student>();
		for(int i=0;i<slist.size();i++){
			student = slist.get(i);
			Finance f = fianceService.getFinanceBySid(student.getSid());
			if(f!=null){
				Student stu = new Student();
				stu.setSname(student.getSname());
				stu.setStype(student.getStype());
				stu.setAcontent(student.getAcontent());
				stu.setFestate(student.getFestate());
				Teacher t = teacherService.getTeacher(student.getTeacherid());
				stu.setTname(t.getTname());
				stu.setFeneeddmoney(f.getFeneeddmoney());
				stu.setFedate(f.getFedate());
				stu.setFeway(f.getFeway());
				stu.setFrpractical(f.getFrpractical());
				li.add(stu);
			}
		}
		int count = li.size();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",count);
		m.put("row",li);
		m.put("data", li);
		String mapjson = JSON.toJSONString(m);
		PrintWriter out = resp.getWriter();
		out.print(mapjson);
		out.flush();
		out.close();
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FianceService getFianceService() {
		return fianceService;
	}

	public void setFianceService(FianceService fianceService) {
		this.fianceService = fianceService;
	}
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public List<Student> getListStuname() {
		return listStuname;
	}
	public void setListStuname(List<Student> listStuname) {
		this.listStuname = listStuname;
	}

	public Finance getFiance() {
		return fiance;
	}

	public void setFiance(Finance fiance) {
		this.fiance = fiance;
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

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

}
