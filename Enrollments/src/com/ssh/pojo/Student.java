package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Student implements Serializable{
	private Integer sid;//主键
	private String stuno;//学号
	private String sname;//学生姓名
	private String snumber;//身份证号
	private String sphone;//联系电话
	private Integer academyid; //报考院校(外键)
	private Integer teacherid;//教师姓名(外键)
	private String sgradations;//报考层次(高达专3年,专达本4年,高达本5年)
	private String ssystem;//学制
	private String acontent;//报考内容
	private String sdate;//报考日期
	private String stype;//报考类型
	
	private String tname;  //获取老师name
	private String aname;  //获取院校name
	
	//缴费(一对多)缴费是多
	private String  festate; //缴费状态
	private Set<Finance> finance = new HashSet<Finance>();
	
	//上传文件表（一对一 学生id作为id）
	private StudentFile sfile;
	
	//学生对院校(老师)是多
	private Academy academy;
	private Teacher teacher;
	
	private Float feneeddmoney;//需缴金额
	private Float frpractical;//实缴金额
	private String fedate;//缴费时间
	private String feway;//缴费方式（现金,支付宝，微信）
	
	public Student(){
		
	}
	public Student(Integer sid, String stuno, String sname, String snumber, String sphone, Integer academyid,
			Integer teacherid, String sgradations, String ssystem, String acontent, String sdate, String stype,
			String tname, String aname, String festate, Set<Finance> finance, StudentFile sfile, Academy academy,
			Teacher teacher) {
		super();
		this.sid = sid;
		this.stuno = stuno;
		this.sname = sname;
		this.snumber = snumber;
		this.sphone = sphone;
		this.academyid = academyid;
		this.teacherid = teacherid;
		this.sgradations = sgradations;
		this.ssystem = ssystem;
		this.acontent = acontent;
		this.sdate = sdate;
		this.stype = stype;
		this.tname = tname;
		this.aname = aname;
		this.festate = festate;
		this.finance = finance;
		this.sfile = sfile;
		this.academy = academy;
		this.teacher = teacher;
	}

	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public Integer getAcademyid() {
		return academyid;
	}
	public void setAcademyid(Integer academyid) {
		this.academyid = academyid;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getSgradations() {
		return sgradations;
	}
	public void setSgradations(String sgradations) {
		this.sgradations = sgradations;
	}
	public String getSsystem() {
		return ssystem;
	}
	public void setSsystem(String ssystem) {
		this.ssystem = ssystem;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public StudentFile getSfile() {
		return sfile;
	}
	public void setSfile(StudentFile sfile) {
		this.sfile = sfile;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Set<Finance> getFinance() {
		return finance;
	}
	public void setFinance(Set<Finance> finance) {
		this.finance = finance;
	}
	public String getFestate() {
		return festate;
	}
	public void setFestate(String festate) {
		this.festate = festate;
	}
	public Float getFeneeddmoney() {
		return feneeddmoney;
	}
	public void setFeneeddmoney(Float feneeddmoney) {
		this.feneeddmoney = feneeddmoney;
	}
	public Float getFrpractical() {
		return frpractical;
	}
	public void setFrpractical(Float frpractical) {
		this.frpractical = frpractical;
	}
	public String getFedate() {
		return fedate;
	}
	public void setFedate(String fedate) {
		this.fedate = fedate;
	}
	public String getFeway() {
		return feway;
	}
	public void setFeway(String feway) {
		this.feway = feway;
	}
	
}
