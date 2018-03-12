package com.ssh.pojo;

import java.io.Serializable;

//专业表
@SuppressWarnings("serial")
public class Major implements Serializable{
	private Integer mrid; //主键id
	private String mrname;  //专业名称
	private Integer academyid; //院校id(外键)
	private String aname;
	
	//专业对院校是多(保存院校对象)
	private Academy academy;
	
	public Major(){
		
	}

	public Major(Integer mrid, String mrname, Integer academyid) {
		this.mrid = mrid;
		this.mrname = mrname;
		this.academyid = academyid;
	}

	public Integer getMrid() {
		return mrid;
	}

	public void setMrid(Integer mrid) {
		this.mrid = mrid;
	}

	public String getMrname() {
		return mrname;
	}

	public void setMrname(String mrname) {
		this.mrname = mrname;
	}

	public Integer getAcademyid() {
		return academyid;
	}

	public void setAcademyid(Integer academyid) {
		this.academyid = academyid;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}
	
}
