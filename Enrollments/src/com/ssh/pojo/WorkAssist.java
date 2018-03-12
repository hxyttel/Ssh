package com.ssh.pojo;

import java.io.Serializable;

public class WorkAssist implements Serializable{
	/**
	 * 工作助手
	 * */
	private Integer wa_id;
				//标题
	private String wa_title;
	private String wa_content;			//内容
	private String  wa_createtime;		//创建时间
	private String wa_state;			//状态(0-未完成,1-已完成)
	//关联老师的外键
	private Integer teacherid;
	private Teacher teacher;
	
	public WorkAssist(){}

	




	public WorkAssist(Integer wa_id, String wa_title, String wa_content, String wa_createtime, String wa_state,
			Integer teacherid, Teacher teacher) {
		super();
		this.wa_id = wa_id;
		this.wa_title = wa_title;
		this.wa_content = wa_content;
		this.wa_createtime = wa_createtime;
		this.wa_state = wa_state;
		this.teacherid = teacherid;
		this.teacher = teacher;
	}






	public Integer getWa_id() {
		return wa_id;
	}

	public void setWa_id(Integer wa_id) {
		this.wa_id = wa_id;
	}

	public String getWa_content() {
		return wa_content;
	}

	public void setWa_content(String wa_content) {
		this.wa_content = wa_content;
	}

	public String getWa_createtime() {
		return wa_createtime;
	}

	public void setWa_createtime(String wa_createtime) {
		this.wa_createtime = wa_createtime;
	}



	public String getWa_state() {
		return wa_state;
	}

	public void setWa_state(String wa_state) {
		this.wa_state = wa_state;
	}






	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getWa_title() {
		return wa_title;
	}

	public void setWa_title(String wa_title) {
		this.wa_title = wa_title;
	}


}
