package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")

//公告表
public class Notice implements Serializable{
	
	private Integer nid; //主键id
	private String ntitle;//标题
	private String ncontent;//内容
	private String ndate;//时间
	private String nflag;//公告,简章
	public Notice() {
	}
	public Notice(Integer nid, String ntitle, String ncontent, String ndate, String nflag) {
		super();
		this.nid = nid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ndate = ndate;
		this.nflag = nflag;
	}

	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getNflag() {
		return nflag;
	}
	public void setNflag(String nflag) {
		this.nflag = nflag;
	}
	
}
