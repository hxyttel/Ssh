package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.StudentDao;
import com.ssh.pojo.Student;
import com.ssh.service.StudentService;


public class StudentServiceImpl implements StudentService{
	private StudentDao studentDao;
	
	//判断身份证号码是否重复
	public boolean getSnumber(String idcard) {
		return studentDao.getSnumber(idcard);
	}
	//保存(添加)学生信息
	public boolean add(Student student) {
		return studentDao.add(student);
	}
	//判断电话号码是否存在
	public boolean getPhone(String sphone) {
		return studentDao.getPhone(sphone);
	}
	
	//返回学生表中艺考培训的一共有多少条数据
	public int getTrainCount() {
		return studentDao.getTrainCount();
	}
	
	//返回学生表中教育考试的一共有多少条数据
	public int getEducationCount() {
		return studentDao.getEducationCount();
	}
	
	//获取到Student表中所有数据
	public List<Student> getStudentList() {
		return studentDao.getStudentList();
	}
	
	//获取到Student表中所有数据
	public List<Student> getStudentLists() {
		return studentDao.getStudentLists();
	}
	
	//根据id删除学生信息
	public boolean delete(Integer id){
		return studentDao.delete(id);
	}
	
	//根据id查询学生信息
	public Student getStudentById(Integer id) {
		return studentDao.getStudentById(id);
	}

	//修改学生信息
	public boolean update(Student student) {
		return studentDao.update(student);
	}
	//根据老师id去查询学生
	public int getCountTeacher(int id) {
		return studentDao.getCountTeacher(id);
	}
	//通过缴费状态查找
		public List<Student> getStuStype() {
			return studentDao.getStuStype();
		}
	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	@Override
	public int getCountStype(String type) {
		return studentDao.getCountStype(type);
	}
	//根据条件查询出所有符合的数据
	public List<Student> listLikeTrain(Student student, int page, int row) {
		return studentDao.listLikeTrain(student, page, row);
	}
	//根据条件查询出所有符合的成考考生
	public List<Student> listLikeEducation(Student student, int page, int row) {
		return studentDao.listLikeEducation(student, page, row);
	}
	
	@Override
	public List<Student> getTeacherList(int id) {
		return studentDao.getTeacherList(id);
	}
	@Override
	public int getStypeTime(String stype,String sdate) {
		return studentDao.getStypeTime(stype,sdate);
	}
	//根据学生姓名获取id
	public int getIdByName(String name) {
		return studentDao.getIdByName(name);
	}
	//遍历老师id去查询学生
	public List<Student> getStudentList(int id) {
		return studentDao.getStudentList(id);
	}
	
}
