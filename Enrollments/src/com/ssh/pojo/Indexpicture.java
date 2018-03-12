package com.ssh.pojo;

import java.io.Serializable;

//轮播图表
@SuppressWarnings("serial")
public class Indexpicture implements Serializable{

	private Integer pid; //主键id
	private String ptitle;//标题
	private String pcontent;//内容
	private String ppicture;//图片
	public Indexpicture() {
	}
	
	public Indexpicture(Integer pid, String ptitle, String pcontent, String ppicture) {
		super();
		this.pid = pid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.ppicture = ppicture;
	}

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPpicture() {
		return ppicture;
	}
	public void setPpicture(String ppicture) {
		this.ppicture = ppicture;
	}
}
