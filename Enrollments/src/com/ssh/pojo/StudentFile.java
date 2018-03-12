package com.ssh.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")

//文件表
public class StudentFile implements Serializable{
	
	private Integer sfid; //主键编号
	private String  sfphoto ; //相片
	private String  sfvoucher ; //缴费凭证
	private String  sfpaper;  //论文文件
	private String  sfdate ;  //上传时间
	private Student student;  //关联学生表id
	private int  sid;
	private String  sname;
	
	public StudentFile() {
	}
	public StudentFile(Integer sfid, String sfphoto, String sfvoucher, String sfpaper, String sfdate, Student student,
			int sid) {
		super();
		this.sfid = sfid;
		this.sfphoto = sfphoto;
		this.sfvoucher = sfvoucher;
		this.sfpaper = sfpaper;
		this.sfdate = sfdate;
		this.student = student;
		this.sid = sid;
	}



	public Integer getSfid() {
		return sfid;
	}

	public void setSfid(Integer sfid) {
		this.sfid = sfid;
	}

	public String getSfphoto() {
		return sfphoto;
	}

	public void setSfphoto(String sfphoto) {
		this.sfphoto = sfphoto;
	}

	public String getSfvoucher() {
		return sfvoucher;
	}

	public void setSfvoucher(String sfvoucher) {
		this.sfvoucher = sfvoucher;
	}

	public String getSfpaper() {
		return sfpaper;
	}

	public void setSfpaper(String sfpaper) {
		this.sfpaper = sfpaper;
	}

	public String getSfdate() {
		return sfdate;
	}

	public void setSfdate(String sfdate) {
		this.sfdate = sfdate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
}
