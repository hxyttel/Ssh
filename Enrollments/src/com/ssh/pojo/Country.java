package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Country implements Serializable{
	private Integer cyid;//主键
	private String cyname;//国家开放报名
	private String cypicture;//图标
	private String cyaddress;//跳转的jsp页面路径
	private String cytitle;//标题
	private String cyphoto1;//代表图
	private String cycontent;//内容
	private String cyphoto2;//专业图
	
	public Country(){
		
	}

	public Country(Integer cyid, String cyname, String cypicture, String cyaddress, String cytitle, String cyphoto1,
			String cycontent, String cyphoto2) {
		this.cyid = cyid;
		this.cyname = cyname;
		this.cypicture = cypicture;
		this.cyaddress = cyaddress;
		this.cytitle = cytitle;
		this.cyphoto1 = cyphoto1;
		this.cycontent = cycontent;
		this.cyphoto2 = cyphoto2;
	}

	public Integer getCyid() {
		return cyid;
	}

	public void setCyid(Integer cyid) {
		this.cyid = cyid;
	}

	public String getCyname() {
		return cyname;
	}

	public void setCyname(String cyname) {
		this.cyname = cyname;
	}

	public String getCypicture() {
		return cypicture;
	}

	public void setCypicture(String cypicture) {
		this.cypicture = cypicture;
	}

	public String getCyaddress() {
		return cyaddress;
	}

	public void setCyaddress(String cyaddress) {
		this.cyaddress = cyaddress;
	}

	public String getCytitle() {
		return cytitle;
	}

	public void setCytitle(String cytitle) {
		this.cytitle = cytitle;
	}

	public String getCyphoto1() {
		return cyphoto1;
	}

	public void setCyphoto1(String cyphoto1) {
		this.cyphoto1 = cyphoto1;
	}

	public String getCycontent() {
		return cycontent;
	}

	public void setCycontent(String cycontent) {
		this.cycontent = cycontent;
	}

	public String getCyphoto2() {
		return cyphoto2;
	}

	public void setCyphoto2(String cyphoto2) {
		this.cyphoto2 = cyphoto2;
	}
	
}
