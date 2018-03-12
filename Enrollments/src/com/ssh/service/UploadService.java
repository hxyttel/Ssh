package com.ssh.service;

import java.util.List;

import com.ssh.pojo.StudentFile;

public interface UploadService {
	public abstract StudentFile  getStudentFile(int id);//得到上传文件对象
	public abstract boolean SaveStudentFile(StudentFile studentfile); //保存上传文件对象
	public abstract boolean UpdateStudentFile(StudentFile studentfile);//修改上传文件
	public abstract boolean DeleteStudentFile(int id);//删除上传文件
	public List<StudentFile> getStudentFileList();//遍历上传文件
	public abstract int getAllCount();//得到一共多少条数据
	List<StudentFile> listLikeStudentFile(int sid,int page,int row); //根据条件模糊查询上传文件表
}	
