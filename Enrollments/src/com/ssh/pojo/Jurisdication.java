package com.ssh.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Jurisdication implements Serializable{
	private Integer jtid;
	private String jtname;
	private Integer jtsort;
	private Integer jtstatus;
	private String  jturl;
	private Set<Rolejur> roeljur = new HashSet<Rolejur>();
	public Jurisdication() {
	}
	public Jurisdication(Integer jtid, String jtname, Integer jtsort, Integer jtstatus, String jturl,
			Set<Rolejur> roeljur) {
		super();
		this.jtid = jtid;
		this.jtname = jtname;
		this.jtsort = jtsort;
		this.jtstatus = jtstatus;
		this.jturl = jturl;
		this.roeljur = roeljur;
	}
	public Integer getJtid() {
		return jtid;
	}

	public void setJtid(Integer jtid) {
		this.jtid = jtid;
	}

	public String getJtname() {
		return jtname;
	}

	public void setJtname(String jtname) {
		this.jtname = jtname;
	}

	public Integer getJtsort() {
		return jtsort;
	}

	public void setJtsort(Integer jtsort) {
		this.jtsort = jtsort;
	}

	public Integer getJtstatus() {
		return jtstatus;
	}

	public void setJtstatus(Integer jtstatus) {
		this.jtstatus = jtstatus;
	}
	public Set<Rolejur> getRoeljur() {
		return roeljur;
	}
	public void setRoeljur(Set<Rolejur> roeljur) {
		this.roeljur = roeljur;
	}
	public String getJturl() {
		return jturl;
	}
	public void setJturl(String jturl) {
		this.jturl = jturl;
	}
	
	
	
	
	
	
	
}
