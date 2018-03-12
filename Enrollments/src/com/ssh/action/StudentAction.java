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
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Student;
import com.ssh.pojo.Teacher;
import com.ssh.service.AcademySerice;
import com.ssh.service.StudentService;
import com.ssh.service.TeacherService;
import com.ssh.util.sendsms;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/")
public class StudentAction extends ActionSupport{
	private Student student;
	private StudentService studentService;
	private TeacherService teacherService;
	private AcademySerice academyService;
	private String fail;
	private List<Teacher> listTeacher;
	private List<Academy> listAcademy;
	
	//注册(向学生表中添加数据)
	@Action(value="register",results={@Result(name="success",type="redirectAction",location="upload.action"),
									  @Result(name="error",type="redirectAction",location="register.action")})
	public String Register(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String reg = "error";
		Integer tid = student.getTeacherid();
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		student.setFestate("未缴费");
		student.setSdate(f.format(d));
		if(tid==null){
			student.setTeacherid(1);
		}else{
			student.setTeacherid(tid);
		}
		boolean isSave=studentService.add(student);
		HttpSession session = request.getSession();
		if(isSave){
			session.setAttribute("student", student);
			session.setAttribute("signMsg", "*报名成功"+student.getSname());
			reg = "success";
		}else{
			session.setAttribute("signMsg", "*报名失败"+student.getSname());
		}
		return reg;
	}
	
	//移除session中的值(当跳出报名成功后便移除success)
	public void removeSign(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("signMsg");
	}
	
	//判断电话号码是否存在
	public String surePhone(){
		Map<String,String> map = new HashMap<>();
		System.out.println(student.getSphone());
		boolean isSure = studentService.getPhone(student.getSphone());
		if(isSure){
			map.put("msg", "手机号码已存在!");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return "error";
	}
	//判断身份证号是否重复
	public String sureIdcard(){
		Map<String,String> map = new HashMap<>();
		boolean isSure = studentService.getSnumber(student.getSnumber());
		if(isSure){
			map.put("msg", "身份证号已使用!");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return "error";
	}
	//手机验证码
	@Action(value="getranks",results={@Result(name="success",location="/accounting.jsp")})
	public String getRanks(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("sphone");
		String code = request.getParameter("code");
		
		sendsms sMs = new sendsms();
		System.out.println(code);
		sMs.senMessage(Integer.parseInt(code),phone );
		return SUCCESS;
	}
	
	//查询遍历student中的艺考报名的list
	public void getArtList(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Student> stuList = studentService.getStudentList();
		Teacher teacher = new Teacher();
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			student = stuList.get(i);
			if(student.getSnumber()==null){
				Student students = new Student();
				students.setSid(student.getSid());
				students.setStuno(student.getStuno());
				students.setSname(student.getSname());
				students.setSphone(student.getSphone());
				students.setAcontent(student.getAcontent());
				students.setSdate(student.getSdate());
				students.setStype(student.getStype());
				students.setTeacherid(student.getTeacherid());
				students.setFestate(student.getFestate());
				teacher = teacherService.getTeacher(student.getTeacherid());
				students.setTname(teacher.getTname());
				list.add(students);
			}
		}
		int countArt = studentService.getTrainCount();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",countArt);
		m.put("rows",list);
		String mapjson = JSON.toJSONString(m);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapjson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询学生表中教育考试报名的学生list
	public void getEducationList(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Student> stuList = studentService.getStudentLists();
		Teacher teacher = new Teacher();
		Academy academy = new Academy();
		List<Student> lists = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			student = stuList.get(i);
			if(student.getSnumber()!=null){
				Student students = new Student();
				students.setSid(student.getSid());
				students.setStuno(student.getStuno());
				students.setSname(student.getSname());
				students.setSphone(student.getSphone());
				students.setAcontent(student.getAcontent());
				students.setSdate(student.getSdate());
				students.setStype(student.getStype());
				students.setFestate(student.getFestate());
				students.setTeacherid(student.getTeacherid());
				teacher = teacherService.getTeacher(student.getTeacherid());
				students.setTname(teacher.getTname());
				students.setSnumber(student.getSnumber());
				students.setAcademyid(student.getAcademyid());
				academy = academyService.getAcademyById(student.getAcademyid());
				students.setAname(academy.getAname());
				students.setSgradations(student.getSgradations());
				students.setSsystem(student.getSsystem());
				lists.add(students);
			}
		}
		int countEducation = studentService.getEducationCount();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",countEducation);
		m.put("rows",lists);
		String mapjson = JSON.toJSONString(m);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapjson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询遍历student中的艺考报名的list(模糊查询)
	@Action(value="likeTrain")
	public void getLikeArtList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		String tname = student.getTname();
		int tid = 0;
		if(tname=="" || tname==null){
			tid=0;
		}else if(tname.isEmpty()){
			tid=0;
		}else{
			tid = teacherService.getIdByName(tname);
		}
		student.setTeacherid(tid);
		List<Student> stuList = studentService.listLikeTrain(student,page,row);
		Teacher teacher = new Teacher();
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			student = stuList.get(i);
			if(student.getSnumber()==null){
				Student students = new Student();
				students.setSid(student.getSid());
				students.setStuno(student.getStuno());
				students.setSname(student.getSname());
				students.setSphone(student.getSphone());
				students.setAcontent(student.getAcontent());
				students.setSdate(student.getSdate());
				students.setStype(student.getStype());
				students.setTeacherid(student.getTeacherid());
				teacher = teacherService.getTeacher(student.getTeacherid());
				students.setTname(teacher.getTname());
				list.add(students);
			}
		}
		int countArt = list.size();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",countArt);
		m.put("rows",list);
		String mapjson = JSON.toJSONString(m);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapjson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询学生表中教育考试报名的学生list(模糊查询)
	@Action(value="likeEducation")
	public void getLikeEducationList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		String tname = student.getTname();
		int tid = 0;
		if(tname=="" || tname==null){
			tid=0;
		}else if(tname.isEmpty()){
			tid=0;
		}else{
			tid = teacherService.getIdByName(tname);
		}
		student.setTeacherid(tid);
		String aname = student.getAname();
		int aid = 0;
		if(aname=="" || aname==null){
			aid=0;
		}else if(aname.isEmpty()){
			aid=0;
		}else{
			aid = academyService.getIdByAname(aname);
		}
		student.setAcademyid(aid);
		List<Student> stuList = studentService.listLikeEducation(student,page,row);
		Teacher teacher = new Teacher();
		Academy academy = new Academy();
		List<Student> lists = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			student = stuList.get(i);
			if(student.getSnumber()!=null){
				Student students = new Student();
				students.setSid(student.getSid());
				students.setStuno(student.getStuno());
				students.setSname(student.getSname());
				students.setSphone(student.getSphone());
				students.setAcontent(student.getAcontent());
				students.setSdate(student.getSdate());
				students.setStype(student.getStype());
				students.setTeacherid(student.getTeacherid());
				teacher = teacherService.getTeacher(student.getTeacherid());
				students.setTname(teacher.getTname());
				students.setSnumber(student.getSnumber());
				students.setAcademyid(student.getAcademyid());
				academy = academyService.getAcademyById(student.getAcademyid());
				students.setAname(academy.getAname());
				students.setSgradations(student.getSgradations());
				students.setSsystem(student.getSsystem());
				lists.add(students);
			}
		}
		int countEducation = lists.size();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",countEducation);
		m.put("rows",lists);
		String mapjson = JSON.toJSONString(m);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapjson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//根据学生id删除学生信息
	public String delete(){
		boolean isDelete = studentService.delete(student.getSid());
		Map<String,Object> map = new HashMap<String,Object>();
		if(isDelete){
			map.put("delete", "true");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//根据id查询对应关于艺考学生的信息
	public void findOneArt(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		Student students = studentService.getStudentById(student.getSid());
		Student stu = new Student();
		stu.setSid(students.getSid());
		stu.setSname(students.getSname());
		stu.setStuno(students.getStuno());
		stu.setSphone(students.getSphone());
		stu.setStype(students.getStype());
		stu.setSdate(students.getSdate());
		students.setFestate(students.getFestate());
		stu.setAcontent(students.getAcontent());
		stu.setTeacherid(students.getTeacherid());
		String mapJsons = JSON.toJSONString(stu);
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(mapJsons);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//根据id查询对应关于教育考试学生的信息
	public void findOneEducation(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		Student stud = studentService.getStudentById(student.getSid());
		Student stus = new Student();
		stus.setSid(stud.getSid());
		stus.setSname(stud.getSname());
		stus.setStuno(stud.getStuno());
		stus.setSphone(stud.getSphone());
		stus.setStype(stud.getStype());
		stus.setSdate(stud.getSdate());
		stus.setAcontent(stud.getAcontent());
		stus.setTeacherid(stud.getTeacherid());
		stus.setSnumber(stud.getSnumber());
		stus.setAcademyid(stud.getAcademyid());
		stus.setSgradations(stud.getSgradations());
		stus.setSsystem(stud.getSsystem());
		stus.setFestate(stud.getFestate());
		String mapJson = JSON.toJSONString(stus);
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(mapJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改学生表中艺考报名的信息
	public String updateArt(){
		boolean isUpdate = studentService.update(student);
		Map<String,Object> map = new HashMap<String,Object>();
		if(isUpdate){
			map.put("update", "true");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//修改学生表中教育考试报名的信息
	public String updateEducation(){
		if(student.getSgradations().equals("高达专")){
			student.setSsystem("3年");
		}else if(student.getSgradations().equals("专达本")){
			student.setSsystem("4年");
		}else if(student.getSgradations().equals("高达本")){
			student.setSsystem("5年");
		}
		boolean isUpdate = studentService.update(student);
		Map<String,Object> map = new HashMap<String,Object>();
		if(isUpdate){
			map.put("update", "true");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//查询教师表中的所有信息
	public String getAllTeacher(){
		listTeacher = teacherService.getTeacherList();
		return SUCCESS;
	}
	
	//查询教师表和院校表中的所有信息
	public String getAllTeacherAndAcademy(){
		listTeacher = teacherService.getTeacherList();
		listAcademy = academyService.listAcademy();
		return SUCCESS;
	}
	
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public AcademySerice getAcademyService() {
		return academyService;
	}

	public void setAcademyService(AcademySerice academyService) {
		this.academyService = academyService;
	}

	public List<Teacher> getListTeacher() {
		return listTeacher;
	}

	public void setListTeacher(List<Teacher> listTeacher) {
		this.listTeacher = listTeacher;
	}

	public List<Academy> getListAcademy() {
		return listAcademy;
	}

	public void setListAcademy(List<Academy> listAcademy) {
		this.listAcademy = listAcademy;
	}
	
}
