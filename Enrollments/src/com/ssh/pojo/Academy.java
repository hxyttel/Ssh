package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")

//院校表
public class Academy implements Serializable{
	private Integer aid; //院校专业表id
	private String aname;//院校名称
	private String amajor;//院校名称(成人教育,国家开发大学,远程教育,艺考,会计)
	private Set<Student> student = new HashSet<Student>();
	private Set<Major> major = new HashSet<Major>();
	private General general; //招生简章表对象
	
	public Academy() {
	}

	public Academy(Integer aid, String aname, String amajor) {
		this.aid = aid;
		this.aname = aname;
		this.amajor = amajor;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAmajor() {
		return amajor;
	}

	public void setAmajor(String amajor) {
		this.amajor = amajor;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	public Set<Major> getMajor() {
		return major;
	}

	public void setMajor(Set<Major> major) {
		this.major = major;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}
	
	
}
