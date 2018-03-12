package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Roels implements Serializable{
	private Integer rid;
	private String  rname;
	private String  rdes;
	private String  rstatus;
	private Set<Teacher> teacher = new HashSet<Teacher>();
	private Set<Rolejur> roeljur = new HashSet<Rolejur>();

	public Roels() {
	}

	public Roels(Integer rid, String rname, String rdes, String rstatus, Set<Teacher> teacher) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rdes = rdes;
		this.rstatus = rstatus;
		this.teacher = teacher;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRdes() {
		return rdes;
	}

	public void setRdes(String rdes) {
		this.rdes = rdes;
	}

	public String getRstatus() {
		return rstatus;
	}

	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	public Set<Rolejur> getRoeljur() {
		return roeljur;
	}

	public void setRoeljur(Set<Rolejur> roeljur) {
		this.roeljur = roeljur;
	}
	
	
}
