package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Arts implements Serializable{
	private Integer asid;//主键
	private String asname;//艺考培训报名
	private String aspicture;//图标
	private String asaddress;//跳转的jsp页面路径
	private String astitle;//标题
	private String asphoto1;//代表图
	private String asphoto2;//专业图 
	
	public Arts(){
		
	}

	public Arts(Integer asid, String asname, String aspicture, String asaddress, String astitle, String asphoto1,
			String asphoto2) {
		this.asid = asid;
		this.asname = asname;
		this.aspicture = aspicture;
		this.asaddress = asaddress;
		this.astitle = astitle;
		this.asphoto1 = asphoto1;
		this.asphoto2 = asphoto2;
	}

	public Integer getAsid() {
		return asid;
	}

	public void setAsid(Integer asid) {
		this.asid = asid;
	}

	public String getAsname() {
		return asname;
	}

	public void setAsname(String asname) {
		this.asname = asname;
	}

	public String getAspicture() {
		return aspicture;
	}

	public void setAspicture(String aspicture) {
		this.aspicture = aspicture;
	}

	public String getAsaddress() {
		return asaddress;
	}

	public void setAsaddress(String asaddress) {
		this.asaddress = asaddress;
	}

	public String getAstitle() {
		return astitle;
	}

	public void setAstitle(String astitle) {
		this.astitle = astitle;
	}

	public String getAsphoto1() {
		return asphoto1;
	}

	public void setAsphoto1(String asphoto1) {
		this.asphoto1 = asphoto1;
	}

	public String getAsphoto2() {
		return asphoto2;
	}

	public void setAsphoto2(String asphoto2) {
		this.asphoto2 = asphoto2;
	}
	
}
