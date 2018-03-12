package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Logf implements Serializable{
	private Integer lid;
	private String userid;
	private String username;
	private String ip;
	private String logtime;
	private String method;
	public Logf() {}
	public Logf(Integer lid, String userid, String username, String isclass, String ip, String logtime,String method) {
		this.lid = lid;
		this.userid = userid;
		this.username = username;
		this.ip = ip;
		this.logtime = logtime;
		this.method=method;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
