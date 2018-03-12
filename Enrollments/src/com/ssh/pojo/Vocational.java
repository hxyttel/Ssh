package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vocational implements Serializable{
	private Integer vlid;//主键
	private String vlname;//艺考培训报名
	private String vlpicture;//图标
	private String vladdress;//跳转的jsp页面路径
	private String vltitle;//标题
	private String vlphoto1;//代表图
	private String vlphoto2;//专业图 
	
	public Vocational(){
		
	}

	public Vocational(Integer vlid, String vlname, String vlpicture, String vladdress, String vltitle, String vlphoto1,
			String vlphoto2) {
		super();
		this.vlid = vlid;
		this.vlname = vlname;
		this.vlpicture = vlpicture;
		this.vladdress = vladdress;
		this.vltitle = vltitle;
		this.vlphoto1 = vlphoto1;
		this.vlphoto2 = vlphoto2;
	}

	public Integer getVlid() {
		return vlid;
	}

	public void setVlid(Integer vlid) {
		this.vlid = vlid;
	}

	public String getVlname() {
		return vlname;
	}

	public void setVlname(String vlname) {
		this.vlname = vlname;
	}

	public String getVlpicture() {
		return vlpicture;
	}

	public void setVlpicture(String vlpicture) {
		this.vlpicture = vlpicture;
	}

	public String getVladdress() {
		return vladdress;
	}

	public void setVladdress(String vladdress) {
		this.vladdress = vladdress;
	}

	public String getVltitle() {
		return vltitle;
	}

	public void setVltitle(String vltitle) {
		this.vltitle = vltitle;
	}

	public String getVlphoto1() {
		return vlphoto1;
	}

	public void setVlphoto1(String vlphoto1) {
		this.vlphoto1 = vlphoto1;
	}

	public String getVlphoto2() {
		return vlphoto2;
	}

	public void setVlphoto2(String vlphoto2) {
		this.vlphoto2 = vlphoto2;
	}
	

}
