package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rolejur implements Serializable{
	private Integer rjid;
	private Integer whether;
	
	//角色表
	private Integer rid;
	private Roels roels;
	
	//权限表
	private Jurisdication jurisdication;
	private Integer jid;
	
	
	public Rolejur() {
	}

	public Rolejur(Integer rjid, Integer whether, Integer rid, Roels roels, Jurisdication jurisdication, Integer jid) {
		super();
		this.rjid = rjid;
		this.whether = whether;
		this.rid = rid;
		this.roels = roels;
		this.jurisdication = jurisdication;
		this.jid = jid;
	}


	public Integer getRjid() {
		return rjid;
	}


	public void setRjid(Integer rjid) {
		this.rjid = rjid;
	}


	public Integer getWhether() {
		return whether;
	}


	public void setWhether(Integer whether) {
		this.whether = whether;
	}


	public Integer getRid() {
		return rid;
	}


	public void setRid(Integer rid) {
		this.rid = rid;
	}


	public Roels getRoels() {
		return roels;
	}


	public void setRoels(Roels roels) {
		this.roels = roels;
	}


	public Jurisdication getJurisdication() {
		return jurisdication;
	}


	public void setJurisdication(Jurisdication jurisdication) {
		this.jurisdication = jurisdication;
	}


	public Integer getJid() {
		return jid;
	}


	public void setJid(Integer jid) {
		this.jid = jid;
	}
	
	

}
