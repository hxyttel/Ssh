package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.UploadDao;
import com.ssh.pojo.StudentFile;
import com.ssh.service.UploadService;

public class UploadServiceImpl implements UploadService{
	private UploadDao studentfileDao;
	
	@Override
	public StudentFile getStudentFile(int id) {
		return studentfileDao.getStudentFile(id);
	}

	@Override
	public boolean SaveStudentFile(StudentFile studentfile) {
		return studentfileDao.SaveStudentFile(studentfile);
	}

	@Override
	public boolean UpdateStudentFile(StudentFile studentfile) {
		return studentfileDao.UpdateStudentFile(studentfile);
	}

	@Override
	public boolean DeleteStudentFile(int id) {
		return studentfileDao.DeleteStudentFile(id);
	}

	@Override
	public List<StudentFile> getStudentFileList() {
		return studentfileDao.getStudentFileList();
	}

	@Override
	public int getAllCount() {
		return studentfileDao.getAllCount();
	}
	//根据条件模糊查询上传文件表
	public List<StudentFile> listLikeStudentFile(int sid, int page, int row) {
		return studentfileDao.listLikeStudentFile(sid, page, row);
	}
	public UploadDao getStudentfileDao() {
		return studentfileDao;
	}

	public void setStudentfileDao(UploadDao studentfileDao) {
		this.studentfileDao = studentfileDao;
	}

}
