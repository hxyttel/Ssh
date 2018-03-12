package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Accounting implements Serializable{
	private Integer agid;//主键
	private String agname;//会计培训报名
	private String agpicture;//图标
	private String agaddress;//跳转的jsp页面路径
	private String agtitle;//标题
	private String agphoto1;//代表图
	private String agphoto2;//专业图
	
	public Accounting(){
		
	}
	public Accounting(Integer agid, String agname, String agpicture, String agaddress, String agtitle, String agphoto1,
			String agphoto2) {
		this.agid = agid;
		this.agname = agname;
		this.agpicture = agpicture;
		this.agaddress = agaddress;
		this.agtitle = agtitle;
		this.agphoto1 = agphoto1;
		this.agphoto2 = agphoto2;
	}
	public Integer getAgid() {
		return agid;
	}
	public void setAgid(Integer agid) {
		this.agid = agid;
	}
	public String getAgname() {
		return agname;
	}
	public void setAgname(String agname) {
		this.agname = agname;
	}
	public String getAgpicture() {
		return agpicture;
	}
	public void setAgpicture(String agpicture) {
		this.agpicture = agpicture;
	}
	public String getAgaddress() {
		return agaddress;
	}
	public void setAgaddress(String agaddress) {
		this.agaddress = agaddress;
	}
	public String getAgtitle() {
		return agtitle;
	}
	public void setAgtitle(String agtitle) {
		this.agtitle = agtitle;
	}
	public String getAgphoto1() {
		return agphoto1;
	}
	public void setAgphoto1(String agphoto1) {
		this.agphoto1 = agphoto1;
	}
	public String getAgphoto2() {
		return agphoto2;
	}
	public void setAgphoto2(String agphoto2) {
		this.agphoto2 = agphoto2;
	}
	
}
