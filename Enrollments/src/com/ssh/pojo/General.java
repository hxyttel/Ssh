package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")

//招生简章表
public class General implements Serializable{
	private Integer gid;   //招生简章id
	private String  glogo; //图标
	private String  gintroduce;  //学校介绍
	private String gpicture;  //院校图片
	private String  gtitle;   //介绍标题
	private String  gmajor;   //专业证图
	private String  gdiploma;  //毕业证图
	private Academy academy;   //院校专业表对象

	public General() {
	}

	public General(Integer gid, String glogo, String gintroduce, String gpicture, String gtitle, String gmajor,
			String gdiploma) {
		this.gid = gid;
		this.glogo = glogo;
		this.gintroduce = gintroduce;
		this.gpicture = gpicture;
		this.gtitle = gtitle;
		this.gmajor = gmajor;
		this.gdiploma = gdiploma;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGlogo() {
		return glogo;
	}

	public void setGlogo(String glogo) {
		this.glogo = glogo;
	}

	public String getGintroduce() {
		return gintroduce;
	}

	public void setGintroduce(String gintroduce) {
		this.gintroduce = gintroduce;
	}

	public String getGpicture() {
		return gpicture;
	}

	public void setGpicture(String gpicture) {
		this.gpicture = gpicture;
	}

	public String getGtitle() {
		return gtitle;
	}

	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}

	public String getGmajor() {
		return gmajor;
	}

	public void setGmajor(String gmajor) {
		this.gmajor = gmajor;
	}

	public String getGdiploma() {
		return gdiploma;
	}

	public void setGdiploma(String gdiploma) {
		this.gdiploma = gdiploma;
	}

     public Academy getAcademy() {
		return academy;
	}

	 public void setAcademy(Academy academy) {
		 this.academy = academy;
	}
	

}
