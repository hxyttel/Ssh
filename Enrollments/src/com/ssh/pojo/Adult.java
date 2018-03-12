package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Adult implements Serializable{
	private Integer atid;//主键
	private String atname;//成人教育
	private String atpicture;//图标
	private String ataddress;//跳转的jsp页面路径
	private String attitle;//标题
	private String atphoto1;//代表图
	private String atcontent;//内容
	private String atphoto2;//专业图
	
	public Adult(){
		
	}
	public Adult(Integer atid, String atname, String atpicture, String ataddress, String attitle, String atphoto1,
			String atcontent, String atphoto2) {
		this.atid = atid;
		this.atname = atname;
		this.atpicture = atpicture;
		this.ataddress = ataddress;
		this.attitle = attitle;
		this.atphoto1 = atphoto1;
		this.atcontent = atcontent;
		this.atphoto2 = atphoto2;
	}
	public Integer getAtid() {
		return atid;
	}
	public void setAtid(Integer atid) {
		this.atid = atid;
	}
	public String getAtname() {
		return atname;
	}
	public void setAtname(String atname) {
		this.atname = atname;
	}
	public String getAtpicture() {
		return atpicture;
	}
	public void setAtpicture(String atpicture) {
		this.atpicture = atpicture;
	}
	public String getAtaddress() {
		return ataddress;
	}
	public void setAtaddress(String ataddress) {
		this.ataddress = ataddress;
	}
	public String getAttitle() {
		return attitle;
	}
	public void setAttitle(String attitle) {
		this.attitle = attitle;
	}
	public String getAtphoto1() {
		return atphoto1;
	}
	public void setAtphoto1(String atphoto1) {
		this.atphoto1 = atphoto1;
	}
	public String getAtcontent() {
		return atcontent;
	}
	public void setAtcontent(String atcontent) {
		this.atcontent = atcontent;
	}
	public String getAtphoto2() {
		return atphoto2;
	}
	public void setAtphoto2(String atphoto2) {
		this.atphoto2 = atphoto2;
	}
	
}
