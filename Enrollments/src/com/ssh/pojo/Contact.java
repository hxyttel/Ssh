package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable{
	private Integer cid;//主键
	private String ctype;//类型
	private String cqqnumber;//QQ号
	private String cphone;//电话号码
	private String cpeople;//联系老师
	public Contact(){
		
	}
	public Contact(Integer cid, String ctype, String cqqnumber, String cphone, String cpeople) {
		this.cid = cid;
		this.ctype = ctype;
		this.cqqnumber = cqqnumber;
		this.cphone = cphone;
		this.cpeople = cpeople;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCqqnumber() {
		return cqqnumber;
	}
	public void setCqqnumber(String cqqnumber) {
		this.cqqnumber = cqqnumber;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCpeople() {
		return cpeople;
	}
	public void setCpeople(String cpeople) {
		this.cpeople = cpeople;
	}
	
}
