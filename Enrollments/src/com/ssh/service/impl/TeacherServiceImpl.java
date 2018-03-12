package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.TeacherDao;
import com.ssh.pojo.Teacher;
import com.ssh.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	public TeacherDao teacherDao;
	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	

	//判断电话号码是否存在
	@Override
	public boolean getPhone(String tphone) {
		return teacherDao.getPhone(tphone);
	}

	//判断密码输入是否正确
	@Override
	public boolean getPassword(String tpwd,String tphone) {
		return teacherDao.getPassword(tpwd,tphone);
	}
	//根据电话号码返回Teacher对象
	@Override
	public Teacher getTeacher(String tphone) {
		return teacherDao.getTeacher(tphone);
	}

	//根据id修改Teacher对象密码
	public boolean updatePasswordById(String password,int tid) {
		return teacherDao.updatePasswordById(password,tid);
	}

	@Override
	public Teacher getTeacher(int id) {
		return teacherDao.getTeacher(id);
	}

	@Override
	public boolean SaveTeacher(Teacher teacher) {
		return teacherDao.SaveTeacher(teacher);
	}

	@Override
	public boolean UpdateTeacher(Teacher teacher) {
		return teacherDao.UpdateTeacher(teacher);
	}

	@Override
	public boolean DeleteTeacher(int id) {
		return teacherDao.DeleteTeacher(id);
	}

	@Override
	public List<Teacher> getTeacherList() {
		return teacherDao.getTeacherList();
	}

	@Override
	public int getAllCount() {
		return teacherDao.getAllCount();
	}


	//根据老师姓名获取到id
	public int getIdByName(String tname) {
		return teacherDao.getIdByName(tname);
	}

	//遍历教师表(模糊查询)
	public List<Teacher> listLikeTeacher(Teacher teacher, int page, int row) {
		return teacherDao.listLikeTeacher(teacher, page, row);
	}
	
}

