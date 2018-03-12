package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Distance implements Serializable{
	private Integer deid;//主键
	private String dename;//远程教育报名
	private String depicture;//图标
	private String deaddress;//跳转的jsp页面路径
	private String detitle;//标题
	private String dephoto1;//代表图
	private String decontent;//内容
	private String dephoto2;//专业图
	
	public Distance(){
		
	}

	public Distance(Integer deid, String dename, String depicture, String deaddress, String detitle, String dephoto1,
			String decontent, String dephoto2) {
		this.deid = deid;
		this.dename = dename;
		this.depicture = depicture;
		this.deaddress = deaddress;
		this.detitle = detitle;
		this.dephoto1 = dephoto1;
		this.decontent = decontent;
		this.dephoto2 = dephoto2;
	}

	public Integer getDeid() {
		return deid;
	}

	public void setDeid(Integer deid) {
		this.deid = deid;
	}

	public String getDename() {
		return dename;
	}

	public void setDename(String dename) {
		this.dename = dename;
	}

	public String getDepicture() {
		return depicture;
	}

	public void setDepicture(String depicture) {
		this.depicture = depicture;
	}

	public String getDeaddress() {
		return deaddress;
	}

	public void setDeaddress(String deaddress) {
		this.deaddress = deaddress;
	}

	public String getDetitle() {
		return detitle;
	}

	public void setDetitle(String detitle) {
		this.detitle = detitle;
	}

	public String getDephoto1() {
		return dephoto1;
	}

	public void setDephoto1(String dephoto1) {
		this.dephoto1 = dephoto1;
	}

	public String getDecontent() {
		return decontent;
	}

	public void setDecontent(String decontent) {
		this.decontent = decontent;
	}

	public String getDephoto2() {
		return dephoto2;
	}

	public void setDephoto2(String dephoto2) {
		this.dephoto2 = dephoto2;
	}
	
}
