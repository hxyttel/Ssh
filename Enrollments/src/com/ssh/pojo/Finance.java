package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Finance implements Serializable{
	private Integer feid; //主键id
	private Float feneeddmoney;//需缴金额
	private Float frpractical;//实缴金额
	private String fedate;//缴费时间
	private String feway;//缴费方式（现金,支付宝，微信）
	
	private Integer studentid;//学生外键id
	private Student student ; 
	private String  sname; //学生姓名
	private String  tname; //老师姓名
	private String  sfestate;//缴费状态
	private String  sacontent;//学习内容
	private String  sstype;//报名类型
	public Finance() {
	}
	public Finance(Integer feid, Float feneeddmoney, Float frpractical, String fedate, String feway, Integer studentid,
			Student student) {
		super();
		this.feid = feid;
		this.feneeddmoney = feneeddmoney;
		this.frpractical = frpractical;
		this.fedate = fedate;
		this.feway = feway;
		this.studentid = studentid;
		this.student = student;
	}

	public Integer getFeid() {
		return feid;
	}

	public void setFeid(Integer feid) {
		this.feid = feid;
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
	

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getSfestate() {
		return sfestate;
	}
	public void setSfestate(String sfestate) {
		this.sfestate = sfestate;
	}
	public String getSacontent() {
		return sacontent;
	}
	public void setSacontent(String sacontent) {
		this.sacontent = sacontent;
	}
	public String getSstype() {
		return sstype;
	}
	public void setSstype(String sstype) {
		this.sstype = sstype;
	}
	
	
	
	
	

}
