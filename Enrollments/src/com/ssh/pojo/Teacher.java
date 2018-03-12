package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Teacher implements Serializable{
	private Integer tid;//主键
	private String tname;//用户名
	private String tpassword;//密码
	private String tsex;//性别
	private Integer tage;//年龄
	private String tphone;//电话
	private Integer departmentid;//所属机构(外键)(财务部,董事会)
	private String tcreatetime;//创建时间
	private String  dname;
	
	//角色表
	private Integer roleid;
	private Roels roels;
	private String ttype;//用户类型(管理员,用户)
	
	private Department department;
	private Set<Student> student = new HashSet<Student>();
	private Set<WorkAssist> workAssist = new HashSet<WorkAssist>();
	
	private String tstatus;//用户状态(正常,停用)
	public Teacher() {
	
	}
	public Teacher(Integer tid, String tname, String tpassword, String tsex, Integer tage, String tphone, String ttype,
			String tstatus, Integer departmentid, String tcreatetime) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tpassword = tpassword;
		this.tsex = tsex;
		this.tage = tage;
		this.tphone = tphone;
		this.ttype = ttype;
		this.tstatus = tstatus;
		this.departmentid = departmentid;
		this.tcreatetime = tcreatetime;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Set<Student> getStudent() {
		return student;
	}
	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	public Integer getTid() {
		return tid;
	}
	
	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTpassword() {
		return tpassword;
	}

	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public Integer getTage() {
		return tage;
	}

	public void setTage(Integer tage) {
		this.tage = tage;
	}

	public String getTphone() {
		return tphone;
	}

	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTstatus() {
		return tstatus;
	}

	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getTcreatetime() {
		return tcreatetime;
	}

	public void setTcreatetime(String tcreatetime) {
		this.tcreatetime = tcreatetime;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Set<WorkAssist> getWorkAssist() {
		return workAssist;
	}
	public void setWorkAssist(Set<WorkAssist> workAssist) {
		this.workAssist = workAssist;
	}
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Roels getRoels() {
		return roels;
	}
	public void setRoels(Roels roels) {
		this.roels = roels;
	}
	
	
	
	
	
	
}
