package com.ssh.pojo;

import java.io.Serializable;

//首页内容表

@SuppressWarnings("serial")
public class Indexcontent implements Serializable{

	private Integer iid;//主键id
	private String  icode;//二维码
	private String iphone;//联系电话
	private String iaddress;//地址
	public Indexcontent() {
	}

	public Indexcontent(Integer iid, String icode, String iphone, String iaddress) {
		super();
		this.iid = iid;
		this.icode = icode;
		this.iphone = iphone;
		this.iaddress = iaddress;
	}

	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
	public String getIcode() {
		return icode;
	}
	public void setIcode(String icode) {
		this.icode = icode;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getIaddress() {
		return iaddress;
	}
	public void setIaddress(String iaddress) {
		this.iaddress = iaddress;
	}

	
	
	
}
