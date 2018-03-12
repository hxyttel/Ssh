package com.ssh.pojo;

import java.io.Serializable;

//热门专业表

@SuppressWarnings("serial")
public class Hotmajor implements Serializable{
	
	private Integer hid;//主键id
	private String  htitle;//标题
	private String  hpicture;//图片
	private String  hcontent;//内容
	
	public Hotmajor() {
	}

	public Hotmajor(Integer hid, String htitle, String hpicture, String hcontent) {
		super();
		this.hid = hid;
		this.htitle = htitle;
		this.hpicture = hpicture;
		this.hcontent = hcontent;
	}

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public String getHtitle() {
		return htitle;
	}

	public void setHtitle(String htitle) {
		this.htitle = htitle;
	}

	public String getHpicture() {
		return hpicture;
	}

	public void setHpicture(String hpicture) {
		this.hpicture = hpicture;
	}

	public String getHcontent() {
		return hcontent;
	}

	public void setHcontent(String hcontent) {
		this.hcontent = hcontent;
	}
	

}
