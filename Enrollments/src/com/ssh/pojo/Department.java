package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")

//部门表
public class Department implements Serializable{
	private Integer did;//部门表id
	private String  dnumber;//部门编号
	private String  ddatetime;//创建时间
	private String  daddress;//部门地址
	private String  dname; //部门名称
	private Set<Teacher> teacher = new HashSet<Teacher>();//一对多的多对象名
	
	public Department() {
	}
	
	public Department(Integer did, String dnumber, String ddatetime, String daddress,String dname) {
		this.did = did;
		this.dnumber = dnumber;
		this.ddatetime = ddatetime;
		this.daddress = daddress;
		this.dname = dname;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDnumber() {
		return dnumber;
	}

	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}

	public String getDdatetime() {
		return ddatetime;
	}

	public void setDdatetime(String ddatetime) {
		this.ddatetime = ddatetime;
	}
	
	public String getDaddress() {
		return daddress;
	}

	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}
	
	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}
	
}
