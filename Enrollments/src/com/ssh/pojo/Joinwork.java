package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")

//共享合作
public class Joinwork implements Serializable{
	private Integer jwid;//主键id
	private String  jwtitle;//标题
	private String  jwpicture; //图片
	private String  jwcontent; //内容
	
	public Joinwork() {
	}
	public Joinwork(Integer jwid, String jwtitle, String jwpicture, String jwcontent) {
		super();
		this.jwid = jwid;
		this.jwtitle = jwtitle;
		this.jwpicture = jwpicture;
		this.jwcontent = jwcontent;
	}

	public Integer getJwid() {
		return jwid;
	}

	public void setJwid(Integer jwid) {
		this.jwid = jwid;
	}

	public String getJwtitle() {
		return jwtitle;
	}

	public void setJwtitle(String jwtitle) {
		this.jwtitle = jwtitle;
	}

	public String getJwpicture() {
		return jwpicture;
	}

	public void setJwpicture(String jwpicture) {
		this.jwpicture = jwpicture;
	}

	public String getJwcontent() {
		return jwcontent;
	}

	public void setJwcontent(String jwcontent) {
		this.jwcontent = jwcontent;
	}
	

}
